package com.example.yabichat.ui

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yabichat.Constants
import com.example.yabichat.R
import com.example.yabichat.model.Chat
import com.example.yabichat.model.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.item_contacts.*
import kotlinx.android.synthetic.main.item_contacts.view.*
import java.util.*
import kotlin.collections.ArrayList

class ContactsAdapter(private val dataSet: ArrayList<User>): RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        init {
//            itemView.setOnClickListener{
//                val i: Intent = Intent(itemView.context, MsgActivity::class.java)
//                i.putExtra(MainActivity.BUNDLE_KEY_CONTACT_UID, dataSet[position].uid)
//                i.putExtra(MainActivity.BUNDLE_KEY_CONTACT_NAME, itemView.item_textView.text)
//                itemView.context.startActivity(i)
//            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_contacts, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.item_textView.text = dataSet[position].name
        holder.itemView.item_imageView.setImageResource(R.drawable.ic_ghost)

        if( position%2 == 0){
            holder.itemView.setBackgroundColor((Color.parseColor("#673AB7")))
            holder.itemView.item_textView.setTextColor((Color.parseColor("#ffffff")))
        }
        else{
            holder.itemView.setBackgroundColor((Color.parseColor("#FF9800")))
        }


        holder.itemView.item_textView.setOnClickListener{
            var dbRef_chat = Firebase.database.getReference(Constants.DATABASE_CHATS).child(
                MainActivity.user.uid)
            var dbRef_chatList = dbRef_chat.child(Constants.DATABASE_CHATS_NODE_CHAT_LIST)
            var dbRef_msgList = dbRef_chat.child(Constants.DATABASE_CHATS_NODE_MSG_LIST)

            dbRef_chatList.orderByKey().equalTo(dataSet[position].uid).addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {}

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (!dataSnapshot.exists()) {
                        //var chatID = dbRef_chatList.push().key.toString()
                        dbRef_chatList.child(dataSet[position].uid).setValue(Chat(dataSet[position].uid, dataSet[position].name.toString(),"", Date().time))
                        //dbRef_msgList.child(contactUID).setValue({})
                    }
                }
            })

            val i: Intent = Intent(holder.itemView.context, MsgActivity::class.java)
            i.putExtra(MainActivity.BUNDLE_KEY_CONTACT_UID, dataSet[position].uid)
            i.putExtra(MainActivity.BUNDLE_KEY_CONTACT_NAME, dataSet[position].name)
            holder.itemView.context.startActivity(i)
        }
    }

    override fun getItemCount(): Int = dataSet.size
}