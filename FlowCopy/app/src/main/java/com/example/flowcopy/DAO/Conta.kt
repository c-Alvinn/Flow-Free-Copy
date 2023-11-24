package com.example.flowcopy.DAO

class Conta(id: Int, nome: String, email : String, senha : String, logado : Int)
{
    var id : Int
    var nome : String
    var email : String
    var senha : String
    var logado : Int

    init {
        this.id = id
        this.nome = nome
        this.email = email
        this.senha = senha
        this.logado = logado
    }

    override fun toString(): String {
        return "Conta(id=$id, nome='$nome', email='$email', senha='$senha', logado=$logado)"
    }

}
