package com.example.flowcopy.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.flowcopy.R

class RegisterActivity : AppCompatActivity() {

    private lateinit var closeBtn: ImageView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        closeBtn = findViewById(R.id.backView)

        closeBtn.setOnClickListener(){
            finish()
        }
    }
}