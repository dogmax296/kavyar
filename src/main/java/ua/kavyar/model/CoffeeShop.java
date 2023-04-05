package ua.kavyar.model;

import java.time.LocalTime;
import java.util.List;
import jakarta.persistence.CascadeType;
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
    private LocalTime open;
    private LocalTime close;
    private String url;
    private String location;
    @OneToOne
    @JoinColumn(unique = true)
    private Photo logo;
    @OneToMany
    @JoinTable(name = "coffee_shops_photos",
            joinColumns = @JoinColumn(name = "coffee_shop_id"),
            inverseJoinColumns = @JoinColumn(name = "photo_id", unique = true))
    private List<Photo> photos;
    @ManyToMany
    @JoinTable(name = "coffee_shops_features",
            joinColumns = @JoinColumn(name = "coffee_shop_id"),
            inverseJoinColumns = @JoinColumn(name = "feature_id"))
    private List<Feature> features;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name = "coffee_shops_product_prices",
            joinColumns = @JoinColumn(name = "coffee_shop_id"),
            inverseJoinColumns = @JoinColumn(name = "product_price_id", unique = true))
    private List<ProductPrice> products;

}
