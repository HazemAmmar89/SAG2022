package com.example.sagapp.ble.ui

import androidx.lifecycle.viewModelScope
import com.example.sagapp.android.Action
import com.example.sagapp.android.BaseViewModel
import com.example.sagapp.ble.domin.BluetoothController
import com.example.sagapp.ble.domin.BluetoothDevice
import com.example.sagapp.ble.domin.BluetoothDeviceDomain
import com.example.sagapp.ble.domin.ConnectionResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

sealed class BluetoothAction : Action {
    data class ScannedDevices (var scannedDevices: List<BluetoothDevice>) :BluetoothAction()
    data class PairedDevices(var pairedDevices: List<BluetoothDevice>) :BluetoothAction()
    data class Connected( val isConnected:Boolean) :BluetoothAction()
    data class Connecting( val isConnecting:Boolean) :BluetoothAction()
    data class Error(val errorMessage:String): BluetoothAction()
}
@HiltViewModel
class BluetoothViewModel @Inject constructor(
    private val bluetoothController: BluetoothController
): BaseViewModel<BluetoothAction>() {

    fun observeDevices() {
        produce(BluetoothAction.ScannedDevices(bluetoothController.scannedDevices.value))
        produce(BluetoothAction.PairedDevices(bluetoothController.pairedDevices.value))
    }


    private var deviceConnectionJob: Job? = null

    init {
        bluetoothController.isConnected.onEach { isConnected ->
            produce(BluetoothAction.Connected(isConnected))
        }
        bluetoothController.errors.onEach{ error ->
            produce(BluetoothAction.Error(error))
        }
    }

    fun connectToDevice(device: BluetoothDeviceDomain) {
        produce(BluetoothAction.Connected(true))
        deviceConnectionJob = bluetoothController
            .connectToDevice(device)
            .listen()
    }

    fun disconnectFromDevice() {
        deviceConnectionJob?.cancel()
        bluetoothController.closeConnection()
        produce(BluetoothAction.Connecting(false))
        produce(BluetoothAction.Connected(false))
    }

    fun waitForIncomingConnections() {
        produce(BluetoothAction.Connecting(true))
        deviceConnectionJob = bluetoothController
            .startBluetoothServer()
            .listen()
    }

    fun startScan() {
        bluetoothController.startDiscovery()
    }

    fun stopScan() {
        bluetoothController.stopDiscovery()
    }

    private fun Flow<ConnectionResult>.listen(): Job {
        return onEach { result ->
            when(result) {
                ConnectionResult.ConnectionEstablished -> {
                    produce(BluetoothAction.Connected(true))
                    produce(BluetoothAction.Connecting(false))
                    produce(BluetoothAction.Error(""))
                }
                is ConnectionResult.Error -> {
                    produce(BluetoothAction.Connected(false))
                    produce(BluetoothAction.Connecting(false))
                    produce(BluetoothAction.Error(result.message))
                }
            }
        }
            .catch { throwable ->
                bluetoothController.closeConnection()
                produce(BluetoothAction.Connected(false))
                produce(BluetoothAction.Connecting(false))
            }
            .launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        bluetoothController.release()
    }
}