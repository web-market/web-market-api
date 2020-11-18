package market.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "ui_product")
@Entity
public class UIProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "sort_order")
    private Long sortOrder;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private boolean isActive;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "category_ui_product",
            joinColumns = {@JoinColumn(name = "ui_product_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    @JsonIgnore
    private List<Category> categories;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "uiProducts")
    @JsonIgnore
    private List<Media> media;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "related_ui_products",
            joinColumns = {@JoinColumn(name = "ui_product_id")},
            inverseJoinColumns = {@JoinColumn(name = "related_ui_product_id")}
    )
    @JsonIgnore
    private List<UIProduct> relatedToCurrentUIProduct;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "related_ui_products",
            joinColumns = {@JoinColumn(name = "related_ui_product_id")},
            inverseJoinColumns = {@JoinColumn(name = "ui_product_id")}
    )
    @JsonIgnore
    private List<UIProduct> currentUIProductRelatedTo;

}
