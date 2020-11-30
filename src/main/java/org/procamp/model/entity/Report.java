package org.procamp.model.entity;


import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "report")
public class Report {

    private Long reportId;
    private User user;
    private String reportName;
    private Double price;
    private Date orderDate;
    private List<Building> buildings;


    public Report() {
    }

    public Report(User user, String reportName, Double price, Date orderDate) {
        this.user = user;
        this.reportName = reportName;
        this.price = price;
        this.orderDate = orderDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "report_seq")
    @SequenceGenerator(name="report_seq", sequenceName = "report_inst_id_seq", allocationSize = 1)
    @Column(name = "inst_id")
    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "report_name")
    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column(name = "order_date", nullable = false)
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @OneToMany(mappedBy = "report", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Report)) return false;
        if ( obj == this) return true;

        Report other = (Report) obj;
        return this.reportId.equals(other.reportId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reportId);
    }
}
