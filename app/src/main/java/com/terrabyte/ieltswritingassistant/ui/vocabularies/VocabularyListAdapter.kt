package com.terrabyte.ieltswritingassistant.ui.vocabularies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.terrabyte.ieltswritingassistant.databinding.ItemVocabListBinding
import com.terrabyte.ieltswritingassistant.model.Word

class VocabularyListAdapter(
    private val onItemClick: (vocabulary: Word) -> Unit
) : RecyclerView.Adapter<VocabularyListAdapter.VocabularyViewHolder>() {

    private val dif = AsyncListDiffer(this, VocabularyListAdapter.ITEM_DIFF)

    inner class VocabularyViewHolder(private val binding: ItemVocabListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val vocabulary = dif.currentList[adapterPosition]

            binding.apply {
                vocabName.text = vocabulary.word

                item.setOnClickListener {
                    onItemClick(vocabulary)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VocabularyViewHolder =
        VocabularyViewHolder(
            ItemVocabListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = dif.currentList.size

    override fun onBindViewHolder(holder: VocabularyViewHolder, position: Int) = holder.bind()

    fun submitList(list: List<Word>) {
        dif.submitList(list)
    }

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<Word>() {
            override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean =
                oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean =
                oldItem == newItem
        }
    }

}