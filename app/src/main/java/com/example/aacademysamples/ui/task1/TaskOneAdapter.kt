package com.example.aacademysamples.ui.task1

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aacademysamples.databinding.ItemRvBinding
import com.example.aacademysamples.model.TaskOne

class TaskOneAdapter() : RecyclerView.Adapter<TaskOneAdapter.TaskOneViewHolder>() {

    private val dif = AsyncListDiffer(this, ITEM_DIFF)

    inner class TaskOneViewHolder(private val binding: ItemRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {

            val taskOne = dif.currentList[adapterPosition]

            binding.apply {

                questionTv.text = taskOne.questionBody
                questionId.text = taskOne.date
                questionDate.text = taskOne.date

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskOneViewHolder =
        TaskOneViewHolder(
            ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: TaskOneViewHolder, position: Int) = holder.bind()


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