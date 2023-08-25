package main;

import DAO.UsuarioDAO;
import entity.Usuario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Date entrada = null;
        Date saida = null;

        System.out.print("Digite o nome: ");
        String name = sc.nextLine();

        try {
            System.out.print("Registro de entrada (dd/MM/yyyy HH:mm): ");
            entrada = dateFormat.parse(sc.nextLine());

            System.out.print("Registro de saída (dd/MM/yyyy HH:mm): ");
            saida = dateFormat.parse(sc.nextLine());
        } catch (ParseException e) {
            System.out.print("Formato de data inválido. Use dd/MM/yyyy HH:mm");
            return;
        }

        Usuario u = new Usuario();

        u.setName(name);
        u.setEntrada(entrada);
        u.setSaida(saida);

        new UsuarioDAO().cadastrarUsuario(u);
        }
    }
