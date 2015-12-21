package ua.com.itproekt.gup.service.nace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.dao.nace.NaceRepository;
import ua.com.itproekt.gup.model.nace.DepartmentOrNace;

import java.util.List;

@Service
public class NaceServiceImpl implements NaceService {
    @Autowired
    NaceRepository naceRepository;

    @Override
    public DepartmentOrNace findById(String id){
        return naceRepository.findOne(id);
    }

    @Override
    public DepartmentOrNace addNace(DepartmentOrNace nace) {return naceRepository.save(nace);}

    @Override
    public List<DepartmentOrNace> findAll(){
        return naceRepository.findAll();
    }

    @Override
    public DepartmentOrNace updateNace(DepartmentOrNace dialogue) {
        return naceRepository.save(dialogue);
    }

//    @Override
//    public List<DepartmentOrNace> findDialogues(Member member) {
//        return naceRepository.findByMembersIn(member);
//    }

}
