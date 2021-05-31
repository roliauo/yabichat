package com.example.yabichat.model

data class
User(val uid: String, var name:String = "UserName", var email: String? = null, var phone: String? = null, var contacts: MutableList<String> = mutableListOf())
