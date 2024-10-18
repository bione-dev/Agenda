package agenda;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class GerenciadorDeContatos {
    private List<Contato> contatos;
    private final String arquivoContatos;
    private final Random random = new Random();
    private final HashSet<String> matriculasExistentes = new HashSet<>();
    private final Scanner scanner;

    public GerenciadorDeContatos(String arquivo) {
        this.contatos = new ArrayList<>();
        this.arquivoContatos = arquivo;
        this.scanner = new Scanner(System.in);
        carregarContatosDoArquivo();
    }

    public boolean validarNome(String nome) {
        return nome != null && !nome.trim().isEmpty() && nome.matches("[a-zA-ZÀ-ÿ\\s]+");
    }

    public boolean validarTelefone(String telefone) {
        return telefone != null && telefone.matches("\\(\\d{2}\\) \\d{5}-\\d{4}");
    }

    public boolean validarEmail(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.compile(regex).matcher(email).matches();
    }

    public String gerarMatriculaAleatoria() {
        String matricula;
        do {
            matricula = String.format("%06d", random.nextInt(1000000));
        } while (matriculasExistentes.contains(matricula));
        matriculasExistentes.add(matricula);
        return matricula;
    }

    public void adicionarContato(String nome, String telefone, String email) {
        if (!validarNome(nome)) {
            System.out.println("Nome inválido. Deve conter apenas letras e espaços.");
            return;
        }
        if (!validarTelefone(telefone)) {
            System.out.println("Telefone inválido. O formato deve ser (XX) XXXXX-XXXX.");
            return;
        }
        if (!validarEmail(email)) {
            System.out.println("E-mail inválido.");
            return;
        }
        
        String matricula = gerarMatriculaAleatoria();
        Contato contato = new Contato(nome, telefone, email, matricula);
        
        System.out.print("Deseja adicionar este contato? (s/n): ");
        String confirmar = scanner.nextLine();
        if (confirmar.equalsIgnoreCase("s")) {
            contatos.add(contato);
            System.out.println("Contato adicionado com matrícula: " + matricula);
            salvarContatosNoArquivo();
        } else {
            System.out.println("Inclusão cancelada.");
        }
    }

    public void listarContatos() {
        if (contatos.isEmpty()) {
            System.out.println("Nenhum contato encontrado.");
            return;
        }
        for (Contato contato : contatos) {
            System.out.println(contato);
        }
    }

    public void editarContato(String matricula, String novoNome, String novoTelefone, String novoEmail, String novaMatricula) {
        Contato contato = getContatoByMatricula(matricula);
        if (contato != null) {
            System.out.println("Detalhes do contato atual:");
            System.out.println(contato);
            System.out.print("Deseja editar este contato? (s/n): ");
            String confirmar = scanner.nextLine();

            if (confirmar.equalsIgnoreCase("s")) {
                if (novoNome != null && !novoNome.isEmpty() && validarNome(novoNome)) {
                    contato.setNome(novoNome);
                }
                if (novoTelefone != null && !novoTelefone.isEmpty() && validarTelefone(novoTelefone)) {
                    contato.setTelefone(novoTelefone);
                }
                if (novoEmail != null && !novoEmail.isEmpty() && validarEmail(novoEmail)) {
                    contato.setEmail(novoEmail);
                }
                if (novaMatricula != null && !novaMatricula.isEmpty()) {
                    if (matriculasExistentes.contains(novaMatricula)) {
                        System.out.println("A matrícula já está em uso. Escolha uma matrícula diferente.");
                        return;
                    }
                    // Remover a matrícula antiga
                    matriculasExistentes.remove(contato.getMatricula());
                    contato.setMatricula(novaMatricula);
                    // Adicionar a nova matrícula
                    matriculasExistentes.add(novaMatricula);
                }
                salvarContatosNoArquivo();
                System.out.println("Contato editado com sucesso!");
            } else {
                System.out.println("Edição cancelada.");
            }
        } else {
            System.out.println("Contato não encontrado.");
        }
    }

    public void removerContatoPorNome(String nome) {
        Contato contatoParaRemover = getContatoByNome(nome);
        if (contatoParaRemover != null) {
            System.out.println("Detalhes do contato a ser excluído:");
            System.out.println(contatoParaRemover);
            System.out.print("Deseja realmente excluir este contato? (s/n): ");
            String confirmar = scanner.nextLine();

            if (confirmar.equalsIgnoreCase("s")) {
                contatos.remove(contatoParaRemover);
                System.out.println("Contato removido com sucesso!");
                salvarContatosNoArquivo();
            } else {
                System.out.println("Exclusão cancelada.");
            }
        } else {
            System.out.println("Contato não encontrado.");
        }
    }

    public void removerContatoPorMatricula(String matricula) {
        Contato contatoParaRemover = getContatoByMatricula(matricula);
        if (contatoParaRemover != null) {
            removerContatoPorNome(contatoParaRemover.getNome()); // Reutiliza a lógica de remoção
        } else {
            System.out.println("Contato não encontrado.");
        }
    }

    private void carregarContatosDoArquivo() {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoContatos))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(" \\| ");
                if (partes.length == 4) {
                    Contato contato = new Contato(partes[0], partes[1], partes[2], partes[3]);
                    contatos.add(contato);
                    matriculasExistentes.add(contato.getMatricula());
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar contatos do arquivo: " + e.getMessage());
        }
    }

    private void salvarContatosNoArquivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoContatos))) {
            for (Contato contato : contatos) {
                bw.write(contato.getNome() + " | " + contato.getTelefone() + " | " + contato.getEmail() + " | " + contato.getMatricula());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar contatos no arquivo: " + e.getMessage());
        }
    }

    public Contato getContatoByMatricula(String matricula) {
        for (Contato contato : contatos) {
            if (contato.getMatricula().equals(matricula)) {
                return contato;
            }
        }
        return null;
    }

    public Contato getContatoByNome(String nome) {
        for (Contato contato : contatos) {
            if (contato.getNome().equalsIgnoreCase(nome)) {
                return contato;
            }
        }
        return null;
    }

    public void fecharScanner() {
        scanner.close();
    }
}
