package business.controllers;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import business.wrapper.TokenWrapper;
import data.daos.UserDao;
import data.entities.User;

@Controller
public class ImageController {
    
    private UserDao userDao;
    
    @Autowired
    ServletContext servletContext;
    
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    // class variable
    final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";

    final java.util.Random rand = new java.util.Random();

    // consider using a Map<String,Boolean> to say whether the identifier is being used or not
    final Set<String> identifiers = new HashSet<String>();
    
    public void uploadImageAssociation(TokenWrapper tokenWrapper, MultipartFile image) {
        
        User association = userDao.findByTokenValue(tokenWrapper.getToken());
        
        if (association != null) {        
            String randomName = "";
            if (image != null && !image.isEmpty()) {
                try {
                    validateImage(image);
                    randomName = randomIdentifier();
                } catch (RuntimeException re) {
                    throw new RuntimeException(re.toString());
                }
                try {
                    saveImage(randomName, image);
                    association.setImgName(randomName);
                    userDao.save(association);
                } catch (IOException e) {
                    throw new RuntimeException("Only JPG images are accepted");
                }
            }
        }        
    }
    
    private void validateImage(MultipartFile image) {
        if (!image.getContentType().equals("image/jpeg")) {
            throw new RuntimeException("Only JPG images are accepted");
        }
    }
    
    private String randomIdentifier() {
        StringBuilder builder = new StringBuilder();
        while (builder.toString().length() == 0) {
            int length = rand.nextInt(5) + 5;
            for (int i = 0; i < length; i++)
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            if (identifiers.contains(builder.toString()))
                builder = new StringBuilder();
        }
        return builder.toString();
    }

    private void saveImage(String filename, MultipartFile image) throws RuntimeException, IOException {
        try {
            File file = new File(servletContext.getRealPath("/") + "/images/" + filename + ".jpg");
            FileUtils.writeByteArrayToFile(file, image.getBytes());
            System.out.println("Go to the location:  " + file.toString() + " on your computer and verify that the image has been stored.");
        } catch (IOException e) {
            throw e;
        }
    }

}
