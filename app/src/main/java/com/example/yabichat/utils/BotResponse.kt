package com.project.chatbot.utils

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.project.chatbot.utils.Constants.OPEN_GOOGLE
import com.project.chatbot.utils.Constants.OPEN_SEARCH
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import com.project.chatbot.utils.SolveMath

object BotResponse {
    //加減乘除
    private val symbols = listOf("+", "-", "*", "/")

    //星期幾的function
    fun getCurrentDay(): Int {
        val value: Calendar = Calendar.getInstance()
        return value.get(Calendar.DAY_OF_WEEK)
    }



    //設計問答
    fun basicResponses(_message: String): String {

        //機器看不懂問題時的隨機回覆
        val random = (0..2).random()
        //將輸入全部轉為小寫
        val message = _message.toLowerCase()

        // 加減乘除運算
        for(symbol in symbols){
            if(message.contains(symbol)) {
                return SolveMath.solveMath(message).toString()
            }
        }

        //其他問答
        return when {
            //今天是今年的第幾週 (week of the year)
            message.contains("week") && message.contains("year") -> {
                val week = Calendar.getInstance(TimeZone.getTimeZone("Asia/Taiwan"))
                    .get(Calendar.WEEK_OF_YEAR)
                week.toString()
            }


            //今天是今年的第幾週 (week of the year)
            message.contains("week") && message.contains("year") -> {
                val week = Calendar.getInstance(TimeZone.getTimeZone("Asia/Taiwan"))
                    .get(Calendar.WEEK_OF_YEAR)
                week.toString()
            }

            //Hello
            message.contains("hello") -> {
                when (random) {
                    0 -> "Hello there!"
                    1 -> "Sup"
                    2 -> "What's up!"
                    else -> "error"
                }
            }

            //How are you?
            message.contains("how are you") -> {
                when (random) {
                    0 -> "I'm doing fine, thanks!"
                    1 -> "I'm hungry..."
                    2 -> "Pretty good! How about you?"
                    else -> "error"
                }
            }

            //現在時間
            message.contains("time") && message.contains("?") -> {
                val timeStamp = Timestamp(System.currentTimeMillis())
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
                val date = sdf.format(Date(timeStamp.time))

                date.toString()
            }


            //開啟Google
            message.contains("open") && message.contains("google") -> {
                OPEN_GOOGLE
            }


            //搜尋
            message.contains("search") -> {
                OPEN_SEARCH
            }

            //無法回答使用者問題時
            else -> {
                when (random) {
                    0 -> "I don't understand..."
                    1 -> "Sorry. Could you ask again?"
                    2 -> "Sorry. I can't help you, but Taiwan can help!"
                    else -> "error"
                }
            }
        }

    }
}