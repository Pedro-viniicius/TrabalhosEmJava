package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private Button btnLogin;

    @FXML
    private Label lblErro;

    // Método chamado quando o botão é pressionado
    @FXML
    private void handleLogin() {
        String usuario = txtUsuario.getText();
        String senha = txtSenha.getText();

        // Verificação simples de login
        if ("admin".equals(usuario) && "1234".equals(senha)) {
            lblErro.setText("Login realizado com sucesso!");

            // Carregar a próxima tela (Dashboard)
            carregarTelaDashboard();

        } else {
            lblErro.setText("Usuário ou senha inválidos!");
        }
    }

    private void carregarTelaDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/openjfx/sistemaigreja/Dashboard.fxml"));
            Parent root = loader.load();

            // Obter a referência do Stage atual
            Stage stage = (Stage) btnLogin.getScene().getWindow();

            // Criar uma nova cena com o Dashboard
            Scene scene = new Scene(root);

            // Definir a nova cena no Stage
            stage.setScene(scene);
            stage.setTitle("Dashboard"); // Atualizar o título da janela
            stage.show(); // Exibir a nova cena

        } catch (IOException e) {
            // Tratamento específico para falha ao carregar a tela
            String erroMensagem = "Erro ao carregar a tela do dashboard. Verifique o caminho do arquivo ou a integridade do FXML.";
            lblErro.setText(erroMensagem);
            System.err.println(erroMensagem);
        }
    }
}
