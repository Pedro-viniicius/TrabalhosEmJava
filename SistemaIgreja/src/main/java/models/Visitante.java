package models;
import connection.DatabaseConnection;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class Visitante {
    private int ID;
    private String nome;
    private LocalDate data_visita;
    private String origem;
    private String telefone1;
    private String telefone2;
    private String interesse;

    // Construtor
    public Visitante(int ID, String nome, LocalDate data_visita, String origem, String telefone1, String telefone2, String interesse) {
        this.ID = ID;
        this.nome = nome;
        this.data_visita = data_visita;
        this.origem = origem;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.interesse = interesse;
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

    public LocalDate getData_visita() {
        return data_visita;
    }

    public void setData_visita(LocalDate data_visita) {
        this.data_visita = data_visita;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getInteresse() {
        return interesse;
    }

    public void setInteresse(String interesse) {
        this.interesse = interesse;
    }
    /**
     * Método estático para obter a quantidade de todos os visitantes.
     *
     * @return Quantidade total de visitantes.
     * @throws SQLException Se ocorrer um erro na consulta.
     */
    public static int obterQuantidadeTodosVisitantes() throws SQLException {
        String sql = "SELECT COUNT(*) FROM visitantes";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return 0;
    }

    /**
     * Método estático para obter a quantidade de visitantes no mês atual.
     *
     * @return Quantidade de visitantes no mês atual.
     * @throws SQLException Se ocorrer um erro na consulta.
     */
    public static int obterQuantidadeVisitantesMesAtual() throws SQLException {
        LocalDate hoje = LocalDate.now();
        int anoAtual = hoje.getYear();
        int mesAtual = hoje.getMonthValue();

        String sql = "SELECT COUNT(*) FROM visitantes WHERE YEAR(data_visita) = ? AND MONTH(data_visita) = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, anoAtual);
            statement.setInt(2, mesAtual);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        }
        return 0;
    }
}
