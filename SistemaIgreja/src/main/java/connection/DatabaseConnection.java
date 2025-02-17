package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/sistemaigreja?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        try {
            // Carrega o driver do MySQL (opcional para versões modernas)
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Erro: Driver do MySQL não encontrado.");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados. Verifique suas credenciais e a disponibilidade do banco.");
        }
        return null;
    }
}
