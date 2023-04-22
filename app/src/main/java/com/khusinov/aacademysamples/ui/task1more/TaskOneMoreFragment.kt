package com.khusinov.aacademysamples.ui.task1more


import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.khusinov.aacademysamples.R
import com.khusinov.aacademysamples.databinding.FragmentTask1Binding
import com.khusinov.aacademysamples.databinding.FragmentTaskOneMoreBinding
import com.khusinov.aacademysamples.model.TaskOne
import com.khusinov.aacademysamples.ui.task1.TaskOneAdapter
import com.khusinov.aacademysamples.viewBinding
import com.squareup.picasso.Picasso


@Suppress("DEPRECATION")
class TaskOneMoreFragment : Fragment(R.layout.fragment_task_one_more) {

    val binding by viewBinding { FragmentTaskOneMoreBinding.bind(it) }
    private val TAG = "TaskOneMoreFragment"

    private lateinit var taskOne: TaskOne
    lateinit var clipboardManager: ClipboardManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            taskOne = arguments?.getParcelable("task")!!
            Log.d(TAG, "onCreate: ${taskOne.sample}")

        }
        setupUI()
    }


    private fun setupUI() {
        binding.apply {
            question.text = taskOne.question
            authorName.text = taskOne.author
            bandScore.text = taskOne.score
            fullAnswer.text = taskOne.sample
            if (taskOne.imageUrl != null && taskOne.imageUrl != "") Picasso.get()
                .load(taskOne.imageUrl).into(imageQuestion)

            imageQuestion.setOnClickListener {
                if (taskOne.imageUrl != null && taskOne.imageUrl != "") {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(taskOne.imageUrl)
                    startActivity(intent)
                }

            }

            sample.setOnClickListener {
                // data
                question.text = taskOne.question
                authorName.text = taskOne.author
                bandScore.text = taskOne.score
                fullAnswer.text = taskOne.sample
                if (taskOne.imageUrl != null && taskOne.imageUrl != "") Picasso.get()
                    .load(taskOne.imageUrl).into(imageQuestion)
                //view
                copyBtnLv.visibility = View.VISIBLE
                question.visibility = View.VISIBLE
                bandScoreLv.visibility = View.VISIBLE
                author.visibility = View.VISIBLE
                imageQuestion.visibility = View.VISIBLE
            }
            vocab.setOnClickListener {
                //data
                fullAnswer.text = taskOne.vocabulary
                // view
                question.visibility = View.GONE
                copyBtnLv.visibility = View.GONE
                bandScoreLv.visibility = View.GONE
                author.visibility = View.GONE
                imageQuestion.visibility = View.GONE
            }
            grammar.setOnClickListener {
                //data
                fullAnswer.text = taskOne.grammar
                // view
                question.visibility = View.GONE
                copyBtnLv.visibility = View.GONE
                bandScoreLv.visibility = View.GONE
                author.visibility = View.GONE
                imageQuestion.visibility = View.GONE

            }

            copyBtn.setOnClickListener {
                if (taskOne.sample != "" && taskOne.sample != null) textCopyThenPost(taskOne.sample!!)
            }

        }
    }

    private fun textCopyThenPost(textCopied: String) {
        var myClipboard =
            getSystemService(requireContext(), ClipboardManager::class.java) as ClipboardManager
        val clip = ClipData.newPlainText("Copied", textCopied)
        myClipboard.setPrimaryClip(clip)
        Toast.makeText(requireActivity(), "Copied", Toast.LENGTH_SHORT).show()
    }

}