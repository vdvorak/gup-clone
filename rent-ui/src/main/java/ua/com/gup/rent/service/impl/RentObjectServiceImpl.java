package ua.com.gup.rent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.com.gup.rent.dto.rentobject.CreateRentObjectDTO;
import ua.com.gup.rent.dto.rentobject.EditRentObjectDTO;
import ua.com.gup.rent.dto.rentobject.RentObjectDTO;
import ua.com.gup.rent.dto.rentobject.ShortDetailsRentObjectDTO;
import ua.com.gup.rent.mapper.RentObjectMapper;
import ua.com.gup.rent.model.mongo.RentObject;
import ua.com.gup.rent.repository.RentObjectRepository;
import ua.com.gup.rent.service.RentObjectService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentObjectServiceImpl extends GenericServiceImpl<RentObjectDTO, String> implements RentObjectService {

    @Autowired
    private RentObjectMapper rentObjectMapper;

    public RentObjectServiceImpl(@Autowired RentObjectRepository rentObjectRepository) {
        super(rentObjectRepository);
    }


    @Override
    public void create(CreateRentObjectDTO t) {
        RentObject rentObject = rentObjectMapper.fromCreateDTOToRentObject(t);
//        rentObject.setOwnerId(SecurityContextHolder.);
        getRepository().create(rentObject);
    }

    @Override
    public void update(EditRentObjectDTO editRentObjectDTO) {
        getRepository().update(null);
    }

    @Override
    public void deleteById(String rentObjectId) {
        getRepository().deleteById(rentObjectId);
    }

    @Override
    public List<ShortDetailsRentObjectDTO> findAll() {
        List<RentObject> rentObjects = getRepository().findAll();
        return rentObjects.stream().map(rentObject -> rentObjectMapper.fromRentObjectToShortDTO(rentObject)).collect(Collectors.toList());
    }

    @Override
    protected RentObjectRepository getRepository() {
        return (RentObjectRepository) super.getRepository();
    }
}
