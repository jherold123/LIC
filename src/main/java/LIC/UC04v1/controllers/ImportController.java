package LIC.UC04v1.controllers;

import LIC.UC04v1.model.Doctor;
import LIC.UC04v1.repositories.DoctorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

@Controller
public class ImportController {

    public ImportController(DoctorRepository doctorRepository){
   //     this.doctorRepository = doctorRepository;
    }

    @GetMapping(path = "/import-Data")
    public String getImports(Model model){
        return "ImportData";
    }
    /*
    This method maps to webpage requests of the format /(profession)/(id)
    We pass parameters, in string form, in the url
     */

    @RequestMapping(path = "/import-Data/{type}/{file}")
    public String docImport(Model model, @PathVariable String type, MultipartFile file) throws IOException {
        String fileLocation;
        InputStream in = file.getInputStream();
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        fileLocation = path.substring(0, path.length() - 1) + file.getOriginalFilename();
        FileOutputStream f = new FileOutputStream(fileLocation);
        int ch = 0;
        while ((ch = in.read()) != -1) {
            f.write(ch);
        }
        f.flush();
        f.close();

        //Passing attributes to the thymeleaf front end
        if (type.equals("doctors"))
            model.addAttribute("docImportMsg", "File: " + file.getOriginalFilename()
                    + " has been uploaded successfully!");
        else
            model.addAttribute("stuImportMsg", "File: " + file.getOriginalFilename()
                    + " has been uploaded successfully!");
        return "ImportData";
    }
}
