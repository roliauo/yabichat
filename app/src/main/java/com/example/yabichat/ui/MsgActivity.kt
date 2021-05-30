package com.example.yabichat.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.yabichat.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*

class MsgActivity : AppCompatActivity() {
    private var dbReference: DatabaseReference? = null
    private var storageReference: StorageReference? = null
    private var inputMsg: EditText? = null
    private var recyclerView: RecyclerView? = null
    var sharedPreferences: SharedPreferences? = null
    var avatarPath: String? = null
    var keyList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_msg)

        findViewAndGetInstance()
    }

    private fun findViewAndGetInstance(){
        inputMsg = findViewById(R.id.input_msg)
        recyclerView = findViewById(R.id.msg_recyclerView)
        dbReference = FirebaseDatabase.getInstance().reference
        storageReference = FirebaseStorage.getInstance().reference
    }


    private fun sendMsg() {
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
    }
}