package com.suraj.moviematch.Utils

import android.content.Context
import android.widget.Toast

class Utils {
    companion object{

         fun makeToast(context : Context, value: String) {
            Toast.makeText(context, value, Toast.LENGTH_SHORT).show()
        }

        fun isValidGmail(email: String): Boolean {
            val regex = Regex("^[a-zA-Z0-9._%+-]+@gmail.com$")
            return regex.matches(email)
        }


    }

}