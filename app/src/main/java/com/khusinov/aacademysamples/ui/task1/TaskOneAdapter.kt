package com.khusinov.aacademysamples.ui.task1

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.khusinov.aacademysamples.databinding.ItemRvBinding
import com.khusinov.aacademysamples.model.TaskOne

class TaskOneAdapter(val list: List<TaskOne>) : RecyclerView.Adapter<TaskOneAdapter.TaskOneViewHolder>() {

    private val TAG = "TaskOneAdapter"

    private val dif = AsyncListDiffer(this, ITEM_DIFF)

    inner class TaskOneViewHolder(private val binding: ItemRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val taskOne = dif.currentList[adapterPosition]
            val taskOne1 = list

            binding.apply {
                Log.d("Adapter 1", "bind: Adapter called")
                var questionBody = taskOne.body
                questionBody = questionBody!!.substring(0,30)
                questionTv.text = questionBody
                questionDate.text = taskOne.date.toString()
                questionId.text = "1"

                questionBody = taskOne1[0].body
                questionTv.text = questionBody
                questionDate.text = taskOne1[0].date.toString()

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
                oldItem.body == newItem.body

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: TaskOne, newItem: TaskOne): Boolean =
                oldItem == newItem
        }
    }

}