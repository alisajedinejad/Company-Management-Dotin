package entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_user")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "c_user") ),
        @AttributeOverride(name = "active", column = @Column(name = "c_active") ),
        @AttributeOverride(name = "createdate", column = @Column(name = "c_createdate") ),
        @AttributeOverride(name = "modificationdate", column = @Column(name = "c_modificationdate") ),
        @AttributeOverride(name = "version", column = @Column(name = "c_version") ),
        @AttributeOverride(name = "modificationdate", column = @Column(name = "c_modificationdate") ),
})
public class User  extends ParentConfig{

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="t_employies_user",
            joinColumns = @JoinColumn( name="c_userId"),
            inverseJoinColumns = @JoinColumn( name="c_emailId")
    )
    private List<User> employies;
    @ManyToOne()

    private User manager;
    @ManyToOne
    private CategoryEntity role;

    public User() {
        super.makeCreatedate();
        super.setActive(true);
        super.setVersion("1.0");
    }

    public CategoryEntity getRole() {
        return role;
    }

    public void setRole(CategoryEntity role) {
        this.role = role;
    }
    @Column(name = "c_name")

    private String name;
    @Column(name = "c_email")

    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public List<User> getEmployies() {
        return employies;
    }

    public void setEmployies(List<User> employies) {
        this.employies = employies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}