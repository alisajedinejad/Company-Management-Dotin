package entity;


import javax.persistence.*;
import java.sql.Date;
import java.util.List;


@Entity
@Table(name = "t_email")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "c_emailId") ),
        @AttributeOverride(name = "active", column = @Column(name = "c_active") ),
        @AttributeOverride(name = "createdate", column = @Column(name = "c_createdate") ),
        @AttributeOverride(name = "modificationdate", column = @Column(name = "c_modificationdate") ),
        @AttributeOverride(name = "version", column = @Column(name = "c_version") ),
        @AttributeOverride(name = "modificationdate", column = @Column(name = "c_modificationdate") ),
})
public class Email extends ParentConfig {

    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> recivers;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "email")
    private List<File> attachments;

    @ManyToOne(fetch = FetchType.EAGER)
    private CategoryEntity importance;

    @ManyToOne
    private User sender;

    @Column(name = "c_context")
    private String context;

    public Email() {
        super.makeCreatedate();
        super.setActive(true);
        super.setVersion("1.0");
    }

    public List<User> getRecivers() {
        return recivers;
    }

    public void setRecivers(List<User> recivers) {
        this.recivers = recivers;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
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

}