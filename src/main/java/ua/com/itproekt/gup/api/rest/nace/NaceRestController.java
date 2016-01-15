package ua.com.itproekt.gup.api.rest.nace;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.com.itproekt.gup.model.nace.DepartmentOrNace;
import ua.com.itproekt.gup.service.nace.NaceService;

import java.beans.PropertyEditorSupport;
import java.util.List;


@RestController
@RequestMapping("api/rest/naceService")
public class NaceRestController {

    private Logger log = Logger.getLogger(NaceRestController.class);
    private static final String LOGGED_TITLE = "- NaceRestController - api/rest/naceService/";

    @Autowired
    NaceService naceService;


    @RequestMapping(
            value = "/nace/create",
            method = RequestMethod.POST,
            headers = "Content-Type=application/json")
    public ResponseEntity<DepartmentOrNace> addDialogue (@RequestBody DepartmentOrNace nace,
                                                 UriComponentsBuilder builder) {
        log.log(Level.INFO, LOGGED_TITLE + "nace/create Hello =)");

        nace = naceService.addNace(nace);
        log.log(Level.INFO, LOGGED_TITLE + "nace/create - nace was created successfully");

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("api/rest/naceService/nace/read/id/{id}")
                        .buildAndExpand(nace.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    // this method provide getting all message from existing dialogue.
    @RequestMapping(value="/read/id/{id}",
            method=RequestMethod.POST)
    public ResponseEntity<DepartmentOrNace> getMessagesForDialogue(@PathVariable("id") DepartmentOrNace nace) {
        log.log(Level.INFO, LOGGED_TITLE + "nace/read/id/{id} Hello =)");
        if (nace == null) {
            log.log(Level.ERROR, LOGGED_TITLE + "nace/read/id/{id} - NO SUCH NACE");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        log.log(Level.INFO, LOGGED_TITLE + "nace/read/id/{id} - nace was find successfully");
        return new ResponseEntity<>(nace, HttpStatus.OK);
    }

    @RequestMapping(value="/nace/read/all",
            method=RequestMethod.POST
    )
    public ResponseEntity<List<DepartmentOrNace>> getAllDialogues(){
        List<DepartmentOrNace> naces = naceService.findAll();

        log.log(Level.INFO, LOGGED_TITLE + "/nace/read/all - all nace was find successfully");
        return new ResponseEntity<>(naces, HttpStatus.OK);
    }

    @RequestMapping(value="/nace/update/id/{id}",
            method=RequestMethod.POST
    )
    public ResponseEntity<DepartmentOrNace> updateDialogue(@PathVariable("id") String id, @RequestBody DepartmentOrNace nace){
        if(nace == null || naceService.findById(id) == null){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if(nace.getId()== null) {
            nace.setId(id);
        }

        nace = naceService.updateNace(nace);
        return new ResponseEntity<>(nace, HttpStatus.OK);
    }

    @InitBinder
    protected void dialogueBinder(WebDataBinder binder) {
        binder.registerCustomEditor(DepartmentOrNace.class,
                new PropertyEditorSupport() {
                    @Override
                    public void setAsText(String id) {
                        DepartmentOrNace nace = null;
                        if (id != null && !id.trim().isEmpty()) {
                            nace = naceService.findById(id);
                        }else {
                            log.log(Level.ERROR, "CAN'T FOUND Nace WITH id = " + id);
                        }
                        setValue(nace);
                    }
                }
        );
    }

}
