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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Data
@Table(name = "raw_product")
@Entity
public class RawProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "raw_product_sequence")
    @SequenceGenerator(name = "raw_product_sequence", sequenceName = "raw_product_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "rawProduct")
    private UIProduct uiProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "manufacturer_id", updatable = false)
    @JsonIgnore
    private Manufacturer manufacturer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rawProduct")
    @Fetch(FetchMode.JOIN)
    @JsonIgnore
    private List<ProductVariant> productVariants;
}
