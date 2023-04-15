package com.khusinov.aacademysamples.ui.task2

import android.annotation.SuppressLint
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
import com.khusinov.aacademysamples.model.TaskTwo
import kotlin.coroutines.coroutineContext

class TaskTwoAdapter() :
    RecyclerView.Adapter<TaskTwoAdapter.TaskTwoViewHolder>() {

    private val TAG = "TaskTwoAdapter"

    private val dif = AsyncListDiffer(this, ITEM_DIFF)

    var onClick: ((TaskTwo) -> Unit)? = null

    inner class TaskTwoViewHolder(private val binding: ItemRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val taskTwoCurrent = dif.currentList[adapterPosition]

            binding.apply {
                var questionBody = taskTwoCurrent.sample
                var firstWords = getFirstWordsUsingSubString(questionBody!!)

                questionId.text = "${adapterPosition + 1}"
                questionTv.text = firstWords
                questionDate.text = taskTwoCurrent.date
                view.setBackgroundColor(Color.parseColor("#FF8413"))

                Log.d(TAG, "bind: ${taskTwoCurrent.date}")

                binding.root.setOnClickListener {
                    Log.d(TAG, "bind: clicled")
                    onClick?.invoke(taskTwoCurrent)


                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskTwoViewHolder =
        TaskTwoViewHolder(
            ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = dif.currentList.size

    override fun onBindViewHolder(holder: TaskTwoViewHolder, position: Int) = holder.bind()

    fun submitList(list: List<TaskTwo>) {
        dif.submitList(list)
    }

    fun getFirstWordsUsingSubString(input: String): String? {
        return input.split(" ").take(7).joinToString(" ") + "..."
    }

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<TaskTwo>() {
            override fun areItemsTheSame(oldItem: TaskTwo, newItem: TaskTwo): Boolean =
                oldItem.body == newItem.body

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: TaskTwo, newItem: TaskTwo): Boolean =
                oldItem == newItem
        }
    }

}