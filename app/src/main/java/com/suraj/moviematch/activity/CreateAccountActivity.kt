package com.suraj.moviematch.activity

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
import org.w3c.dom.Text

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var edit_Email: EditText
    private lateinit var edit_Password: EditText
    private lateinit var btnCreateAccount: Button
    private lateinit var txtHaveAccount: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        initView()

        setUpListener()

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            updateUI(currentUser)
        }
    }


    private fun initView() {

        auth = Firebase.auth
        edit_Email = findViewById(R.id.edit_email)
        edit_Password = findViewById(R.id.edit_password)
        btnCreateAccount = findViewById(R.id.btnCreateAccount)
        txtHaveAccount = findViewById(R.id.txtHaveAccount)

    }


    private fun setUpListener() {

        btnCreateAccount.setOnClickListener {

            when {

                edit_Email.text.toString().isEmpty() -> makeToast(baseContext, "Enter Email")

                edit_Password.text.toString().isEmpty() -> makeToast(baseContext, "Enter Password")

                !Utils.isValidGmail(edit_Email.text.toString()) -> makeToast(baseContext, "Invalid email")

                else -> createAccount(edit_Email.text.toString(), edit_Password.text.toString())

            }

        }

        txtHaveAccount.setOnClickListener {
            var intent = Intent(this@CreateAccountActivity, loginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    makeToast(baseContext, "Create Account successfully")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    makeToast(baseContext, "Authentication failed.")
                }
            }
    }


    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val intent = Intent(this@CreateAccountActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}