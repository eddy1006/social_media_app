package com.example.socialmediaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.socialmediaapp.daos.postdao
import kotlinx.android.synthetic.main.activity_post.*

class PostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        button.setOnClickListener{
            val text = editTextTextPersonName.text.toString().trim()
            if(text.isNotEmpty()){
                val postDao = postdao()
                postDao.addpost(text)
                finish()
            }else{
                Toast.makeText(this,"Please add something",Toast.LENGTH_SHORT).show()
            }
        }
    }
}