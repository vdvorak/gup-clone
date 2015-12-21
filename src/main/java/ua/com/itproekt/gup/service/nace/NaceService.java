package ua.com.itproekt.gup.service.nace;

import ua.com.itproekt.gup.model.nace.DepartmentOrNace;

import java.util.List;


public interface NaceService {
    public DepartmentOrNace findById(String id);

    public DepartmentOrNace addNace(DepartmentOrNace nace);

    public List<DepartmentOrNace> findAll();

    public DepartmentOrNace updateNace(DepartmentOrNace dialogue);
//        public List<DepartmentOrNace> findAllDialogues(Member member);

}
