package com.khusinov.aacademysamples.ui.task2

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.khusinov.aacademysamples.databinding.ItemRvBinding
import com.khusinov.aacademysamples.model.TaskOne
import com.khusinov.aacademysamples.model.TaskTwo

class TaskTwoAdapter(val list: List<TaskTwo>) :
    RecyclerView.Adapter<TaskTwoAdapter.TaskTwoViewHolder>() {

    private val TAG = "TaskTwoAdapter"

    private val dif = AsyncListDiffer(this, ITEM_DIFF)

    inner class TaskTwoViewHolder(private val binding: ItemRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val taskTwo = dif.currentList[adapterPosition]
            val taskTwo1 = list

            binding.apply {
                Log.d("Adapter 1", "bind: Adapter called")
                var questionBody = taskTwo.body
                questionBody = questionBody!!.substring(0, 30)
                questionTv.text = questionBody
                questionDate.text = taskTwo.date.toString()
                questionId.text = "1"

                view.setBackgroundColor(Color.parseColor("#FF8413"))

                questionBody = taskTwo1[0].body
                questionTv.text = questionBody
                questionDate.text = taskTwo1[0].date.toString()

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