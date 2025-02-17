package models;

import connection.DatabaseConnection;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Membro {
    private int id;
    private String nome;
    private String CPF;
    private String dataNascimento;
    private String genero;
    private String estadoCivil;
    private String dataBatismo;
    private String ministerio;
    private String status;

    // Construtor
    public Membro(int id, String nome, String CPF, String dataNascimento, String genero, String estadoCivil, String dataBatismo, String ministerio, String status) {
        this.id = id;
        this.nome = nome;
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.estadoCivil = estadoCivil;
        this.dataBatismo = dataBatismo;
        this.ministerio = ministerio;
        this.status = status;
    }
    public Membro( String nome, String CPF, String dataNascimento, String genero, String estadoCivil, String dataBatismo, String ministerio, String status) {
        this.nome = nome;
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.estadoCivil = estadoCivil;
        this.dataBatismo = dataBatismo;
        this.ministerio = ministerio;
        this.status = status;
    }

    public Membro() {
        this.id = 0;
        this.nome = "";
        this.CPF = "";
        this.dataNascimento = "";
        this.genero = "";
        this.estadoCivil = "";
        this.dataBatismo = "";
        this.ministerio = "";
        this.status = "";
    }

    // Getters e Setters
    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getDataBatismo() {
        return dataBatismo;
    }

    public void setDataBatismo(String dataBatismo) {
        this.dataBatismo = dataBatismo;
    }

    public String getMinisterio() {
        return ministerio;
    }

    public void setMinisterio(String ministerio) {
        this.ministerio = ministerio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Método para calcular idade
    private int calcularIdade(String dataNascimento) {
        try {
            if (dataNascimento == null || dataNascimento.isEmpty()) {
                return 0;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dataNasc = LocalDate.parse(dataNascimento, formatter);
            return Period.between(dataNasc, LocalDate.now()).getYears();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

public Map<String, Map<String, Integer>> buscarDistribuicaoIdadePorGenero() {
    Map<String, Map<String, Integer>> distribuicao = new HashMap<>();
    String query = "SELECT genero, TIMESTAMPDIFF(YEAR, dataNascimento, CURDATE()) AS idade FROM membros";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement pstmt = connection.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {
            String genero = rs.getString("genero");
            int idade = rs.getInt("idade");
            String faixaEtaria = determinarFaixaEtaria(idade);

            distribuicao.putIfAbsent(genero, new HashMap<>());
            Map<String, Integer> faixas = distribuicao.get(genero);
            faixas.put(faixaEtaria, faixas.getOrDefault(faixaEtaria, 0) + 1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return distribuicao;
}

    // Método para determinar a faixa etária
    private String determinarFaixaEtaria(int idade) {
        if (idade >= 0 && idade <= 18) {
            return "0-18";
        } else if (idade >= 19 && idade <= 35) {
            return "19-35";
        } else if (idade >= 36 && idade <= 50) {
            return "36-50";
        } else {
            return "51+";
        }
    }

    // Método para contar membros ativos
    public static int obterQuantidadeMembrosAtivos() throws SQLException {
        String sql = "SELECT COUNT(*) FROM membros WHERE status = 'Ativo'";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            return resultSet.next() ? resultSet.getInt(1) : 0;
        }
    }

    // Método para contar membros inativos
    public static int obterQuantidadeMembrosInativos() throws SQLException {
        String sql = "SELECT COUNT(*) FROM membros WHERE status = 'Inativo'";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            return resultSet.next() ? resultSet.getInt(1) : 0;
        }
    }
    
    public static Map<Integer, String> buscarNomeEIdDosMembrosOrdenadosPorNome() {
        Map<Integer, String> membros = new LinkedHashMap<>();
        // Consulta que retorna somente o ID e o nome, já ordenados pelo nome.
        String sql = "SELECT id, nome FROM membros ORDER BY nome ASC";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                membros.put(id, nome);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return membros;
    }
    public static List<Membro> buscarTodosOsMembrosOrdenadosPorNome() {
    List<Membro> membros = new ArrayList<>();

        // Consulta para buscar todos os dados dos membros, ordenados pelo nome
        String sql = "SELECT id, nome, CPF, dataNascimento, genero, estadoCivil, dataBatismo, ministerio, status FROM membros ORDER BY nome ASC";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Membro membro = new Membro(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("CPF"),
                    rs.getString("dataNascimento"),
                    rs.getString("genero"),
                    rs.getString("estadoCivil"),
                    rs.getString("dataBatismo"),
                    rs.getString("ministerio"),
                    rs.getString("status")
                );
                membros.add(membro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return membros;
    }

    
    /**
     * Retorna uma ObservableList formatada para exibição na ListView.
     */
    public static ObservableList<String> carregarMembros() {
        Map<Integer, String> membrosMap = buscarNomeEIdDosMembrosOrdenadosPorNome();
        return formatarParaListView(membrosMap);
    }
    
    
    public static ObservableList<String> carregar() {
    List<Membro> membros = buscarTodosOsMembrosOrdenadosPorNome();
    return formatarParaListView(membros);
    }
    private static ObservableList<String> formatarParaListView(List<Membro> membros) {
    ObservableList<String> listaFormatada = FXCollections.observableArrayList();

    for (Membro membro : membros) {
        String item = String.format("%s (ID: %d) - CPF: %s - Nascimento: %s - Gênero: %s - Estado Civil: %s - Batismo: %s - Ministério: %s - Status: %s",
                membro.getNome(), membro.getId(), membro.getCPF(), membro.getDataNascimento(), 
                membro.getGenero(), membro.getEstadoCivil(), membro.getDataBatismo(), 
                membro.getMinisterio(), membro.getStatus());
        
        listaFormatada.add(item);
    }
    
    return listaFormatada;
}


    /**
     * Filtra os membros pelo nome e retorna uma ObservableList filtrada.
     */
    public static ObservableList<String> filtrarMembros(String pesquisa) {
        Map<Integer, String> membrosMap = buscarNomeEIdDosMembrosOrdenadosPorNome();
        Map<Integer, String> membrosFiltrados = new LinkedHashMap<>();

        pesquisa = pesquisa.trim().toLowerCase();

        if (pesquisa.isEmpty()) {
            return formatarParaListView(membrosMap);
        }

        for (Map.Entry<Integer, String> entry : membrosMap.entrySet()) {
            String idString = String.valueOf(entry.getKey()); // Converte o ID para string
            String nome = entry.getValue().toLowerCase();

            // Verifica se a pesquisa corresponde ao nome ou ao ID
            if (nome.contains(pesquisa) || idString.equals(pesquisa)) {
                membrosFiltrados.put(entry.getKey(), entry.getValue());
            }
        }

        return formatarParaListView(membrosFiltrados);
    }


    /**
     * Converte um mapa de membros (ID -> Nome) para uma ObservableList formatada.
     */
    private static ObservableList<String> formatarParaListView(Map<Integer, String> membros) {
        ObservableList<String> membrosList = FXCollections.observableArrayList();
        for (Map.Entry<Integer, String> entry : membros.entrySet()) {
            membrosList.add(entry.getKey() + " - " + entry.getValue());
        }
        return membrosList;
    }
        public static Membro buscarMembroPorId(int id) {
        String sql = "SELECT * FROM membros WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Membro(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("CPF"),
                    rs.getString("dataNascimento"),
                    rs.getString("genero"),
                    rs.getString("estadoCivil"),
                    rs.getString("dataBatismo"),
                    rs.getString("ministerio"),
                    rs.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
        // Método para adicionar um novo membro ao banco de dados
    // Adiciona uma lista de membros ao banco de dados
    public static void adicionarMembros(List<Membro> membros) {
        String query = "INSERT INTO membros (nome, CPF, dataNascimento, genero, estadoCivil, dataBatismo, ministerio, status) " +
                       "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                for (Membro membro : membros) {    
                    stmt.setString(1, membro.getNome());
                    stmt.setString(2, membro.getCPF());
                    stmt.setDate(3, java.sql.Date.valueOf(membro.getDataNascimento()));
                    stmt.setString(4, membro.getGenero());
                    stmt.setString(5, membro.getEstadoCivil());
                    stmt.setDate(6, java.sql.Date.valueOf(membro.getDataBatismo()));
                    stmt.setString(7, membro.getMinisterio());
                    stmt.setString(8, membro.getStatus());

                    stmt.addBatch();  // Adiciona à lista de comandos a serem executados em lote
                }
                stmt.executeBatch();  // Executa todas as inserções de uma vez
            } catch (SQLException e) {
                e.printStackTrace();
                throw new SQLException("Erro ao inserir membros no banco de dados.", e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro de conexão com o banco de dados.", e);
        }
    }

    public boolean atualizarNoBanco() {
        String sql = "UPDATE membros SET nome = ?, CPF = ?, dataNascimento = ?, genero = ?, estadoCivil = ?, " +
                     "dataBatismo = ?, ministerio = ?, status = ? WHERE id = ?";
       boolean statuss = false;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, nome);
            ps.setString(2, CPF);
            ps.setString(3, dataNascimento);
            ps.setString(4, genero);
            ps.setString(5, estadoCivil);
            ps.setString(6, dataBatismo);
            ps.setString(7, ministerio);
            ps.setString(8, status);
            ps.setInt(9, id);

            ps.executeUpdate();
            return statuss = true;
        } catch (SQLException e) {
            e.printStackTrace();
            return statuss = false;
        }
    }
}