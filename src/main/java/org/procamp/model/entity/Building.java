package org.procamp.model.entity;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "building")
public class Building {


    private Long buildingId;
    private Report report;
    private String buildingName;
    private boolean isActive;
    private List<Activity> activities;


    public Building() {
    }

    public Building(Report report, String buildingName, boolean isActive) {
        this.report = report;
        this.buildingName = buildingName;
        this.isActive = isActive;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "building_seq")
    @SequenceGenerator(name="building_seq", sequenceName = "building_inst_id_seq", allocationSize = 1)
    @Column(name = "inst_id")
    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    @ManyToOne
    @JoinColumn(name = "report_id", nullable = false)
    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    @Column(name = "building_name")
    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    @Column(name = "is_active")
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Building)) return false;
        if ( obj == this) return true;

        Building other = (Building) obj;
        return this.buildingId.equals(other.buildingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buildingId);
    }
}
