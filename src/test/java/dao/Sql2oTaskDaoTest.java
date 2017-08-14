
package dao;
import dao.TaskDao;
import dao.Sql2oTaskDao;
import models.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;


import java.util.ArrayList;
import java.util.List;


import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Guest on 8/14/17.
 */
public class Sql2oTaskDaoTest {
    private Sql2oTaskDao taskDao; //ignore me for now. We'll create this soon.
    private Connection conn; //must be sql2o class conn

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        taskDao = new Sql2oTaskDao(sql2o); //ignore me for now

        //keep connection open through entire test so it does not get erased.
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingCourseSetsId() throws Exception {
        Task task = new Task ("mow the lawn");
        int originalTaskId = task.getId();
        ArrayList<String> testList = new ArrayList<String>();
        taskDao.add(task);
        assertNotEquals(originalTaskId, task.getId()); //how does this work?
    }

    @Test
    public void existingTasksCanBeFoundById() throws Exception {
        Task task = new Task ("mow the lawn");
        taskDao.add(task); //add to dao (takes care of saving)
        Task foundTask = taskDao.findById(task.getId()); //retrieve
        assertEquals(task, foundTask); //should be the same
    }

    @Test
    public void noTasksAreFound() throws Exception {
        List<Task> testList = new ArrayList<Task>();
        assertEquals(testList,taskDao.getAll());
    }

    @Test
    public void allTasksAreFound() throws Exception {
        Task task = new Task ("mow the lawn");
        Task task2 = new Task ("sweep the floor");
        taskDao.add(task);
        taskDao.add(task2);
        assertEquals(2,taskDao.getAll().size());
    }

    @Test
    public void deleteASingleTask() throws Exception {
        Task task = new Task("mow the lawn");
        taskDao.add(task);
        taskDao.deleteById(task.getId());
        assertEquals(0, taskDao.getAll().size());
    }

    @Test
    public void deleteAllTasks() throws Exception {
        Task task = new Task("mow the lawn");
        Task task2 = new Task("sweep the floor");
        taskDao.add(task);
        taskDao.add(task2);
        taskDao.clearAllTasks();
        assertEquals(0, taskDao.getAll().size());
    }

    @Test
    public void updateASingleTask() throws Exception {
        Task task = new Task("mow the lawn");
        taskDao.add(task);
        taskDao.update(task.getId(),"walk the dog");
        Task updatedTask = taskDao.findById(task.getId());
        assertEquals("walk the dog", updatedTask.getDescription());
    }

}
