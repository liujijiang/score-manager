package cn.redarm.studentscoremanager.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Author redarm
 * @Date 2020/6/20 3:38 下午
 **/
@Entity
@Table(name = "score_nature")
public class ScoreNature extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "score_nature_name")
    private String name;

    @Transient
    @OneToMany(targetEntity = CourseTaken.class, cascade = CascadeType.REMOVE, mappedBy = "scoreNature", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<CourseTaken> courseTakens = new HashSet<>();

    @Override
    public String toString() {
        return "ScoreNature{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CourseTaken> getCourseTakens() {
        return courseTakens;
    }

    public void setCourseTakens(Set<CourseTaken> courseTakens) {
        this.courseTakens = courseTakens;
    }

    public ScoreNature() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScoreNature)) return false;
        if (!super.equals(o)) return false;
        ScoreNature that = (ScoreNature) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getCourseTakens(), that.getCourseTakens());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
