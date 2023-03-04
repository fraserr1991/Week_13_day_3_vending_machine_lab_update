package machine;

import coin.Coin;
import product.Cola;
import product.Crisps;
import product.IPurchaseable;
import product.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class VendingMachine {
    private ArrayList<Coin> validCoins ;
    private ArrayList<Coin> rejectedCoins;
    private Double currentBalance;
    private ArrayList<Coin> returnedCoins;
    private HashMap<Integer, IPurchaseable> products;
    private int startCode = 101;

    private HashMap<Integer, IPurchaseable> dispensedItems;


    public VendingMachine(){
        this.validCoins = new ArrayList<>();
        this.validCoins.add(new Coin(.10));
        this.validCoins.add(new Coin(.50));
        this.validCoins.add(new Coin(1.0));
        this.validCoins.add(new Coin(2.0));

        this.rejectedCoins = new ArrayList<>();
        this.rejectedCoins.add(new Coin(0.01));
        this.rejectedCoins.add(new Coin(0.02));

        this.returnedCoins = new ArrayList<>();

        this.products = new HashMap<>();
        this.products.put(100, new Cola("coke", "cola",1.0));
        this.products.put(101, new Crisps("walkers", "crisps",0.65));

        this.dispensedItems = new HashMap<>();

        this.currentBalance = 0.0;
    }

    public int getSizeOfReturnedCoins(){
        return this.returnedCoins.size();
    }

    public int getSizeOfProducts(){
        return this.products.size();
    }

    public int getSizeOfDispensedItems(){
        return this.dispensedItems.size();
    }

    public void emptyCurrentBalance(){
        this.currentBalance = 0.0;
    }

    public Double getCurrentBalance(){
        return this.currentBalance;
    }

    public Boolean checkCoinIsAccepted(Coin coin) {
        for(int i = 0; i < validCoins.size(); i++){
            if(coin.getValue() == validCoins.get(i).getValue()){
                return true;
            } else if(coin.getValue() == rejectedCoins.get(i).getValue()){
                System.out.println("coin not accepted");
                returnedCoins.add(coin);
                return false;
            }
        }
        return false;
    }

    public Double addCoin(Coin coin){
        if(checkCoinIsAccepted(coin)) {
            return currentBalance += coin.getValue();
        }
        return currentBalance;
    }

    public void addProductToVendingMachine(IPurchaseable product){
        products.put(startCode += 1, product);
    }

    public String buyProductFromVendingMachine(int code) {
        try {
            if (!productCodeMatch(code)) {
                return "Product does not exist, please try another code";
            }

        if (!isBalanceAdequateForPurchase(code)) {
            return "Balance inadequate, please insert more coins and try again";
        }

        dispenseItem(code);
        removeItemFromProducts(code);
        emptyCurrentBalance();
        return "Enjoy your product!";
    } catch(Exception e){
            return "An error occurred while processing the transaction. Please try again later.";
        }
    }

    public Boolean productCodeMatch(int code){
        if(products.containsKey(code)){
            return true;
        } else{
            return false;
        }

    }


    private Boolean isBalanceAdequateForPurchase(int code) {
        Double productForPurchase = products.get(code).purchase();
        if (productForPurchase <= currentBalance){
            return true;
        }
        return false;
    }

    public void dispenseItem(int code){
        for (int i = 0; i < products.size(); i++) {
            IPurchaseable key = products.get(code);
            dispensedItems.put(code, key);
        }
    }

    public void removeItemFromProducts(int code){
        products.remove(code);

    }
//    public Boolean isBalanceAdequateForPurchase(int code){
//        IPurchaseable key = products.get(code);
//
//        for (int i = 0; i < products.size(); i++) {
//        }
//    }

}
