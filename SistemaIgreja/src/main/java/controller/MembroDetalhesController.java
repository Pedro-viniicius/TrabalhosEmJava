package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Membro;

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
        Membro membroAtualizado = new Membro(
                membroId,
                textFieldNome.getText(),
                textFieldCPF.getText(),
                textFieldDataNascimento.getText(),
                textFieldGenero.getText(),
                textFieldEstadoCivil.getText(),
                textFieldDataBatismo.getText(),
                textFieldMinisterio.getText(),
                textFieldStatus.getText()
        );

        membroAtualizado.atualizarNoBanco();

        // Exibe um alerta informando que deu certo
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText(null);
        alert.setContentText("Dados do membro atualizados com sucesso!");
        alert.showAndWait();

        // Atualiza a ListView na tela principal
        if (mainController != null) {
            mainController.atualizarLista();
        }

        // Fecha a janela atual
        Stage stage = (Stage) btnSalvar.getScene().getWindow();
        stage.close();
    }
}
