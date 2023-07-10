package com.example.features.calls

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.telephony.TelephonyManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*



class FirebasePhoneCallHelper(private val context: Context) {

    private val TAG = "FirebasePhoneCallHelper"
    private lateinit var telephonyManager: TelephonyManager
    private lateinit var firebaseDatabaseHelper: FirebaseDatabaseHelper
    private lateinit var contactManager: ContactManager

    fun startListenForCalls() {
        // Initialize Firebase
        FirebaseApp.initializeApp(context)

        // Initialize TelephonyManager
        telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        // Initialize FirebaseDatabaseHelper
        firebaseDatabaseHelper = FirebaseDatabaseHelper()

        // Initialize ContactManager
        contactManager = ContactManager(context)

        // Check if permission to make phone calls has been granted
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Request permission to make phone calls if not already granted
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(Manifest.permission.CALL_PHONE),
                2
            )
        } else {
            // Permission already granted, add value event listener to database reference
            firebaseDatabaseHelper.addValueEventListener(valueEventListener)
        }
    }

    private val valueEventListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            for (contactSnapshot in dataSnapshot.children) {
                val contactNameOrNumber = contactSnapshot.key
                val phoneNumber = contactSnapshot.value.toString()
                if (contactNameOrNumber != null) {
                    val contactPhoneNumber = contactManager.getPhoneNumber(contactNameOrNumber)
                    if (contactPhoneNumber != null) {
                        makePhoneCall(context, contactNameOrNumber, contactPhoneNumber)
                    } else {
                        val contactName = contactManager.getContactName(phoneNumber)
                        if (contactName != null) {
                            makePhoneCall(context, contactName, phoneNumber)
                        } else {
                            Log.d(TAG, "Contact not found: $contactNameOrNumber")
                        }
                    }
                }
            }
        }

        override fun onCancelled(databaseError: DatabaseError) {
            Log.e(TAG, "Error reading from Firebase Database", databaseError.toException())
        }
    }

    @SuppressLint("MissingPermission")
    private fun makePhoneCall(context: Context, contactNameOrNumber: String, phoneNumber: String) {
        // Check if permission to make phone calls has been granted
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission not granted, do nothing
            Log.d(TAG, "Permission to make phone calls not granted")
            return
        }

        // Permission granted, make the phone call
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneNumber"))
        context.startActivity(intent)
    }

    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 2 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            firebaseDatabaseHelper.addValueEventListener(valueEventListener)
        }
    }
}