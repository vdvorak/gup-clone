package ua.com.gup.rent.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ua.com.gup.common.mapper.CommonCategoryMapper;
import ua.com.gup.rent.model.mongo.category.RentOfferCategory;
import ua.com.gup.rent.model.rent.RentOfferCategoryCount;
import ua.com.gup.rent.model.rent.RentOfferCategoryShort;
import ua.com.gup.rent.service.category.RentOfferCategoryService;
import ua.com.gup.rent.service.dto.category.RentOfferCategoryCreateDTO;
import ua.com.gup.rent.service.dto.category.RentOfferCategoryUpdateDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferCategoryCountDTO;
import ua.com.gup.rent.service.sequence.RentSequenceService;

import java.util.LinkedList;
import java.util.List;

@Component
public class RentOfferCategoryMapper extends CommonCategoryMapper<RentOfferCategoryCountDTO, RentOfferCategoryCount> {

    private static final String RENT_CATEGORY_SEQUENCE_ID = "rent_category_sequence";
    private static final String RENT_CATEGORY_SEQUENCE_ORDER = "rent_category_sequence_order";

    @Autowired
    private RentSequenceService rentSequenceService;
    @Autowired
    private RentOfferCategoryService categoryService;

    public RentOfferCategory categoryCreateDTOToCategory(RentOfferCategoryCreateDTO rentOfferCategoryCreateDTO) {
        RentOfferCategory rentOfferCategory = new RentOfferCategory();
        fromCategoryCreateDTOToCategory(rentOfferCategoryCreateDTO, rentOfferCategory);
        rentOfferCategory.setCode((int) rentSequenceService.getNextSequenceValue(RENT_CATEGORY_SEQUENCE_ID));
        return rentOfferCategory;
    }

    public RentOfferCategory categoryUpdateDTOToCategory(RentOfferCategoryUpdateDTO rentOfferCategoryUpdateDTO) {
        RentOfferCategory rentOfferCategory = new RentOfferCategory();
        fromCategoryCreateDTOToCategory(rentOfferCategoryUpdateDTO, rentOfferCategory);
        rentOfferCategory.setCode(rentOfferCategoryUpdateDTO.getCode());
        rentOfferCategory.setId(rentOfferCategoryUpdateDTO.getId());
        return rentOfferCategory;
    }

    private void fromCategoryCreateDTOToCategory(RentOfferCategoryCreateDTO source, RentOfferCategory target) {
        source.getTitle().replaceAll((k, v) -> StringUtils.isEmpty(v) ? "" : v.trim());
        source.getDescription().replaceAll((k, v) -> StringUtils.isEmpty(v) ? "" : v.trim());

        target.setActive(source.isActive());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());
        target.setKey(source.getKey());
        target.setParent(source.getParent());
        target.setColor(source.getColor());
        target.setOrder((int) rentSequenceService.getNextSequenceValue(RENT_CATEGORY_SEQUENCE_ORDER));

    }

    @Override
    public RentOfferCategoryCountDTO fromOfferCategoryCountToOfferCategoryCountDTO(RentOfferCategoryCount offerCategoryCount) {
        RentOfferCategoryCountDTO offerCategoryCountDTO = new RentOfferCategoryCountDTO();
        offerCategoryCountDTO.setCount(offerCategoryCount.getCount());
        String categoriesRegExp = offerCategoryCount.getCategoriesRegExp();
        final String[] categories = categoriesRegExp.split("/");
        final LinkedList<RentOfferCategory> offerCategories = categoryService.getRentOfferCategories(Integer.parseInt(categories[categories.length - 1]));
        final RentOfferCategory offerCategory = offerCategories.get(offerCategories.size() - 1);
        offerCategoryCountDTO.setCode(offerCategory.getCode());
        offerCategoryCountDTO.setKey(offerCategory.getKey());
        offerCategoryCountDTO.setTitle(offerCategory.getTitle());
        return offerCategoryCountDTO;
    }

    public LinkedList<RentOfferCategoryShort> offerCategoriesByCategoriesIds(List<Integer> ids) {
        LinkedList<RentOfferCategoryShort> offerCategories = new LinkedList<>();
        categoryService.findByCodeInOrderByCodeAsc(ids).forEach(e -> offerCategories.add(new RentOfferCategoryShort(e)));
        return offerCategories;
    }
}