package com.example.flowcopy.DAO

class Conta(id: Int, nome: String, senha: String)
{
    var id : Int
    var nome : String
    var senha : String

    init {
        this.id = id
        this.nome = nome
        this.senha = senha
    }

    override fun toString(): String {
        return "Conta(id=$id, nome='$nome', senha=$senha)"
    }

}
