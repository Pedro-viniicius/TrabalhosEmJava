/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author temnq
 */
public class Contatos {
    private long numero;
    private String membros_CPF;
    private String telefone;
    private String email;
    private int membros_ID;
    
        // Construtor
    public Contatos(long numero, String membros_CPF, String telefone, String email, int membros_ID) {
        this.numero = numero;
        this.membros_CPF = membros_CPF;
        this.telefone = telefone;
        this.email = email;
        this.membros_ID = membros_ID;
    }

    // Getters e Setters
    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public String getMembros_CPF() {
        return membros_CPF;
    }

    public void setMembros_CPF(String membros_CPF) {
        this.membros_CPF = membros_CPF;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMembros_ID() {
        return membros_ID;
    }

    public void setMembros_ID(int membros_ID) {
        this.membros_ID = membros_ID;
    }
}
