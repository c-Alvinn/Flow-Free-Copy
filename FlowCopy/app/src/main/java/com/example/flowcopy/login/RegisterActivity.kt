package com.example.flowcopy.login

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.example.flowcopy.DAO.Conta
import com.example.flowcopy.DAO.DAO_Conta
import com.example.flowcopy.DAO.MyDataBaseHelper
import com.example.flowcopy.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


        //Carregamento dos dados do banco
        val banco_contas = MyDataBaseHelper(applicationContext)
        val operacoesBanco = DAO_Conta(banco_contas)

        binding.backView.setOnClickListener(){
            finish()
        }

        binding.buttonRegister.setOnClickListener {

            fecharTeclado()

            val username: String = binding.editTextUsername.text.toString()
            val email: String = binding.editTextEmail.text.toString()
            val password: String = binding.editTextPassword.text.toString()
            val id = operacoesBanco.retornarUltimoID() + 1

            if (username != "" && email != "" && password != "") {
                //Inserção da nova Conta no banco de dados.
                if (operacoesBanco.inserirConta(Conta(id,username,email,password,0))){
                    limparCampos(binding.editTextUsername,binding.editTextEmail, binding.editTextPassword)
                    val msg = "Conta criada com sucesso!"
                    binding.popUp.text = msg
                    binding.popUp.visibility = View.VISIBLE
                    binding.popUp.postDelayed({binding.popUp.visibility = View.INVISIBLE},2000)
                    binding.popUp.postDelayed({finish()},2000)
                }else {
                    val msg = "Erro na Inserção.\nTente novamente."
                    binding.popUp.text = msg
                    binding.popUp.visibility = View.VISIBLE
                    binding.popUp.postDelayed({binding.popUp.visibility = View.INVISIBLE},2000)
                }

            }else {
                val msg = "Algo está incorreto.\nVerifique e tente novamente."
                binding.popUp.text = msg
                binding.popUp.visibility = View.VISIBLE
                binding.popUp.postDelayed({binding.popUp.visibility = View.INVISIBLE},2000)
            }

        }
    }

    private fun limparCampos(campo1 : EditText, campo2 : EditText, campo3 : EditText){
        campo1.text.clear()
        campo2.text.clear()
        campo3.text.clear()
    }

    private fun fecharTeclado() {
        val view: View? = currentFocus
        if (view != null) {
            val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}