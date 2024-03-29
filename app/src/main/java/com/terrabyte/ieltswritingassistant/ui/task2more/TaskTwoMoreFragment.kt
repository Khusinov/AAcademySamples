package com.terrabyte.ieltswritingassistant.ui.task2more

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.terrabyte.ieltswritingassistant.R
import com.terrabyte.ieltswritingassistant.databinding.FragmentTaskTwoMoreBinding
import com.terrabyte.ieltswritingassistant.model.TaskTwo
import com.terrabyte.ieltswritingassistant.viewBinding
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
            question.text = taskTwo.question
            fullAnswer.text = Html.fromHtml(taskTwo.sample)

            if (taskTwo.imageUrl != null && taskTwo.imageUrl != "") Picasso.get()
                .load(taskTwo.imageUrl).into(imageQuestion)

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
                fullAnswer.text = Html.fromHtml(taskTwo.sample)
                if (taskTwo.imageUrl != null && taskTwo.imageUrl != "") Picasso.get()
                    .load(taskTwo.imageUrl).into(imageQuestion)
                //view
                question.visibility = View.VISIBLE
                copyBtnLv.visibility = View.VISIBLE
                bandScoreLv.visibility = View.VISIBLE
                author.visibility = View.VISIBLE
                imageQuestion.visibility = View.VISIBLE

                vocab.setCardBackgroundColor(Color.parseColor("#FF8413"))
                sample.setCardBackgroundColor(Color.RED)
                grammar.setCardBackgroundColor(Color.parseColor("#FF8413"))
            }
            vocab.setOnClickListener {
                //data
                fullAnswer.text = Html.fromHtml(taskTwo.vocabulary)
                // view
                question.visibility = View.GONE
                copyBtnLv.visibility = View.GONE
                bandScoreLv.visibility = View.GONE
                author.visibility = View.GONE
                imageQuestion.visibility = View.GONE

                vocab.setCardBackgroundColor(Color.RED)
                sample.setCardBackgroundColor(Color.parseColor("#FF8413"))
                grammar.setCardBackgroundColor(Color.parseColor("#FF8413"))
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

                vocab.setCardBackgroundColor(Color.parseColor("#FF8413"))
                sample.setCardBackgroundColor(Color.parseColor("#FF8413"))
                grammar.setCardBackgroundColor(Color.RED)

            }

            copyBtn.setOnClickListener {
                if (taskTwo.sample != "" && taskTwo.sample != null) textCopyThenPost(taskTwo.sample!!)
            }

        }
    }

    private fun textCopyThenPost(textCopied: String) {
        val myClipboard =
            ContextCompat.getSystemService(
                requireContext(),
                ClipboardManager::class.java
            ) as ClipboardManager
        val clip = ClipData.newPlainText("Copied", textCopied)
        myClipboard.setPrimaryClip(clip)
        Toast.makeText(requireActivity(), "Copied", Toast.LENGTH_SHORT).show()
    }


}