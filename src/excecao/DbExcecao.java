package excecao;

public class DbExcecao extends RuntimeException {
    public DbExcecao(String message) {
        super(message);
    }
}
