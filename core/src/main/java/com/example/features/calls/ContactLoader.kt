package com.example.features.calls

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.provider.ContactsContract
import com.google.firebase.database.FirebaseDatabase

class ContactLoader(private val context: Context) {

    private val database = FirebaseDatabase.getInstance().reference.child("contacts")

    @SuppressLint("Range")
    fun loadContacts() {
        val contentResolver: ContentResolver = context.contentResolver
        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            null
        )

        if (cursor != null && cursor.moveToFirst()) {
            do {
                val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                val name =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val phoneNumber = getPhoneNumber(id)

                if (name != null && phoneNumber != null) {
                    val contact = Contact(name, phoneNumber)
                    saveContactToFirebase(contact)
                }
            } while (cursor.moveToNext())
            cursor.close()
        }
    }

    @SuppressLint("Range")
    private fun getPhoneNumber(contactId: String): String? {
        val phoneCursor = context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
            arrayOf(contactId),
            null
        )

        var phoneNumber: String? = null
        if (phoneCursor != null && phoneCursor.moveToFirst()) {
            phoneNumber =
                phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            phoneCursor.close()
        }

        return phoneNumber
    }

    private fun saveContactToFirebase(contact: Contact) {
        val sanitizedContactName = contact.name.replace(".", "_")
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("contacts").child(sanitizedContactName)
        myRef.setValue(contact)
    }
}