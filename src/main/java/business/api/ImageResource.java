package business.api;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import business.controllers.ImageController;
import business.wrapper.AnimalWrapper;
import business.wrapper.TokenWrapper;

@RestController
@RequestMapping(Uris.SERVLET_MAP + Uris.IMAGES)
public class ImageResource {    
    
    @Autowired 
    ServletContext servletContext;
    
    private ImageController imageController;
    
    @Autowired public void setImageController(ImageController imageController) {
        this.imageController = imageController;
    }
    
    @ResponseBody
    @RequestMapping(value = "/photo/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] testphoto(@PathVariable String id) throws IOException {
        InputStream in = servletContext.getResourceAsStream("/images/" + id + ".jpg");
        return IOUtils.toByteArray(in);
    }
            
    @RequestMapping(value = Uris.ASSOCIATIONS, method = RequestMethod.POST, headers = "content-type=multipart/*")
    public void uploadImage(@Valid final TokenWrapper tokenWrapper, @RequestParam(value = "image", required = false) MultipartFile image) {        
        imageController.uploadImageAssociation(tokenWrapper, image);  
    }
    
    @RequestMapping(value = Uris.ANIMALS, method = RequestMethod.POST, headers = "content-type=multipart/*")
    public void uploadImage(@Valid final AnimalWrapper animalWrapper, @RequestParam(value = "image", required = false) MultipartFile[] images) {        
        imageController.uploadImagesAnimal(animalWrapper, images);  
    }
}
