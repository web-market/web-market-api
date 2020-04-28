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
@ToString(exclude = "subFolders")
@Table(name = "media_folder")
@Entity
public class MediaFolder {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "media_folder_sequence")
    @SequenceGenerator(name = "media_folder_sequence", sequenceName = "media_folder_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "parent_folder_id")
    @JsonIgnore
    private MediaFolder parentFolder;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentFolder")
    @Fetch(FetchMode.JOIN)
    @JsonIgnore
    private List<MediaFolder> subFolders;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mediaFolder")
    @Fetch(FetchMode.JOIN)
    @JsonIgnore
    private List<Media> media;

}
