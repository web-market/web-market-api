package market.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
@ToString(exclude = "products")
@Table(name = "media")
@Entity
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "media_sequence")
    @SequenceGenerator(name = "media_sequence", sequenceName = "media_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "public_id")
    private String publicId;

    @Column(name = "height")
    private Long height;

    @Column(name = "width")
    private Long width;

    @Column(name = "format")
    private String format;

    @Column(name = "url")
    private String url;

    @Column(name = "secure_url")
    private String secureUrl;

    @Column(name = "is_primary", columnDefinition = "boolean default false")
    private boolean isPrimary;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "media_category_id")
    @JsonIgnore
    private MediaCategory mediaCategory;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_media",
            joinColumns = {@JoinColumn(name = "media_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    @JsonIgnore
    private List<Product> products;

}
