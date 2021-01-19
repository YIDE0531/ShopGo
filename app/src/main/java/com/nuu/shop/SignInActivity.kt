package com.nuu.shop

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        btn_sign_in.setOnClickListener {
            signUp()
        }

        btn_login.setOnClickListener {
            login()
        }
    }

    private fun signUp(){
        val sEmail = edt_email.text.toString()
        val sPassWd = edt_ppss.text.toString()

        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(sEmail, sPassWd)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    AlertDialog.Builder(this@SignInActivity)
                        .setTitle("Sign In")
                        .setMessage("Account created")
                        .setPositiveButton("OK"){dialog, which ->
                            setResult(Activity.RESULT_OK)
                            finish()
                        }.show()
                }else{
                    AlertDialog.Builder(this@SignInActivity)
                        .setTitle("Sign In")
                        .setMessage(task.exception?.message)
                        .setPositiveButton("OK", null)
                        .show()
                }
            }
    }

    private fun login() {
        val sEmail = edt_email.text.toString()
        val sPassWd = edt_ppss.text.toString()

        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(
                sEmail,
                sPassWd
            )
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    setResult(Activity.RESULT_OK)
                    finish()
                }else{
                    AlertDialog.Builder(this@SignInActivity)
                        .setTitle("Login")
                        .setMessage(task.exception?.message)
                        .setPositiveButton("OK", null)
                        .show()
                }
            }
    }
}