package market.projection.filterValue;

import org.springframework.beans.factory.annotation.Value;

public interface FilterValueView {

    Long getId();

    String getValue();

    Long getSortOrder();

    @Value("#{target.getFilter().getId()}")
    Long getFilterId();

}
