package com.example.socialmediaapp.daos

import com.example.socialmediaapp.models.posts
import com.example.socialmediaapp.models.users
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class postdao {

    val db = FirebaseFirestore.getInstance()
    var postCollection = db.collection("posts")
    private val auth = Firebase.auth

    fun addpost(text: String) {
        GlobalScope.launch {
            val currentuserid = auth.currentUser!!.uid
            val usersdao = usersdao()
            val user = usersdao.getUserById(currentuserid).await().toObject(users::class.java)!!

            val currentTime =  System.currentTimeMillis()
            val post = posts(text,user,currentTime)
            postCollection.document().set(post)
        }
    }

    private fun getPostById(postid : String) : Task<DocumentSnapshot>{
        return postCollection.document(postid).get()
    }

    fun updatelikes(postid:String){
        GlobalScope.launch {
            val currentuserid = auth.currentUser!!.uid
            val post = getPostById(postid).await().toObject(posts::class.java)!!
            val isLikedby = post.likedBy.contains(currentuserid)

            if(isLikedby){
                post.likedBy.remove(currentuserid)
            } else {
                post.likedBy.add(currentuserid)
            }
            postCollection.document(postid).set(post)
        }
    }
}