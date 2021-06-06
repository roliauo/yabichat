package com.example.yabichat.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yabichat.Constants.RECEIVE_ID
import com.example.yabichat.Constants.SEND_ID
import com.example.yabichat.R
import com.example.yabichat.model.Msg
import kotlinx.android.synthetic.main.message_item.view.*

class MsgAdapter(private val messagesList: ArrayList<Msg>): RecyclerView.Adapter<MsgAdapter.MsgViewHolder>() {

    inner class MsgViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MsgViewHolder {
        return MsgViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return messagesList.size
    }

    override fun onBindViewHolder(holder: MsgViewHolder, index: Int) {
        val currentMessage = messagesList[index]
        when (currentMessage.tag) {
            SEND_ID -> {
                holder.itemView.tv_send_message.apply {
                    text = currentMessage.msg
                    visibility = View.VISIBLE
                }
                holder.itemView.tv_receive_message.visibility = View.INVISIBLE
            }
            RECEIVE_ID -> {
                holder.itemView.tv_receive_message.apply {
                    text = currentMessage.msg
                    visibility = View.VISIBLE
                }
                holder.itemView.tv_send_message.visibility = View.INVISIBLE
            }
        }
    }

}