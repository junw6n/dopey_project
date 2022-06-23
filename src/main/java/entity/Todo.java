package entity;

public class Todo {
    private int id;
    private String title;
    private String description;
    private int status;

    public Todo() {
    }

    public Todo(int id, String title, String description, int status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) { this.description = description; }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }
}
