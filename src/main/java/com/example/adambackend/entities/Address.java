package com.example.adambackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "address_detail")
    private String addressDetail;
    @Column(name = "is_deleted")
    private Boolean isDeleted;
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;
    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;
    @ManyToOne
    @JoinColumn(name = "ward_id")
    private Ward ward;
    @Column(name = "is_active")
    private Boolean isActive;
    @JsonIgnore
    @OneToMany(mappedBy = "address")
    private List<Order> orders = new ArrayList<>();
}
