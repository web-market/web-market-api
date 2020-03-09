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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Data
@Table(name = "manufacturer")
@Entity
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "manufacturer_sequence")
    @SequenceGenerator(name = "manufacturer_sequence", sequenceName = "manufacturer_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "manufacturer")
    @Fetch(FetchMode.JOIN)
    @JsonIgnore
    private List<Product> products;
}
