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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@Table(name = "store_product_variant_audit")
@Entity
public class StoreProductVariantAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_product_variant_audit_sequence")
    @SequenceGenerator(name = "store_product_variant_audit_sequence", sequenceName = "store_product_variant_audit_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "product_variant_quantity")
    private Long productVariantQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "store_id")
    @JsonIgnore
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "product_variant_Id")
    @JsonIgnore
    private ProductVariant productVariant;

}
