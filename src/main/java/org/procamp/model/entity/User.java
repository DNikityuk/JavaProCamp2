package org.procamp.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "public")
public class User {

    private Long userId;
    private String userName;
    private String lastName;
    private String email;
    private String emailBackup;
    private String tn;
    private String tnBackup;
    private List<Report> reports;


    public User() {
    }

    public User(String userName, String lastName, String email, String tn) {
        this.userName = userName;
        this.lastName = lastName;
        this.email = email;
        this.tn = tn;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name="user_seq", sequenceName = "user_inst_id_seq", allocationSize = 1)
    @Column(name = "inst_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name = "user_name", nullable = false)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "email_backup")
    public String getEmailBackup() {
        return emailBackup;
    }

    public void setEmailBackup(String emailBackup) {
        this.emailBackup = emailBackup;
    }

    @Column(name = "TN", nullable = false)
    public String getTn() {
        return tn;
    }

    public void setTn(String tn) {
        this.tn = tn;
    }

    @Column(name = "TN_backup")
    public String getTnBackup() {
        return tnBackup;
    }

    public void setTnBackup(String tnBackup) {
        this.tnBackup = tnBackup;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) return false;
        if ( obj == this) return true;

        User other = (User) obj;
        return this.userId.equals(other.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
