package com.br.opet.inter;

import java.util.UUID;

public class ObjSalao {

    private String nome,rua,bairro,cidade,numero,Uid,senhaChamada;

    public ObjSalao() {

    }

    public String getSenhaChamada() {
        return senhaChamada;
    }

    public void setSenhaChamada(String senhaChamada) {
        this.senhaChamada = senhaChamada;
    }

    @Override
    public String toString() {
        return nome;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }


}
