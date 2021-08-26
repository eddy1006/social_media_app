package com.example.socialmediaapp.daos

import com.example.socialmediaapp.models.users
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class usersdao {
    val db = FirebaseFirestore.getInstance()
    val userscollection = db.collection("users")

    fun adduser(user : users?){
        user?.let {
            GlobalScope.launch(Dispatchers.IO){
                userscollection.document(user.uid).set(it)
            }
        }
    }

    fun getUserById(uId: String): Task<DocumentSnapshot> {
        return userscollection.document(uId).get()
    }
}