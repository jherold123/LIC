package LIC.UC04v1.Bootstrap;

/*
The bootstrap java file utilizes a pregenerated list of doctors to populate our database.
The database is of the H2Variety as of now, but the spring framework will adapt our tables
to a different database type upon our needs, further into the project.
 */

import LIC.UC04v1.model.Doctor;
import LIC.UC04v1.repositories.ClerkshipRepository;
import LIC.UC04v1.repositories.DoctorRepository;
import LIC.UC04v1.repositories.StudentRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.*;
@Component//Bootstrap classlinks to database
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private ClerkshipRepository clerkshipRepository;
    private DoctorRepository doctorRepository;
    private StudentRepository studentRepository;

    @java.lang.Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            initData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//link to repositoriesz
    public Bootstrap(ClerkshipRepository clerkshipRepository, DoctorRepository doctorRepository, StudentRepository studentRepository) {
        this.clerkshipRepository = clerkshipRepository;
        this.doctorRepository = doctorRepository;
        this.studentRepository = studentRepository;
    }
    private void initData() throws IOException {
        String fileName = "doctors.csv";//get csv file with generated doctors

        ClassLoader classLoader = super.getClass().getClassLoader();

        File file = new File(classLoader.getResource(fileName).getFile());

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));//read file

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String st;//read file into database on instance of doctor at a time.
        br.readLine();
        String[] values;
        while ((st = br.readLine()) != null){
            values = st.split(",");
            Doctor doc = new Doctor();
            doc.setName(values[0]+" "+values[1]);
            doc.setEmail(values[2]);
            doc.setProfession(values[3]);
            System.out.println(values[4]);
            doc.setAvailable(values[4]);
            doctorRepository.save(doc);
        }

    }
}