package macochave.com.cargardb;

/**
 * Created by u on 27/05/2016.
 */
public class Categoria {
    private int id;
    private String categoria;

    public Categoria() {
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return categoria;
    }
}
