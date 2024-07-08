package com.Website.WebBanHang.Service;

import com.Website.WebBanHang.model.CartItem;
import com.Website.WebBanHang.model.Product;
import com.Website.WebBanHang.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import java.util.ArrayList;
import java.util.List;
@Service
@SessionScope
public class CartService {
    private List<CartItem> cartItems = new ArrayList<>();
    @Autowired
    private ProductRepository productRepository;
    public void addToCart(int productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId));
                        cartItems.add(new CartItem(product, quantity));
    }
    public List<CartItem> getCartItems() {
        return cartItems;
    }
    public void removeFromCart(int productId) {
        cartItems.removeIf(item -> item.getProduct().getId() == (productId));
    }
    public void clearCart() {
        cartItems.clear();
    }
}
