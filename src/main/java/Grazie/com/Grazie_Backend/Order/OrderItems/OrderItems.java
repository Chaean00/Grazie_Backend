package Grazie.com.Grazie_Backend.Order.OrderItems;

import Grazie.com.Grazie_Backend.Order.Order;
import Grazie.com.Grazie_Backend.Product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/*
    Chaean00
    주문 상품 Entity
 */
@Entity
@Table(name = "order_items")
@Getter
@Setter
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_item_id;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "product_price")
    private int product_price;
    @Column(name = "total_price")
    private int total_price;

    @Override
    public String toString() {
        return "OrderItems{" +
                "order_item_id=" + order_item_id +
                ", product=" + product +
                ", quantity=" + quantity +
                ", product_price=" + product_price +
                ", total_price=" + total_price +
                '}';
    }
}