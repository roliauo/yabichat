package com.example.yabichat.model

data class Chat(val id: Int, val member: User, val msg: String? = null, val timestamp: Long = 0 )
