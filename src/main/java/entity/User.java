package entity;


import javax.persistence.*;
import java.util.List;

@Entity
public class User  extends ParentConfig{

    @OneToMany(fetch = FetchType.EAGER)
    private List<User> employies;
    @ManyToOne()
    private User manager;
    @OneToOne
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

    private String name;

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