package controller;

import connection.DatabaseConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CadastroController {

    @FXML
    private ComboBox<String> ComboBoxEstadoCivil;

    @FXML
    private ComboBox<String> ComboBoxGenero;

    @FXML
    private ComboBox<String> ComboBoxMinisterio;

    @FXML
    private ComboBox<String> ComboBoxStatus;

    @FXML
    private Button btnAdicionarNumero;

    @FXML
    private Button btnSalvar;

    @FXML
    private TextField textFieldBairro;

    @FXML
    private TextField textFieldCEP;

    @FXML
    private TextField textFieldCPF;

    @FXML
    private TextField textFieldCidade;

    @FXML
    private TextField textFieldDataBatismo;

    @FXML
    private TextField textFieldDataNascimento;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextField textFieldEstado;

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldNumero;

    @FXML
    private TextField textFieldRua;

    @FXML
    private TextField textFieldTelefone;

    @FXML
    private VBox vboxTelefones;

    @FXML
    public void initialize() {
        // Preenche os ComboBoxes com alguns exemplos
        ComboBoxEstadoCivil.getItems().addAll("Solteiro", "Casado", "Divorciado", "Viúvo");
        ComboBoxGenero.getItems().addAll("Masculino", "Feminino", "Outro");
        ComboBoxMinisterio.getItems().addAll("Louvor", "Comunhão", "Ensino", "Administração");
        ComboBoxStatus.getItems().addAll("Ativo", "Inativo");

        // Ao perder o foco do campo CEP, chama a API viaCEP para preencher o endereço
        textFieldCEP.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) { // Quando perde o foco
                String cep = textFieldCEP.getText().trim().replaceAll("[^0-9]", "");
                if (!cep.isEmpty() && cep.length() == 8) { // Valida que o CEP tem 8 dígitos
                    buscarEndereco(cep);
                } else {
                    System.out.println("CEP inválido!");
                }
            }
        });
    }

    private void buscarEndereco(String cep) {
        Task<Endereco> task = new Task<Endereco>() {
            @Override
            protected Endereco call() throws Exception {
                return buscaEnderecoPorCEP(cep);
            }
        };

        task.setOnSucceeded(event -> {
            Endereco endereco = task.getValue();
            if (endereco != null) {
                textFieldRua.setText(endereco.logradouro);
                textFieldBairro.setText(endereco.bairro);
                textFieldCidade.setText(endereco.localidade);
                textFieldEstado.setText(endereco.uf);
            } else {
                System.out.println("Endereço não encontrado para o CEP: " + cep);
            }
        });

        task.setOnFailed(event -> {
            System.out.println("Erro ao buscar endereço: " + task.getException());
        });

        new Thread(task).start();
    }

    private Endereco buscaEnderecoPorCEP(String cep) {
        Endereco endereco = null;
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://viacep.com.br/ws/" + cep + "/json/"))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String jsonString = response.body();
                // Se o JSON informar erro, retorna null
                if (jsonString.contains("\"erro\": true")) {
                    return null;
                }
                JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();

                endereco = new Endereco();
                endereco.logradouro = json.has("logradouro") && !json.get("logradouro").isJsonNull()
                        ? json.get("logradouro").getAsString() : "";
                endereco.bairro = json.has("bairro") && !json.get("bairro").isJsonNull()
                        ? json.get("bairro").getAsString() : "";
                endereco.localidade = json.has("localidade") && !json.get("localidade").isJsonNull()
                        ? json.get("localidade").getAsString() : "";
                endereco.uf = json.has("uf") && !json.get("uf").isJsonNull()
                        ? json.get("uf").getAsString() : "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return endereco;
    }

    @FXML
    private void handleBtnAdicionarNumero() {
        String telefone = textFieldTelefone.getText().trim();
        if (!telefone.isEmpty()) {
            Label labelTelefone = new Label(telefone);
            vboxTelefones.getChildren().add(labelTelefone);
            textFieldTelefone.clear();
        }
    }

    @FXML
    private void handleBtnSalvar() {
        // Coleta os dados dos campos da interface
        String nome = textFieldNome.getText().trim();
        String cpf = textFieldCPF.getText().trim();
        String dataNascimentoStr = textFieldDataNascimento.getText().trim();
        String dataBatismoStr = textFieldDataBatismo.getText().trim();
        String email = textFieldEmail.getText().trim();
        String cepStr = textFieldCEP.getText().trim();
        String rua = textFieldRua.getText().trim();
        String bairro = textFieldBairro.getText().trim();
        String cidade = textFieldCidade.getText().trim();
        String estado = textFieldEstado.getText().trim();
        String numeroStr = textFieldNumero.getText().trim();
        String estadoCivil = ComboBoxEstadoCivil.getValue();
        String genero = ComboBoxGenero.getValue();
        String ministerio = ComboBoxMinisterio.getValue();
        String status = ComboBoxStatus.getValue();

        // Valida e converte as datas (formato dd/MM/yyyy)
        java.sql.Date sqlDataNascimento = null;
        java.sql.Date sqlDataBatismo = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date parsedDataNascimento = sdf.parse(dataNascimentoStr);
            java.util.Date parsedDataBatismo = sdf.parse(dataBatismoStr);
            sqlDataNascimento = new java.sql.Date(parsedDataNascimento.getTime());
            sqlDataBatismo = new java.sql.Date(parsedDataBatismo.getTime());
        } catch (ParseException e) {
            System.err.println("Formato de data inválido. Utilize dd/MM/yyyy.");
            e.printStackTrace();
            return;
        }

        // Converte o número da casa para int
        int numero = 0;
        try {
            numero = Integer.parseInt(numeroStr);
        } catch (NumberFormatException e) {
            System.err.println("Número inválido. Insira um valor numérico.");
            return;
        }

        // Converte o CEP para long
        long cep = 0;
        try {
            cep = Long.parseLong(cepStr);
        } catch (NumberFormatException e) {
            System.err.println("CEP inválido. Insira somente números.");
            return;
        }

        // Abre a conexão com o banco de dados
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.err.println("Erro na conexão com o banco de dados.");
            return;
        }

        try {
            // Inicia a transação
            conn.setAutoCommit(false);

            // Insere os dados na tabela membros
            String sqlMembros = "INSERT INTO membros (CPF, dataBatismo, dataNascimento, estadoCivil, genero, ministerio, nome, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            int membroId = 0;
            try (PreparedStatement psMembros = conn.prepareStatement(sqlMembros, Statement.RETURN_GENERATED_KEYS)) {
                psMembros.setString(1, cpf);
                psMembros.setDate(2, sqlDataBatismo);
                psMembros.setDate(3, sqlDataNascimento);
                psMembros.setString(4, estadoCivil);
                psMembros.setString(5, genero);
                psMembros.setString(6, ministerio);
                psMembros.setString(7, nome);
                psMembros.setString(8, status);

                psMembros.executeUpdate();

                // Recupera o id gerado
                try (ResultSet rs = psMembros.getGeneratedKeys()) {
                    if (rs.next()) {
                        membroId = rs.getInt(1);
                    }
                }
            }

            // Insere os dados na tabela enderecos
            String sqlEnderecos = "INSERT INTO enderecos (bairro, CEP, cidade, estado, membros_CPF, membros_ID, numero, rua) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement psEnderecos = conn.prepareStatement(sqlEnderecos)) {
                psEnderecos.setString(1, bairro);
                psEnderecos.setLong(2, cep);
                psEnderecos.setString(3, cidade);
                psEnderecos.setString(4, estado);
                psEnderecos.setString(5, cpf);
                psEnderecos.setInt(6, membroId);
                psEnderecos.setInt(7, numero);
                psEnderecos.setString(8, rua);

                psEnderecos.executeUpdate();
            }

            // Insere os dados na tabela contatos para cada telefone adicionado
            String sqlContatos = "INSERT INTO contatos (email, membros_CPF, telefone) VALUES (?, ?, ?)";
            try (PreparedStatement psContatos = conn.prepareStatement(sqlContatos)) {
                for (Node node : vboxTelefones.getChildren()) {
                    if (node instanceof Label) {
                        String telefone = ((Label) node).getText();
                        psContatos.setString(1, email);
                        psContatos.setString(2, cpf);
                        psContatos.setString(3, telefone);
                        psContatos.addBatch();
                    }
                }
                psContatos.executeBatch();
            }

            // Confirma a transação
            conn.commit();
            System.out.println("Membro salvo com sucesso!");

            // Opcional: Limpa os campos da interface após o salvamento
            limparCampos();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
                System.err.println("Transação revertida devido a erro.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Limpa os campos da interface após o salvamento.
     */
    private void limparCampos() {
        textFieldNome.clear();
        textFieldCPF.clear();
        textFieldDataNascimento.clear();
        textFieldDataBatismo.clear();
        textFieldEmail.clear();
        textFieldCEP.clear();
        textFieldRua.clear();
        textFieldBairro.clear();
        textFieldCidade.clear();
        textFieldEstado.clear();
        textFieldNumero.clear();
        textFieldTelefone.clear();
        ComboBoxEstadoCivil.getSelectionModel().clearSelection();
        ComboBoxGenero.getSelectionModel().clearSelection();
        ComboBoxMinisterio.getSelectionModel().clearSelection();
        ComboBoxStatus.getSelectionModel().clearSelection();
        vboxTelefones.getChildren().clear();
    }

    // Classe auxiliar para representar o endereço retornado pela API viaCEP
    private static class Endereco {
        String logradouro;
        String bairro;
        String localidade;
        String uf;
    }
}
