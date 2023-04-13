package com.khusinov.aacademysamples

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.khusinov.aacademysamples.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityWelcomeBinding
    private val TAG = "WelcomeActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        window?.statusBarColor = getColor(R.color.white)

        val db = Firebase.firestore
        db.collection("question").get().addOnSuccessListener {
            Log.d(TAG, "onCreate: galdi")
        }.addOnFailureListener { exception ->
            Log.w(TAG, "setupUI: Error getting documents.  ", exception)
        }

        db.collection("question2").get().addOnSuccessListener {
            Log.d(TAG, "onCreate: galdi")
        }.addOnFailureListener { exception ->
            Log.w(TAG, "setupUI: Error getting documents.  ", exception)
        }


        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
        
    }
}