package market.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Data
@Table(name = "filter")
@Entity
public class Filter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "filter_sequence")
    @SequenceGenerator(name = "filter_sequence", sequenceName = "filter_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "filter")
    @JsonIgnore
    private List<FilterValue> filterValues;

}
