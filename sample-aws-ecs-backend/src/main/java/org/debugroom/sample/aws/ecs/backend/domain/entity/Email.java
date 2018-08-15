package org.debugroom.sample.aws.ecs.backend.domain.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@IdClass(EmailPK.class)
public class Email {
    private String userId;
    private String emailId;
    private String email;
    private Integer ver;
    private Timestamp lastUpdatedDate;

    @Id
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "email_id")
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "ver")
    public Integer getVer() {
        return ver;
    }

    public void setVer(Integer ver) {
        this.ver = ver;
    }

    @Basic
    @Column(name = "last_updated_date")
    public Timestamp getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return Objects.equals(userId, email1.userId) &&
                Objects.equals(emailId, email1.emailId) &&
                Objects.equals(email, email1.email) &&
                Objects.equals(ver, email1.ver) &&
                Objects.equals(lastUpdatedDate, email1.lastUpdatedDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, emailId, email, ver, lastUpdatedDate);
    }
}
