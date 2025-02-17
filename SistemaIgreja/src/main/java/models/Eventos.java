/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.time.LocalDate;

/**
 *
 * @author temnq
 */
public class Eventos {
    private int ID;
    private LocalDate data_fim;
    private LocalDate data_inicio;
    private String nome_Evento;
    private String local;
    private String objetivo;
    private String resultado;
    
        // Construtor
    public Eventos(int ID, LocalDate data_fim, LocalDate data_inicio, String nome_Evento, String local, String objetivo, String resultado) {
        this.ID = ID;
        this.data_fim = data_fim;
        this.data_inicio = data_inicio;
        this.nome_Evento = nome_Evento;
        this.local = local;
        this.objetivo = objetivo;
        this.resultado = resultado;
    }

    // Getters e Setters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public LocalDate getData_fim() {
        return data_fim;
    }

    public void setData_fim(LocalDate data_fim) {
        this.data_fim = data_fim;
    }

    public LocalDate getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(LocalDate data_inicio) {
        this.data_inicio = data_inicio;
    }

    public String getNome_Evento() {
        return nome_Evento;
    }

    public void setNome_Evento(String nome_Evento) {
        this.nome_Evento = nome_Evento;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
}
