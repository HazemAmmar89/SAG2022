package com.example.data.whatsapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.example.features.whatsapp.WhatsappSendMessage
import javax.inject.Inject

class WhatsappSendMessageImpl @Inject constructor(private val context: Context): WhatsappSendMessage {
     override fun shareText(phone:String,message:String) {
        val intent = Intent(
            Intent.ACTION_VIEW, Uri.parse(
                "https://api.whatsapp.com/send?phone=" + "2$phone" +
                        "&text=" + "$message"
            )
        )
         val intent1 =intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent1)
    }


}