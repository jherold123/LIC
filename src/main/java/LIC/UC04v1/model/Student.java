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
    private String email; //student email

    public Student(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Clerkship> getClerkships() {
        return clerkships;
    }

    public void setClerkships(List<Clerkship> clerkships) {
        this.clerkships = clerkships;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Student(int id) {
        this.id = id;
    }
}
