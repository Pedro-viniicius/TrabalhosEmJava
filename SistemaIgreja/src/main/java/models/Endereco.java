/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author temnq
 */
public class Endereco {
    private int membros_ID;
    private String membros_CPF;
    private String rua;
    private String bairro;
    private String cidade;
    private long CEP;
    private String pontoReferencia;
    
        // Construtor
    public Endereco(int membros_ID, String membros_CPF, String rua, String bairro, String cidade, long CEP, String pontoReferencia) {
        this.membros_ID = membros_ID;
        this.membros_CPF = membros_CPF;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.CEP = CEP;
        this.pontoReferencia = pontoReferencia;
    }

    // Getters e Setters
    public int getMembros_ID() {
        return membros_ID;
    }

    public void setMembros_ID(int membros_ID) {
        this.membros_ID = membros_ID;
    }

    public String getMembros_CPF() {
        return membros_CPF;
    }

    public void setMembros_CPF(String membros_CPF) {
        this.membros_CPF = membros_CPF;
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

    public long getCEP() {
        return CEP;
    }

    public void setCEP(long CEP) {
        this.CEP = CEP;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
    }
}
