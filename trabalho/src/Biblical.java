import java.util.ArrayList;
import java.util.Iterator;

public class Biblical {
    private ArrayList<Ebook> ebooks;
    private ArrayList<Emprestimo> emprestimos;

    public Biblical() {
        this.ebooks = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }

    public void adicionarEbook(Ebook ebook) {
        ebooks.add(ebook);
    }

    public ArrayList<Ebook> getEbooks() {
        return ebooks;
    }

    public void mostrarEbooksDisponiveis(String searchText) {
        System.out.println("Ebooks disponíveis:");
        for (Ebook ebook : ebooks) {
            if (!ebook.isEmprestado() && (searchText.isEmpty() || ebook.getNome().toLowerCase().contains(searchText)
                    || ebook.getAutor().toLowerCase().contains(searchText))) {
                ebook.mostrarEbook();
            }
        }
    }

    public void emprestarEbook(int isbn, Usuario usuario) {
        for (Ebook ebook : ebooks) {
            if (ebook.getIsbn() == isbn && !ebook.isEmprestado()) {
                ebook.setEmprestado(true);
                emprestimos.add(new Emprestimo(ebook, usuario));
                System.out.println("Ebook emprestado com sucesso!");
                return;
            }
        }
        System.out.println("Ebook não encontrado ou já emprestado.");
    }

    public void devolverEbook(int isbn) {
        Iterator<Emprestimo> iterator = emprestimos.iterator();
        while (iterator.hasNext()) {
            Emprestimo emprestimo = iterator.next();
            if (emprestimo.getEbook().getIsbn() == isbn) {
                emprestimo.getEbook().setEmprestado(false);
                iterator.remove();
                System.out.println("Ebook devolvido com sucesso!");
                return;
            }
        }
        System.out.println("Ebook não encontrado nos empréstimos.");
    }
}
