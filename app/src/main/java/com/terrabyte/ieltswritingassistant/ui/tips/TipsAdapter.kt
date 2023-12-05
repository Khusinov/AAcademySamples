package com.terrabyte.ieltswritingassistant.ui.tips

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.terrabyte.ieltswritingassistant.databinding.ItemVideoLessonsBinding
import com.terrabyte.ieltswritingassistant.model.VideoLessons


class TipsAdapter : RecyclerView.Adapter<TipsAdapter.TipsViewHolder>() {

    private val dif = AsyncListDiffer(this, TipsAdapter.ITEM_DIFF)

    inner class TipsViewHolder(private val binding: ItemVideoLessonsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val tipCurrent = dif.currentList[adapterPosition]

            binding.apply {
                videoName.text = "${adapterPosition + 1}. ${tipCurrent.name}"
                videoType.text = ""

                if (tipCurrent.videoUrl != null && tipCurrent.videoUrl != "") {

                }

                item.setOnClickListener {

                    val url = "${tipCurrent.videoUrl}"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    root.context.startActivity(intent)

                }
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