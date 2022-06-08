package com.example.awan.ui.logreg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.awan.MainActivity
import com.example.awan.MainMenuActivity
import com.example.awan.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var inEmail: EditText
    private lateinit var inPass: EditText
    lateinit var btnLog: Button
    private lateinit var btnReg: Button

    // Creating firebaseAuth object
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // View Binding
        inEmail = findViewById(R.id.email)
        inPass = findViewById(R.id.password)
        btnLog = findViewById(R.id.login)
        btnReg = findViewById(R.id.register)

        // initialising Firebase auth object
        auth = FirebaseAuth.getInstance();

        btnLog.setOnClickListener {
            login()
        }

        btnReg.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            // using finish() to end the activity
        }
    }

    private fun login() {
        val email = inEmail.text.toString()
        val pass = inPass.text.toString()

        // check pass
        if (email.isBlank() || pass.isBlank()) {
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }
        // calling signInWithEmailAndPassword(email, pass)
        // function using Firebase auth object
        // On successful response Display a Toast
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Intent(this, MainMenuActivity::class.java).also {
                    startActivity(it)
                }
                Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_SHORT).show()
            } else
                Toast.makeText(this, "Log In failed ", Toast.LENGTH_SHORT).show()
        }
    }

}