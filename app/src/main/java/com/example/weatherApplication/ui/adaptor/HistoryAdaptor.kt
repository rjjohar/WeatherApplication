package com.example.weatherApplication.ui.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherApplication.R
import com.example.weatherApplication.databinding.ItemHistoryBinding

class HistoryAdaptor :
    RecyclerView.Adapter<HistoryAdaptor.ViewHolder>() {
    private var list: MutableList<String>? = null
    var onClickListener: (String) -> Unit = {  }

    inner class ViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(name: String) {
            binding.name = name
            binding.clItem.setOnClickListener {
                onClickListener(name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemHistoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_history, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list?.let {
            holder.bind(it[position])
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    fun setDetail(list: List<String>) {
        this.list = list.toMutableList()
        notifyDataSetChanged()
    }
}
