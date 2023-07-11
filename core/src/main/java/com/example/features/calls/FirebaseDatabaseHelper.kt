package com.example.features.calls


import com.google.firebase.database.*

class FirebaseDatabaseHelper {

    private val databaseReference = FirebaseDatabase.getInstance().reference.child("commands").ref.child("calls")

    fun addValueEventListener(listener: ValueEventListener) {
        databaseReference.addValueEventListener(listener)
    }
}