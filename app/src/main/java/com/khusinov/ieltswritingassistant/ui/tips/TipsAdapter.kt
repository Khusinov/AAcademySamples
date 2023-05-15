package com.khusinov.ieltswritingassistant.ui.tips

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.khusinov.ieltswritingassistant.databinding.ItemRv2Binding
import com.khusinov.ieltswritingassistant.model.Tip
import com.squareup.picasso.Picasso

class TipsAdapter : RecyclerView.Adapter<TipsAdapter.TipsViewHolder>() {

    private val dif = AsyncListDiffer(this, TipsAdapter.ITEM_DIFF)

    inner class TipsViewHolder(private val binding: ItemRv2Binding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val tipCurrent = dif.currentList[adapterPosition]

            binding.apply {
                tipId.text = "${adapterPosition + 1}"
                textview.text = tipCurrent.tipBody

                if (tipCurrent.imageUrl != null && tipCurrent.imageUrl != "")
                    Picasso.get().load(tipCurrent.imageUrl).into(imageTip)
                else imageTip.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipsViewHolder =
        TipsViewHolder(ItemRv2Binding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = dif.currentList.size

    override fun onBindViewHolder(holder: TipsViewHolder, position: Int) = holder.bind()

    fun submitList(list: List<Tip>) {
        dif.submitList(list)
    }

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<Tip>() {
            override fun areItemsTheSame(oldItem: Tip, newItem: Tip): Boolean =
                oldItem.tipBody == newItem.tipBody

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Tip, newItem: Tip): Boolean =
                oldItem == newItem
        }
    }

}