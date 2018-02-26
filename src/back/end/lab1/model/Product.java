package back.end.lab1.model;

/**
 *
 * @author Horacio
 */
public class Product {

    private String code;
    private String name;
    private int price;
    private int imported;
    private Kind kind;

    public Product(String code, String name, int price, int imported, Kind kind) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.imported = imported;
        this.kind = kind;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImported() {
        return imported;
    }

    public void setImported(int imported) {
        this.imported = imported;
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }
    
}
