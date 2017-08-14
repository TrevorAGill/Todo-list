package dao;
import dao.Sql2oTaskDao;
import dao.CategoryDao;
import models.Category;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

/**
 * Created by Guest on 8/14/17.
 */
public class Sql2oCategoryDaoTest {
    private Sql2oCategoryDao categoryDao; //ignore me for now. We'll create this soon.
    private Connection conn; //must be sql2o class conn

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        categoryDao = new Sql2oCategoryDao(sql2o); //ignore me for now

        //keep connection open through entire test so it does not get erased.
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingCourseSetsId() throws Exception {
        Category category = new Category ("work");
        int originalCategoryId = category.getId();
        ArrayList<String> testList = new ArrayList<String>();
        categoryDao.add(category);
        assertNotEquals(originalCategoryId, category.getId()); //how does this work?
    }

    @Test
    public void existingCategoriesCanBeFoundById() throws Exception {
        Category category = new Category ("personal");
        categoryDao.add(category); //add to dao (takes care of saving)
        Category foundCategory = categoryDao.findById(category.getId()); //retrieve
        assertEquals(category, foundCategory); //should be the same
    }

    @Test
    public void noCategoriesAreFound() throws Exception {
        List<Category> testList = new ArrayList<Category>();
        assertEquals(testList,categoryDao.getAll());
    }

    @Test
    public void allCategoriesAreFound() throws Exception {
        Category category = new Category ("work");
        Category category2 = new Category ("personal");
        categoryDao.add(category);
        categoryDao.add(category2);
        assertEquals(2,categoryDao.getAll().size());
    }

    @Test
    public void deleteASingleCategory() throws Exception {
        Category category = new Category("work");
        categoryDao.add(category);
        categoryDao.deleteById(category.getId());
        assertEquals(0, categoryDao.getAll().size());
    }

    @Test
    public void deleteAllCategories() throws Exception {
        Category category = new Category("work");
        Category category2 = new Category("personal");
        categoryDao.add(category);
        categoryDao.add(category2);
        categoryDao.clearAllCategories();
        assertEquals(0, categoryDao.getAll().size());
    }

    @Test
    public void updateASingleCategory() throws Exception {
        Category category = new Category("work");
        categoryDao.add(category);
        categoryDao.update(category.getId(),"personal");
        Category updatedCategory = categoryDao.findById(category.getId());
        assertEquals("personal", updatedCategory.getName());
    }

}