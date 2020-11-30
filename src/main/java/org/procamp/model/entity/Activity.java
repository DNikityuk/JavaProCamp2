package org.procamp.model.entity;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "activity")
public class Activity {

    private Long activityId;
    private Building building;
    private String workName;
    private String measurment;
    private Double price;
    private Double amount;


    public Activity() {
    }

    public Activity(Building building, String workName, String measurment, Double price, Double amount) {
        this.building = building;
        this.workName = workName;
        this.measurment = measurment;
        this.price = price;
        this.amount = amount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_seq")
    @SequenceGenerator(name="activity_seq", sequenceName = "activity_inst_id_seq", allocationSize = 1)
    @Column(name = "inst_id")
    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    @ManyToOne
    @JoinColumn(name = "building_id",nullable = false)
    public Building getBuilding() {return building;}

    public void setBuilding(Building building) {
        this.building = building;
    }

    @Column(name = "work_name")
    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getMeasurment() {
        return measurment;
    }

    public void setMeasurment(String measurment) {
        this.measurment = measurment;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Activity)) return false;
        if ( obj == this) return true;

        Activity other = (Activity) obj;
        return this.activityId.equals(other.activityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityId);
    }
}
