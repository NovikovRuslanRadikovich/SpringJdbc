package ru.kpfu.itis.NovikovRuslan.entities;

import javax.persistence.*;



@Entity
@Table(name="schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "schedule_id_sequence")
    @SequenceGenerator(name = "schedule_id_sequence", sequenceName = "schedule_id_seq", allocationSize = 1)
    Long id;

    @OneToOne(targetEntity = Doctor.class)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    Doctor doctor;

    @Column(name = "monday_schedule")
    String monday_schedule;

    @Column(name = "tuesday_schedule")
    String tuesday_schedule;

    @Column(name = "wednesday_schedule")
    String wednesday_schedule;

    @Column(name = "thursday_schedule")
    String thursday_schedule;

    @Column(name = "friday_schedule")
    String friday_schedule;

    @Column(name = "saturday_schedule")
    String saturday_schedule;

    @Column(name = "sunday_schedule")
    String sunday_schedule;

    public Schedule(){

    }

    public Schedule(Doctor doctor,String monday_schedule,String tuesday_schedule,String wednesday_schedule,String thursday_schedule,String friday_schedule,String saturday_schedule,String sunday_schedule){
        this.doctor = doctor;
        this.monday_schedule = monday_schedule;
        this.tuesday_schedule = tuesday_schedule;
        this.wednesday_schedule = wednesday_schedule;
        this.thursday_schedule = thursday_schedule;
        this.friday_schedule = friday_schedule;
        this.saturday_schedule = saturday_schedule;
        this.sunday_schedule = sunday_schedule;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public Doctor getDoctor() {return doctor;}

    public void setDoctor(Doctor doctor) {this.doctor = doctor;}

    public String getMonday_schedule() {
        return monday_schedule;
    }

    public void setMonday_schedule(String monday_schedule) {this.monday_schedule = monday_schedule;}

    public String getTuesday_schedule() {return tuesday_schedule;}

    public void setTuesday_schedule(String tuesday_schedule) {this.tuesday_schedule = tuesday_schedule;}

    public String getWednesday_schedule() {return wednesday_schedule;}

    public void setWednesday_schedule(String wednesday_schedule) {this.wednesday_schedule = wednesday_schedule;}

    public String getThursday_schedule() {return thursday_schedule;}

    public void setThursday_schedule(String thursday_schedule) {this.thursday_schedule = thursday_schedule;}

    public String getFriday_schedule() {return friday_schedule;}

    public void setFriday_schedule(String friday_schedule) {this.friday_schedule = friday_schedule;}

    public String getSaturday_schedule() {return saturday_schedule;}

    public void setSaturday_schedule(String saturday_schedule) {this.saturday_schedule = saturday_schedule;}

    public String getSunday_schedule() {return sunday_schedule;}

    public void setSunday_schedule(String sunday_schedule) {this.sunday_schedule = sunday_schedule;}

}
