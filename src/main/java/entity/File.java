package entity;


import javax.persistence.*;

@Entity
public class File  extends ParentConfig{


    @ManyToOne
    private Email email;
    private String location;
    private String size;

    public File() {
        super.makeCreatedate();
        super.setActive(true);
        super.setVersion("1.0");
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}