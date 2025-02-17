package controller;

import connection.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javafx.scene.control.Alert.AlertType;

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
    private TextField textFieldNome;

    @FXML
    private TextField textFieldCPF;

    @FXML
    private TextField textFieldDataNascimento;

    @FXML
    private TextField textFieldDataBatismo;

    @FXML
    private Button btnSalvar;

    @FXML
    public void initialize() {
        // Preenchendo os ComboBox com as opções especificadas
        ComboBoxMinisterio.setItems(FXCollections.observableArrayList(
            "Louvor e Adoração", "Pregação e Ensino", "Escola Bíblica Dominical",
            "Ministério Infantil", "Ministério de Adolescentes", "Ministério de Jovens",
            "Ministério de Casais", "Intercessão pelos Cultos", "Grupos de Oração",
            "Evangelismo Local", "Evangelismo em Hospitais e Presídios", "SAF (Sociedade Auxiliadora Feminina)"
        ));

        ComboBoxGenero.setItems(FXCollections.observableArrayList("Masculino", "Feminino"));

        ComboBoxEstadoCivil.setItems(FXCollections.observableArrayList("Solteiro", "Casado", "Viúvo"));

        ComboBoxStatus.setItems(FXCollections.observableArrayList("Ativo", "Inativo", "Afastado", "Transferido", "Jubilado"));

        // Adicionando evento ao botão salvar
        btnSalvar.setOnAction(event -> salvarMembro());
    }

    private void salvarMembro() {
        String nome = textFieldNome.getText().trim();
        String cpf = textFieldCPF.getText().trim();
        String dataNascimento = textFieldDataNascimento.getText().trim();
        String dataBatismo = textFieldDataBatismo.getText().trim();
        String ministerio = ComboBoxMinisterio.getValue();
        String genero = ComboBoxGenero.getValue();
        String estadoCivil = ComboBoxEstadoCivil.getValue();
        String status = ComboBoxStatus.getValue();

        // Validação de campos obrigatórios
        if (nome.isEmpty() || cpf.isEmpty()) {
            mostrarAlerta("Erro", "Os campos Nome e CPF são obrigatórios!", AlertType.ERROR);
            return;
        }

        // Validação do CPF (deve conter exatamente 11 dígitos numéricos)
        if (!cpf.matches("\\d{11}")) {
            mostrarAlerta("Erro", "O CPF deve conter exatamente 11 dígitos numéricos!", AlertType.ERROR);
            return;
        }

        // Validação das datas (se fornecidas)
        LocalDate dataNascimentoFormatada = null;
        LocalDate dataBatismoFormatada = null;

        if (!dataNascimento.isEmpty()) {
            try {
                dataNascimentoFormatada = LocalDate.parse(dataNascimento);
            } catch (DateTimeParseException e) {
                mostrarAlerta("Erro", "Formato de Data de Nascimento inválido! Use o formato YYYY-MM-DD.", AlertType.ERROR);
                return;
            }
        }

        if (!dataBatismo.isEmpty()) {
            try {
                dataBatismoFormatada = LocalDate.parse(dataBatismo);
            } catch (DateTimeParseException e) {
                mostrarAlerta("Erro", "Formato de Data de Batismo inválido! Use o formato YYYY-MM-DD.", AlertType.ERROR);
                return;
            }
        }

        // Validação de seleção nos ComboBox
        if (estadoCivil == null || genero == null || status == null) {
            mostrarAlerta("Erro", "Selecione opções válidas para Gênero, Estado Civil e Status.", AlertType.ERROR);
            return;
        }

        String sql = "INSERT INTO membros (nome, cpf, dataNascimento, dataBatismo, ministerio, genero, estadoCivil, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setObject(3, dataNascimentoFormatada); // Data de nascimento
            stmt.setObject(4, dataBatismoFormatada); // Data de batismo (pode ser null)
            stmt.setString(5, ministerio);
            stmt.setString(6, genero);
            stmt.setString(7, estadoCivil);
            stmt.setString(8, status);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                mostrarAlerta("Sucesso", "Membro cadastrado com sucesso!", AlertType.INFORMATION);
                limparCampos();
            } else {
                mostrarAlerta("Erro", "Não foi possível cadastrar o membro.", AlertType.ERROR);
            }
        } catch (SQLException e) {
            mostrarAlerta("Erro no Banco de Dados", "Erro ao salvar os dados: " + e.getMessage(), AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String titulo, String mensagem, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    private void limparCampos() {
        textFieldNome.clear();
        textFieldCPF.clear();
        textFieldDataNascimento.clear();
        textFieldDataBatismo.clear();
        ComboBoxMinisterio.getSelectionModel().clearSelection();
        ComboBoxGenero.getSelectionModel().clearSelection();
        ComboBoxEstadoCivil.getSelectionModel().clearSelection();
        ComboBoxStatus.getSelectionModel().clearSelection();
    }
}
