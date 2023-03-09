package com.khusinov.aacademysamples.ui.task1

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.khusinov.aacademysamples.databinding.ItemRvBinding
import com.khusinov.aacademysamples.model.TaskOne

class TaskOneAdapter() : RecyclerView.Adapter<TaskOneAdapter.TaskOneViewHolder>() {

    private val dif = AsyncListDiffer(this, ITEM_DIFF)

    inner class TaskOneViewHolder(private val binding: ItemRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val taskOne = dif.currentList[adapterPosition]

            binding.apply {
                var questionBody = taskOne.questionBody
                questionBody = questionBody.substring(0, 40) + "..."
                questionTv.text = questionBody
                questionId.text = taskOne.id.toString()
                questionDate.text = taskOne.date
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskOneViewHolder =
        TaskOneViewHolder(
            ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = dif.currentList.size

    override fun onBindViewHolder(holder: TaskOneViewHolder, position: Int) = holder.bind()

    fun submitList(list: List<TaskOne>) {
        dif.submitList(list)
    }

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<TaskOne>() {
            override fun areItemsTheSame(oldItem: TaskOne, newItem: TaskOne): Boolean =
                oldItem.questionBody == newItem.questionBody

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: TaskOne, newItem: TaskOne): Boolean =
                oldItem == newItem
        }
    }

}