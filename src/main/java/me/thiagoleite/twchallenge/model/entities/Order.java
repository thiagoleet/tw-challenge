package me.thiagoleite.twchallenge.model.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            mappedBy = "order"
    )
    private List<OrderItem> items = new ArrayList<>();

    private Date date;

    public Order() {
        date = new Date();
    }

    public Order(List<OrderItem> items) {
        this.items = items;
        this.date = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void add(OrderItem item) {
        items.add(item);
        item.setOrder(this);
    }
}
