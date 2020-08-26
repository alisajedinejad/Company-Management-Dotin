package entity;


import javax.persistence.*;
import java.util.Date;

@Entity
public class DayOffRequest  extends ParentConfig{

    @OneToOne()
    private CategoryEntity status;
    private int USerId;
    @Temporal(TemporalType.DATE)
    private Date start;
    @Temporal(TemporalType.DATE)
    private Date end;

    public DayOffRequest() {
        super.makeCreatedate();
        super.setActive(true);
        super.setVersion("1.0");
    }

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


    public void setId(Integer id) {
        this.id = id;
    }


}