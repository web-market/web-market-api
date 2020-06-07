package market.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString(exclude = "products")
@Table(name = "media")
@Entity
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "media_sequence")
    @SequenceGenerator(name = "media_sequence", sequenceName = "media_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "media_folder_id")
    @JsonIgnore
    private MediaFolder mediaFolder;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "media")
    @Fetch(FetchMode.JOIN)
    @JsonIgnore
    private List<File> files;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_media",
            joinColumns = {@JoinColumn(name = "media_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    @JsonIgnore
    private List<Product> products;

}
