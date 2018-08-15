package org.debugroom.sample.aws.ecs.backend.domain.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class EmailPK implements Serializable {
    private String userId;
    private String emailId;

    @Column(name = "user_id")
    @Id
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "email_id")
    @Id
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailPK emailPK = (EmailPK) o;
        return Objects.equals(userId, emailPK.userId) &&
                Objects.equals(emailId, emailPK.emailId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, emailId);
    }
}
