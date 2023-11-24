package com.example.flowcopy.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flowcopy.DAO.Conta
import com.example.flowcopy.DAO.DAO_Conta
import com.example.flowcopy.DAO.MyDataBaseHelper
import com.example.flowcopy.databinding.ActivityAccountBinding

class AccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Carregamento dos dados do banco
        val banco_contas = MyDataBaseHelper(applicationContext)
        val operacoesBanco = DAO_Conta(banco_contas)

        val conta: Conta? = operacoesBanco.retornarContaLogada()
        if (conta != null){
            binding.username.text = conta.nome
        }

        binding.btnChange.setOnClickListener(){
            val intent = Intent(this, ChangeActivity::class.java)
            startActivity(intent)
        }

        binding.btnExit.setOnClickListener(){

        }

        binding.backView.setOnClickListener(){
            finish()
        }

    }
}