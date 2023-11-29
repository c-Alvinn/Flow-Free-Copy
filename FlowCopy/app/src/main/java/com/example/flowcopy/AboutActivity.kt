package com.example.flowcopy

import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import com.example.flowcopy.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        // Verificar se a versão do Android é igual ou superior a Lollipop (API 21)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Definir a cor desejada para a barra de notificação
            window.statusBarColor = getColor(R.color.black) // Substitua "sua_cor" pelo ID real da sua cor
        }

        binding.alvaroLayout.setOnClickListener(){
            val url = "https://github.com/c-Alvinn"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        binding.gustavoLayout.setOnClickListener(){
            val url = "https://github.com/DevGustavus"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        binding.iftmLayout.setOnClickListener(){
            val url = "https://iftm.edu.br/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        binding.backView.setOnClickListener(){
            finish()
        }
    }
}