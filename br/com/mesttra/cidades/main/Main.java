package br.com.mesttra.cidades.main;

import br.com.mesttra.cidades.dao.CidadeDAO;
import br.com.mesttra.cidades.pojo.CidadePOJO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        CidadeDAO cidadeDAO = new CidadeDAO();
        int opcao = 0;

        do {
            try {
                menu();
                opcao = Integer.parseInt(in.nextLine());

                switch (opcao) {
                    case 1 -> cadastrarCidade(in, cidadeDAO);
                    case 2 -> removerCidade(in, cidadeDAO);
                    case 3 -> exibeCidades(cidadeDAO.selectCidades());
                    case 4 -> buscaCidades(in, cidadeDAO);
                    case 5 -> System.out.println("Obrigado por utilzar o sistema, até logo!");
                    default -> System.out.println("Opção inválida, tente novamente.");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } while (opcao != 5);

        in.close();
    }

    private static void exibeCidades(ArrayList<CidadePOJO> cidades) {
        if (cidades != null) {
            System.out.println("\n# ===== Cidades Cadastradas ===== #");

            for (CidadePOJO cidade : cidades)
                System.out.printf("> %d. %s\n", cidade.getDdd(), cidade.getNome());

            int nCidades = cidades.size();
            System.out.printf("  Total de %d cidade%s cadastrada%s\n", nCidades, nCidades > 1 ? "s" : "", nCidades > 1 ? "s" : "");
            System.out.println("# =============================== #\n");
        }
    }

    private static void buscaCidades(Scanner in, CidadeDAO cidadeDAO) {
        int opcao = 0;

        do {
            menuBusca();
            opcao = Integer.parseInt(in.nextLine());

            switch (opcao) {
                case 1 -> {
                    System.out.print("Informe o DDD: ");
                    String params = in.nextLine();

                    exibeCidades(cidadeDAO.buscaCidades(1, params));
                }
                case 2 -> {
                    System.out.print("Informe o nome: ");
                    String params = in.nextLine();

                    exibeCidades(cidadeDAO.buscaCidades(2, params));
                }
                case 3 -> {
                    System.out.print("Informe o estado: ");
                    String params = in.nextLine();

                    exibeCidades(cidadeDAO.buscaCidades(3, params));
                }
                case 4 -> {
                    System.out.print("A cidade é capital (S/N)? : ");
                    String params = in.nextLine();

                    exibeCidades(cidadeDAO.buscaCidades(4, params));
                }
                case 5 -> System.out.println();
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        } while(opcao != 5);
    }

    private static void removerCidade(Scanner in, CidadeDAO cidadeDAO) throws SQLException {
        System.out.print("Informe o DDD da cidade a ser removida: ");
        int ddd = Integer.parseInt(in.nextLine());

        if (cidadeDAO.deleteCidade(ddd))
            System.out.println("Cidade removida com sucesso!");
    }

    private static void cadastrarCidade(Scanner in, CidadeDAO cidadeDAO) throws SQLException {
        System.out.print("Informe o DDD: ");
        int ddd = Integer.parseInt(in.nextLine());

        System.out.print("Informe o nome da cidade: ");
        String nome = in.nextLine();

        System.out.print("Informe o número de habitantes: ");
        int habitantes = Integer.parseInt(in.nextLine());

        System.out.print("Informe a renda per capita: R$");
        double renda = Double.parseDouble(in.nextLine());

        System.out.print("A cidade é uma capital (S/N)? ");
        boolean capital = in.nextLine().equalsIgnoreCase("S");

        System.out.print("Informe o estado: ");
        String estado = in.nextLine();

        System.out.print("Informe o nome do prefeito: ");
        String prefeito = in.nextLine();

        CidadePOJO cidade = new CidadePOJO(ddd, nome, habitantes, renda,
                capital, estado, prefeito);

        if (cidadeDAO.insertCidade(cidade))
            System.out.printf("Cidade %s cadastrada com sucesso!\n", nome);
    }

    private static void menuBusca() {
        System.out.println("# ===== Busca de Cidades  ===== #");
        System.out.println("| 1. Pesquisar por DDD          |");
        System.out.println("| 2. Pesquisar por nome         |");
        System.out.println("| 3. Pesquisar por estado       |");
        System.out.println("| 4. Pesquisar por capital      |");
        System.out.println("| 5. Voltar                     |");
        System.out.println("# ============================= #");
        System.out.print("Escolha uma opção: ");
    }

    private static void menu() {
        System.out.println("# ===== Cadastro de Cidades ===== #");
        System.out.println("| 1. Cadastrar                    |");
        System.out.println("| 2. Remover                      |");
        System.out.println("| 3. Consulta Cidades Cadastradas |");
        System.out.println("| 4. Busca de cidades             |");
        System.out.println("| 5. Sair                         |");
        System.out.println("# =============================== #");
        System.out.print("Escolha uma opção: ");
    }
}
