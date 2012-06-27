package shopping.checkout;

import java.math.BigDecimal;

public interface Discount {
    public void addItem(Product product);
    public void addItem(Product product, int count);
    public boolean applies();
    public BigDecimal amount();
    public String name();
    public void reset();
}
