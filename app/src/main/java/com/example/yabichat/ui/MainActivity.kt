package com.example.yabichat.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.yabichat.R
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var userName: String
    private var dbReference: DatabaseReference? = null
    private var storageReference: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = Firebase.auth
        setContentView(R.layout.activity_main)

        checkIfLogin()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // setup menu
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    private fun findViewAndGetInstance(){
        dbReference = FirebaseDatabase.getInstance().reference
        storageReference = FirebaseStorage.getInstance().reference
    }

    private fun checkIfLogin() {
        if (mAuth.currentUser == null) {
            val loginIntent: Intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(loginIntent)
        } else {
            greeting()
        }
    }

    private fun greeting(){
        userName = mAuth.currentUser!!.displayName.toString()
        Toast.makeText(
            baseContext,
            "Welcome !"
                + userName,
            Toast.LENGTH_SHORT).show()
        text_greeting.setText(userName)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_sign_out) {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setCancelable(false)
                .setTitle("登出")
                .setMessage("確定要登出了嗎？")
                .setPositiveButton(android.R.string.ok,
                    DialogInterface.OnClickListener { dialog, which ->
                        AuthUI.getInstance()
                            .signOut(this)
                            .addOnCompleteListener {
                                Toast.makeText(this, "登出成功", Toast.LENGTH_LONG).show()
                                finish()
                            }
                    }).setNegativeButton(android.R.string.no,
                    DialogInterface.OnClickListener { dialog, which -> }).create()
            builder.show()
        }
        return true
    }

    private fun reload() {
        finish();
        startActivity(getIntent());
    }

}