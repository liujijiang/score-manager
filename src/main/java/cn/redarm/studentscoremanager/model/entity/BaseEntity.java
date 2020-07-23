package cn.redarm.studentscoremanager.model.entity;

import cn.redarm.studentscoremanager.util.DateUtil;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Objects;

/**
 * @Author redarm
 * @Date 2020/6/19 3:10 下午
 **/
@MappedSuperclass
public class BaseEntity {

    private String createDate;

    private String updateDate;

    @PrePersist
    public void prePersist() {
        this.createDate = DateUtil.now();
        this.updateDate = DateUtil.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updateDate = DateUtil.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(getCreateDate(), that.getCreateDate()) &&
                Objects.equals(getUpdateDate(), that.getUpdateDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCreateDate(), getUpdateDate());
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                '}';
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
