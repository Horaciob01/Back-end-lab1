
package back.end.lab1.model;

/**
 *
 * @author Horacio
 */
public class Kind {
    private int id;
    private String name;
    private int tax_percentaje;

    public Kind(int id, String name, int tax_percentaje) {
        this.id = id;
        this.name = name;
        this.tax_percentaje = tax_percentaje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTax_percentaje() {
        return tax_percentaje;
    }

    public void setTax_percentaje(int tax_percentaje) {
        this.tax_percentaje = tax_percentaje;
    }
}
