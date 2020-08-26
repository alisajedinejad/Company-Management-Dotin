package entity;


import javax.persistence.*;
import java.sql.Date;
import java.util.List;


@Entity
public class Email extends ParentConfig {
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> recivers;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "email")
    private List<File> attachments;
    @OneToOne
    private CategoryEntity importance;
    private int sender;
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

}