package LIC.UC04v1.model;

import javax.persistence.*;

//Doctor Class (table in database)

@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id; //doctor id
    @OneToOne //has a one to one association with clerkship
    private Clerkship clerkship; //doctors clerkship
    private String name; //doctor name
    private String email; //doctor email
    private String profession;
    private String available; //doctors availability (binary string)
    //!! NOTE: Someone needs to explain in the comments how this binary string works!!
    // So someone in the future is not horribly confused. DO IT OR ELSE I will be sad :(

    //Getter and setter methods for doctor class

    public String isAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Clerkship getClerkship() {
        return clerkship;
    }

    public void setClerkship(Clerkship clerkship) {
        this.clerkship = clerkship;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Doctor(){

    }

    public Doctor(String name){
        this.name = name;
    }

    public Doctor(int id) {
        this.id = id;
    }
}
