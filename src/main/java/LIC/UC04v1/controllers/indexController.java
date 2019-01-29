package LIC.UC04v1.controllers;

import LIC.UC04v1.model.Doctor;
import LIC.UC04v1.repositories.DoctorRepository;
import org.jboss.jandex.IndexReader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
/*
The index controller is what we are using to send and recieve data between the front end and the back end.
 */
@Controller
public class indexController {

    public String que = "";

//Test 1
    private DoctorRepository doctorRepository;//link to doctor reposotory


    public indexController(DoctorRepository doctorRepository){
        this.doctorRepository = doctorRepository;
    }

    @GetMapping(path = "/")
    public String getDoctors(Model model){
       /* int count = 0;
        ArrayList<Doctor> AvailDoctors = new ArrayList<Doctor>();
        for(Doctor doc: doctorRepository.findAll()){
            if(doc.getId()==3)doc.setAvailable(false);
            if(doc.isAvailable() && doc.getProfession().equals(que)){
                count++;
                AvailDoctors.add(doc);
            }
        }
        model.addAttribute("doctors", AvailDoctors);
        model.addAttribute("count", count);*/

        return "index";
    }
    /*
    This method maps to webpage requests of the format /(profession)/(id)
    We pass parameters, in string form, in the url
     */
    @RequestMapping(path = "/{profession}/{id}")
    public String neuro(Model model, @PathVariable String profession, @PathVariable String id){
        int count = 0;
        System.out.println(profession);
        ArrayList<Doctor> AvailNeuroDocs = new ArrayList<Doctor>();
        int counts[] = new int[28];
        for(Doctor doc: doctorRepository.findAll()){//this loop tracks how many doctors are available on certain days and times.
            if((doc.getProfession().equals(profession))){
                for(int i = 0; i<28;i++){
                    if(doc.getAvailable().charAt(i) == '1'){
                        counts[i] = counts[i]+1;
                    }
                }
                count++;
                System.out.println(profession+" "+count);
                AvailNeuroDocs.add(doc);
            }
        }
        //Passing attributes to the thymeleaf front ent
        model.addAttribute("id", id);
        model.addAttribute("prof", profession);
        model.addAttribute("w1SunM", counts[0]);
        model.addAttribute("w1SunA", counts[1]);
        model.addAttribute("w1MonM", counts[2]);
        model.addAttribute("w1MonA", counts[3]);
        model.addAttribute("w1TuesM", counts[4]);
        model.addAttribute("w1TuesA", counts[5]);
        model.addAttribute("w1WedsM", counts[6]);
        model.addAttribute("w1WedsA", counts[7]);
        model.addAttribute("w1ThurM", counts[8]);
        model.addAttribute("w1ThurA", counts[9]);
        model.addAttribute("w1FriM", counts[10]);
        model.addAttribute("w1FriA", counts[11]);
        model.addAttribute("w1SatM", counts[12]);
        model.addAttribute("w1SatA", counts[13]);

        model.addAttribute("w2SunM", counts[14]);
        model.addAttribute("w2SunA", counts[15]);
        model.addAttribute("w2MonM", counts[16]);
        model.addAttribute("w2MonA", counts[17]);
        model.addAttribute("w2TuesM", counts[18]);
        model.addAttribute("w2TuesA", counts[19]);
        model.addAttribute("w2WedsM", counts[20]);
        model.addAttribute("w2WedsA", counts[21]);
        model.addAttribute("w2ThurM", counts[22]);
        model.addAttribute("w2ThurA", counts[23]);
        model.addAttribute("w2FriM", counts[24]);
        model.addAttribute("w2FriA", counts[25]);
        model.addAttribute("w2SatM", counts[26]);
        model.addAttribute("w2SatA", counts[27]);

        return "index";
    }

    @RequestMapping(path = "/joke")//This is a test function
    public String joke(){
        System.out.println("joke");
        return "index";
    }
}
