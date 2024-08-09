import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Criar a biblioteca e adicionar alguns ebooks
        Biblical biblioteca = new Biblical();
        biblioteca.adicionarEbook(new Ebook("Java Programming", "John Doe", 29.99, 12345));
        biblioteca.adicionarEbook(new Ebook("Python Basics", "Jane Smith", 19.99, 67890));
        biblioteca.adicionarEbook(new Ebook("C++ Essentials", "Mike Johnson", 24.99, 11223));

        // Inicializar a interface gráfica
        SwingUtilities.invokeLater(() -> new BibliotecaGUI(biblioteca));
    }
}


