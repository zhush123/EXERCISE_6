import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Stock {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty product;
    private final SimpleIntegerProperty price;
    private final SimpleIntegerProperty stockquantity;

    public Stock(int id, String prod, int prodprice, int stkquantity) {
        this.id = new SimpleIntegerProperty (id);
        this.product = new SimpleStringProperty (prod);
        this.price = new SimpleIntegerProperty (prodprice);
        this.stockquantity = new SimpleIntegerProperty (stkquantity);
    }

    public int getId() {
        return id.get();
    }

    public String getProduct() {
        return product.get();
    }

    public int getPrice() {
        return price.get();
    }

    public int getStockquantity() {
        return stockquantity.get();
    }
}
