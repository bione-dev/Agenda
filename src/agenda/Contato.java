package agenda;

public class Contato {
    private String nome;
    private String telefone;
    private String email;
    private String matricula;

    public Contato(String nome, String telefone, String email, String matricula) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.matricula = matricula;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getMatricula() {
        return matricula;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " | Telefone: " + telefone + " | E-mail: " + email + " | Matrícula: " + matricula;
    }
}
