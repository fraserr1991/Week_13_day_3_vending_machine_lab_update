package product;

public class Crisps extends Product implements IPurchaseable {
    private String brand;

    public Crisps(String brand,String name, Double price){
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
