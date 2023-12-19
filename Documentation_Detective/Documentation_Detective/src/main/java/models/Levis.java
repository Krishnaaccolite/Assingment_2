package models;

import annotations.ClassDocumentation;
import annotations.MethodDocumentation;
@ClassDocumentation
public class Levis {
    int Price;
    public Levis(int Price) {
        this.Price = Price;
    }
    /**
     * This method returns the number of Purchase by levis
     * @return number of orders
     */
    @MethodDocumentation
    public int getPurchased() {
        return this.Price;
    }


    @MethodDocumentation
    public void setPurchased(int Price) {
        System.out.println("Levis Shopping bill:" + Price);
        this.Price=Price;
    }

}
