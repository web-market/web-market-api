package market.product.rawProduct.dto;

import org.springframework.beans.factory.annotation.Value;

public interface RawProductView {

    Long getId();

    String getName();

    String getDescription();

    @Value("#{target.getManufacturer().getId()}")
    Long getManufacturerId();

}
