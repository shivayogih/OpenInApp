package com.shivayogi.openinapp.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast


fun openWhatsApp(context: Context, phoneNumber: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("https://wa.me/$phoneNumber")
            setPackage("com.whatsapp")
        }
        context.startActivity(intent)
    } catch (e: Exception) {
        // Handle the case when WhatsApp is not installed
        Toast.makeText(context, "WhatsApp not installed.", Toast.LENGTH_SHORT).show()
    }
}
