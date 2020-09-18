package entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_dayOffRequest")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "c_dayOffRequestId")),
        @AttributeOverride(name = "active", column = @Column(name = "c_active")),
        @AttributeOverride(name = "createdate", column = @Column(name = "c_createdate")),
        @AttributeOverride(name = "modificationdate", column = @Column(name = "c_modificationdate")),
        @AttributeOverride(name = "version", column = @Column(name = "c_version")),
        @AttributeOverride(name = "modificationdate", column = @Column(name = "c_modificationdate")),
})
public class DayOffRequest extends ParentConfig {

    @ManyToOne(fetch = FetchType.EAGER)
    private CategoryEntity status;

    @ManyToOne
    private User USerId;

    @Column(name = "c_start")
    private String start;

    @Column(name = "c_end")
    private String end;


    @Column(name = "c_start_clock")
    private String startClock;

    @Column(name = "c_end_clock")
    private String endClock;

    public String getStartClock() {
        return startClock;
    }

    public void setStartClock(String startClock) {
        this.startClock = startClock;
    }

    public String getEndClock() {
        return endClock;
    }

    public void setEndClock(String endClock) {
        this.endClock = endClock;
    }

    public DayOffRequest() {
        super.makeCreatedate();
        super.setActive(true);
        super.setVersion("1.0");
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public CategoryEntity getStatus() {
        return status;
    }

    public void setStatus(CategoryEntity status) {
        this.status = status;
    }

    public User getUSerId() {
        return USerId;
    }

    public void setUSerId(User USerId) {
        this.USerId = USerId;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}