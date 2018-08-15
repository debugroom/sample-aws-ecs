package org.debugroom.sample.aws.ecs.backend.domain.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "grp", schema = "public", catalog = "tmp")
public class Group {
    private String groupId;
    private String groupName;
    private Integer ver;
    private Timestamp lastUpdatedDate;

    @Id
    @Column(name = "group_id")
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "group_name")
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
        Group group = (Group) o;
        return Objects.equals(groupId, group.groupId) &&
                Objects.equals(groupName, group.groupName) &&
                Objects.equals(ver, group.ver) &&
                Objects.equals(lastUpdatedDate, group.lastUpdatedDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(groupId, groupName, ver, lastUpdatedDate);
    }
}
