package entity;


import javax.persistence.*;
import java.util.Date;

@Entity
public class DayOffRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne()
    private CategoryEntity status;
    private int USerId;
    @Temporal(TemporalType.DATE)
    private Date start;
    @Temporal(TemporalType.DATE)
    private Date end;

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public CategoryEntity getStatus() {
        return status;
    }

    public void setStatus(CategoryEntity status) {
        this.status = status;
    }

    public int getUSerId() {
        return USerId;
    }

    public void setUSerId(int USerId) {
        this.USerId = USerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DayOffRequest{" +
                "id=" + id +
                ", status=" + status +
                ", USerId=" + USerId +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}