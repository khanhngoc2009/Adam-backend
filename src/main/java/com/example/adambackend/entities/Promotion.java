package com.example.adambackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "promotions")
@Entity
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String promotionType;
    private String promotionName;
    @Column(name="is_deleted")
    private Boolean isDeleted;
    @JsonIgnore
    @OneToMany(mappedBy = "promotion")
    private List<SaleEventCode> saleEventCodeList= new ArrayList<>();

}