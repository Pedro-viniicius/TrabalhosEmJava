/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author temnq
 */
public class Ministerios {
    private int ID;
    private String nome;
    private String descricao;
    private String lider_membros_CPF;
    private String membros_CPF;
    private String atividade;
    
    // Construtor
    public Ministerios(int ID, String nome, String descricao, String lider_membros_CPF, String membros_CPF, String atividade) {
        this.ID = ID;
        this.nome = nome;
        this.descricao = descricao;
        this.lider_membros_CPF = lider_membros_CPF;
        this.membros_CPF = membros_CPF;
        this.atividade = atividade;
    }

    // Getters e Setters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLider_membros_CPF() {
        return lider_membros_CPF;
    }

    public void setLider_membros_CPF(String lider_membros_CPF) {
        this.lider_membros_CPF = lider_membros_CPF;
    }

    public String getMembros_CPF() {
        return membros_CPF;
    }

    public void setMembros_CPF(String membros_CPF) {
        this.membros_CPF = membros_CPF;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }
    
}
