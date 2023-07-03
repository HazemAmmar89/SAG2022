package com.example.sagapp.ble.data

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import com.example.sagapp.ble.domin.BluetoothDeviceDomain

@SuppressLint("MissingPermission")
fun BluetoothDevice.toBluetoothDeviceDomain(): BluetoothDeviceDomain {
    return BluetoothDeviceDomain(
        name = name,
        address = address
    )
}