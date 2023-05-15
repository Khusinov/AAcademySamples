package com.khusinov.ieltswritingassistant

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.khusinov.ieltswritingassistant.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityWelcomeBinding
    private val TAG = "WelcomeActivity"
    var numberOfTask1 = 0
    var numberOfTask2 = 0
    var numberOfMistakes = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        window?.statusBarColor = getColor(R.color.white)

        val db = Firebase.firestore
        numberOfTask1 = 0
        numberOfTask2 = 0
        db.collection("question").get().addOnSuccessListener {
            Log.d(TAG, "onCreate: galdi")
            for (i in it) {
                numberOfTask1++
            }
        }.addOnFailureListener { exception ->
            Log.w(TAG, "setupUI: Error getting documents.  ", exception)
        }

        db.collection("question2").get().addOnSuccessListener {
            Log.d(TAG, "onCreate: galdi")
            for (i in it) {
                numberOfTask2++
            }
        }.addOnFailureListener { exception ->
            Log.w(TAG, "setupUI: Error getting documents.  ", exception)
        }

        db.collection("mistake").get().addOnSuccessListener {
            Log.d(TAG, "onCreate: galdi")
            for (i in it) {
                numberOfMistakes++
            }
        }.addOnFailureListener { exception ->
            Log.w(TAG, "setupUI: Error getting documents.  ", exception)
        }


        Handler(Looper.getMainLooper()).postDelayed({
            saveToSharedPrf()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)

    }

    private fun saveToSharedPrf() {

        val sharedPreferences: SharedPreferences =
            getSharedPreferences("MySharedPref", MODE_PRIVATE)

        val myEdit: SharedPreferences.Editor = sharedPreferences.edit()

        myEdit.putInt("numberOfTask1", numberOfTask1)
        myEdit.putInt("numberOfTask2", numberOfTask2)
        myEdit.putInt("numberOfMistakes", numberOfMistakes)

        myEdit.apply()


    }
}