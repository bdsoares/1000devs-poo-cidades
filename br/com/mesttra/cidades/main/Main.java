package br.com.mesttra.cidades.main;

import br.com.mesttra.cidades.dao.CidadeDAO;
import br.com.mesttra.cidades.pojo.CidadePOJO;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        CidadeDAO cidadeDAO = new CidadeDAO();
        int opcao = 0;

        do {
            try {
                menu();
                System.out.print("Escolha uma opção: ");
                opcao = Integer.parseInt(in.nextLine());

                switch (opcao) {
                    case 1 -> {
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
                    case 2 -> {
                        System.out.print("Informe o DDD da cidade a ser removida: ");
                        int ddd = Integer.parseInt(in.nextLine());

                        if (cidadeDAO.deleteCidade(ddd))
                            System.out.println("Cidade removida com sucesso!");
                    }
                    case 3 -> System.out.println("Obrigado por utilzar o sistema, até logo!");
                    default -> System.out.println("Opção inválida, tente novamente.");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } while (opcao != 3);

        in.close();
    }

    public static void menu() {
        System.out.println("# ===== Cadastro de Cidades ===== #");
        System.out.println("1. Cadastrar");
        System.out.println("2. Remover");
        System.out.println("3. Sair");
        System.out.println("# =============================== #");
    }
}
