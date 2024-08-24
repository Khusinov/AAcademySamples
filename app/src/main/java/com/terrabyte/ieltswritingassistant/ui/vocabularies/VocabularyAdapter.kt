package com.terrabyte.ieltswritingassistant.ui.vocabularies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.terrabyte.ieltswritingassistant.databinding.ItemVocabulariesBinding
import com.terrabyte.ieltswritingassistant.model.Vocabularies


class VocabularyAdapter(
    private val onItemClick: (vocabulary: Vocabularies) -> Unit
) : RecyclerView.Adapter<VocabularyAdapter.VocabularyViewHolder>() {

    private val dif = AsyncListDiffer(this, VocabularyAdapter.ITEM_DIFF)

    inner class VocabularyViewHolder(private val binding: ItemVocabulariesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val vocabulary = dif.currentList[adapterPosition]

            binding.apply {
                topicName.text = vocabulary.topicName
                id.text = vocabulary.id.toString()
                vocabType.text = vocabulary.type

                item.setOnClickListener {
                    onItemClick(vocabulary)
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

    fun submitList(list: List<Vocabularies>) {
        dif.submitList(list)
    }

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<Vocabularies>() {
            override fun areItemsTheSame(oldItem: Vocabularies, newItem: Vocabularies): Boolean =
                oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Vocabularies, newItem: Vocabularies): Boolean =
                oldItem == newItem
        }
    }

}