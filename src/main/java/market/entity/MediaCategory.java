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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Data
@ToString(exclude = "subCategories")
@Table(name = "media_category")
@Entity
public class MediaCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "media_category_sequence")
    @SequenceGenerator(name = "media_category_sequence", sequenceName = "media_category_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "parent_category_id")
    @JsonIgnore
    private MediaCategory parentCategory;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentCategory")
    @Fetch(FetchMode.JOIN)
    @JsonIgnore
    private List<MediaCategory> subCategories;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mediaCategory")
    @Fetch(FetchMode.JOIN)
    @JsonIgnore
    private List<Media> media;

}
