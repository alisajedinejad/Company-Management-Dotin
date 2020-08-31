package entity;


import javax.persistence.*;

@Entity
@Table(name = "t_file")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "c_file") ),
        @AttributeOverride(name = "active", column = @Column(name = "c_active") ),
        @AttributeOverride(name = "createdate", column = @Column(name = "c_createdate") ),
        @AttributeOverride(name = "modificationdate", column = @Column(name = "c_modificationdate") ),
        @AttributeOverride(name = "version", column = @Column(name = "c_version") ),
        @AttributeOverride(name = "modificationdate", column = @Column(name = "c_modificationdate") ),
})
public class File  extends ParentConfig{


    @ManyToOne
    private Email email;
    @Column(name = "c_location")

    private String location;
    @Column(name = "c_size")

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