package entity;//package entity;


import service.CalendarService;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ali on 25/08/2020.
 */

@MappedSuperclass
public class ParentConfig implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    private boolean active;
    private String createdate;
    private String modificationdate;
    private String version;

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

    public void makeCreatedate() {
        Date thisTime = CalendarService.convertToJalali(new java.util.Date());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");



        this.createdate =dateFormat.format(thisTime) ;
    }

    public void makeModificationdate() {
        Date thisTime = CalendarService.convertToJalali(new java.util.Date());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        this.modificationdate = dateFormat.format(thisTime);
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getModificationdate() {
        return modificationdate;
    }

    public void setModificationdate(String modificationdate) {
        this.modificationdate = modificationdate;
    }
}
