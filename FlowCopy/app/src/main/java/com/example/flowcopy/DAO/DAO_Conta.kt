package com.example.flowcopy.DAO

import android.content.ContentValues
import android.util.Log

class DAO_Conta(banco : MyDataBaseHelper)
{
    var banco : MyDataBaseHelper
    init {
        this.banco = banco
    }
    fun inserirConta(conta : Conta){
        val db_insercao = this.banco.writableDatabase
        var valores = ContentValues().apply{
            put("id", conta.id)
            put("nome", conta.nome)
            put("senha", conta.senha)
        }
        val confirmaInsercao = db_insercao?.insert("Conta",  null, valores)
        Log.i("Teste","Inserção: "+confirmaInsercao)
    }
    fun mostrarContas(): List<Conta>{
        val listaContas = ArrayList<Conta>()
        val db_leitura = this.banco.readableDatabase
        var cursor = db_leitura.rawQuery("select * from Conta",null)
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow("id"))
                val nome = getString(getColumnIndexOrThrow("nome"))
                val senha = getString(getColumnIndexOrThrow("senha"))
                Log.i("Teste","ID: "+id+" - Nome: "+nome+" - Senha: "+senha)
                listaContas.add(Conta(id,nome,senha))
            }
        }
        cursor.close()
        return(listaContas)
    }
    fun atualizarConta(conta : Conta){
        val db_atualizacao = this.banco.writableDatabase
        var valores = ContentValues().apply{
            put("nome", conta.nome)
            put("senha", conta.senha)
        }
        val condicao = "id = "+conta.id
        val confirmaAtualizacao = db_atualizacao.update("Conta", valores, condicao, null)
        Log.i("Teste", "Atualização: $confirmaAtualizacao")
    }
    fun excluirConta(conta : Conta){
        val db_exclusao = this.banco.readableDatabase
        val condicao = "id = "+conta.id
        val confirmaExclusao = db_exclusao.delete("Conta", condicao, null)
        Log.i("Teste","Após a exclusão: "+confirmaExclusao)
    }

}
