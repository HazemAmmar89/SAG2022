package com.example.sagapp.alarm.ui.adapters



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.data.alarm.AlarmItem
import com.example.sagapp.databinding.AlarmItemsViewBinding

import javax.inject.Inject


class AlarmAdapter @Inject constructor(val glide: RequestManager) :
    RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder>() {

    inner class AlarmViewHolder(private val binding: AlarmItemsViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AlarmItem) {

//            binding.cardContainer.setOnClickListener {
//                onItemClickListener?.let { it(item) }
//            }
        }
    }

    var heroes: List<AlarmItem>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private val differCallBack = object : DiffUtil.ItemCallback<AlarmItem>() {
        override fun areItemsTheSame(oldItem: AlarmItem, newItem: AlarmItem): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(
            oldItem: AlarmItem,
            newItem: AlarmItem,
        ): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        return AlarmViewHolder(
            AlarmItemsViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    private var onItemClickListener: ((AlarmItem) -> Unit)? = null

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        val hero = heroes[position]
        holder.apply {
            bind(hero)
        }
    }

    override fun getItemCount() = heroes.size

    fun setOnItemClickListener(listener: (AlarmItem) -> Unit) {
        onItemClickListener = listener
    }
}