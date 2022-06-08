package com.example.awan.ui.logreg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.awan.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    lateinit var inEmail: EditText
    private lateinit var inPass: EditText
    lateinit var inRepass: EditText
    private lateinit var btnReg: Button
    lateinit var RedirectLogin: TextView

    // create Firebase authentication object
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // View Bindings
        inEmail = findViewById(R.id.email)
        inPass = findViewById(R.id.password)
        inRepass = findViewById(R.id.repassword)
        btnReg = findViewById(R.id.register)
        RedirectLogin = findViewById(R.id.redirectlog)

        // Initialising auth object
        auth = Firebase.auth

        btnReg.setOnClickListener {
            register()
        }
        // switching from signUp Activity to Login Activity
        RedirectLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun register() {
        val email = inEmail.text.toString()
        val pass = inPass.text.toString()
        val repass = inRepass.text.toString()

        // check pass
        if (email.isBlank() || pass.isBlank() || repass.isBlank()) {
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }

        if (pass != repass) {
            Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT).show()
            return
        }
        // If all credential are correct
        // We call createUserWithEmailAndPassword
        // using auth object and pass the
        // email and pass in it.
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Intent(this@RegisterActivity, LoginActivity::class.java).also {
                    startActivity(it)
                }
                Toast.makeText(this, "Successfully Registered", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to Register!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}