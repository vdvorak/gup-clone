package ua.com.gup.rent.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.common.command.CommandException;
import ua.com.gup.common.dto.CommonCreateDTO;
import ua.com.gup.rent.command.rent.offer.bid.CreateRentOfferBidCommand;
import ua.com.gup.rent.component.executor.RentCommandExecutor;
import ua.com.gup.rent.event.offer.bid.RentOfferBidCreatedEvent;
import ua.com.gup.rent.service.dto.rent.bid.RentOfferBidCreateDTO;
import ua.com.gup.rent.service.rent.RentOfferBidService;
import ua.com.gup.rent.service.rent.RentOfferService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/offers")
public class RentOfferBidEndpoint {

    @Autowired
    private RentOfferBidService bidService;

    @Autowired
    private RentOfferService offerService;

    @Autowired
    private RentCommandExecutor executor;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PreAuthorize("hasAuthority('CREATE_OFFER_BID')")
    @RequestMapping(value = "/{seo}/bids", method = RequestMethod.POST)
    public ResponseEntity createOffer(
            @PathVariable String seo,
            @Valid @RequestBody RentOfferBidCreateDTO offerCreateDTO) throws CommandException {

        if (!offerService.existsBySeoUrl(seo)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        CreateRentOfferBidCommand createRentOfferCommand = new CreateRentOfferBidCommand(bidService, offerCreateDTO);
        executor.doCommand(createRentOfferCommand);
        applicationEventPublisher.publishEvent(new RentOfferBidCreatedEvent(bidService.findOne(createRentOfferCommand.getObjectId())));
        return new ResponseEntity<>(new CommonCreateDTO(createRentOfferCommand.getObjectId()), HttpStatus.CREATED);
    }
}
