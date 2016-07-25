//package ua.com.itproekt.gup.controller.test.services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import ua.com.itproekt.gup.service.filestorage.StorageService;
//
///**
// * Created by Zver on 13.10.2015.
// */
//@Controller
//@RequestMapping(value = "/storage")
//public class ImageTestController {
//
//    @Autowired
//    private StorageService storageService;
//
////    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
////    public void getById (@PathVariable(value="id") String id, HttpServletResponse response) throws IOException {
////        GridFSDBFile file = storageService.get(serviceName, id);
////        System.out.println("************" + file);
////        if (file!=null) {
////            byte[] data = IOUtils.toByteArray(file.getInputStream());
////            response.setContentType(file.getContentType());
////            response.setContentLength((int)file.getLength());
////            response.getOutputStream().write(data);
////            response.getOutputStream().flush();
////        } else {
////            response.setStatus(HttpStatus.NOT_FOUND.value());
////        }
////    }
////
////
////    @RequestMapping(value="/upload/", method=RequestMethod.POST)
////    public @ResponseBody
////    String handleFileUpload(@RequestParam("image") MultipartFile image){
////        String name = "test11";
////        if (!image.isEmpty()) {
////            try {
//////                byte[] bytes = file.getBytes();
////                storageService.save(serviceName, image.getInputStream(), image.getContentType(), image.getOriginalFilename());
////
////                return "You successfully uploaded " + name + " into " + name + "-uploaded !";
////            } catch (Exception e) {
////                return "You failed to upload " + name + " => " + e.getMessage();
////            }
////        } else {
////            return "You failed to upload " + name + " because the file was empty.";
////        }
////    }
//
////    @RequestMapping(value = "/store", method = RequestMethod.POST)
////    public ResponseEntity<String> store (@RequestParam MultipartFile file, WebRequest webRequest) {
////        try {
////            String storedId = storageService.save(file.getInputStream(), file.getContentType(), file.getOriginalFilename());
////            String storedURL = "/storage/id/" + storedId;
////            HttpHeaders responseHeaders = new HttpHeaders();
//////            responseHeaders.setLocation(location);
////            return new ResponseEntity<String>(storedURL, responseHeaders, HttpStatus.OK);
////        } catch (Exception e) {
////            return new ResponseEntity<String>(e.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////    }
//}
