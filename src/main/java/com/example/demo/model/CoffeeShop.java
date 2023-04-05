package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "coffee_shops")
public class CoffeeShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    private Boolean isDisable;
    @Column(unique = true)
    private String title;
    @Column(length = 1000)
    private String description;
    private String phone;
    private String open;
    private String close;
//    private String instagram;
//    private String facebook;
//    private String url;
//    @OneToOne
//    private Photo logo;
//    @OneToMany
//    @JoinTable(name = "coffee_shops_photos",
//            joinColumns = @JoinColumn(name = "coffee_shop_id"),
//            inverseJoinColumns = @JoinColumn(name = "photo_id", unique = true))
//    private List<Photo> photos;
    @ManyToMany
    @JoinTable(name = "coffee_shops_features",
            joinColumns = @JoinColumn(name = "coffee_shop_id"),
            inverseJoinColumns = @JoinColumn(name = "feature_id"))
    private List<Feature> features;
//    private String location;

    public CoffeeShop() {
//        photos = new ArrayList<>();
//        features = new ArrayList<>();
        isDisable = false;
    }
}
