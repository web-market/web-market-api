package market.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Data
@Table(name = "store_operation")
@Entity
public class StoreOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_operation_sequence")
    @SequenceGenerator(name = "store_operation_sequence", sequenceName = "store_operation_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "id_from")
    @JsonIgnore
    private Store fromStore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_to")
    @JsonIgnore
    private Store toStore;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    @Column(name = "quantity")
    private Long quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation")
    private StoreOperationType operation;

}
