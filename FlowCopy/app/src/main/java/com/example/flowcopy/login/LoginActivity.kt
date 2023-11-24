package com.example.flowcopy.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import com.example.flowcopy.DAO.Conta
import com.example.flowcopy.DAO.DAO_Conta
import com.example.flowcopy.DAO.MyDataBaseHelper
import com.example.flowcopy.MenuActivity
import com.example.flowcopy.R
import com.example.flowcopy.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Carregamento dos dados do banco
        val banco_contas = MyDataBaseHelper(applicationContext)
        val operacoesBanco = DAO_Conta(banco_contas)

        binding.backView.setOnClickListener(){
            finish()
        }

        binding.buttonLogin.setOnClickListener {
            //Validar login.
            val username = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()
            if (operacoesBanco.validarLogin(username,password)) {
                limparCampos(binding.editTextUsername, binding.editTextPassword)
                val msg = "Olá $username!\nSeja bem-vindo!"
                binding.popUp.text = msg
                binding.popUp.visibility = View.VISIBLE
                binding.popUp.postDelayed({binding.popUp.visibility = View.INVISIBLE},2000)
            }else {
                val msg = "Login inválido.\nVerifique suas credenciais e tente novamente."
                binding.popUp.text = msg
                binding.popUp.visibility = View.VISIBLE
                binding.popUp.postDelayed({binding.popUp.visibility = View.INVISIBLE},2000)
            }

        }

        binding.buttonRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    fun limparCampos(campo1 : EditText, campo2 : EditText){
        campo1.text.clear()
        campo2.text.clear()
    }
}