package entity;


import javax.persistence.*;
import java.util.List;

@Entity
public class Category extends ParentConfig{
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<CategoryEntity> categoryEntities;
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