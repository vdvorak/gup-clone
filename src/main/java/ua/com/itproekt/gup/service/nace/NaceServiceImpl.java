package ua.com.itproekt.gup.service.nace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.dao.nace.NaceRepository;
import ua.com.itproekt.gup.model.nace.DepartmentOrNace;
import ua.com.itproekt.gup.model.nace.NACE;

import java.util.List;

@Service
public class NaceServiceImpl implements NaceService {
    @Autowired
    NaceRepository naceRepository;

    @Override
    public NACE findById(String id){
        return naceRepository.findOne(id);
    }

    @Override
    public NACE addNace(NACE nace) {return naceRepository.save(nace);}

    @Override
    public List<NACE> findAll(){
        return naceRepository.findAll();
    }

    @Override
    public NACE updateNace(NACE nace) {
        return naceRepository.save(nace);
    }

//    @Override
//    public List<DepartmentOrNace> findDialogues(Member member) {
//        return naceRepository.findByMembersIn(member);
//    }

}
