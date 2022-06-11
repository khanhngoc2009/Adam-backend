package com.example.adambackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "detail_products")
@Entity
public class DetailProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    @Column(name="price_import")
    private Double priceImport;
    @Column(name="price_export")
    private Double priceExport;
    @Column(name = "is_deleted")
    private Boolean isDelete;
    @Column(name = "image_product")
    private String productImage;

    @OneToMany(mappedBy = "detailProduct")
    List<CartItems> cartItems = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;
    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;
    @OneToMany(mappedBy = "detailProduct")
    private List<DetailOrder> detailOrders = new ArrayList<>();



}
