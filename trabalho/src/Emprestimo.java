public class Emprestimo {
    private final Ebook ebook;
    private final Usuario usuario;

    public Emprestimo(Ebook ebook, Usuario usuario) {
        this.ebook = ebook;
        this.usuario = usuario;
    }

    public Ebook getEbook() {
        return ebook;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}



