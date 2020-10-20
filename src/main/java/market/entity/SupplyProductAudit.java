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
@Table(name = "supply_product_audit")
@Entity
public class SupplyProductAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supply_product_audit_sequence")
    @SequenceGenerator(name = "supply_product_audit_sequence", sequenceName = "supply_product_audit_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "product_quantity")
    private Long productQuantity;

    @Column(name = "price_per_item", precision = 10, scale = 2)
    private Double pricePerItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "supply_id")
    @JsonIgnore
    private Supply supply;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

}
