package market.filter.dto;

import org.springframework.beans.factory.annotation.Value;

public interface FilterView {

    Long getId();

    String getName();

    String getDisplayName();

    Long getSortOrder();

    @Value("#{target.getFilterValues().size() != 0}")
    Boolean getHasFilterValues();
}
