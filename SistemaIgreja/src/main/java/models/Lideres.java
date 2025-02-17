/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author temnq
 */
public class Lideres {
    private int ID;
    private String membros_nome;
    private int data_nascimento;
    private String membros_CPF;
    private String ministerioResponsavel;
    
    // Getters e Setters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMembros_nome() {
        return membros_nome;
    }

    public void setMembros_nome(String membros_nome) {
        this.membros_nome = membros_nome;
    }

    public int getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(int data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getMembros_CPF() {
        return membros_CPF;
    }

    public void setMembros_CPF(String membros_CPF) {
        this.membros_CPF = membros_CPF;
    }

    public String getMinisterioResponsavel() {
        return ministerioResponsavel;
    }

    public void setMinisterioResponsavel(String ministerioResponsavel) {
        this.ministerioResponsavel = ministerioResponsavel;
    }
    
    
}
