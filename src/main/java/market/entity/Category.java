package market.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
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
@Table(name = "category")
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_sequence")
    @SequenceGenerator(name = "category_sequence", sequenceName = "category_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "sort_order")
    private Long sortOrder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parentCategory")
//    @JsonIgnore
    private List<Category> subCategories;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "category_product",
            joinColumns = {@JoinColumn(name = "category_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    @JsonIgnore
    private List<Product> products;

}
