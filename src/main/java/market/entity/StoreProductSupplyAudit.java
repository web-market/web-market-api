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
@Table(name = "store_product_supply_audit")
@Entity
public class StoreProductSupplyAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_product_audit_sequence")
    @SequenceGenerator(name = "store_product_audit_sequence", sequenceName = "store_product_audit_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "store_id")
    @JsonIgnore
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "supply_id")
    @JsonIgnore
    private Supply supply;

    @Column(name = "product_quantity")
    private Long productQuantity;
}
