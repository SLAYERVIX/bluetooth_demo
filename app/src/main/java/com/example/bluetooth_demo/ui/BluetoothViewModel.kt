package com.example.bluetooth_demo.ui

import androidx.lifecycle.ViewModel
import com.example.bluetooth_demo.domain.BluetoothController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BluetoothViewModel
@Inject constructor(private val bluetoothController: BluetoothController) : ViewModel() {
    fun startDiscovery() = bluetoothController.startDiscovery()
    fun cancelDiscovery() = bluetoothController.stopDiscovery()
}