package entity;


import javax.persistence.*;
import java.sql.Date;
import java.util.List;


@Entity
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> recivers;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "email")
    private List<File> attachments;
    @OneToOne
    private CategoryEntity importance;
    private int sender;
    private String context;

    public List<User> getRecivers() {
        return recivers;
    }

    public void setRecivers(List<User> recivers) {
        this.recivers = recivers;
    }

    private Date creationTIme;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public List<File> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<File> attachments) {
        this.attachments = attachments;
    }

    public CategoryEntity getImportance() {
        return importance;
    }

    public void setImportance(CategoryEntity importance) {
        this.importance = importance;
    }

    public Date getCreationTIme() {
        return creationTIme;
    }

    public void setCreationTIme(Date creationTIme) {
        this.creationTIme = creationTIme;
    }
}