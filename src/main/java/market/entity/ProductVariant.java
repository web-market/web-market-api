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
@Table(name = "product_variant")
@Entity
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_variant_sequence")
    @SequenceGenerator(name = "product_variant_sequence", sequenceName = "product_variant_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "raw_product_id")
    @JsonIgnore
    private RawProduct rawProduct;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productVariant")
    @Fetch(FetchMode.JOIN)
    @JsonIgnore
    private List<StoreProductVariantAudit> storeProductVariantAudits;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "productVariants")
    @JsonIgnore
    private List<Media> media;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_variant_filter_value",
            joinColumns = {@JoinColumn(name = "product_variant_id")},
            inverseJoinColumns = {@JoinColumn(name = "filter_value_id")}
    )
    @JsonIgnore
    private List<FilterValue> filterValues;

}
