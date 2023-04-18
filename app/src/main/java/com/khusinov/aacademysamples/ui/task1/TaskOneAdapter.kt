package com.khusinov.aacademysamples.ui.task1

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.khusinov.aacademysamples.R
import com.khusinov.aacademysamples.databinding.ItemRvBinding
import com.khusinov.aacademysamples.model.TaskOne

class TaskOneAdapter() :
    RecyclerView.Adapter<TaskOneAdapter.TaskOneViewHolder>() {

    private val TAG = "TaskOneAdapter"

    private val dif = AsyncListDiffer(this, ITEM_DIFF)

    var onClick: ((TaskOne) -> Unit)? = null

    inner class TaskOneViewHolder(private val binding: ItemRvBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind() {
            val taskOneCurrent = dif.currentList[adapterPosition]

            binding.apply {
                var questionBody = taskOneCurrent.sample
                var firstWords = getFirstWordsUsingSubString(questionBody!!)

                questionId.text = "${adapterPosition + 1}"
                questionTv.text = taskOneCurrent.question
                questionDate.text = taskOneCurrent.date
                view.setBackgroundColor(Color.RED)

                Log.d(TAG, "bind: ${taskOneCurrent.date}")

                binding.root.setOnClickListener {
                    Log.d(TAG, "bind: clicled")
                    onClick?.invoke(taskOneCurrent)


                }

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

    fun getFirstWordsUsingSubString(input: String): String? {
        return input.split(" ").take(7).joinToString(" ") + "..."
    }

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<TaskOne>() {
            override fun areItemsTheSame(oldItem: TaskOne, newItem: TaskOne): Boolean =
                oldItem.body == newItem.body

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: TaskOne, newItem: TaskOne): Boolean =
                oldItem == newItem
        }
    }

}