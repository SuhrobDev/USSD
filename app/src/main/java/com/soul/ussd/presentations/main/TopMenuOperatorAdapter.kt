package com.soul.ussd.presentations.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.soul.ussd.databinding.ItemTopMenuOperatorBinding
import com.soul.ussd.domain.main.models.TopMenuOperatorModel

class TopMenuOperatorAdapter : RecyclerView.Adapter<TopMenuOperatorAdapter.TopMenuViewHolder>() {
    private var _data = mutableListOf<TopMenuOperatorModel>()


    fun setNewData(newData: List<TopMenuOperatorModel>) {
        _data.clear()
        _data.addAll(newData)
        notifyDataSetChanged()
    }

    inner class TopMenuViewHolder(private val binding: ItemTopMenuOperatorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: TopMenuOperatorModel) {
            binding.operatorBackground.setBackgroundResource(data.background)
            binding.operatorTitleImg.setImageResource(data.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TopMenuViewHolder(
            ItemTopMenuOperatorBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun getItemCount() = _data.size

    override fun onBindViewHolder(holder: TopMenuViewHolder, position: Int) =
        holder.bindData(_data[position])
}