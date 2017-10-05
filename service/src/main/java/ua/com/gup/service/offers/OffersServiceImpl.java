package ua.com.gup.service.offers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ua.com.gup.domain.offer.Offer;
import ua.com.gup.domain.offer.OfferModerationReport;
import ua.com.gup.domain.offer.OfferRepository;
import ua.com.gup.dto.OfferRegistration;
import ua.com.gup.model.EntityPage;
import ua.com.gup.model.file.FileUploadWrapper;
import ua.com.gup.model.offer.Address;
import ua.com.gup.model.offer.Image;
import ua.com.gup.model.offer.RentedOfferPeriodInfo;
import ua.com.gup.model.offer.Reservation;
import ua.com.gup.model.offer.filter.OfferFilterOptions;
import ua.com.gup.service.filestorage.StorageService;
import ua.com.gup.util.SecurityOperations;
import ua.com.gup.util.Translit;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class OffersServiceImpl implements OffersService {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private StorageService storageService;

    @Override
    public void create(Offer offer) {
        OfferModerationReport offerModerationReport = new OfferModerationReport();
        //todo maybe add in future
        //offerModerationReport.setModerationStatus(ModerationStatus.NO);

        try {
            List<Image> images = null;
            if (offer.getImageIds().size() < 1) {
                images = new ArrayList<>();
                Image image = new Image();
                image.setUrl("null");
                image.setImageId("58edef514c8ea73b0dff0164"); //TODO hard)
                images.add(image);

            }
            //todo vdvorak
            //offer.setImageIds(images);
        } catch (NullPointerException e) {
            offer.setImageIds(null);
        }

        Offer newOffer = new Offer();
        newOffer.setAuthorId(offer.getAuthorId());
        //newOffer.setUserInfo(offer.getUserInfo());
        newOffer.setCreatedDate(ZonedDateTime.now());
        newOffer.setLastOfferModerationReport(offerModerationReport);
        //newOffer.setProperties(offer.getProperties())
        newOffer.setImageIds(offer.getImageIds());
        newOffer.setSeoUrl(offer.getSeoUrl());
        //newOffer.setSeoKey(offer.getSeoKey())
        newOffer.setCategories(offer.getCategories());
        newOffer.setYoutubeVideoId(offer.getYoutubeVideoId());
        newOffer.setTitle(offer.getTitle());
        newOffer.setDescription(offer.getDescription());
        newOffer.setPrice(offer.getPrice());
        //newOffer.setOldPrice(0l)
        //newOffer.setPriceCanBeNegotiated(offer.getPriceCanBeNegotiated())
        //newOffer.setUsed(offer.getUsed())
        //newOffer.setActive(Boolean.TRUE)
        //newOffer.setDeleted(false)
        newOffer.setAddress(offer.getAddress());
        newOffer.getPrice().setCurrency(offer.getPrice().getCurrency());
        //newOffer.setCanBeReserved(offer.getCanBeReserved())
        //newOffer.setMaximumReservedPeriod(offer.getMaximumReservedPeriod())
        //newOffer.setCanBeRented(offer.getCanBeRented())
        //newOffer.setAvailableShippingMethods(offer.getAvailableShippingMethods())
        //newOffer.setAvailablePaymentMethods(offer.getAvailablePaymentMethods())
        //newOffer.setShowOrdersCount(offer.isShowOrdersCount())
        //newOffer.setPaidServices(offer.getPaidServices())
        //newOffer.setMonthOfPrices(offer.getMonthOfPrices())
        //newOffer.setRents(offer.getRents())
        //newOffer.setMadeInUkraine(offer.isMadeInUkraine());

        offerRepository.create(newOffer);
        offer.setId(newOffer.getId());
    }

    @Override
    public Offer findById(String offerId) {
        return offerRepository.findById(offerId);
    }

    @Override
    public Offer findBySeoKey(String seoKey) {
        return offerRepository.findBySeoKey(seoKey);
    }


    @Override
    public boolean offerExists(String id) {
        return offerRepository.offerExists(id);
    }

    @Override
    public void delete(String id) {
        List<String> imagesIds = findById(id).getImageIds();
        if (imagesIds != null) {
            offerRepository.delete(id);
        }

    }

    @Override
    public EntityPage<Offer> findOffersWihOptions(OfferFilterOptions offerFilterOptions) {
        return offerRepository.findOffersWithOptions(offerFilterOptions);
    }


    @Override
    public Offer edit(Offer oldOffer) {
        Offer newOffer = new Offer();
        newOffer.setId(oldOffer.getId());
        newOffer.setLastOfferModerationReport(oldOffer.getLastOfferModerationReport());
        newOffer.setAuthorId(oldOffer.getAuthorId());
        //newOffer.setUserInfo(oldOffer.getUserInfo());
        newOffer.setCreatedDate(ZonedDateTime.now());
        newOffer.setCategories(oldOffer.getCategories());
        //newOffer.setProperties(oldOffer.getProperties());
        newOffer.setImageIds(oldOffer.getImageIds());
        newOffer.setSeoUrl(oldOffer.getSeoUrl());
        //newOffer.setSeoKey(oldOffer.getSeoKey());
        //newOffer.setSeoCategory(oldOffer.getSeoCategory());
        newOffer.setYoutubeVideoId(oldOffer.getYoutubeVideoId());
        newOffer.setTitle(oldOffer.getTitle());
        newOffer.setDescription(oldOffer.getDescription());
        newOffer.setPrice(oldOffer.getPrice());
        //newOffer.setOldPrice(offerRepository.findById(oldOffer.getId()).getPrice() != null ? offerRepository.findById(oldOffer.getId()).getPrice() : 0l)
        //newOffer.setPriceCanBeNegotiated(oldOffer.getPriceCanBeNegotiated());
        //newOffer.setUsed(oldOffer.getUsed());
        //newOffer.setActive(oldOffer.getActive());
        newOffer.setAddress(oldOffer.getAddress());
        newOffer.getPrice().setCurrency(oldOffer.getPrice().getCurrency());
        //newOffer.setCanBeReserved(oldOffer.getCanBeReserved());
        //newOffer.setMaximumReservedPeriod(oldOffer.getMaximumReservedPeriod());
        //newOffer.setCanBeRented(oldOffer.getCanBeRented());
        //newOffer.setAvailableShippingMethods(oldOffer.getAvailableShippingMethods());
        //newOffer.setAvailablePaymentMethods(oldOffer.getAvailablePaymentMethods());
        //newOffer.setShowOrdersCount(oldOffer.isShowOrdersCount()); //TODO
        //newOffer.setDeleted(oldOffer.isDeleted());
        //newOffer.setPaidServices(oldOffer.getPaidServices());
        //newOffer.setMonthOfPrices(oldOffer.getMonthOfPrices());
        //newOffer.setRents(oldOffer.getRents());
        //newOffer.setMadeInUkraine(oldOffer.isMadeInUkraine());

        return offerRepository.findAndUpdate(newOffer);
    }

    @Override
    public ResponseEntity<String> editByUser(OfferRegistration offerRegistration, MultipartFile[] files) {

        Offer updatedOffer = offerRegistration.getOffer();

        // check is offer not null and exist
        if (updatedOffer.getId() == null) {
            return new ResponseEntity<>("You did not sent offer ID", HttpStatus.BAD_REQUEST);
        } else if (!offerExists(updatedOffer.getId())) {
            return new ResponseEntity<>("Offer with this ID: " + updatedOffer.getId() + " is not exist", HttpStatus.NOT_FOUND);
        }

        Offer oldOffer = findById(updatedOffer.getId());

        String userId = SecurityOperations.getLoggedUserId();


        // Check if current user is not an author
        if (!findById(updatedOffer.getId()).getAuthorId().equals(userId)) {
            return new ResponseEntity<>("You are not author of this offer", HttpStatus.FORBIDDEN);
        }


        // update SEO url title for offer
        String newTranslitTitle = Translit.makeTransliteration(updatedOffer.getTitle());
        String newSeoUrl = newTranslitTitle + "-" + oldOffer.getSeoUrl();
        updatedOffer.setSeoUrl(newSeoUrl);


        updatedOffer.setImageIds(prepareImageBeforeOfferUpdate(oldOffer, offerRegistration, files));


        // if critical information was changed in the offer - we must resubmit this offer for the moderation
        //todo maybe add in future
        /*if (isOfferWasCriticalChanged(oldOffer, updatedOffer, files)) {
            oldOffer.getLastOfferModerationReport().setModerationStatus(ModerationStatus.NO);
            updatedOffer.setOfferModerationReports(oldOffer.getOfferModerationReports());
        }*/

        Offer newOffer = edit(updatedOffer);

        return new ResponseEntity<>(newOffer.getSeoUrl(), HttpStatus.OK);
    }

    @Override
    public void reserveOffer(String offerId, Reservation reservation) {
        //todo vdvorak this method not use
/*        Reservation newReservation = new Reservation()
                .setProfileId(reservation.getProfileId())
                .setUserContactInfo(reservation.getUserContactInfo())
                .setCreatedDateEqualsToCurrentDate();
        Offer newOffer = new Offer();
        newOffer.setId(offerId);
        //newOffer.setReservation(newReservation);
        Offer updatedOffer = offerRepository.findAndUpdate(newOffer);*/
    }

    @Override
    public void deleteReservation(String offerId) {
        offerRepository.deleteReservation(offerId);
    }

    @Override
    public void rentOffer(String offerId, RentedOfferPeriodInfo rentedOfferPeriodInfo) {
        offerRepository.rentOffer(offerId, rentedOfferPeriodInfo);
    }

    @Override
    public void deleteRent(String offerId, String rentId) {
        offerRepository.deleteRent(offerId, rentId);
    }

    @Override
    public void setActive(String offerId, boolean isActive) {
        Offer offer = new Offer();
        offer.setId(offerId);
        //offer.setActive(isActive);
        offerRepository.findAndUpdate(offer);
    }

    @Override
    public Set<String> getMatchedNames(String name) {
        return offerRepository.getMatchedNames(name);
    }

    @Override
    public String getMainOfferImage(Offer offer) {
        List<String> imageListId = offer.getImageIds();
        if (imageListId != null) {
            for (String imageId : imageListId) {
                if (!StringUtils.isEmpty(imageId)) {
                    return imageId;
                }
            }
        }
        return null;
    }
    //----------------------------------------------------- Helpers ----------------------------------------------------

    /**
     * Create OfferFilterOption object for search offers relevant to current on it's city.
     *
     * @param offer the offer to which we must find relevant offers.
     * @return the OfferFilterOptions object.
     */
    private OfferFilterOptions offerFilterOptionsPreparatorForRelevantSearchWithCity(Offer offer) {
        OfferFilterOptions offerFilterOptions = new OfferFilterOptions();
        offerFilterOptions.setAddress(new Address());

        // add categories in filter
        offerFilterOptions.setCategories(offer.getCategories());

        // add address in filter
        if (offer.getAddress().getCity() != null) {
            offerFilterOptions
                    .getAddress()
                    .setCity(offer.getAddress().getCity());
            /*offerFilterOptions.getAddress().setArea(offer.getAddress().getArea());*/
        }

        return offerFilterOptions;
    }


    private List<String> prepareImageBeforeOfferUpdate(Offer oldOffer, OfferRegistration newOfferRegistration, MultipartFile[] files) {

        List<String> resultImages = new ArrayList<>();
        FileUploadWrapper fileUploadWrapper = new FileUploadWrapper();

        List<Image> newImageList = newOfferRegistration.getImages();
        List<String> oldImageList = oldOffer.getImageIds();
        deleteImages(oldImageList, newImageList); // delete unnecessary images

        for (Image image : newImageList) {
            Integer currentImageIndex = image.getIndex();
            Image newImage = new Image();
            if (currentImageIndex != null) {
                // Сохраняем одну фотографию, которая лежит по указанному индексу
                try {
                    fileUploadWrapper
                            .setServiceName("offers")
                            .setInputStream(files[currentImageIndex].getInputStream())
                            .setContentType(files[currentImageIndex].getContentType())
                            .setFilename(files[currentImageIndex].getOriginalFilename());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String newImageId = storageService.saveCachedImageOffer(fileUploadWrapper);
                newImage.setImageId(newImageId);
                resultImages.add(newImageId);
            } else if (!StringUtils.isEmpty(image.getImageId())) {
                newImage.setImageId(image.getImageId());
                resultImages.add(image.getImageId());
            }
        }

        return resultImages;
    }


    /**
     * Find if in the new ImageList some images were deleted and delete them from the DB.
     *
     * @param oldImageList - the old Image list.
     * @param newImageList - the new Image list.
     */
    private void deleteImages(List<String> oldImageList, List<Image> newImageList) {
        Set<String> setOfTheImagesForDelete = new HashSet<>();
        boolean isDeleted;
        for (String oldImage : oldImageList) {
            isDeleted = true;
            for (Image newImage : newImageList) {
                if (oldImage.equals(newImage.getImageId())) {
                    isDeleted = false;
                }
            }
            if (isDeleted) {
                setOfTheImagesForDelete.add(oldImage);
            }
        }
        storageService.deleteListOfOfferImages(setOfTheImagesForDelete);
    }


}
