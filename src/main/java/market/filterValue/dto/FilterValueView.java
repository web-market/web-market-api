package market.filterValue.dto;

import org.springframework.beans.factory.annotation.Value;

public interface FilterValueView {

    Long getId();

    String getName();

    Long getSortOrder();

    @Value("#{target.getFilter().getId()}")
    Long getFilterId();

}
