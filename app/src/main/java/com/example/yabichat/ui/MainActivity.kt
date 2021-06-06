package com.example.yabichat.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.yabichat.Constants
import com.example.yabichat.R
import com.example.yabichat.model.Chat
import com.example.yabichat.model.User
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_msg.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference

    companion object {
        lateinit var user: User
        const val BUNDLE_KEY_CONTACT_UID = "BUNDLE_KEY_CONTACT_UID"
        const val BUNDLE_KEY_CONTACT_NAME = "BUNDLE_KEY_CONTACT_NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        // get contacts list -> click -> msg
        dbRef = Firebase.database.getReference(Constants.DATABASE_USERS) // Contacts
        user = intent.getParcelableExtra(LoginActivity.BUNDLE_KEY_USER)!!

        var chatID = "5G8nftNnrUYt41fNN6puKW52xRl2"
        var memberName = "Test"

        btn_chat.setOnClickListener {
//            chat("6aFJCLDK2WQONaBJdGcwNbZLO8x2", "Mandy Lin")
            chat(chatID, memberName)
        }

        text_greeting.text = user.name

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_sign_out -> {
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
        }

        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun chat(contactUID: String, memberName: String) {
        var dbRef_chat = Firebase.database.getReference(Constants.DATABASE_CHATS).child(user.uid)
        var dbRef_chatList = dbRef_chat.child(Constants.DATABASE_CHATS_NODE_CHAT_LIST)
        var dbRef_msgList = dbRef_chat.child(Constants.DATABASE_CHATS_NODE_MSG_LIST)

//        var memberName: String = ""
//        Firebase.database.getReference(Constants.DATABASE_USERS).child(contactUID).addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                memberName = dataSnapshot.child(Constants.KEY_NAME).value.toString()
//                //msg_userName.text = memberName
//                Log.i(Constants.TAG_DEBUG, dataSnapshot.child(Constants.KEY_NAME).toString())
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                //Log.w(TAG, "onCancelled", databaseError.toException())
//            }
//        })

        dbRef_chatList.orderByKey().equalTo(contactUID).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {}

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (!dataSnapshot.exists()) {
                        //var chatID = dbRef_chatList.push().key.toString()
                        dbRef_chatList.child(contactUID).setValue(Chat(contactUID, memberName,"", Date().time))
                        //dbRef_msgList.child(contactUID).setValue({})
                    }
                }
            })

        val i: Intent = Intent(this, MsgActivity::class.java)
        i.putExtra(BUNDLE_KEY_CONTACT_UID, contactUID)
        i.putExtra(BUNDLE_KEY_CONTACT_NAME, memberName)
        startActivity(i)
    }

    private fun reload() {
        finish();
        startActivity(getIntent());
    }

}