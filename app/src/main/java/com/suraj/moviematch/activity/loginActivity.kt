package com.suraj.moviematch.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.suraj.moviematch.R
import com.suraj.moviematch.Utils.Utils
import com.suraj.moviematch.Utils.Utils.Companion.makeToast

class loginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var btnlogin: Button
    private lateinit var txtCreateAccount: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()

        setUpListener()

    }

    private fun initView() {

        auth = Firebase.auth
        editEmail = findViewById(R.id.editemail)
        editPassword = findViewById(R.id.editpassword)
        btnlogin = findViewById(R.id.btnlogin)
        txtCreateAccount = findViewById(R.id.txtCreateAccount)

    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            updateUI(currentUser)
        }
    }

    private fun setUpListener() {

        btnlogin.setOnClickListener {

            when {
                editEmail.text.toString().isEmpty() -> makeToast(baseContext, "Enter Email")

                editPassword.text.toString().isEmpty() -> makeToast(baseContext, "Enter Password")

                !Utils.isValidGmail(editEmail.text.toString()) -> makeToast(baseContext, "Invalid email")

                else -> login(editEmail.text.toString(), editPassword.text.toString())
            }

        }

        txtCreateAccount.setOnClickListener {
            var intent = Intent(this@loginActivity, CreateAccountActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun login(email: String, password: String) {

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    makeToast(baseContext, "Authentication failed.")
                }
            }

    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val intent = Intent(this@loginActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}