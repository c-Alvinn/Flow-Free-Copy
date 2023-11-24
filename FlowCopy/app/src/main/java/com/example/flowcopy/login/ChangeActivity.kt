package com.example.flowcopy.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flowcopy.databinding.ActivityChangeBinding

class ChangeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChangeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backView.setOnClickListener(){
            finish()
        }
    }
}