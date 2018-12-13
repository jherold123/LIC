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
    private String Time; //time of clerkship


    public Clerkship(){}

    public Clerkship(int id) {
        this.id = id;
    }
}
