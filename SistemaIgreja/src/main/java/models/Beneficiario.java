package models;

import connection.DatabaseConnection;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Beneficiario {
    private int ID;
    private String nome;
    private String foto;
    private String tipo_beneficio;
    private LocalDate data_recebimento;
    private String frequencia;
    private String necessidade;
    private String status;

    // Construtor
    public Beneficiario(int ID, String nome, String foto, String tipo_beneficio, LocalDate data_recebimento, String frequencia, String necessidade, String status) {
        this.ID = ID;
        this.nome = nome;
        this.foto = foto;
        this.tipo_beneficio = tipo_beneficio;
        this.data_recebimento = data_recebimento;
        this.frequencia = frequencia;
        this.necessidade = necessidade;
        this.status = status;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTipo_beneficio() {
        return tipo_beneficio;
    }

    public void setTipo_beneficio(String tipo_beneficio) {
        this.tipo_beneficio = tipo_beneficio;
    }

    public LocalDate getData_recebimento() {
        return data_recebimento;
    }

    public void setData_recebimento(LocalDate data_recebimento) {
        this.data_recebimento = data_recebimento;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public String getNecessidade() {
        return necessidade;
    }

    public void setNecessidade(String necessidade) {
        this.necessidade = necessidade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Método estático para obter a quantidade de beneficiários.
     *
     * @return Quantidade de beneficiários.
     * @throws SQLException Se ocorrer um erro na consulta.
     */
    public static int obterQuantidadeTodosBeneficiarios() {
        String sql = "SELECT COUNT(*) FROM beneficiarios";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter a quantidade de beneficiários: " + e.getMessage());
        }
        return 0;
    }

    public static int obterQuantidadeBeneficiariosAtivos() {
        String sql = "SELECT COUNT(*) FROM beneficiarios WHERE status = 'ativo'";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter a quantidade de beneficiários ativos: " + e.getMessage());
        }
        return 0;
    }
}
