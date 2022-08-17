package domain;

public class Todo {
    private Integer id;
    private String title;
    private String description;
    private TodoStatus status;

    public Todo() {
    }

    public Todo(Integer id, String title, String description, TodoStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) { this.description = description; }

    public TodoStatus getStatus() { return status; }

    public void setStatus(TodoStatus status) { this.status = status; }
}
