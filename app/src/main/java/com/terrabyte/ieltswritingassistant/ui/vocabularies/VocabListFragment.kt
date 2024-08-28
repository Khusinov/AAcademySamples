package com.terrabyte.ieltswritingassistant.ui.vocabularies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.terrabyte.ieltswritingassistant.R
import com.terrabyte.ieltswritingassistant.databinding.FragmentVocabListBinding
import com.terrabyte.ieltswritingassistant.model.Word
import com.terrabyte.ieltswritingassistant.viewBinding

class VocabListFragment : Fragment(R.layout.fragment_vocab_list) {

    private val binding by viewBinding { FragmentVocabListBinding.bind(it) }

    private val TAG = "VocabListFragment"
    private val adapter by lazy { VocabularyListAdapter(this::onItemClick) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.apply {


            val videosList = ArrayList<Word>()

            val db = Firebase.firestore
            db.collection("word").get().addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("Galdi bi", "${document.id} => ${document.data}")

                    val word = Word(
                        document.data["id"].toString().toInt(),
                        document.data["word"].toString(),
                        document.data["audio"].toString(),
                        document.data["meaning"].toString(),
                        document.data["sentenceUse"].toString()
                    )
                    videosList.add(word)

                }
                callIt(videosList.sortedBy { it.id })
            }.addOnFailureListener { exception ->
                Log.w(TAG, "setupUI: Error getting documents.  ", exception)
            }
        }
    }

    private fun callIt(list: List<Word>) {
        binding.apply {
            rv.adapter = adapter
            adapter.submitList(list)
        }
    }

    private fun onItemClick(vocabulary: Word) {

    }
}
