package com.example.flowcopy.play

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flowcopy.R
import com.example.flowcopy.databinding.ActivityMenuBinding
import com.example.flowcopy.databinding.ActivityMenuPlayBinding
import com.example.flowcopy.play.games.FifthGameActivity
import com.example.flowcopy.play.games.FirstGameActivity
import com.example.flowcopy.play.games.FourthGameActivity
import com.example.flowcopy.play.games.SecondGameActivity
import com.example.flowcopy.play.games.ThirdGameActivity

class MenuPlayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuPlayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backView.setOnClickListener(){
            finish()
        }

        binding.btnGame1.setOnClickListener(){
            val intent = Intent(this, FirstGameActivity::class.java)
            startActivity(intent)
        }
        binding.btnGame2.setOnClickListener(){
            val intent = Intent(this, SecondGameActivity::class.java)
            startActivity(intent)
        }
        binding.btnGame3.setOnClickListener(){
            val intent = Intent(this, ThirdGameActivity::class.java)
            startActivity(intent)
        }
        binding.btnGame4.setOnClickListener(){
            val intent = Intent(this, FourthGameActivity::class.java)
            startActivity(intent)
        }
        binding.btnGame5.setOnClickListener(){
            val intent = Intent(this, FifthGameActivity::class.java)
            startActivity(intent)
        }
    }
}