package com.m.g.upload.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;

@Controller
@RequestMapping("/")
public class FileUploadController {


    @RequestMapping("/")
    public ModelAndView showUpload() {
        return new ModelAndView("upload");
    }


    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> uploadFile(
            @RequestParam("uploadfile") MultipartFile uploadfile) throws FileNotFoundException {
        String message = "";
        String line;
        if (uploadfile.getSize() > 0) {
            File file = convertMultiPartFileToFile(uploadfile);
            try(BufferedReader br = new BufferedReader(new FileReader(file))) {
                System.out.println("Printing lines:");
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
                message = "Uploaded the file successfully at : " + file.getAbsolutePath();
                System.out.println(message);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    private File convertMultiPartFileToFile(MultipartFile multipartFile) {
        final File file = new File(multipartFile.getOriginalFilename());
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(multipartFile.getBytes());
        } catch (final IOException ex) {
            System.out.println("Error converting the multi-part file to file= " + ex.getMessage());
        }
        return file;
    }

}
