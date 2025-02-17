package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Membro;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class MembroDetalhesController {

    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldCPF;
    @FXML
    private TextField textFieldDataNascimento;
    @FXML
    private TextField textFieldGenero;
    @FXML
    private TextField textFieldEstadoCivil;
    @FXML
    private TextField textFieldDataBatismo;
    @FXML
    private TextField textFieldMinisterio;
    @FXML
    private TextField textFieldStatus;
    @FXML
    private Button btnSalvar;

    private int membroId;
    private MembrosController mainController; // Referência ao controlador principal

    public void carregarMembro(int id, MembrosController controller) {
        this.membroId = id;
        this.mainController = controller; // Guarda referência para atualizar a lista

        Membro membro = Membro.buscarMembroPorId(id);
        if (membro != null) {
            textFieldNome.setText(membro.getNome());
            textFieldCPF.setText(membro.getCPF());
            textFieldDataNascimento.setText(membro.getDataNascimento());
            textFieldGenero.setText(membro.getGenero());
            textFieldEstadoCivil.setText(membro.getEstadoCivil());
            textFieldDataBatismo.setText(membro.getDataBatismo());
            textFieldMinisterio.setText(membro.getMinisterio());
            textFieldStatus.setText(membro.getStatus());
        }

        btnSalvar.setOnAction(event -> salvarAlteracoes());
    }

    private void salvarAlteracoes() {
        String nome = textFieldNome.getText().trim();
        String cpf = textFieldCPF.getText().trim();
        String dataNascimento = textFieldDataNascimento.getText().trim();
        String genero = textFieldGenero.getText().trim();
        String estadoCivil = textFieldEstadoCivil.getText().trim();
        String dataBatismo = textFieldDataBatismo.getText().trim();
        String ministerio = textFieldMinisterio.getText().trim();
        String status = textFieldStatus.getText().trim();

        // Validação de campos obrigatórios
        if (nome.isEmpty() || cpf.isEmpty()) {
            mostrarAlerta("Erro", "Os campos Nome e CPF são obrigatórios!", Alert.AlertType.ERROR);
            return;
        }

        // Validação do CPF (deve conter exatamente 11 dígitos numéricos)
        if (!cpf.matches("\\d{11}")) {
            mostrarAlerta("Erro", "O CPF deve conter exatamente 11 dígitos numéricos!", Alert.AlertType.ERROR);
            return;
        }

        // Validação das datas (se fornecidas)
        LocalDate dataNascimentoFormatada = null;
        LocalDate dataBatismoFormatada = null;

        if (!dataNascimento.isEmpty()) {
            try {
                dataNascimentoFormatada = LocalDate.parse(dataNascimento);
            } catch (DateTimeParseException e) {
                mostrarAlerta("Erro", "Formato de Data de Nascimento inválido! Use o formato YYYY-MM-DD.", Alert.AlertType.ERROR);
                return;
            }
        }

        if (!dataBatismo.isEmpty()) {
            try {
                dataBatismoFormatada = LocalDate.parse(dataBatismo);
            } catch (DateTimeParseException e) {
                mostrarAlerta("Erro", "Formato de Data de Batismo inválido! Use o formato YYYY-MM-DD.", Alert.AlertType.ERROR);
                return;
            }
        }

        // Validação de valores permitidos
        if (!genero.equalsIgnoreCase("Masculino") && !genero.equalsIgnoreCase("Feminino")) {
            mostrarAlerta("Erro", "O gênero deve ser 'Masculino' ou 'Feminino'.", Alert.AlertType.ERROR);
            return;
        }

        if (!estadoCivil.equalsIgnoreCase("Solteiro") && !estadoCivil.equalsIgnoreCase("Casado") && !estadoCivil.equalsIgnoreCase("Viúvo")) {
            mostrarAlerta("Erro", "O estado civil deve ser 'Solteiro', 'Casado' ou 'Viúvo'.", Alert.AlertType.ERROR);
            return;
        }

        if (!status.equalsIgnoreCase("Ativo") && !status.equalsIgnoreCase("Inativo") && 
            !status.equalsIgnoreCase("Afastado") && !status.equalsIgnoreCase("Transferido") && 
            !status.equalsIgnoreCase("Jubilado")) {
            mostrarAlerta("Erro", "O status deve ser 'Ativo', 'Inativo', 'Afastado', 'Transferido' ou 'Jubilado'.", Alert.AlertType.ERROR);
            return;
        }

        // Criando objeto Membro atualizado
        Membro membroAtualizado = new Membro(
                membroId,
                nome,
                cpf,
                dataNascimentoFormatada != null ? dataNascimentoFormatada.toString() : null,
                genero,
                estadoCivil,
                dataBatismoFormatada != null ? dataBatismoFormatada.toString() : null,
                ministerio,
                status
        );

        boolean atualizado = membroAtualizado.atualizarNoBanco();

        if (atualizado) {
            mostrarAlerta("Sucesso", "Dados do membro atualizados com sucesso!", Alert.AlertType.INFORMATION);

            // Atualiza a ListView na tela principal
            if (mainController != null) {
                mainController.atualizarLista();
            }

            // Fecha a janela atual
            Stage stage = (Stage) btnSalvar.getScene().getWindow();
            stage.close();
        } else {
            mostrarAlerta("Erro", "Não foi possível atualizar os dados do membro.", Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
