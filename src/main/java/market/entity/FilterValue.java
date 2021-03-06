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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Data
@Table(name = "filter_value")
@Entity
public class FilterValue {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "filter_value_sequence")
    @SequenceGenerator(name = "filter_value_sequence", sequenceName = "filter_value_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "value")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "filter_id")
    private Filter filter;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_filter_value",
            joinColumns = {@JoinColumn(name = "filter_value_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    @JsonIgnore
    private List<Product> products;

}
