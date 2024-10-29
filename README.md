

Este conjunto de classes forma uma aplicação simples de gerenciamento de contatos, permitindo ao usuário adicionar, editar, listar e remover contatos, garantindo a validação de dados e a persistência em um arquivo. O design é modular e orientado a objetos, o que facilita a manutenção e a expansão futura da funcionalidade do aplicativo.

### 1. Classe `Contato`
A classe `Contato` representa um contato individual com suas propriedades e métodos para acessar e modificar essas propriedades.

**Propriedades:**
- `nome`: O nome do contato.
- `telefone`: O número de telefone do contato.
- `email`: O e-mail do contato.
- `matricula`: Uma identificação única para o contato.

**Construtor:**
- Inicializa as propriedades do contato com os valores fornecidos.

**Métodos:**
- **Getters**: Métodos para acessar cada propriedade (nome, telefone, email e matrícula).
- **Setters**: Métodos para modificar cada propriedade.
- **toString()**: Retorna uma representação em string do contato, formatada de maneira legível.

### 2. Classe `GerenciadorDeContatos`
Esta classe é responsável por gerenciar a lista de contatos, incluindo adição, edição, remoção e validação de dados.

**Propriedades:**
- `contatos`: Uma lista que armazena objetos `Contato`.
- `arquivoContatos`: O caminho do arquivo onde os contatos são armazenados.
- `random`: Um gerador de números aleatórios para criar matrículas aleatórias.
- `matriculasExistentes`: Um conjunto que armazena matrículas já utilizadas para evitar duplicatas.
- `scanner`: Um scanner para capturar entrada do usuário.

**Construtor:**
- Inicializa a lista de contatos, o caminho do arquivo e carrega os contatos existentes do arquivo.

**Métodos:**
- **validarNome(nome)**: Valida se o nome é válido (não nulo, não vazio e contém apenas letras e espaços).
- **validarTelefone(telefone)**: Valida se o telefone está no formato correto (ex: (XX) XXXXX-XXXX).
- **validarEmail(email)**: Valida se o e-mail está em um formato adequado.
- **gerarMatriculaAleatoria()**: Gera uma matrícula única aleatória.
- **adicionarContato(nome, telefone, email)**: Adiciona um novo contato após validar os dados.
- **listarContatos()**: Lista todos os contatos armazenados.
- **editarContato(matricula, novoNome, novoTelefone, novoEmail, novaMatricula)**: Edita um contato existente, se encontrado.
- **removerContatoPorNome(nome)**: Remove um contato com base no nome.
- **removerContatoPorMatricula(matricula)**: Remove um contato com base na matrícula, reutilizando a lógica de remoção por nome.
- **carregarContatosDoArquivo()**: Carrega os contatos do arquivo para a lista.
- **salvarContatosNoArquivo()**: Salva os contatos da lista no arquivo.
- **getContatoByMatricula(matricula)**: Busca um contato pelo número de matrícula.
- **getContatoByNome(nome)**: Busca um contato pelo nome.
- **fecharScanner()**: Fecha o scanner usado para entrada de dados.

### 3. Classe `Main`
A classe `Main` contém o método principal que executa o programa e interage com o usuário.

**Métodos:**
- `main(String[] args)`: O ponto de entrada do programa. Cria uma instância do `GerenciadorDeContatos` e exibe um menu com opções para o usuário, permitindo adicionar, listar, editar e remover contatos, além de sair do programa.

**Fluxo:**
- Um loop infinito que apresenta um menu de opções.
- Dependendo da escolha do usuário, ele chama os métodos apropriados do `GerenciadorDeContatos` para realizar as ações desejadas.
- O loop continua até que o usuário escolha sair.

--- 

