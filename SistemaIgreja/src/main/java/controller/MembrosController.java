package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Membro;

// Imports do Apache POI para XLSX
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// Imports do iText para PDF
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.poi.ss.usermodel.DataFormatter;

public class MembrosController {

    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnExportar;
    @FXML
    private Button btnImportar;
    @FXML
    private Button btnVoltar;
    @FXML
    private ListView<String> membersListView;
    @FXML
    private TextField textFieldPesquisa;

    @FXML
    public void initialize() {
        membersListView.setItems(Membro.carregarMembros());

        btnBuscar.setOnAction(event -> {
            String pesquisa = textFieldPesquisa.getText();
            membersListView.setItems(Membro.filtrarMembros(pesquisa));
        });

        membersListView.setOnMouseClicked(event -> {
            String selectedItem = membersListView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                int id = extrairIdDoTexto(selectedItem);
                abrirJanelaDetalhes(id);
            }
        });

        btnVoltar.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/openjfx/sistemaigreja/Dashboard.fxml"));
                Stage stage = (Stage) btnVoltar.getScene().getWindow();
                stage.setScene(new Scene(loader.load()));
                stage.show();
            } catch (IOException e) {
                mostrarErro("Erro ao carregar a tela de Dashboard", e);
            }
        });

        btnExportar.setOnAction(event -> {
            ChoiceDialog<String> dialog = new ChoiceDialog<>("CSV", "CSV", "XLSX", "PDF");
            dialog.setTitle("Exportar Membros");
            dialog.setHeaderText("Selecione o formato para exportação:");
            dialog.setContentText("Formato:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(format -> {
                List<Membro> membros = Membro.buscarTodosOsMembrosOrdenadosPorNome();

                switch(format.toUpperCase()){
                    case "CSV":
                        exportToCSV(membros);
                        break;
                    case "XLSX":
                        exportToXLSX(membros);
                        break;
                    case "PDF":
                        exportToPDF(membros);
                        break;
                    default:
                        break;
                }
            });
        });

        btnImportar.setOnAction(event -> importarMembrosDoExcel());
    }

    private int extrairIdDoTexto(String texto) {
        try {
            return Integer.parseInt(texto.split(" - ")[0]);
        } catch (NumberFormatException e) {
            mostrarErro("Erro ao extrair ID do membro", e);
            return -1;
        }
    }

    private void abrirJanelaDetalhes(int membroId) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/openjfx/sistemaigreja/MembroDetalhes.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Detalhes do Membro");

            MembroDetalhesController controller = loader.getController();
            controller.carregarMembro(membroId, this);

            stage.showAndWait();
        } catch (IOException e) {
            mostrarErro("Erro ao abrir a janela de detalhes do membro", e);
        }
    }

    public void atualizarLista() {
        membersListView.setItems(Membro.carregarMembros());
    }

    private void importarMembrosDoExcel() {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("Formato Esperado");
        infoAlert.setHeaderText("Formato do Arquivo Excel:");
        infoAlert.setContentText(
                "O arquivo deve conter as seguintes colunas na primeira linha:\n\n" +
                "Nome | CPF | Data Nascimento | Gênero | Estado Civil | Data Batismo | Ministério | Status\n\n" +
                "Os dados devem estar preenchidos corretamente.");
        infoAlert.showAndWait();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar Arquivo Excel");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Excel Files (*.xlsx)", "*.xlsx"));
        File file = fileChooser.showOpenDialog(btnImportar.getScene().getWindow());

        if (file != null) {
            List<Membro> novosMembros = lerDadosDoExcel(file);
            if (!novosMembros.isEmpty()) {
                Membro.adicionarMembros(novosMembros);
                atualizarLista();
                Alert sucesso = new Alert(Alert.AlertType.INFORMATION, "Importação concluída com sucesso!", ButtonType.OK);
                sucesso.showAndWait();
            } else {
                Alert erro = new Alert(Alert.AlertType.ERROR, "Erro ao importar dados. Verifique o arquivo!", ButtonType.OK);
                erro.showAndWait();
            }
        }
    }

    public List<Membro> lerDadosDoExcel(File file) {
        List<Membro> membros = new ArrayList<>();
        DataFormatter dataFormatter = new DataFormatter();

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            boolean primeiraLinha = true;

            for (Row row : sheet) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }

                try {
                    String nome = dataFormatter.formatCellValue(row.getCell(0));
                    String cpf = dataFormatter.formatCellValue(row.getCell(1));
                    String dataNascimento = dataFormatter.formatCellValue(row.getCell(2));
                    String genero = dataFormatter.formatCellValue(row.getCell(3));
                    String estadoCivil = dataFormatter.formatCellValue(row.getCell(4));
                    String dataBatismo = dataFormatter.formatCellValue(row.getCell(5));
                    String ministerio = dataFormatter.formatCellValue(row.getCell(6));
                    String status = dataFormatter.formatCellValue(row.getCell(7));

                    if (nome.isEmpty() || cpf.isEmpty()) {
                        System.out.println("Linha ignorada por falta de dados essenciais: " + row.getRowNum());
                        continue;
                    }

                    Membro membro = new Membro(nome, cpf, dataNascimento, genero, estadoCivil, dataBatismo, ministerio, status);
                    membros.add(membro);

                } catch (Exception e) {
                    System.err.println("Erro ao processar linha " + row.getRowNum() + ": " + e.getMessage());
                    mostrarErro("Erro ao processar a linha " + row.getRowNum(), e);
                }
            }
        } catch (IOException e) {
            mostrarErro("Erro ao abrir o arquivo Excel", e);
        }

        return membros;
    }

    private void exportToCSV(List<Membro> membros) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar arquivo CSV");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("CSV files (*.csv)", "*.csv"));
        File file = fileChooser.showSaveDialog(btnExportar.getScene().getWindow());

        if (file != null) {
            try (PrintWriter pw = new PrintWriter(file)) {
                pw.println("ID,Nome,CPF,Data Nascimento,Gênero,Estado Civil,Data Batismo,Ministério,Status");
                for (Membro membro : membros) {
                    pw.printf("%d,%s,%s,%s,%s,%s,%s,%s,%s%n",
                            membro.getId(), membro.getNome(), membro.getCPF(), membro.getDataNascimento(),
                            membro.getGenero(), membro.getEstadoCivil(), membro.getDataBatismo(),
                            membro.getMinisterio(), membro.getStatus());
                }
            } catch (IOException e) {
                mostrarErro("Erro ao exportar para CSV", e);
            }
        }
    }

    private void exportToXLSX(List<Membro> membros) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar arquivo XLSX");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Excel files (*.xlsx)", "*.xlsx"));
        File file = fileChooser.showSaveDialog(btnExportar.getScene().getWindow());

        if (file != null) {
            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Membros");

                Row header = sheet.createRow(0);
                String[] headers = {"ID", "Nome", "CPF", "Data Nascimento", "Gênero", "Estado Civil", "Data Batismo", "Ministério", "Status"};
                for (int i = 0; i < headers.length; i++) {
                    header.createCell(i).setCellValue(headers[i]);
                }

                int rowNum = 1;
                for (Membro membro : membros) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(membro.getId());
                    row.createCell(1).setCellValue(membro.getNome());
                    row.createCell(2).setCellValue(membro.getCPF());
                    row.createCell(3).setCellValue(membro.getDataNascimento());
                    row.createCell(4).setCellValue(membro.getGenero());
                    row.createCell(5).setCellValue(membro.getEstadoCivil());
                    row.createCell(6).setCellValue(membro.getDataBatismo());
                    row.createCell(7).setCellValue(membro.getMinisterio());
                    row.createCell(8).setCellValue(membro.getStatus());
                }

                for (int i = 0; i < headers.length; i++) {
                    sheet.autoSizeColumn(i);
                }

                try (FileOutputStream fos = new FileOutputStream(file)) {
                    workbook.write(fos);
                }
            } catch (IOException e) {
                mostrarErro("Erro ao exportar para XLSX", e);
            }
        }
    }

    private void exportToPDF(List<Membro> membros) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar arquivo PDF");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("PDF files (*.pdf)", "*.pdf"));
        File file = fileChooser.showSaveDialog(btnExportar.getScene().getWindow());

        if (file != null) {
            Document document = new Document();
            try {
                PdfWriter.getInstance(document, new FileOutputStream(file));
                document.open();
                document.add(new Paragraph("Membros Registrados"));
                document.add(new Paragraph(" "));

                for (Membro membro : membros) {
                    document.add(new Paragraph(
                            String.format("ID: %d\nNome: %s\nCPF: %s\nData Nascimento: %s\nGênero: %s\nEstado Civil: %s\nData Batismo: %s\nMinistério: %s\nStatus: %s\n\n",
                                    membro.getId(), membro.getNome(), membro.getCPF(), membro.getDataNascimento(),
                                    membro.getGenero(), membro.getEstadoCivil(), membro.getDataBatismo(),
                                    membro.getMinisterio(), membro.getStatus())));  
                }
            } catch (DocumentException | FileNotFoundException e) {
                mostrarErro("Erro ao exportar para PDF", e);
            } finally {
                document.close();
            }
        }
    }

    private void mostrarErro(String mensagem, Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(mensagem);
        alert.setContentText("Detalhes do erro: " + e.getMessage());
        alert.showAndWait();
    }

}


