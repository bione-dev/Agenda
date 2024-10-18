package agenda;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GerenciadorDeContatos gerenciador = new GerenciadorDeContatos("contatos.txt");

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n1. Adicionar contato");
                System.out.println("2. Listar contatos");
                System.out.println("3. Editar contato");
                System.out.println("4. Excluir contato por nome");
                System.out.println("5. Excluir contato por matrícula");
                System.out.println("6. Sair");
                System.out.print("Escolha uma opção: ");

                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir nova linha

                switch (opcao) {
                    case 1:
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Telefone: ");
                        String telefone = scanner.nextLine();
                        System.out.print("E-mail: ");
                        String email = scanner.nextLine();
                        gerenciador.adicionarContato(nome, telefone, email);
                        break;
                    case 2:
                        gerenciador.listarContatos();
                        break;
                    
                    case 3:
                        System.out.print("Digite a matrícula do contato a ser editado: ");
                        String matriculaEdit = scanner.nextLine();
                        System.out.print("Novo nome (deixe em branco para não alterar): ");
                        String novoNome = scanner.nextLine();
                        System.out.print("Novo telefone (deixe em branco para não alterar): ");
                        String novoTelefone = scanner.nextLine();
                        System.out.print("Novo e-mail (deixe em branco para não alterar): ");
                        String novoEmail = scanner.nextLine();
                        System.out.print("Nova matrícula (deixe em branco para não alterar): ");
                        String novaMatricula = scanner.nextLine();

                        gerenciador.editarContato(
                            matriculaEdit,
                            novoNome.isEmpty() ? null : novoNome,
                            novoTelefone.isEmpty() ? null : novoTelefone,
                            novoEmail.isEmpty() ? null : novoEmail,
                            novaMatricula.isEmpty() ? null : novaMatricula 
                        );
                        break;

                        
                         
                                                 
                       
                    case 4:
                        System.out.print("Digite o nome do contato a ser excluído: ");
                        String nomeContato = scanner.nextLine();
                        gerenciador.removerContatoPorNome(nomeContato);
                        break;
                    case 5:
                        System.out.print("Digite a matrícula do contato a ser excluído: ");
                        String matriculaExcluir = scanner.nextLine();
                        gerenciador.removerContatoPorMatricula(matriculaExcluir);
                        break;
                    case 6:
                        gerenciador.fecharScanner();
                        System.out.println("Saindo...");
                        return; // Sair do programa
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }
        }
    }
}
