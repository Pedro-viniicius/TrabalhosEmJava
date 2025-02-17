package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class DashboardController {

    @FXML
    private StackedBarChart<String, Number> graficoDistribuicaoIdade;

    @FXML
    private PieChart graficoPizza;

    @FXML
    private Button btnBeneficiarios;

    @FXML
    private Button btnMembros;

    @FXML
    private Button btnMinisterios;

    @FXML
    private Button btnVisitantes;

    @FXML
    private Button btnideres;

    @FXML
    private Label labelDashboard;

    @FXML
    private void initialize() {
        try {
            ChartManager.configurarGraficoPizza(graficoPizza);
            ChartManager.configurarGraficoDistribuicaoIdade(graficoDistribuicaoIdade);
        } catch (SQLException e) {
            labelDashboard.setText("Erro ao carregar os dados do gráfico.");
        }
        
        // Configura a ação do botão de membros
        btnMembros.setOnAction(event -> navegarParaMembros());
    }
    
    /**
     * Navega para a janela de membros ao clicar no botão btnMembros.
     */
    private void navegarParaMembros() {
        try {
            // Carrega o arquivo FXML da janela de membros (ajuste o caminho conforme a sua estrutura de pastas)
            Parent membrosRoot = FXMLLoader.load(getClass().getResource("/org/openjfx/sistemaigreja/membros.fxml"));
            Scene membrosScene = new Scene(membrosRoot);
            
            // Obtém o stage atual e altera a cena
            Stage stage = (Stage) btnMembros.getScene().getWindow();
            stage.setScene(membrosScene);
            stage.show();
        } catch (IOException e) {
            labelDashboard.setText("Erro ao carregar a janela de Membros.");
        }
    }
    
    // Outros métodos de navegação e manipulação de eventos...
}
