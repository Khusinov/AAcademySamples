package com.terrabyte.ieltswritingassistant.ui.task1more


import android.annotation.SuppressLint
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
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.terrabyte.ieltswritingassistant.R
import com.terrabyte.ieltswritingassistant.databinding.FragmentTaskOneMoreBinding
import com.terrabyte.ieltswritingassistant.model.TaskOne
import com.terrabyte.ieltswritingassistant.viewBinding
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


    @SuppressLint("ResourceAsColor")
    private fun setupUI() {
        binding.apply {
            question.text = taskOne.question
         //   question.text = Html.fromHtml(taskOne.question)
            authorName.text = taskOne.author
            fullAnswer.text = Html.fromHtml(taskOne.sample)
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
                fullAnswer.text = Html.fromHtml(taskOne.sample)
                if (taskOne.imageUrl != null && taskOne.imageUrl != "") Picasso.get()
                    .load(taskOne.imageUrl).into(imageQuestion)
                //view
                copyBtnLv.visibility = View.VISIBLE
                question.visibility = View.VISIBLE
                bandScoreLv.visibility = View.VISIBLE
                author.visibility = View.VISIBLE
                imageQuestion.visibility = View.VISIBLE
                vocab.setCardBackgroundColor(Color.RED)
                sample.setCardBackgroundColor(Color.parseColor("#FF8413"))
                grammar.setCardBackgroundColor(Color.RED)
            }
            vocab.setOnClickListener {
                //data
                fullAnswer.text = Html.fromHtml(taskOne.vocabulary)
                // view
                question.visibility = View.GONE
                copyBtnLv.visibility = View.GONE
                bandScoreLv.visibility = View.GONE
                author.visibility = View.GONE
                imageQuestion.visibility = View.GONE
                vocab.setCardBackgroundColor(Color.parseColor("#FF8413"))
                sample.setCardBackgroundColor(Color.RED)
                grammar.setCardBackgroundColor(Color.RED)
            }
            grammar.setOnClickListener {
                //data
                fullAnswer.text = Html.fromHtml(taskOne.grammar)
                // view
                question.visibility = View.GONE
                copyBtnLv.visibility = View.GONE
                bandScoreLv.visibility = View.GONE
                author.visibility = View.GONE
                imageQuestion.visibility = View.GONE

                vocab.setCardBackgroundColor(Color.RED)
                sample.setCardBackgroundColor(Color.RED)
                grammar.setCardBackgroundColor(Color.parseColor("#FF8413"))

            }

            copyBtn.setOnClickListener {
                if (taskOne.sample != "" && taskOne.sample != null) textCopyThenPost(taskOne.sample!!)
            }

        }
    }

    private fun textCopyThenPost(textCopied: String) {
        val myClipboard =
            getSystemService(requireContext(), ClipboardManager::class.java) as ClipboardManager
        val clip = ClipData.newPlainText("Copied", textCopied)
        myClipboard.setPrimaryClip(clip)
        Toast.makeText(requireActivity(), "Copied", Toast.LENGTH_SHORT).show()
    }

}