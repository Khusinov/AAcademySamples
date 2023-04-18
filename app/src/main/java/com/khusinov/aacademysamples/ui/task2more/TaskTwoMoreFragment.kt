package com.khusinov.aacademysamples.ui.task2more

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.khusinov.aacademysamples.R
import com.khusinov.aacademysamples.databinding.FragmentTaskTwoMoreBinding
import com.khusinov.aacademysamples.model.TaskTwo
import com.khusinov.aacademysamples.viewBinding
import com.squareup.picasso.Picasso


@Suppress("DEPRECATION")
class TaskTwoMoreFragment : Fragment(R.layout.fragment_task_two_more) {

    val binding by viewBinding { FragmentTaskTwoMoreBinding.bind(it) }
    private val TAG = "TaskTwoMoreFragment"

    private lateinit var taskTwo: TaskTwo
    lateinit var clipboardManager: ClipboardManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            taskTwo = arguments?.getParcelable("task")!!
            Log.d(TAG, "onCreate: ${taskTwo.sample}")

        }
        setupUI()
    }


    private fun setupUI() {
        binding.apply {
            authorName.text = taskTwo.author
            bandScore.text = taskTwo.score
            fullAnswer.text = taskTwo.sample
            if (taskTwo.imageUrl != null && taskTwo.imageUrl != "") Picasso.get()
                .load(taskTwo.imageUrl).into(imageQuestion)

            icBack.setOnClickListener {
                findNavController().navigate(R.id.action_taskTwoMoreFragment_to_taskTwoFragment)
            }
            imageQuestion.setOnClickListener {
                if (taskTwo.imageUrl != null && taskTwo.imageUrl != "") {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(taskTwo.imageUrl)
                    startActivity(intent)
                }

            }

            sample.setOnClickListener {
                // data
                question.text = taskTwo.question
                authorName.text = taskTwo.author
                bandScore.text = taskTwo.score
                fullAnswer.text = taskTwo.sample
                if (taskTwo.imageUrl != null && taskTwo.imageUrl != "") Picasso.get()
                    .load(taskTwo.imageUrl).into(imageQuestion)
                //view
                question.visibility = View.VISIBLE
                copyBtnLv.visibility = View.VISIBLE
                bandScoreLv.visibility = View.VISIBLE
                author.visibility = View.VISIBLE
                imageQuestion.visibility = View.VISIBLE
            }
            vocab.setOnClickListener {
                //data
                fullAnswer.text = taskTwo.vocabulary
                // view
                question.visibility = View.GONE
                copyBtnLv.visibility = View.GONE
                bandScoreLv.visibility = View.GONE
                author.visibility = View.GONE
                imageQuestion.visibility = View.GONE
            }
            grammar.setOnClickListener {
                //data
                fullAnswer.text = taskTwo.grammar
                // view
                question.visibility = View.GONE
                copyBtnLv.visibility = View.GONE
                bandScoreLv.visibility = View.GONE
                author.visibility = View.GONE
                imageQuestion.visibility = View.GONE

            }

            copyBtn.setOnClickListener {
                if (taskTwo.sample != "" && taskTwo.sample != null) textCopyThenPost(taskTwo.sample!!)
            }

        }
    }

    private fun textCopyThenPost(textCopied: String) {
        var myClipboard =
            ContextCompat.getSystemService(
                requireContext(),
                ClipboardManager::class.java
            ) as ClipboardManager
        val clip = ClipData.newPlainText("Copied", textCopied)
        myClipboard.setPrimaryClip(clip)
        Toast.makeText(requireActivity(), "Copied", Toast.LENGTH_SHORT).show()
    }


}