package com.example.flowcopy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flowcopy.databinding.ActivityMenuBinding
import com.example.flowcopy.login.LoginActivity
import com.example.flowcopy.play.MenuPlayActivity

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPlay.setOnClickListener(){
            val intent = Intent(this, MenuPlayActivity::class.java)
            startActivity(intent)
        }
        binding.btnAccount.setOnClickListener(){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}