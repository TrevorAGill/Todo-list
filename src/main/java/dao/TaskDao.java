package dao;

import models.Task;

import java.util.List;

/**
 * Created by Guest on 8/14/17.
 */
public interface TaskDao {

    //create
    void add (Task task);

    //read
    public List<Task> getAll();

    //find
    Task findById(int id);

    //update
    void update(int id, String content, int categoryId);

    //clear all tasks
    void clearAllTasks();

    void deleteById(int id);
}


//Delete our getAll, clearAllTasks, findById, update and deleteTask methods as this is functionality that will move to our DAO.