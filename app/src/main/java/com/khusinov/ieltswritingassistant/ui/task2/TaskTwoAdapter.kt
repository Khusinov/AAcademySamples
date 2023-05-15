package com.khusinov.ieltswritingassistant.ui.task2

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.khusinov.ieltswritingassistant.databinding.ItemRvBinding
import com.khusinov.ieltswritingassistant.model.TaskTwo

class TaskTwoAdapter() :
    RecyclerView.Adapter<TaskTwoAdapter.TaskTwoViewHolder>() {

    private val TAG = "TaskTwoAdapter"

    var colorId = 0
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
                questionTv.text = taskTwoCurrent.question
                questionDate.text = taskTwoCurrent.date
                view.setBackgroundColor(Color.parseColor("#FF8413"))

                when (colorId) {
                    0 -> {
                        // red
                        colorId = 1
                        view.setBackgroundColor(Color.RED)
                        CardViewNumber.setCardBackgroundColor(Color.RED)
                        questionId.setTextColor(Color.RED)
                        icArrow.setColorFilter(Color.RED)
                        questionDate.setTextColor(Color.RED)
                    }
                    1 -> {
                        // green
                        view.setBackgroundColor(Color.parseColor("#34C142"))
                        CardViewNumber.setCardBackgroundColor(Color.parseColor("#34C142"))
                        questionId.setTextColor(Color.parseColor("#34C142"))
                        icArrow.setColorFilter(Color.parseColor("#34C142"))
                        questionDate.setTextColor(Color.parseColor("#34C142"))
                        colorId = 2
                    }
                    2 -> {
                        // blue
                        view.setBackgroundColor(Color.BLUE)
                        CardViewNumber.setCardBackgroundColor(Color.BLUE)
                        questionId.setTextColor(Color.BLUE)
                        icArrow.setColorFilter(Color.BLUE)
                        questionDate.setTextColor(Color.BLUE)
                        colorId = 3
                    }
                    3 -> {
                        //orange
                        view.setBackgroundColor(Color.parseColor("#FF8413"))
                        CardViewNumber.setCardBackgroundColor(Color.parseColor("#FF8413"))
                        questionId.setTextColor(Color.parseColor("#FF8413"))
                        icArrow.setColorFilter(Color.parseColor("#FF8413"))
                        questionDate.setTextColor(Color.parseColor("#FF8413"))
                        colorId = 0
                    }
                }




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
                oldItem.sample == newItem.sample

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: TaskTwo, newItem: TaskTwo): Boolean =
                oldItem == newItem
        }
    }

}