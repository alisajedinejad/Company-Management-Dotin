package entity;


import javax.persistence.*;

@Entity
@Table(name = "t_categoryEntity")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "c_categoryEntityId")),
        @AttributeOverride(name = "active", column = @Column(name = "c_active")),
        @AttributeOverride(name = "createdate", column = @Column(name = "c_createdate")),
        @AttributeOverride(name = "modificationdate", column = @Column(name = "c_modificationdate")),
        @AttributeOverride(name = "version", column = @Column(name = "c_version")),
        @AttributeOverride(name = "modificationdate", column = @Column(name = "c_modificationdate")),
})
public class CategoryEntity extends ParentConfig {


    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @Column(name = "c_code")
    private String Code;

    @Column(name = "c_name")
    private String name;

    public CategoryEntity() {
        super.makeCreatedate();
        super.setActive(true);
        super.setVersion("1.0");
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}