package market.projection.productVariant;

import org.springframework.beans.factory.annotation.Value;

public interface ProductVariantView {

    Long getId();

    String getName();

    String getDescription();

    @Value("#{target.getRawProduct().getId()}")
    Long getRawProductId();

}
