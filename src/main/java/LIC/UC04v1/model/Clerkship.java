package LIC.UC04v1.model;

import javax.persistence.*;

//Clerkship class (table in database)

@Entity
public class Clerkship {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id; //id of clerkship
    @ManyToOne
    private Student student; //student associated to clerkship
    @OneToOne
    private Doctor doctor; //doctor associated to clerkship
    private String time; //time of clerkship
    //How are we formatting the time?? We need to re-evaluate


    public Clerkship(){}

    public Clerkship(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
