package com.example.flowcopy.DAO

import android.content.ContentValues
import android.util.Log

class DAO_Conta(banco : MyDataBaseHelper)
{
    var banco : MyDataBaseHelper
    init {
        this.banco = banco
    }
    fun inserirConta(conta : Conta): Boolean{
        val db_insercao = this.banco.writableDatabase
        val valores = ContentValues().apply{
            put("id", conta.id)
            put("nome", conta.nome)
            put("email", conta.email)
            put("senha", conta.senha)
        }
        val confirmaInsercao = db_insercao?.insert("Conta",  null, valores)
        Log.i("Teste","Inserção: "+confirmaInsercao)
        if (confirmaInsercao?.toInt()!! <= 0){
            return false
        }
        return true
    }
    fun mostrarContas(): List<Conta>{
        val listaContas = ArrayList<Conta>()
        val db_leitura = this.banco.readableDatabase
        val cursor = db_leitura.rawQuery("select * from Conta",null)
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow("id"))
                val nome = getString(getColumnIndexOrThrow("nome"))
                val email = getString(getColumnIndexOrThrow("email"))
                val senha = getString(getColumnIndexOrThrow("senha"))
                val logado = getInt(getColumnIndexOrThrow("logado"))
                android.util.Log.i("Teste","ID: "+id+" - Nome: "+nome+ " - Email: "+email+ " - Senha: "+senha+" - Logado: "+logado)
                listaContas.add(Conta(id,nome,email,senha,logado))
            }
        }
        cursor.close()
        return(listaContas)
    }
    fun atualizarConta(conta : Conta): Boolean{
        val db_atualizacao = this.banco.writableDatabase
        val valores = ContentValues().apply{
            put("nome", conta.nome)
            put("email", conta.email)
            put("senha", conta.senha)
            put("logado", conta.logado)
        }
        val condicao = "id = "+conta.id
        val confirmaAtualizacao = db_atualizacao.update("Conta", valores, condicao, null)
        Log.i("Teste", "Atualização: $confirmaAtualizacao")
        if (confirmaAtualizacao <= 0){
            return false
        }
        return true
    }
    fun excluirConta(conta : Conta){
        val db_exclusao = this.banco.readableDatabase
        val condicao = "id = "+conta.id
        val confirmaExclusao = db_exclusao.delete("Conta", condicao, null)
        Log.i("Teste","Após a exclusão: "+confirmaExclusao)
    }
    fun validarLogin(username : String, password : String): Boolean{
        var flag : Boolean = true
        val db_leitura = this.banco.readableDatabase
        val cursor = db_leitura.rawQuery("select * from Conta",null)
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow("id"))
                val nome = getString(getColumnIndexOrThrow("nome"))
                val email = getString(getColumnIndexOrThrow("email"))
                val senha = getString(getColumnIndexOrThrow("senha"))
                val logado = getInt(getColumnIndexOrThrow("logado"))
                android.util.Log.i("Teste","ID: "+id+" - Nome: "+nome+ " - Email: "+email+ " - Senha: "+senha+" - Logado: "+logado)

                if(nome == username && senha == password){
                    android.util.Log.i("Teste", "Usuario encontrado: " + nome + " " + senha)
                    flag = true
                    break
                }
                else{
                    android.util.Log.i("Teste", "Usuario não encontrado!!!!!")
                    flag = false
                }
            }
        }
        cursor.close()
        return flag
    }

    fun retornarConta(username : String, password : String): Conta? {
        var conta: Conta? = null
        val db_leitura = this.banco.readableDatabase
        val cursor = db_leitura.rawQuery("select * from Conta",null)
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow("id"))
                val nome = getString(getColumnIndexOrThrow("nome"))
                val email = getString(getColumnIndexOrThrow("email"))
                val senha = getString(getColumnIndexOrThrow("senha"))
                val logado = getInt(getColumnIndexOrThrow("logado"))
                android.util.Log.i("Teste","ID: "+id+" - Nome: "+nome+ " - Email: "+email+ " - Senha: "+senha+" - Logado: "+logado)

                if(nome == username && senha == password){
                    android.util.Log.i("Teste", "Usuario encontrado: " + nome + " " + senha)
                    conta = Conta(id,nome,email,senha,logado)
                    break
                }
            }
        }
        cursor.close()
        return conta
    }

    fun retornarContaLogada(): Conta? {
        var conta: Conta? = null
        val db_leitura = this.banco.readableDatabase
        val cursor = db_leitura.rawQuery("select * from Conta",null)
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow("id"))
                val nome = getString(getColumnIndexOrThrow("nome"))
                val email = getString(getColumnIndexOrThrow("email"))
                val senha = getString(getColumnIndexOrThrow("senha"))
                val logado = getInt(getColumnIndexOrThrow("logado"))
                android.util.Log.i("Teste","ID: "+id+" - Nome: "+nome+ " - Email: "+email+ " - Senha: "+senha+" - Logado: "+logado)

                if(logado == 1){
                    android.util.Log.i("Teste", "Usuario encontrado: " + nome + " ")
                    conta = Conta(id,nome,email,senha,logado)
                    break
                }
            }
        }
        cursor.close()
        return conta
    }

    fun logarConta(conta : Conta): Boolean {
        val db_atualizacao = this.banco.writableDatabase
        val db_leitura = this.banco.readableDatabase
        val cursor = db_leitura.rawQuery("select * from Conta",null)
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow("id"))
                val nome = getString(getColumnIndexOrThrow("nome"))
                val email = getString(getColumnIndexOrThrow("email"))
                val senha = getString(getColumnIndexOrThrow("senha"))
                val logado = getInt(getColumnIndexOrThrow("logado"))
                android.util.Log.i("Teste","ID: "+id+" - Nome: "+nome+ " - Email: "+email+ " - Senha: "+senha+" - Logado: "+logado)

                if(id == conta.id){
                    val valores = ContentValues().apply{
                        put("logado", 1)
                    }
                    val condicao = "id = "+conta.id
                    val confirmaAtualizacao = db_atualizacao.update("Conta", valores, condicao, null)
                    Log.i("Teste", "Atualização: $confirmaAtualizacao")
                    if (confirmaAtualizacao <= 0){
                        return false
                    }
                    break
                }
            }
        }
        cursor.close()
        return true
    }
    fun deslogarConta(conta : Conta): Boolean {
        val db_atualizacao = this.banco.writableDatabase
        val db_leitura = this.banco.readableDatabase
        val cursor = db_leitura.rawQuery("select * from Conta",null)
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow("id"))
                val nome = getString(getColumnIndexOrThrow("nome"))
                val email = getString(getColumnIndexOrThrow("email"))
                val senha = getString(getColumnIndexOrThrow("senha"))
                val logado = getInt(getColumnIndexOrThrow("logado"))
                android.util.Log.i("Teste","ID: "+id+" - Nome: "+nome+ " - Email: "+email+ " - Senha: "+senha+" - Logado: "+logado)

                if(id == conta.id){
                    val valores = ContentValues().apply{
                        put("logado", 0)
                    }
                    val condicao = "id = "+conta.id
                    val confirmaAtualizacao = db_atualizacao.update("Conta", valores, condicao, null)
                    Log.i("Teste", "Atualização: $confirmaAtualizacao")
                    if (confirmaAtualizacao <= 0){
                        return false
                    }
                    break
                }
            }
        }
        cursor.close()
        return true
    }
    fun retornarUltimoID(): Int{
        val db_leitura = this.banco.readableDatabase
        val cursor = db_leitura.rawQuery("select * from Conta",null)
        var ultimoId = 0
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow("id"))
                ultimoId = id
                android.util.Log.i("Teste","ID: "+id)
            }
        }
        cursor.close()
        return ultimoId
    }
}
