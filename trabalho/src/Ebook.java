public class Ebook {
    private final String nome;
    private final String autor;
    private final Double valor;
    private final int isbn;
    private boolean emprestado; // Não final porque pode mudar ao longo da vida útil do objeto

    public Ebook(String nome, String autor, Double valor, int isbn) {
        this.nome = nome;
        this.autor = autor;
        this.valor = valor;
        this.isbn = isbn;
        this.emprestado = false;
    }

    public String getNome() {
        return nome;
    }

    public String getAutor() {
        return autor;
    }

    public Double getValor() {
        return valor;
    }

    public int getIsbn() {
        return isbn;
    }

    public boolean isEmprestado() {
        return emprestado;
    }

    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }

    public void mostrarEbook() {
        System.out.println("---------------------");
        System.out.println("Nome: " + nome);
        System.out.println("Autor: " + autor);
        System.out.println("ISBN: " + isbn);
        System.out.println("Estado: " + (emprestado ? "Livro emprestado" : "Disponível para empréstimo"));
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Autor: " + autor + ", Valor: " + valor + ", ISBN: " + isbn;
    }
}

