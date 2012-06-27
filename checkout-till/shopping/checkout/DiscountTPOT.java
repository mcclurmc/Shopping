package shopping.checkout;

import shopping.checkout.Discount;
import shopping.checkout.Product;

import java.math.BigDecimal;

public class DiscountTPOT implements Discount {
    private int count = 0;
    private int numDiscounts = 0;
    private boolean applies = false;
    private Product leastCostProduct = null;

    @Override
    public void addItem(Product product) {
        if (product.isTPOT()) {
            count = count++;
            if (count >= 3)
                applies = true;
            if (leastCostProduct == null ||
                    leastCostProduct.unitPrice().compareTo(product.unitPrice()) > 0)
                leastCostProduct = product;
            numDiscounts++;
            count = 0;
        }
    }

    public void addItem(Product product, int count) {
        for (int i=0; i < count; i++) {
            addItem(product);
        }
    }

    @Override
    public boolean applies() {
        return applies;
    }

    @Override
    public BigDecimal amount() {
        if (leastCostProduct != null)
            return leastCostProduct.unitPrice()
                    .negate()
                    .multiply(new BigDecimal(numDiscounts));
        else
            return new BigDecimal(0);
    }

    @Override
    public String name() {
        if (leastCostProduct != null)
            return "TPOT (" + leastCostProduct.name() + " x " + numDiscounts + ")";
        else
            return "No discount";
    }

    @Override
    public void reset() {
        count = 0;
        numDiscounts = 0;
        applies = false;
        leastCostProduct = null;
    }
}
