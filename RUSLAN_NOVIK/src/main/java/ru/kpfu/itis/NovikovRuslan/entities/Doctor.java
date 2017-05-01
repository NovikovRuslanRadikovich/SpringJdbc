package ru.kpfu.itis.NovikovRuslan.entities;



import javax.persistence.*;


@Entity
@Table(name="doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctors_id_generator")
    @SequenceGenerator(name = "doctors_id_generator", sequenceName = "doctors_id_seq", allocationSize = 1)
    Long id;

    @Column(name = "fio")
    String fio;

    @Column(name = "specialization")
    String specialization;

    @Column(name = "regalia")
    String regalia;

    @Column(name = "telephone")
    String telephone;

    @OneToOne(mappedBy = "doctor",fetch = FetchType.EAGER,orphanRemoval=true)
    Schedule schedule;

    @ManyToOne(targetEntity = Polyclinic.class)
    @JoinColumn(name = "doctor_polyclinic", referencedColumnName = "id")
    Polyclinic doctor_polyclinic;

    @ManyToOne(targetEntity = City.class)
    @JoinColumn(name = "doctor_city",referencedColumnName = "id")
    City doctor_city;

    @Column(name = "city_name")
    String city_name;

    @Column(name = "polyclinic_name")
    String polyclinic_name;



    public Doctor(){

    }


    public Doctor(City doctor_city, Polyclinic doctor_polyclinic, String fio, String specialization, String regalia, String telephone
    , String city_name, String polyclinic_name){
        this.doctor_city = doctor_city;
        this.doctor_polyclinic =  doctor_polyclinic;
        this.fio = fio;
        this.specialization = specialization;
        this.regalia = regalia;
        this.telephone = telephone;
        this.city_name = city_name;
        this.polyclinic_name = polyclinic_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRegalia() {
        return regalia;
    }

    public void setRegalia(String regalia) {
        this.regalia = regalia;
    }

    public String getFio() {return fio;}

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Polyclinic getDoctor_polyclinic() {
        return doctor_polyclinic;
    }

    public void setDoctor_polyclinic(Polyclinic doctor_polyclinic) {
        this.doctor_polyclinic = doctor_polyclinic;
    }

    public void setDoctor_city(City doctor_city) {
        this.doctor_city = doctor_city;
    }

    public City getDoctor_city() {
        return doctor_city;
    }

    public Schedule getSchedule() {return schedule;}

    public void setSchedule(Schedule schedule) {this.schedule = schedule;}

    public String getCity_name() {return city_name;}

    public void setCity_name(String city_name) {this.city_name = city_name;}

    public String getPolyclinic_name() {return polyclinic_name;}

    public void setPolyclinic_name(String polyclinic_name) {this.polyclinic_name = polyclinic_name;}

}
