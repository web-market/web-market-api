package market.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "model")
@Entity
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "model_sequence")
    @SequenceGenerator(name = "model_sequence", sequenceName = "model_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "model")
    private String model;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "model")
    @Fetch(FetchMode.JOIN)
    @JsonIgnore
    private List<Product> products;

}
