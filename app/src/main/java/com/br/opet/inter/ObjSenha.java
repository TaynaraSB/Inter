package com.br.opet.inter;

public class ObjSenha {

    private String senhaExibida;

    public ObjSenha() {

    }

    public String getSenhaExibida() {
        return senhaExibida;
    }

    public void setSenhaExibida(String senhaExibida) {
        this.senhaExibida = senhaExibida;
    }

    @Override
    public String toString() {
        return  senhaExibida
               ;
    }
}
