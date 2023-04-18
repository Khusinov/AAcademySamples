package com.khusinov.aacademysamples.ui.mistakes

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.khusinov.aacademysamples.databinding.ItemRv2Binding
import com.khusinov.aacademysamples.model.TaskOne
import com.khusinov.aacademysamples.model.Tip
import com.squareup.picasso.Picasso

class MistakesAdapter : RecyclerView.Adapter<MistakesAdapter.MistakesViewHolder>() {

    val diff = AsyncListDiffer(this, ITEM_DIFF)

    inner class MistakesViewHolder(private val binding: ItemRv2Binding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.apply {
                val mistakeCurrent = diff.currentList[adapterPosition]
                view.setBackgroundColor(Color.parseColor("#5F45FF"))

                tipId.text = "${adapterPosition + 1}"
                textview.text = mistakeCurrent.tipBody

                if (mistakeCurrent.imageUrl != null && mistakeCurrent.imageUrl != "") Picasso.get()
                    .load(mistakeCurrent.imageUrl).into(imageTip)
                else imageTip.visibility = View.GONE
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MistakesViewHolder =
        MistakesViewHolder(
            ItemRv2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = diff.currentList.size

    override fun onBindViewHolder(holder: MistakesViewHolder, position: Int) = holder.bind()

    fun submitList(list: List<Tip>) {
        diff.submitList(list)
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