package ua.com.gup.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.mongo.model.offer.OfferCategory;
import ua.com.gup.service.category.CategoryService;

import java.util.LinkedList;
import java.util.List;

@Component
public class OfferCategoryMapper {
    @Autowired
    private CategoryService categoryService;


    public LinkedList<OfferCategory> offerCategoriesByCategoriesIds(List<Integer> ids) {
        LinkedList<OfferCategory> offerCategories = new LinkedList<>();
        categoryService.findByCodeInOrderByCodeAsc(ids).forEach(e -> offerCategories.add(new OfferCategory(e)));
        return offerCategories;
    }

}
