package com.soul.ussd.presentations.ussd_codes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.soul.ussd.databinding.ItemServiceBinding
import com.soul.ussd.domain.ussd_codes.models.UssdCodesModel

class UssdCodesAdapter : RecyclerView.Adapter<UssdCodesAdapter.MainOperatorViewHolder>() {
    var _data = mutableListOf<UssdCodesModel>()
    private var clickListener: ((String, String, String, String, Int) -> Unit)? = null

    fun setClickListener(f: (String, String, String, String, Int) -> Unit) {
        clickListener = f
    }

    fun setNewData(newData: List<UssdCodesModel>) {
        val diff = DiffUtil.calculateDiff(UssdCodesDiffUtil(_data, newData))
        _data.addAll(newData)
        diff.dispatchUpdatesTo(this)
    }

    inner class MainOperatorViewHolder(private val binding: ItemServiceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(data: UssdCodesModel) {
            binding.title.text = data.title
            itemView.setOnClickListener {
                clickListener?.invoke(
                    data.title,
                    data.content,
                    data.code,
                    data.full_content,
                    data.id
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MainOperatorViewHolder(
            ItemServiceBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun getItemCount() = _data.size

    override fun onBindViewHolder(holder: MainOperatorViewHolder, position: Int) =
        holder.bindView(_data[position])
}