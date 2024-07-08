package com.Website.WebBanHang.Service;

import com.Website.WebBanHang.model.CartItem;
import com.Website.WebBanHang.model.Order;
import com.Website.WebBanHang.model.OrderDetail;
import com.Website.WebBanHang.repository.OrderDetailRepository;
import com.Website.WebBanHang.repository.OrderRepository;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private CartService cartService; // Assuming you have a CartService
    @Transactional
    public Order createOrder(Order order, List<CartItem> cartItems) {

// Lưu đơn hàng vào cơ sở dữ liệu
        order = orderRepository.save(order);
// Lưu các chi tiết đơn hàng vào cơ sở dữ liệu
        for (CartItem item : cartItems) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setProduct(item.getProduct());
            detail.setQuantity(item.getQuantity());
            orderDetailRepository.save(detail);
        }
// Xóa giỏ hàng sau khi đặt hàng (tùy chọn)
        cartService.clearCart();
        return order;
    }
}