package models;

import annotations.ClassDocumentation;
import annotations.MethodDocumentation;

@ClassDocumentation
public class AllenSolly {
    int Price;

    public AllenSolly(int Price) {
        this.Price = Price;
    }
    /**
     * This method returns the number of Purchase by AllenSooly
     * @return number of orders
     */

    public int getPurchased() {
        return this.Price;
    }
    @MethodDocumentation
    public void setPurchased(int Price) {
        System.out.println("AllenSolly Shopping bill:" + Price);
        this.Price = Price;
    }
}
