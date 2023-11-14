package com.khusinov.ieltswritingassistant.ui.tips

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.khusinov.ieltswritingassistant.databinding.ItemRv2Binding
import com.khusinov.ieltswritingassistant.databinding.ItemVideoLessonsBinding
import com.khusinov.ieltswritingassistant.model.Tip
import com.khusinov.ieltswritingassistant.model.VideoLessons
import com.squareup.picasso.Picasso

class TipsAdapter : RecyclerView.Adapter<TipsAdapter.TipsViewHolder>() {

    private val dif = AsyncListDiffer(this, TipsAdapter.ITEM_DIFF)

    inner class TipsViewHolder(private val binding: ItemVideoLessonsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val tipCurrent = dif.currentList[adapterPosition]

            binding.apply {
                videoName.text = "${adapterPosition + 1}"

                if (tipCurrent.videoUrl != null && tipCurrent.videoUrl != "") {

                } else videoView.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipsViewHolder =
        TipsViewHolder(
            ItemVideoLessonsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = dif.currentList.size

    override fun onBindViewHolder(holder: TipsViewHolder, position: Int) = holder.bind()

    fun submitList(list: List<VideoLessons>) {
        dif.submitList(list)
    }

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<VideoLessons>() {
            override fun areItemsTheSame(oldItem: VideoLessons, newItem: VideoLessons): Boolean =
                oldItem.name == newItem.name

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: VideoLessons, newItem: VideoLessons): Boolean =
                oldItem == newItem
        }
    }

}