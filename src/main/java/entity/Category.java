package entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_category")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "c_categoryId")),
        @AttributeOverride(name = "active", column = @Column(name = "c_active")),
        @AttributeOverride(name = "createdate", column = @Column(name = "c_createdate")),
        @AttributeOverride(name = "modificationdate", column = @Column(name = "c_modificationdate")),
        @AttributeOverride(name = "version", column = @Column(name = "c_version")),
        @AttributeOverride(name = "modificationdate", column = @Column(name = "c_modificationdate")),
})

public class Category extends ParentConfig {



    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<CategoryEntity> categoryEntities;

    @Column(name = "c_code")
    private String Code;

    public Category() {
        super.makeCreatedate();
        super.setActive(true);
        super.setVersion("1.0");

    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public List<CategoryEntity> getCategoryEntities() {
        return categoryEntities;
    }

    public void setCategoryEntities(List<CategoryEntity> employies) {
        this.categoryEntities = employies;
    }


}