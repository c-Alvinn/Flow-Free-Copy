package com.example.flowcopy.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.flowcopy.DAO.Conta
import com.example.flowcopy.DAO.DAO_Conta
import com.example.flowcopy.DAO.MyDataBaseHelper
import com.example.flowcopy.R
import com.example.flowcopy.databinding.ActivityLoginBinding
import com.example.flowcopy.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Carregamento dos dados do banco
        val banco_contas = MyDataBaseHelper(applicationContext)
        val operacoesBanco = DAO_Conta(banco_contas)

        binding.backView.setOnClickListener(){
            finish()
        }

        binding.buttonRegister.setOnClickListener {
            val username: String = binding.editTextUsername.text.toString()
            val email: String = binding.editTextEmail.text.toString()
            val password: String = binding.editTextPassword.text.toString()

            if (username != "" && email != "" && password != "") {
                //Inserção da nova Conta no banco de dados.
                operacoesBanco.inserirConta(Conta(gerarID(),username,email,password,0))
                limparCampos(binding.editTextUsername,binding.editTextEmail, binding.editTextPassword)
                val msg = "Conta criada com sucesso!"
                binding.popUp.text = msg
                binding.popUp.visibility = View.VISIBLE
                binding.popUp.postDelayed({binding.popUp.visibility = View.INVISIBLE},2000)
            }else {
                val msg = "Algo está incorreto.\nVerifique e tente novamente."
                binding.popUp.text = msg
                binding.popUp.visibility = View.VISIBLE
                binding.popUp.postDelayed({binding.popUp.visibility = View.INVISIBLE},2000)
            }

        }
    }

    companion object {
        private var ultimoIdGerado = 0

        fun gerarID(): Int {
            ultimoIdGerado++
            return ultimoIdGerado
        }
    }

    fun limparCampos(campo1 : EditText, campo2 : EditText, campo3 : EditText){
        campo1.text.clear()
        campo2.text.clear()
        campo3.text.clear()
    }
}