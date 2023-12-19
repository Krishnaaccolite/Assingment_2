package models;

import annotations.MethodDocumentation;

public class MontyCarlo {
    int Price;

    public MontyCarlo(int Price) {
        this.Price = Price;
    }

    @MethodDocumentation
    public int getPurchased() {
        return this.Price;
    }


    @MethodDocumentation
    public void setPurchased(int Price) {
        System.out.println("MontyCarlo Shopping bill:" + Price);
        this.Price = Price;
    }
}

