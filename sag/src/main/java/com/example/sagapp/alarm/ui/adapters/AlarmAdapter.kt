package com.example.sagapp.alarm.ui.adapters



import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.example.data.alarm.AlarmItem
import com.example.sagapp.databinding.AlarmItemsViewBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject


class AlarmAdapter @Inject constructor() :
    RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder>() {

    inner class AlarmViewHolder(private val binding: AlarmItemsViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AlarmItem) {

            binding.alarmTv.text=item.message
            val parsedDate = LocalDateTime.parse(item.time.toString(), DateTimeFormatter.ISO_DATE_TIME)
            val formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
            binding.alarmStatus.text=formattedDate.toString()


//            binding.cardContainer.setOnClickListener {
//                onItemClickListener?.let { it(item) }
//            }
        }
    }

    var alarms: List<AlarmItem>
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
        val hero = alarms[position]
        holder.apply {
            bind(hero)
        }
    }

    override fun getItemCount() = alarms.size

    fun setOnItemClickListener(listener: (AlarmItem) -> Unit) {
        onItemClickListener = listener
    }
}