import coin.Coin;
import machine.VendingMachine;
import org.junit.Before;
import org.junit.Test;
import product.Cola;
import product.Product;

import java.math.RoundingMode;
import java.text.DecimalFormat;


import static org.junit.Assert.assertEquals;

public class TestVendingMachine {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    VendingMachine vendingMachine;
    Coin coin1;
    Coin coin2;
    Coin coin3;

    Cola coke;

    @Before
    public void before(){
        vendingMachine = new VendingMachine();
        coin1 = new Coin(.10);
        coin2 = new Coin(1.0);
        coin3 = new Coin(.02);
        coke = new Cola("coke", "cola", 1.0);
    }

    @Test
    public void acceptsValidCoins(){
        vendingMachine.addCoin(coin1);
        assertEquals(0.10, vendingMachine.getCurrentBalance(),0.0);
    }

    @Test public void rejectsInvalidCoins(){
        vendingMachine.addCoin(coin3);
        assertEquals(0, vendingMachine.getCurrentBalance(),0.0);
    }
    @Test
    public void addsOnlyValidCoinsToCurrentBalance(){
        vendingMachine.addCoin(coin1);
        vendingMachine.addCoin(coin2);
        vendingMachine.addCoin(coin3);
        vendingMachine.addCoin(coin1);
        //Hacky? The result was 1.2000000002, and I needed to convert the number rec to the real number.
        assertEquals("1.20", df.format(vendingMachine.getCurrentBalance()));

    }

    @Test
    public void returnRejectedCoins(){
        vendingMachine.addCoin(coin3);
        assertEquals(1, vendingMachine.getSizeOfReturnedCoins());
    }

    @Test
    public void getSizeOfProducts(){
        assertEquals(2, vendingMachine.getSizeOfProducts());
    }

    @Test
    public void addProductForPurchase(){
        vendingMachine.addProductToVendingMachine(coke);
        assertEquals(3, vendingMachine.getSizeOfProducts());
    }

    @Test
    public void productCodeMatch(){
        assertEquals(true, vendingMachine.productCodeMatch(101));
    }
    @Test
    public void productCodeMismatch(){
        assertEquals(false, vendingMachine.productCodeMatch(107));
    }

    @Test
    public void CanDispenseItem(){
        vendingMachine.dispenseItem(101);
        assertEquals(1, vendingMachine.getSizeOfDispensedItems());
        assertEquals(2, vendingMachine.getSizeOfProducts());
    }

    @Test
    public void CanRemoveItemFromProducts(){
        vendingMachine.removeItemFromProducts(101);
        assertEquals(1, vendingMachine.getSizeOfProducts());
    }

    @Test
    public void hasRequiredBalance(){
        vendingMachine.addCoin(coin1);
        vendingMachine.addCoin(coin2);
        vendingMachine.addCoin(coin3);
        vendingMachine.addCoin(coin1);
        assertEquals("Enjoy your product!", vendingMachine.buyProductFromVendingMachine(101));
        assertEquals("0.00", df.format(vendingMachine.getCurrentBalance()));

    }

    @Test
    public void requiredBalanceNotMet(){
        vendingMachine.addCoin(coin1);
        assertEquals("Balance inadequate, please insert more coins and try again", vendingMachine.buyProductFromVendingMachine(101));
        assertEquals("0.10", df.format(vendingMachine.getCurrentBalance()));

    }
    
//    @Test
//    public void removeProductFromProducts(){
//
//        assertEquals()
//    }

}
