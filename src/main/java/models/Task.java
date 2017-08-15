package models;

import java.time.LocalDateTime;

public class Task {

    private String description;
    private boolean completed;
    private LocalDateTime createdAt;
    private int id;
    private int categoryId;

    public Task(String description, int categoryId){
        this.setDescription(description);
        this.completed = false;
        this.createdAt = LocalDateTime.now();
        this.setCategoryId(getCategoryId());
    }


    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (completed != task.completed) return false;
        if (id != task.id) return false;
        if (categoryId != task.categoryId) return false;
        if (!description.equals(task.description)) return false;
        return createdAt != null ? createdAt.equals(task.createdAt) : task.createdAt == null;
    }

    @Override
    public int hashCode() {
        int result = description.hashCode();
        result = 31 * result + (completed ? 1 : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + categoryId;
        return result;
    }


    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getCompleted(){
        return this.completed;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public int getId() {
        return this.id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
