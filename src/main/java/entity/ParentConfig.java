package entity;//package entity;


import service.CalendarService;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ali on 25/08/2020.
 */
@Entity
@Table(name = "ParentConfig")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class ParentConfig implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    int id;
    boolean active;
    @Temporal(TemporalType.DATE)
    Date createdate;
    @Temporal(TemporalType.DATE)
    Date modificationdate;
    String version;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public void makeCreatedate() {
       Date thisTime = CalendarService.convertToJalali(new java.util.Date());
        this.createdate = thisTime;
    }



    public Date getModificationdate() {
        return modificationdate;
    }

    public void setModificationdate(Date modificationdate) {
        this.modificationdate = modificationdate;
    }
    public void makeModificationdate() {
        Date thisTime = CalendarService.convertToJalali(new java.util.Date());
        this.modificationdate = thisTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
