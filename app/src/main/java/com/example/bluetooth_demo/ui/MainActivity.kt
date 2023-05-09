package com.example.bluetooth_demo.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.bluetooth_demo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: BluetoothViewModel by viewModels()

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableBluetooth()

        binding.root.setOnClickListener {
            if (viewModel.startDiscovery()) {
                Log.d("rabbit", "Discovery start")
            }
            else {
                Log.d("rabbit", "Discovery failed")
            }
        }

        binding.root.setOnLongClickListener {
            if (viewModel.cancelDiscovery()) {
                Log.d("rabbit", "Discovery stopped")
            }
            else {
                Log.d("rabbit", "Discovery stop failed")
            }
            return@setOnLongClickListener true
        }
    }

    private fun enableBluetooth() {
        val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        resultLauncher.launch(enableBtIntent)
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // Handle the result here
            }
        }
}