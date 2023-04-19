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
import org.w3c.dom.Text

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var edit_Email: EditText
    private lateinit var edit_Password: EditText
    private lateinit var btnCreateAccount: Button
    private lateinit var txtlogin: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        initView()

        setUpListener()

    }

    private fun initView() {

        auth = Firebase.auth
        edit_Email = findViewById(R.id.edit_email)
        edit_Password = findViewById(R.id.edit_password)
        btnCreateAccount = findViewById(R.id.btnCreateAccount)
        txtlogin = findViewById(R.id.txtlogin)


    }

    private fun setUpListener() {
        btnCreateAccount.setOnClickListener(View.OnClickListener {
            if (edit_Email.text.toString().isEmpty() || edit_Password.text.toString().isEmpty()) {
                Toast.makeText(this, "Enter Email or Password", Toast.LENGTH_SHORT).show()
            } else
                if (!isValidGmail(edit_Email.text.toString())) {
                    Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show()
                } else
                    createAccount(edit_Email.text.toString(), edit_Password.text.toString())
        })



        txtlogin.setOnClickListener {
            var intent = Intent(this@CreateAccountActivity,loginActivity :: class.java)
            startActivity(intent)
            finish()
        }
    }


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            updateUI(currentUser)
        }
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ContentValues.TAG, "createUserWithEmail:success")
                    Toast.makeText(this, "Create Account successfully", Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    //updateUI(null)
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

    fun isValidGmail(email: String): Boolean {
        val regex = Regex("^[a-zA-Z0-9._%+-]+@gmail.com$")
        return regex.matches(email)
    }

}