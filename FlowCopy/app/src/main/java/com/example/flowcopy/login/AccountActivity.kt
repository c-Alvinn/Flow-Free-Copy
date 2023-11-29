package com.example.flowcopy.login

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.flowcopy.DAO.Conta
import com.example.flowcopy.DAO.DAO_Conta
import com.example.flowcopy.DAO.MyDataBaseHelper
import com.example.flowcopy.MenuActivity
import com.example.flowcopy.R
import com.example.flowcopy.databinding.ActivityAccountBinding

class AccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        // Verificar se a versão do Android é igual ou superior a Lollipop (API 21)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Definir a cor desejada para a barra de notificação
            window.statusBarColor = getColor(R.color.black) // Substitua "sua_cor" pelo ID real da sua cor
        }

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
            if (operacoesBanco.deslogarConta(conta!!)){
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        binding.backView.setOnClickListener(){
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnRemove.setOnClickListener(){
            binding.popUpRemove.visibility = View.VISIBLE

            binding.btnYes.setOnClickListener(){
                operacoesBanco.excluirConta(operacoesBanco.retornarContaLogada()!!)
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            binding.btnNo.setOnClickListener(){
                binding.popUpRemove.visibility = View.INVISIBLE
            }
        }

    }
}