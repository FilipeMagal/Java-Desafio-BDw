package DAO;

import conexao.Conexao;
import entity.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO {

    public void cadastrarUsuario(Usuario usuario){
        String sql = "INSERT INTO registros (NAME, ENTRADA, SAIDA) VALUES (?, ?, ?)";

        try (Connection connection = Conexao.getConn();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, usuario.getName());
            ps.setTimestamp(2, new java.sql.Timestamp(usuario.getEntrada().getTime()));
            ps.setTimestamp(3, new java.sql.Timestamp(usuario.getSaida().getTime()));

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
