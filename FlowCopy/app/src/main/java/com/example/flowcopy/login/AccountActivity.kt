package com.example.flowcopy.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flowcopy.databinding.ActivityAccountBinding

class AccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backView.setOnClickListener(){
            finish()
        }

    }
}