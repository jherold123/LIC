package LIC.UC04v1.model;

import javax.persistence.*;
import java.util.List;

/*
Student class (table in database)
 */

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //
    private int id; //student id

    @OneToMany(mappedBy = "student") //has a one to many association with clerkships
    private List<Clerkship> clerkships; //clerkships the student has
    private String Name;

    public Student(){}

    public Student(int id) {
        this.id = id;
    }
}
