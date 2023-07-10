package com.example.features.calls

import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.provider.ContactsContract
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest
import android.annotation.SuppressLint
import android.net.Uri

class ContactManager(private val context: Context) {

    @SuppressLint("Range")
    fun getPhoneNumber(contactName: String): String? {
        val contentResolver: ContentResolver = context.contentResolver
        var phoneNumber: String? = null

        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Permission granted, look up the contact's phone number
            val cursor = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER),
                "${ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME} = ?",
                arrayOf(contactName),
                null
            )

            if (cursor != null && cursor.moveToFirst()) {
                // Found the contact, get the phone number
                phoneNumber =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            } else {
                // Contact not found
                phoneNumber = null
            }

            cursor?.close()
        } else {
            // Permission not granted, request it
            ActivityCompat.requestPermissions(
                context as AppCompatActivity,
                arrayOf(Manifest.permission.READ_CONTACTS),
                1
            )
        }

        return phoneNumber
    }
    fun getContactName(phoneNumber: String): String? {
        val uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber))
        val cursor = context.contentResolver.query(uri, arrayOf(ContactsContract.PhoneLookup.DISPLAY_NAME), null, null, null)
        var contactName: String? = null
        if (cursor != null && cursor.moveToFirst()) {
            contactName = cursor.getString(0)
            cursor.close()
        }
        return contactName
    }

}