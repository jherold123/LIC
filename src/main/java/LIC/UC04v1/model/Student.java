package LIC.UC04v1.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(mappedBy = "student")
    private List<Clerkship> clerkships;
    private String Name;

    public Student(){}

    public Student(int id) {
        this.id = id;
    }
}
