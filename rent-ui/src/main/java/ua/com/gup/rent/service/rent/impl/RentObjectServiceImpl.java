package ua.com.gup.rent.service.rent.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ua.com.gup.rent.mapper.RentObjectMapper;
import ua.com.gup.rent.model.mongo.rent.Rent;
import ua.com.gup.rent.repository.rent.RentObjectRepository;
import ua.com.gup.rent.service.abstracted.RentGenericServiceImpl;
import ua.com.gup.rent.service.dto.rent.RentCreateDTO;
import ua.com.gup.rent.service.dto.rent.RentDTO;
import ua.com.gup.rent.service.dto.rent.RentEditDTO;
import ua.com.gup.rent.service.dto.rent.RentShortDetailsDTO;
import ua.com.gup.rent.service.rent.RentObjectService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import ua.com.gup.common.service.FileStorageService;

@Service
public class RentObjectServiceImpl extends RentGenericServiceImpl<RentDTO, String> implements RentObjectService {

    @Autowired
    private Environment e;

    @Autowired
    private RentObjectMapper rentObjectMapper;

    @Autowired
    private FileStorageService fileStorageService;

    @Value(value = "classpath:images/demo.png")
    private Resource demoImage;

    public RentObjectServiceImpl(@Autowired RentObjectRepository rentObjectRepository) {
        super(rentObjectRepository);
    }

    private void handleMultipartFile() {

    }

    @Override
    public void create(RentCreateDTO t) {
        List images = fileStorageService.save(t.getImages());
        Rent rent = rentObjectMapper.fromCreateDTOToRentObject(t);
        rent.setImages(images);
        getRepository().create(rent);
    }

    @Override
    public void update(RentEditDTO rentEditObjectDTO) {
        getRepository().update(null);
    }

    @Override
    public void deleteById(String rentObjectId) {
        getRepository().deleteById(rentObjectId);
    }

    @Override
    public List<RentShortDetailsDTO> findAll() {
        List<Rent> rents = getRepository().findAll();
        return rents.stream().map(rent -> rentObjectMapper.fromRentObjectToShortDTO(rent)).collect(Collectors.toList());
    }

    @Override
    protected RentObjectRepository getRepository() {
        return (RentObjectRepository) super.getRepository();
    }
}
