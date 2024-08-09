import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

// Classe para a interface gráfica da Biblioteca
public class BibliotecaGUI {

    private JTextField searchField; // Campo de texto para pesquisa
    private JTextArea resultArea; // Área de texto para exibir os resultados
    private JTextField isbnField; // Campo de texto para inserir ISBN
    private Biblical biblioteca; // Instância da biblioteca

    // Construtor da classe BibliotecaGUI
    public BibliotecaGUI(Biblical biblioteca) {
        this.biblioteca = biblioteca;

        // Configuração da interface gráfica
        JFrame frame = new JFrame("Biblioteca Virtual");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel(new BorderLayout());

        // Painel para a barra de pesquisa e botão de pesquisa
        JPanel searchPanel = new JPanel(new FlowLayout());

        // Barra de pesquisa
        searchField = new JTextField(20);
        searchPanel.add(searchField);

        // Botão para pesquisar eBooks
        JButton pesquisarButton = new JButton("Pesquisar");
        pesquisarButton.addActionListener(e -> mostrarEbooks());
        searchPanel.add(pesquisarButton);

        // Adiciona o painel de pesquisa na parte superior da interface
        panel.add(searchPanel, BorderLayout.NORTH);

        // Área de texto para exibir os resultados da pesquisa
        resultArea = new JTextArea();
        resultArea.setEditable(false); // Impede a edição direta do texto
        JScrollPane scrollPane = new JScrollPane(resultArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Painel para ações de empréstimo, devolução e limpeza
        JPanel actionPanel = new JPanel(new FlowLayout());

        // Campo para inserir o ISBN
        isbnField = new JTextField(10);
        actionPanel.add(new JLabel("ISBN:"));
        actionPanel.add(isbnField);

        // Botão para emprestar livro
        JButton emprestarButton = new JButton("Emprestar");
        emprestarButton.addActionListener(e -> emprestarEbook());
        actionPanel.add(emprestarButton);

        // Botão para devolver livro
        JButton devolverButton = new JButton("Devolver");
        devolverButton.addActionListener(e -> devolverEbook());
        actionPanel.add(devolverButton);

        // Botão para limpar a tela
        JButton limparButton = new JButton("Limpar Tela");
        limparButton.addActionListener(e -> limparTela());
        actionPanel.add(limparButton);

        // Adiciona o painel de ações na parte inferior da interface
        panel.add(actionPanel, BorderLayout.SOUTH);

        // Configura a janela
        frame.add(panel);
        frame.setVisible(true);
    }

    // Método para exibir eBooks com base na pesquisa
    private void mostrarEbooks() {
        String searchText = searchField.getText().trim().toLowerCase();
        mostrarEbooks(searchText);
    }

    // Método para mostrar todos os eBooks ou eBooks filtrados
    private void mostrarEbooks(String searchText) {
        List<Ebook> ebooks = biblioteca.getEbooks(); // Obtém a lista de eBooks
        resultArea.setText(""); // Limpa a área de texto antes de exibir novos resultados

        for (Ebook ebook : ebooks) {
            if (!ebook.isEmprestado() && (searchText.isEmpty() || ebook.getNome().toLowerCase().contains(searchText)
                    || ebook.getAutor().toLowerCase().contains(searchText))) {
                resultArea.append(ebook + "\n"); // Adiciona o eBook à área de texto
            }
        }

        if (resultArea.getText().isEmpty()) {
            resultArea.setText("Nenhum eBook encontrado.");
        }
    }

    // Método para emprestar um eBook
    private void emprestarEbook() {
        try {
            int isbn = Integer.parseInt(isbnField.getText().trim());
            Usuario usuario = new Usuario("Usuário Padrão", 123456789); // Usuário temporário
            biblioteca.emprestarEbook(isbn, usuario);
            JOptionPane.showMessageDialog(null, "Ebook emprestado com sucesso!");
            mostrarEbooks(); // Atualiza a lista após o empréstimo
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, insira um ISBN válido.");
        }
    }

    // Método para devolver um eBook
    private void devolverEbook() {
        try {
            int isbn = Integer.parseInt(isbnField.getText().trim());
            biblioteca.devolverEbook(isbn);
            JOptionPane.showMessageDialog(null, "Ebook devolvido com sucesso!");
            mostrarEbooks(); // Atualiza a lista após a devolução
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, insira um ISBN válido.");
        }
    }

    // Método para limpar a tela
    private void limparTela() {
        searchField.setText(""); // Limpa o campo de pesquisa
        isbnField.setText(""); // Limpa o campo de ISBN
        resultArea.setText(""); // Limpa a área de texto
    }

    // Método main para inicializar a aplicação
    public static void main(String[] args) {
        Biblical biblioteca = new Biblical();
        biblioteca.adicionarEbook(new Ebook("Java Programming", "John Doe", 29.99, 12345));
        biblioteca.adicionarEbook(new Ebook("Python Basics", "Jane Smith", 19.99, 67890));
        biblioteca.adicionarEbook(new Ebook("C++ Essentials", "Mike Johnson", 24.99, 11223));

        SwingUtilities.invokeLater(() -> new BibliotecaGUI(biblioteca));
    }
}
