package com.example.flowcopy

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.flowcopy.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        // Verificar se a versão do Android é igual ou superior a Lollipop (API 21)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Definir a cor desejada para a barra de notificação
            window.statusBarColor = getColor(R.color.black) // Substitua "sua_cor" pelo ID real da sua cor
        }


        val intent = Intent(this, MenuActivity::class.java)
        binding.text.postDelayed({startActivity(intent)}, 1250)
        binding.text.postDelayed({finish()}, 1350)
    }
}