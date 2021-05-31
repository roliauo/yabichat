package com.example.yabichat.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.yabichat.R
import com.example.yabichat.model.ChatMsg
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*

class MsgActivity : AppCompatActivity() {
//    private var dbReference: DatabaseReference? = null
//    private var storageReference: StorageReference? = null
//    var sharedPreferences: SharedPreferences? = null
//    var avatarPath: String? = null
//    var keyList = ArrayList<String>()

    private var inputMsg: EditText? = null
    private var recyclerView: RecyclerView? = null

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_msg)

        findViewAndGetInstance()
    }

    private fun findViewAndGetInstance(){
        inputMsg = findViewById(R.id.input_msg)
        recyclerView = findViewById(R.id.msg_recyclerView)

//        dbReference = FirebaseDatabase.getInstance().reference
//        storageReference = FirebaseStorage.getInstance().reference

        database = Firebase.database.reference
    }


//    private fun sendMsg() {
//        val msg: String = inputMsg.getText().toString()
//        val userName = FirebaseAuth.getInstance().currentUser!!.displayName
//        val time = Date().time
//        val key: String? = dbReference.push().getKey()
//        keyList.add(key)
//        if (TextUtils.isEmpty(avatarPath)) avatarPath = ""
//        dbReference.child(key).setValue(ChatMsg(userName, msg, time, uuid, "", avatarPath))
//        inputMsg.setText("")
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