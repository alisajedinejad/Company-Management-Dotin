package entity;


import javax.persistence.*;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<CategoryEntity> categoryEntities;
    private String Code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryEntities=" + categoryEntities +
                ", Code='" + Code + '\'' +
                '}';
    }
}