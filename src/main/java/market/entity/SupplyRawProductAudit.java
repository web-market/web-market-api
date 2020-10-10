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
@Table(name = "supply_raw_product_audit")
@Entity
public class SupplyRawProductAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supply_raw_product_audit_sequence")
    @SequenceGenerator(name = "supply_raw_product_audit_sequence", sequenceName = "supply_raw_product_audit_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "raw_product_quantity")
    private Long rawProductQuantity;

    @Column(name = "price_per_item")
    private Double pricePerItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "supply_id")
    @JsonIgnore
    private Supply supply;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "raw_product_id")
    @JsonIgnore
    private RawProduct rawProduct;

}
