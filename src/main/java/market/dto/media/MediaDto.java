package market.dto.media;

import lombok.Getter;
import lombok.Setter;
import market.entity.MediaCategory;

import java.time.LocalDateTime;

@Getter
@Setter
public class MediaDto {

    private String name;
    private String publicId;
    private String format;
    private String mediaType;
    private String url;
    private String secureUrl;
    private LocalDateTime creationDate;

    private MediaDto(String name, String publicId, String format, String mediaType,
                     String url, String secureUrl, LocalDateTime creationDate) {
        this.name = name;
        this.publicId = publicId;
        this.format = format;
        this.mediaType = mediaType;
        this.url = url;
        this.secureUrl = secureUrl;
        this.creationDate = creationDate;
    }

    public static class Builder {

        private String name;
        private String publicId;
        private String format;
        private String mediaType;
        private String url;
        private String secureUrl;
        private LocalDateTime creationDate;
        private MediaCategory mediaCategory;

        public Builder(String name) {
            this.name = name;
        }

        public Builder setPublicId(String publicId) {
            this.publicId = publicId;
            return this;
        }

        public Builder setFormat(String format) {
            this.format = format;
            return this;
        }

        public Builder setMediaType(String mediaType) {
            this.mediaType = mediaType;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setSecureUrl(String secureUrl) {
            this.secureUrl = secureUrl;
            return this;
        }

        public Builder setCreationDate(LocalDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

//        public Builder setMediaCategory(MediaCategory mediaCategory) {
//            this.mediaCategory = mediaCategory;
//            return this;
//        }

        public MediaDto build() {
            return new MediaDto(this.name,
                    this.publicId,
                    this.format,
                    this.mediaType,
                    this.url,
                    this.secureUrl,
                    this.creationDate
                    /*this.mediaCategory*/);
        }
    }

}
