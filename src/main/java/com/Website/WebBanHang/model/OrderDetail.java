package com.Website.WebBanHang.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_details")
public class OrderDetail {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private int quantity;
        @ManyToOne
        @JoinColumn(name = "product_id")
        private Product product;
        @ManyToOne
        @JoinColumn(name = "order_id")
        private Order order;
}
