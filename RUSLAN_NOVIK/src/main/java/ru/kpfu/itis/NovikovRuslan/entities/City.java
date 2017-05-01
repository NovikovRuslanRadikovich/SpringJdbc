package ru.kpfu.itis.NovikovRuslan.entities;





import javax.persistence.*;
import java.util.List;



@Entity
@Table(name="cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cities_id_generator")
    @SequenceGenerator(name = "cities_id_generator", sequenceName = "cities_id_seq", allocationSize = 1)
    Long id;

    @Column(name = "name", unique = true)
    String name;

    @OneToMany(mappedBy = "polyclinic_city",fetch = FetchType.EAGER, orphanRemoval=true)
    List<Polyclinic> polyclinics;

    @OneToMany(mappedBy = "doctor_city",fetch = FetchType.EAGER, orphanRemoval=true)
    List<Doctor> doctors;

    public City(){

    }

    public City(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Polyclinic> getPolyclinics() {
        return polyclinics;
    }

    public void setPolyclinics(List<Polyclinic> polyclinics) {
        this.polyclinics = polyclinics;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

}
