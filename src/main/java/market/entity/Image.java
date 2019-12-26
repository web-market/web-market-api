package market.entity;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@Table(name = "image")
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_sequence")
    @SequenceGenerator(name = "image_sequence", sequenceName = "image_id_seq", allocationSize = 1)
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
    @JoinColumn(name = "product_id")
    private Product product;

}
