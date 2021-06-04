package com.example.yabichat.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.yabichat.R
import com.example.yabichat.model.User
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
    private var user: User? = null
    private var dbReference: DatabaseReference? = null
    private var storageReference: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewAndGetInstance()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    private fun findViewAndGetInstance(){
        dbReference = FirebaseDatabase.getInstance().reference
        storageReference = FirebaseStorage.getInstance().reference
        
        user = intent.getParcelableExtra(LoginActivity.BUNDLE_KEY_USER)

        text_greeting.text = user!!.name
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