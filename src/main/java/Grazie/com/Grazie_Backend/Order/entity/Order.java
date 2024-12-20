package Grazie.com.Grazie_Backend.Order.entity;

import Grazie.com.Grazie_Backend.Order.OrderItems.entity.OrderItems;
import Grazie.com.Grazie_Backend.Pay.entity.Pay;
import Grazie.com.Grazie_Backend.Store.entity.Store;
import Grazie.com.Grazie_Backend.member.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

/*
    Chaean00
    주문 Entity
 */
@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;

    @Column(name = "total_price", nullable = false)
    @Min(0)
    @NotNull
    private int total_price;

    @Column(name = "discount_price", nullable = false)
    @Min(0)
    @NotNull
    private int discount_price;

    @Column(name = "final_price", nullable = false)
    @Min(0)
    @NotNull
    private int final_price;

    @Column(name = "order_date", nullable = false)
    @NotNull
    private LocalDateTime order_date;

    @Column(name = "order_mode", nullable = false)
    @Size(max = 10)
    @NotNull
    private String order_mode;

    @Column(name = "accept")
    @Size(max = 5)
    @NotNull
    private String accept;

    @Column(name = "requirement")
    @Size(max = 255)
    @NotNull
    private String requirement;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    @NotNull
    private Store store;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

//    @OneToOne
//    @JoinColumn(name = "coupon_id")
//    private Coupon coupon_id;

    @OneToOne
    @JoinColumn(name = "pay_id")
    private Pay pay;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @NotNull
    private List<OrderItems> orderItems;

}
