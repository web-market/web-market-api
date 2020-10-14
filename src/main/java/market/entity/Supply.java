package market.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Table(name = "supply")
@Entity
public class Supply {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supply_sequence")
    @SequenceGenerator(name = "supply_sequence", sequenceName = "supply_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "identification_number", unique = true)
    private String identificationNumber;

    @Column(name = "comment")
    private String comment;

    @Column(name = "arrival_date")
    private LocalDateTime arrivalDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SupplyStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "store_id")
    @JsonIgnore
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "provider_id")
    @JsonIgnore
    private Provider provider;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "supply")
    @JsonIgnore
    private List<SupplyRawProductAudit> supplyRawProductAudits;

}
