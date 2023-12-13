package com.terrabyte.ieltswritingassistant.ui.vocabularies

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.terrabyte.ieltswritingassistant.databinding.ItemVideoLessonsBinding
import com.terrabyte.ieltswritingassistant.databinding.ItemVocabulariesBinding
import com.terrabyte.ieltswritingassistant.model.VideoLessons


class VocabularyAdapter : RecyclerView.Adapter<VocabularyAdapter.VocabularyViewHolder>() {

    private val dif = AsyncListDiffer(this, VocabularyAdapter.ITEM_DIFF)

    inner class VocabularyViewHolder(private val binding: ItemVocabulariesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val tipCurrent = dif.currentList[adapterPosition]

            binding.apply {
                topicName.text = tipCurrent.name
                id.text = tipCurrent.id
                vocabType.text = tipCurrent.type

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VocabularyViewHolder =
        VocabularyViewHolder(
            ItemVocabulariesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = dif.currentList.size

    override fun onBindViewHolder(holder: VocabularyViewHolder, position: Int) = holder.bind()

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