package com.example.yabichat.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Msg(val msg: String, val timestamp: Long = 0 ): Parcelable
