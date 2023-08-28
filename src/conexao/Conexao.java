package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    // Configurações para estabelecer a conexão com o banco de dados
    private static final String url = "jdbc:mysql://localhost:3306/cadastro_pessoas";// URL do banco de dados
    private static final String user = "root";// Nome de usuário do banco de dados
    private static final String password = "";// Senha do banco de dados

    private static Connection conn;// Variável para armazenar a conexão

    public static Connection getConn() {  // Método estático para obter uma conexão com o banco de dados
        try {
            if (conn == null) { // Se ainda não existe uma conexão
                conn = DriverManager.getConnection(url, user, password);// Estabelece a conexão
                return conn;// Retorna a conexão estabelecida
            } else {
                return conn;// Retorna a conexão existente
            }
        } catch(SQLException e){
            e.printStackTrace();// Imprime informações sobre o erro no console
            return null;// Retorna null em caso de erro
        }
    }
}
