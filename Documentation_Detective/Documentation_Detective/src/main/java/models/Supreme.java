package models;

import annotations.ClassDocumentation;
import annotations.MethodDocumentation;

@ClassDocumentation
public class Supreme {
    int Price;


    public Supreme(int Price) {
        this.Price = Price;
    }
    @MethodDocumentation
    public int getPurchased() {
        return this.Price;
    }

    /**
     * This method returns the number of Purchase by Supreme
     * @return number of orders
     */


    public void setPurchased(int Price) {
        System.out.println("Supreme Shopping bill:" + Price);
        this.Price = Price;
    }
}
