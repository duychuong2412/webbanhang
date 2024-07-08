package com.Website.WebBanHang.model;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private String DiaChi;
    private String Email;
    private String SDT;
    private String GhiChu;
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }
    public void setEmail(String Email) {
        this.Email = Email;
    }
    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

}