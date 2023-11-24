package com.example.flowcopy.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.flowcopy.DAO.Conta
import com.example.flowcopy.DAO.DAO_Conta
import com.example.flowcopy.DAO.MyDataBaseHelper
import com.example.flowcopy.databinding.ActivityChangeBinding

class ChangeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChangeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Carregamento dos dados do banco
        val banco_contas = MyDataBaseHelper(applicationContext)
        val operacoesBanco = DAO_Conta(banco_contas)

        val conta: Conta? = operacoesBanco.retornarContaLogada()
        if (conta != null){
            binding.editTextUsername.setText(conta.nome)
            binding.editTextEmail.setText(conta.email)
            binding.editTextPassword.setText(conta.senha)
        }

        binding.buttonChange.setOnClickListener(){
            val username = binding.editTextUsername.text.toString()
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()

            if (conta != null){
                if (username != "" && email != "" && password != ""){
                    conta.nome = username
                    conta.email = email
                    conta.senha = password

                    if (operacoesBanco.atualizarConta(conta)) {
                        limparCampos(binding.editTextUsername,binding.editTextEmail, binding.editTextPassword)
                        val msg = "Conta criada com sucesso!"
                        binding.popUp.text = msg
                        binding.popUp.visibility = View.VISIBLE
                        binding.popUp.postDelayed({binding.popUp.visibility = View.INVISIBLE},2000)
                    }else {
                        val msg = "Erro no Update.\nTente novamente."
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

        binding.backView.setOnClickListener(){
            finish()
        }
    }

    fun limparCampos(campo1 : EditText, campo2 : EditText, campo3 : EditText){
        campo1.text.clear()
        campo2.text.clear()
        campo3.text.clear()
    }
}