package ua.com.gup.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.common.mapper.CommonCategoryMapper;
import ua.com.gup.dto.offer.OfferCategoryCountDTO;
import ua.com.gup.mongo.model.offer.OfferCategory;
import ua.com.gup.mongo.model.offer.OfferCategoryCount;
import ua.com.gup.service.category.CategoryService;

import java.util.*;

@Component
public class OfferCategoryMapper extends CommonCategoryMapper<OfferCategoryCountDTO, OfferCategoryCount> {
    @Autowired
    private CategoryService categoryService;

    @Override
    public OfferCategoryCountDTO fromOfferCategoryCountToOfferCategoryCountDTO(OfferCategoryCount offerCategoryCount) {
        OfferCategoryCountDTO offerCategoryCountDTO = new OfferCategoryCountDTO();
        offerCategoryCountDTO.setCount(offerCategoryCount.getCount());
        String categoriesRegExp = offerCategoryCount.getCategoriesRegExp();
        final String[] categories = categoriesRegExp.split("/");
        final LinkedList<OfferCategory> offerCategories = categoryService.getOfferCategories(Integer.parseInt(categories[categories.length - 1]));
        final OfferCategory offerCategory = offerCategories.get(offerCategories.size() - 1);
        offerCategoryCountDTO.setCode(offerCategory.getCode());
        offerCategoryCountDTO.setKey(offerCategory.getKey());
        offerCategoryCountDTO.setTitle(offerCategory.getTitle());
        return offerCategoryCountDTO;
    }

    public LinkedList<OfferCategory> offerCategoriesByCategoriesIds(List<Integer> ids) {
        LinkedList<OfferCategory> offerCategories = new LinkedList<>();
        categoryService.findByCodeInOrderByCodeAsc(ids).forEach(e -> offerCategories.add(new OfferCategory(e)));
        return offerCategories;
    }

}
