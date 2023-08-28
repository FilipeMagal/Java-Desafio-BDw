package DAO;

import conexao.Conexao;
import entity.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // Método para cadastrar um novo usuário no banco de dados
    public void cadastrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO registros (NAME, ENTRADA, SAIDA) VALUES (?, ?, ?)"; // usado para inserir os dados
        Connection connection = Conexao.getConn(); // Usado para conectar com o banco de dados
        try  {
            if (connection != null) { //  se a conexão não for nula, entrará no próximo try
                try (PreparedStatement ps = connection.prepareStatement(sql)) { // Inicia um bloco try onde um PreparedStatement é criado e inicializado com uma consulta SQL
                    // Configura os parâmetros da consulta SQL com os valores do usuário
                    ps.setString(1, usuario.getName());
                    ps.setTimestamp(2, new java.sql.Timestamp(usuario.getEntrada().getTime()));
                    ps.setTimestamp(3, new java.sql.Timestamp(usuario.getSaida().getTime()));

                    // Executa a consulta
                    ps.execute();
                }
            } else { // se a conexão for nula
                System.out.println("Conexão nula. Não foi possível cadastrar o usuário.");
            }
        } catch (SQLException e) { // tratamento de exceção
            e.printStackTrace();
        }
    }
    // Método para listar todos os usuários do banco de dados
    public List<Usuario> listarUsuario(){
        List<Usuario> usuarios = new ArrayList<>(); // criação da lista
        String sql = "SELECT * FROM registros"; // usado para mostrar os dados
        Connection connection = Conexao.getConn(); //  conexão com o banco de dados para realizar a exibição
        try {
            if (connection != null) { // se não for nula
                    PreparedStatement ps = connection.prepareStatement(sql); // Prepara e executa a consulta SQL usando o 'PreparedStatement' ('ps').
                    ResultSet rs = ps.executeQuery(); // O 'ResultSet' ('rs') armazenará os resultados da consulta.

                    // Para cada resultado da consulta, cria um objeto Usuario e adiciona à lista
                    while (rs.next()) {
                        Usuario usuario = new Usuario();
                        usuario.setId(rs.getInt("id"));
                        usuario.setName(rs.getString("name"));
                        usuario.setEntrada(rs.getTimestamp("entrada"));
                        usuario.setSaida(rs.getTimestamp("saida"));
                        usuarios.add(usuario);
                    }
                }else {//se a conexão for nula
                System.err.println("A conexão com o banco de dados é nula. Não é possível listar usuários.");
            }
            } catch (SQLException ex) { // tratamento de exceção
            throw new RuntimeException(ex);
        }
        return usuarios; // Retorna a lista de usuários obtida do banco de dados
    }
}
