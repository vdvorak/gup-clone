package ua.com.itproekt.gup.service.nace;

import ua.com.itproekt.gup.model.nace.NACE;

import java.util.List;


public interface NaceService {
    public NACE findById(String id);

    public NACE addNace(NACE nace);

    public List<NACE> findAll();

    public NACE updateNace(NACE nace);
//        public List<DepartmentOrNace> findAllDialogues(Member member);

}
