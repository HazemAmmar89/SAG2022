package com.example.sagapp.ble.ui

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sagapp.android.BaseActivity
import com.example.sagapp.android.extentions.observe
import com.example.sagapp.databinding.ActivityBluetoothBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BluetoothActivity : BaseActivity<ActivityBluetoothBinding, BluetoothViewModel>() {

    @Inject
    lateinit var scanDevicesAdapter: BluetoothListAdapter
    @Inject
    lateinit var pairedDevicesAdapter: BluetoothListAdapter
    private val bluetoothManager by lazy {
        applicationContext.getSystemService(BluetoothManager::class.java)
    }
    private val bluetoothAdapter by lazy {
        bluetoothManager?.adapter
    }

    private val isBluetoothEnabled: Boolean
        get() = bluetoothAdapter?.isEnabled == true

    override val mViewModel: BluetoothViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContentView(binding.root)

        val enableBluetoothLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { /* Not needed */ }

        val permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { perms ->
            val canEnableBluetooth = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                perms[Manifest.permission.BLUETOOTH_CONNECT] == true
            } else true

            if(canEnableBluetooth && !isBluetoothEnabled) {
                enableBluetoothLauncher.launch(
                    Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                )
            }
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.BLUETOOTH_SCAN,
                    Manifest.permission.BLUETOOTH_CONNECT,
                )
            )
        }
        binding.startScan.setOnClickListener {
            mViewModel.startScan()
            mViewModel.observeDevices()

        }
        binding.stopScan.setOnClickListener {
            mViewModel.stopScan()
        }
        binding.startServer.setOnClickListener {
            mViewModel.waitForIncomingConnections()
        }
        pairedDevicesAdapter.setOnItemClickListener {
            mViewModel.connectToDevice(it)
        }
        scanDevicesAdapter.setOnItemClickListener {
            mViewModel.connectToDevice(it)
        }
        subscribeToObservers()
        mViewModel.observeDevices()

    }
    private fun subscribeToObservers() {
        mViewModel.apply {
            observe(mViewModel.viewState) {
                handleUiState(it)
            }
        }
    }

    private fun handleUiState(action: BluetoothAction) {
       when(action){
           is BluetoothAction.PairedDevices -> {
               binding.scanRv.apply {
                   pairedDevicesAdapter.bleDevices = action.pairedDevices
                   adapter = pairedDevicesAdapter
                   layoutManager = LinearLayoutManager(context)
               }

               Toast.makeText(applicationContext, ""+action.pairedDevices, Toast.LENGTH_SHORT).show()
           }
           is BluetoothAction.ScannedDevices -> {
               binding.pairedRv.apply {
                   scanDevicesAdapter.bleDevices = action.scannedDevices
                   adapter = scanDevicesAdapter
                   layoutManager = LinearLayoutManager(context)
               }
               Toast.makeText(applicationContext, ""+action.scannedDevices, Toast.LENGTH_SHORT).show()
           }
           is BluetoothAction.Connected -> {
               Toast.makeText(applicationContext, "connecting sone"+action.isConnected, Toast.LENGTH_SHORT).show()
           }
           is BluetoothAction.Connecting -> {
               Toast.makeText(applicationContext, ""+action.isConnecting, Toast.LENGTH_SHORT).show()
           }
           is BluetoothAction.Error -> {
               Toast.makeText(applicationContext, ""+action.errorMessage, Toast.LENGTH_SHORT).show()
           }
       }
    }

}