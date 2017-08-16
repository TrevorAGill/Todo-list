package dao;

import models.Category;
import models.Task;

import java.util.List;

/**
 * Created by Guest on 8/14/17.
 */
public interface CategoryDao {
    //create
    void add (Category category);

    //read
    List<Category> getAll();

    //get all tasks for specific category
    List<Task> getAllTasksByCategory(int categoryId);

    //find category by id
    Category findById(int id);

    //update
    void update(int id, String name);

    //delete
    void deleteById(int id);
    void clearAllCategories();

}
