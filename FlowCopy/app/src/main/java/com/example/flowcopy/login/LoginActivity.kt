package com.example.flowcopy.login

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.example.flowcopy.DAO.Conta
import com.example.flowcopy.DAO.DAO_Conta
import com.example.flowcopy.DAO.MyDataBaseHelper
import com.example.flowcopy.MenuActivity
import com.example.flowcopy.R
import com.example.flowcopy.databinding.ActivityLoginBinding
import com.example.flowcopy.play.games.FirstGameActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
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


        binding.buttonLogin.setOnClickListener {

            fecharTeclado()

            //Validar login.
            val username = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()

            if (username != "" && password != ""){
                try {
                    if (operacoesBanco.validarLogin(username,password)) {
                        val conta : Conta? = operacoesBanco.retornarConta(username,password)
                        if (operacoesBanco.logarConta(conta!!)){
                            limparCampos(binding.editTextUsername, binding.editTextPassword)
                            val msg = "Olá $username!\nSeja bem-vindo!"
                            binding.popUp.text = msg
                            binding.popUp.visibility = View.VISIBLE
                            binding.popUp.postDelayed({binding.popUp.visibility = View.INVISIBLE},2000)
                            val intent = Intent(this, MenuActivity::class.java)
                            binding.root.postDelayed({startActivity(intent)}, 2200)
                            binding.root.postDelayed({finish()}, 2200)
                        }else {
                            val msg = "Erro ao executar o Login\nTente novamente."
                            binding.popUp.text = msg
                            binding.popUp.visibility = View.VISIBLE
                            binding.popUp.postDelayed(
                                { binding.popUp.visibility = View.INVISIBLE },
                                2000
                            )
                        }

                    }else {
                        val msg = "Erro ao executar o Login\nTente novamente."
                        binding.popUp.text = msg
                        binding.popUp.visibility = View.VISIBLE
                        binding.popUp.postDelayed({binding.popUp.visibility = View.INVISIBLE},2000)
                    }
                }catch (e: Exception) {
                    val msg = "Erro ao executar o Login\nTente novamente."
                    binding.popUp.text = msg
                    binding.popUp.visibility = View.VISIBLE
                    binding.popUp.postDelayed({binding.popUp.visibility = View.INVISIBLE},2000)
                }
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

    private fun limparCampos(campo1 : EditText, campo2 : EditText){
        campo1.text.clear()
        campo2.text.clear()
    }

    private fun fecharTeclado() {
        val view: View? = currentFocus
        if (view != null) {
            val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}