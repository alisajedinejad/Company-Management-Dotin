package entity;


import javax.persistence.*;

@Entity
public class CategoryEntity  extends ParentConfig{


    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    private String Code;

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