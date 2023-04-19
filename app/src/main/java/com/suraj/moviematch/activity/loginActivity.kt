package com.suraj.moviematch.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.suraj.moviematch.R

class loginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var btnlogin: Button


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

    }

    private fun setUpListener() {
        btnlogin.setOnClickListener(View.OnClickListener {
            if (editEmail.text.toString().isEmpty() || editPassword.text.toString().isEmpty()) {
                Toast.makeText(this, "Enter Email or Password", Toast.LENGTH_SHORT).show()
            } else
                if (!isValidGmail(editEmail.text.toString())) {
                    Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show()
                } else
                    login(editEmail.text.toString(), editPassword.text.toString())
        })
    }

    fun login(email: String , password : String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
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
    fun isValidGmail(email: String): Boolean {
        val regex = Regex("^[a-zA-Z0-9._%+-]+@gmail.com$")
        return regex.matches(email)
    }
}