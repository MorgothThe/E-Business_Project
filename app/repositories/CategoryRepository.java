package repositories;

import models.Category;

public class CategoryRepository extends GenericRepository<Category> {

    public CategoryRepository(){
        super(Category.class);
    }

}
