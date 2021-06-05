package com.example.yabichat.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.yabichat.Constants
import com.example.yabichat.R
import com.example.yabichat.model.Chat
import com.example.yabichat.model.Msg
import com.example.yabichat.model.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_msg.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

class MsgActivity : AppCompatActivity() {
    private lateinit var et_message: EditText
    private lateinit var rv_message: RecyclerView
    private lateinit var btn_send: Button

    private lateinit var dbRef_chatList: DatabaseReference
    private lateinit var dbRef_msgList: DatabaseReference
    private lateinit var chatID: String
    val listData = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_msg)

        init()
    }

    private fun init(){
        et_message = findViewById(R.id.et_message)
        rv_message = findViewById(R.id.rv_message)
        btn_send = findViewById(R.id.btn_send)

        chatID = intent.getStringExtra(MainActivity.BUNDLE_KEY_CONTACT_UID).toString()
        // dbRef = Firebase.database.getReference(Constants.DATABASE_CHATS).child(MainActivity.user.uid).child(Constants.DATABASE_CHATS_NODE_MSG_LIST).child(chatID)
        val dbRef = Firebase.database.getReference(Constants.DATABASE_CHATS).child(MainActivity.user.uid)
        dbRef_chatList = dbRef.child(Constants.DATABASE_CHATS_NODE_CHAT_LIST).child(chatID)
        dbRef_msgList = dbRef.child(Constants.DATABASE_CHATS_NODE_MSG_LIST).child(chatID)

//        msg_userName.text =
        btn_send.setOnClickListener {
            sendMessage()
        }
        btn_back.setOnClickListener{
            val i: Intent = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }

    }

    private fun sendMessage() {
        val msg = et_message.text.toString()
        val timestamp = Date().time

        if (msg.isNotEmpty()) {
            val key: String = dbRef_msgList.push().key.toString()
            dbRef_msgList.child(key).setValue(Msg(msg, timestamp))
            dbRef_chatList.setValue(Chat(chatID, msg, timestamp))
            et_message.setText("")

//            adapter.insertMessage(Message(message, SEND_ID, timeStamp))
//            rv_message.scrollToPosition(adapter.itemCount - 1)
        }
    }

//    private fun sendMsg() {
//        val msg: String = et_message.text.toString()
//        val userName = FirebaseAuth.getInstance().currentUser!!.displayName
//        val time = Date().time
//        val key: String? = dbRef.push().getKey()
//        keyList.add(key)
//        //if (TextUtils.isEmpty(avatarPath)) avatarPath = ""
//        dbRef.child(key).setValue(Chat())
//        et_message.setText("")
//        val saveKeyList: MutableSet<String> = HashSet()
//        for (i in keyList.indices) {
//            saveKeyList.add(keyList.get(i))
//        }
//        sharedPreferences.edit().putStringSet("keyList", saveKeyList).commit()
//    }

//    fun basicReadWrite() {
//        // [START write_message]
//        // Write a message to the database
//        val database = Firebase.database
//        val myRef = database.getReference("message")
//
//        myRef.setValue("Hello, World!")
//        // [END write_message]
//
//        // [START read_message]
//        // Read from the database
//        myRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                val value = dataSnapshot.getValue<String>()
//                Log.d(TAG, "Value is: $value")
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException())
//            }
//        })
//        // [END read_message]
//    }
//
//    private fun displayChatMsg() {
//        try {
//            var adapter = object :
//                FirebaseRecyclerAdapter<ChatMessage?, com.example.r30_a.chattool.Controller.MainActivity.ChatMessageHolder>(
//                    ChatMessage::class.java,
//                    R.layout.message,
//                    com.example.r30_a.chattool.Controller.MainActivity.ChatMessageHolder::class.java,
//                    reference.limitToLast(10)
//                ) {
//                override fun onCreateViewHolder(
//                    parent: ViewGroup,
//                    viewType: Int
//                ): com.example.r30_a.chattool.Controller.MainActivity.ChatMessageHolder {
//                    val view: View =
//                        LayoutInflater.from(context).inflate(R.layout.message, parent, false)
//                    return com.example.r30_a.chattool.Controller.MainActivity.ChatMessageHolder(view)
//                }
//
//                override fun populateViewHolder(
//                    viewHolder: com.example.r30_a.chattool.Controller.MainActivity.ChatMessageHolder,
//                    model: ChatMessage?,
//                    position: Int
//                ) {
//                    viewHolder.setValues(model)
//                    viewHolder.img_avatar_other.setOnClickListener(View.OnClickListener { v: View? ->
//                        showInfo(
//                            position
//                        )
//                    })
//                    viewHolder.img_avatar_user.setOnClickListener(View.OnClickListener { v: View? ->
//                        showInfo(
//                            position
//                        )
//                    })
//                }
//            }
//            recyclerView!!.layoutManager = linearLayoutManager
//            recyclerView!!.setHasFixedSize(true)
//            recyclerView!!.adapter = adapter
//            recyclerView!!.scrollToPosition(adapter.getItemCount() - 1)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
}