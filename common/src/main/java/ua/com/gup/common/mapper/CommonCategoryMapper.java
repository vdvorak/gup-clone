package ua.com.gup.common.mapper;

import ua.com.gup.common.dto.offer.CommonCategoryCountDTO;
import ua.com.gup.common.model.offer.CommonCategoryCount;

public abstract class CommonCategoryMapper<T extends CommonCategoryCountDTO, K extends CommonCategoryCount> {

    public abstract T fromOfferCategoryCountToOfferCategoryCountDTO(K commonCategoryCount);
}
