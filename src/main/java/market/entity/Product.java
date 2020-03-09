package market.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Data
@Table(name = "product")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", precision = 10, scale = 2)
    private Double price;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "minimum_quantity")
    private Long minimumQuantity;

    @Column(name = "sort_order")
    private Long sortOrder;

    @Column(name = "is_subtract_product", columnDefinition = "boolean default false")
    private boolean isSubtractProduct;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private boolean isActive;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy="products")
    @JsonIgnore
    private List<Category> categories;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy="products")
    @JsonIgnore
    private List<FilterValue> filterValues;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    @JsonIgnore
    private List<Image> images;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "manufacturer_id", updatable = false)
    @JsonIgnore
    private Manufacturer manufacturer;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "related_products",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "related_product_id")}
    )
    @JsonIgnore
    private List<Product> relatedToCurrentProduct;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "related_products",
            joinColumns = {@JoinColumn(name = "related_product_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    @JsonIgnore
    private List<Product> currentProductRelatedTo;

}
