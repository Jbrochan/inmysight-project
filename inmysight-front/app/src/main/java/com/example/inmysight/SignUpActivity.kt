package com.example.inmysight

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.math.sign

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // Variables
        // Firebase firestore database
        val db = Firebase.firestore
        // User id
        var userId: String = ""
        // User password
        var userPassword: String = ""
        // User's company
        var userCompany: String = ""


        // Sign up function
        val signUpButton: Button = findViewById(R.id.signUpLogInButton)
        signUpButton.setOnClickListener {
            // Store user id, password at variables from plain text
            userId = findViewById<TextView>(R.id.signUpIdInput).text.toString()
            userPassword = findViewById<TextView>(R.id.signUpPasswordInput).text.toString()
            userCompany = findViewById<TextView>(R.id.signUpCompanyInput).text.toString()

            val userData = hashMapOf(
                "userId" to userId,
                "userPassword" to userPassword,
                "userCompany" to userCompany)
            // Send user's id, password, company to database
            db.collection("root").document("company")
                .collection("companies").document(userCompany)
                .collection("users").document(userId).set(userData).addOnSuccessListener { Log.d(TAG, "Sign Up Success!!") }
            // Go to LobbyActivity
            intent = Intent(this, LobbyActivity::class.java)
            startActivity(intent)
        }

        // Move to main activity when press close button
        val closeButton: Button = findViewById(R.id.signUpCancelButton)
        closeButton.setOnClickListener{
            finish()
        }
    }
}
