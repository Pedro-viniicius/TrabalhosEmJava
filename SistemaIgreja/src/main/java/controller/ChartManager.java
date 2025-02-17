package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Map;
import models.Membro;
import models.Visitante;
import models.Beneficiario;

public class ChartManager {

    /**
     * Configura o gráfico de pizza com os dados obtidos dos modelos.
     * 
     * @param graficoPizza Instância do PieChart a ser configurado.
     * @throws SQLException Caso haja erro na obtenção dos dados.
     */
    public static void configurarGraficoPizza(PieChart graficoPizza) throws SQLException {
        int membrosAtivos = Membro.obterQuantidadeMembrosAtivos();
        int membrosInativos = Membro.obterQuantidadeMembrosInativos();
        int visitantes = Visitante.obterQuantidadeVisitantesMesAtual();
        int beneficiarios = Beneficiario.obterQuantidadeBeneficiariosAtivos();

        ObservableList<PieChart.Data> dados = gerarDadosGraficoPizza(membrosAtivos, membrosInativos, visitantes, beneficiarios);
        graficoPizza.setData(dados);
        graficoPizza.setLabelsVisible(true);
        graficoPizza.setLegendVisible(true);
        graficoPizza.setPrefSize(600, 400);

        // Adicionar tooltips e forçar exibição dos rótulos
        for (PieChart.Data dado : dados) {
            Tooltip tooltip = new Tooltip(dado.getName() + ": " + (int) dado.getPieValue());
            tooltip.setShowDelay(Duration.millis(100));
            Tooltip.install(dado.getNode(), tooltip);
            dado.getNode().setStyle("-fx-pie-label-visible: true;");
        }
    }

    /**
     * Gera os dados para o gráfico de pizza.
     */
    public static ObservableList<PieChart.Data> gerarDadosGraficoPizza(int membrosAtivos, int membrosInativos, int visitantes, int beneficiarios) {
        String mesAtual = obterMesAtualFormatado();
        ObservableList<PieChart.Data> dados = FXCollections.observableArrayList();
        dados.add(new PieChart.Data("Membros Ativos", membrosAtivos));
        dados.add(new PieChart.Data("Membros Inativos", membrosInativos));
        dados.add(new PieChart.Data("Visitantes (" + mesAtual + ")", visitantes));
        dados.add(new PieChart.Data("Beneficiários", beneficiarios));
        return dados;
    }

    /**
     * Obtém o nome do mês atual formatado.
     */
    private static String obterMesAtualFormatado() {
        LocalDate hoje = LocalDate.now();
        return hoje.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
    }

    /**
     * Configura o gráfico de distribuição de idade por gênero.
     * 
     * @param graficoDistribuicaoIdade Instância do StackedBarChart a ser configurado.
     */
    public static void configurarGraficoDistribuicaoIdade(StackedBarChart<String, Number> graficoDistribuicaoIdade) {
        graficoDistribuicaoIdade.setTitle("Distribuição de Idade por Gênero");
        graficoDistribuicaoIdade.setCategoryGap(10);

        // Criar séries para cada gênero
        XYChart.Series<String, Number> serieMasculino = new XYChart.Series<>();
        serieMasculino.setName("Masculino");
        XYChart.Series<String, Number> serieFeminino = new XYChart.Series<>();
        serieFeminino.setName("Feminino");

        // Obter a distribuição de idade a partir do modelo
        Membro membro = new Membro();
        Map<String, Map<String, Integer>> distribuicao = membro.buscarDistribuicaoIdadePorGenero();

        ObservableList<XYChart.Data<String, Number>> dadosMasculino = FXCollections.observableArrayList();
        ObservableList<XYChart.Data<String, Number>> dadosFeminino = FXCollections.observableArrayList();

        // Preencher dados para o gênero masculino
        distribuicao.getOrDefault("masculino", Map.of()).forEach((faixaEtaria, quantidade) -> {
            dadosMasculino.add(new XYChart.Data<>(faixaEtaria, quantidade));
        });
        
        // Preencher dados para o gênero feminino
        distribuicao.getOrDefault("feminino", Map.of()).forEach((faixaEtaria, quantidade) -> {
            dadosFeminino.add(new XYChart.Data<>(faixaEtaria, quantidade));
        });

        serieMasculino.setData(dadosMasculino);
        serieFeminino.setData(dadosFeminino);

        graficoDistribuicaoIdade.getData().addAll(serieMasculino, serieFeminino);

        // Adicionar tooltips para cada barra
        graficoDistribuicaoIdade.getData().forEach(serie -> {
            serie.getData().forEach(data -> {
                Tooltip tooltip = new Tooltip(serie.getName() + "\n" + data.getXValue() + ": " + data.getYValue());
                Tooltip.install(data.getNode(), tooltip);
            });
        });
    }
}
