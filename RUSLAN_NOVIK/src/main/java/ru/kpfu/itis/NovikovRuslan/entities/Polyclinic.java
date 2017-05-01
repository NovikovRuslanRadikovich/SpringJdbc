package ru.kpfu.itis.NovikovRuslan.entities;




import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="polyclinics")
public class Polyclinic {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "polyclinics_id_generator")
    @SequenceGenerator(name = "polyclinics_id_generator", sequenceName = "polyclinics_id_seq", allocationSize = 1)
    Long id;

    @Column(name = "polyclinic_name")
    String polyclinic_name;

    @Column(name = "polyclinic_address")
    String polyclinic_address;

    @ManyToOne(targetEntity = City.class)
    @JoinColumn(name = "polyclinic_city", referencedColumnName = "id" )
    City polyclinic_city;

    @OneToMany(mappedBy = "doctor_polyclinic",fetch = FetchType.LAZY, orphanRemoval=true)
    List<Doctor> polyclinic_doctors;

    public Polyclinic(){

    }

    public Polyclinic(String polyclinic_name,String polyclinic_address,City polyclinic_city) {
        this.polyclinic_name = polyclinic_name;
        this.polyclinic_address = polyclinic_address;
        this.polyclinic_city = polyclinic_city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPolyclinic_name(){return polyclinic_name;}

    public void setPolyclinic_name(String polyclinic_name){this.polyclinic_name = polyclinic_name;}

    public String getPolyclinic_address(){return polyclinic_address;}

    public void setPolyclinic_address(String polyclinic_address){this.polyclinic_address = polyclinic_address;}

    public List<Doctor> getPolyclinic_doctors(){return polyclinic_doctors;}

    public void setPolyclinic_doctors(List<Doctor> polyclinic_doctors){this.polyclinic_doctors = polyclinic_doctors;}

    public City getPolyclinic_city() {return polyclinic_city;}

    public void setPolyclinic_city(City polyclinic_city) {this.polyclinic_city = polyclinic_city;}

}
