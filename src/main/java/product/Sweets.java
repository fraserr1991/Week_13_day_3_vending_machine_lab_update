package product;

public class Sweets extends Product implements IPurchaseable {
    private String brand;

    public Sweets(String brand,String name, Double price){
        super(name, price);
        this.brand = brand;
    }

    public String getBrand(){
        return this.brand;
    }

    public Double purchase(){
        return this.getPrice();
    }
}
