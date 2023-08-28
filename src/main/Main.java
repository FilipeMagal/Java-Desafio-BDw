package main;

import DAO.UsuarioDAO;
import entity.Usuario;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Guarda os dados
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // Formata data e hora

        Date entrada;
        Date saida;

        System.out.print("Digite o nome: ");
        String name = sc.nextLine(); // Guardando o dado do nome

        try { // Se tudo estiver certo, será feito esse bloco sem problemas
            System.out.print("Registro de entrada (dd/MM/yyyy HH:mm): ");
            entrada = dateFormat.parse(sc.nextLine()); // Usando o formato para pegar o registro de entrada

            System.out.print("Registro de saída (dd/MM/yyyy HH:mm): ");
            saida = dateFormat.parse(sc.nextLine());// Usando o formato para pegar o registro de saída
        } catch (ParseException e) { // Se digitarem o dia ou horário fora do padrão do dateFormat, será exibida essa mensagem
            System.out.print("Formato de data inválido. Use: dd/MM/yyyy HH:mm");
            return;
        }

        Usuario u = new Usuario(); // instanciando objeto Usuario

        u.setName(name); // O nome sendo alterado para o qual foi fornecido acima
        u.setEntrada(entrada); // A entrada sendo alterada para o qual foi fornecido acima
        u.setSaida(saida); // A entrada sendo alterada para o qual foi fornecido acima

        new UsuarioDAO().cadastrarUsuario(u); // Após mudar as informações, é colocada esses valores de cadastro diretamente para o banco de dados

        UsuarioDAO usuarioDAO = new UsuarioDAO();// Instanciando objeto UsuarioDAO
        List<Usuario> usuarios = usuarioDAO.listarUsuario(); // criando a lista usuarios e colocando as informações para serem exibidas

        System.out.println("Lista de Usuários:");
        for (Usuario usuario : usuarios) { // Laço de repetição para mostrar todos os usuários da lista
            System.out.println("Nome: " + usuario.getName());
            System.out.println("Entrada: " + usuario.getEntrada());
            System.out.println("Saída: " + usuario.getSaida());
            // Calculando a duração da permanência em minutos
            long duracaoEmMilissegundos = usuario.getSaida().getTime() - usuario.getEntrada().getTime();
            long duracaoEmMinutos = duracaoEmMilissegundos / (60 * 1000);
            System.out.println("Tempo de espera: " + duracaoEmMinutos + " minutos");
            System.out.println("-------------------------");
        }
    }
}
