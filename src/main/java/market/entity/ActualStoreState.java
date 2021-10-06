package market.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Data
@Table(name = "actual_store_state")
@Entity
public class ActualStoreState {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "actual_store_state_sequence")
    @SequenceGenerator(name = "actual_store_state_sequence", sequenceName = "actual_store_state_id_seq", allocationSize = 1)
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

    @Column(name = "overall_quantity")
    private Long overallQuantity;

}
