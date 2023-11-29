package com.example.flowcopy

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flowcopy.DAO.Conta
import com.example.flowcopy.DAO.DAO_Conta
import com.example.flowcopy.DAO.MyDataBaseHelper
import com.example.flowcopy.databinding.ActivityMenuBinding
import com.example.flowcopy.login.AccountActivity
import com.example.flowcopy.login.LoginActivity
import com.example.flowcopy.play.MenuPlayActivity

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


        //Carregamento dos dados do banco
        val banco_contas = MyDataBaseHelper(applicationContext)
        val operacoesBanco = DAO_Conta(banco_contas)

        val conta: Conta? = operacoesBanco.retornarContaLogada()

        if(conta == null){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnPlay.setOnClickListener(){
            val intent = Intent(this, MenuPlayActivity::class.java)
            startActivity(intent)
        }
        binding.btnAccount.setOnClickListener(){
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnAbout.setOnClickListener(){
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
    }
}