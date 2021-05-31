package com.example.yabichat.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.yabichat.R
import com.example.yabichat.model.User
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: DatabaseReference

    // user info
    private lateinit var uid: String
    private lateinit var name: String
    private lateinit var email: String
    private lateinit var phone: String

    companion object {
        private const val RC_SIGN_IN = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = Firebase.auth
        database = Firebase.database.reference

        checkIfLogin()
    }

    private fun checkIfLogin() {
        if (mAuth.currentUser == null) {
            createSignInIntent()
        } else {
            loginSuccess()
        }
    }

    private fun loginSuccess() {
        val mainIntent: Intent = Intent(this, MainActivity::class.java)
        val bundle = Bundle()
        bundle.putString("name", name)
        bundle.putString("uid", uid)
        bundle.putString("email", email)
        bundle.putString("phone", phone)
        mainIntent.putExtras(bundle)
        startActivity(mainIntent)
        finish()
    }

    private fun createSignInIntent() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
        )
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.drawable.yabichat)
                .setTheme(R.style.Theme_YabiChat)
                .build(),
            RC_SIGN_IN
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                createUser()

                loginSuccess()
            } else {
                Toast.makeText(
                    baseContext,
                    R.string.loginFail,
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createUser() {
        val currentUser = mAuth.currentUser //FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            name = getUserName()

            //database.child("users").child(userId).setValue(user)
            database.child("users").child(currentUser.uid).setValue(User(currentUser.uid, name, currentUser.email, currentUser.phoneNumber))
        }


    }

    private fun getUserName(): String =
        when (true) {
            mAuth.currentUser!!.displayName != null -> mAuth.currentUser!!.displayName.toString()
            mAuth.currentUser!!.email != null -> mAuth.currentUser!!.email.toString()
            mAuth.currentUser!!.phoneNumber != null -> mAuth.currentUser!!.phoneNumber.toString()
            else -> "UserName"
        }
//
//    private fun getUserProfile() {
//        // [START get_user_profile]
//        val user = FirebaseAuth.getInstance().currentUser
//        if (user != null) {
//            // Name, email address, and profile photo Url
//            val name = user.displayName
//            val email = user.email
//            val photoUrl = user.photoUrl
//
//            // Check if user's email is verified
//            val emailVerified = user.isEmailVerified
//
//            // The user's ID, unique to the Firebase project. Do NOT use this value to
//            // authenticate with your backend server, if you have one. Use
//            // FirebaseUser.getIdToken() instead.
//            val uid = user.uid
//        }
//        // [END get_user_profile]
//    }
}