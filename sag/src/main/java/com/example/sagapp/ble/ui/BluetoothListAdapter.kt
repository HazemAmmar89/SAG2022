package com.example.sagapp.ble.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sagapp.ble.domin.BluetoothDevice
import com.example.sagapp.databinding.ItemBluetoothRvBinding
import javax.inject.Inject

class BluetoothListAdapter @Inject constructor() : RecyclerView.Adapter<BluetoothListAdapter.BluetoothViewHolder>() {

    inner class BluetoothViewHolder(private val binding:ItemBluetoothRvBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: BluetoothDevice) {
            binding.bluetoothName.text = item.name

            binding.bluetoothName.setOnClickListener {
                onItemClickListener?.let { it(item) }
            }
        }
    }

    var bleDevices: List<BluetoothDevice>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private val differCallBack = object : DiffUtil.ItemCallback<BluetoothDevice>() {
        override fun areItemsTheSame(oldItem: BluetoothDevice, newItem: BluetoothDevice): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(
            oldItem: BluetoothDevice,
            newItem: BluetoothDevice,
        ): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BluetoothViewHolder {
        return BluetoothViewHolder(
            ItemBluetoothRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    private var onItemClickListener: ((BluetoothDevice) -> Unit)? = null

    override fun onBindViewHolder(holder: BluetoothViewHolder, position: Int) {
        val bleDevice = bleDevices[position]
        holder.apply {
         bind(bleDevice)
        }
    }

    override fun getItemCount() = bleDevices.size

    fun setOnItemClickListener(listener: (BluetoothDevice) -> Unit) {
        onItemClickListener = listener
    }
}