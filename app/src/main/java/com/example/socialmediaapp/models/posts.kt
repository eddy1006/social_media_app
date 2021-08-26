package com.example.socialmediaapp.models

data class posts(val text:String = "",
                 val user:users = users(),
                 val createdAt: Long = 0L,
                 val likedBy: ArrayList<String> = ArrayList()
                 )
