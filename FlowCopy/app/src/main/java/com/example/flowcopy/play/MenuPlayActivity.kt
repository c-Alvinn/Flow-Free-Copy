package com.example.flowcopy.play

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flowcopy.R
import com.example.flowcopy.databinding.ActivityMenuBinding
import com.example.flowcopy.databinding.ActivityMenuPlayBinding
import com.example.flowcopy.play.games.FirstGameActivity

class MenuPlayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuPlayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGame1.setOnClickListener(){
            val intent = Intent(this, FirstGameActivity::class.java)
            startActivity(intent)
        }
    }
}