package application;

public class TodoRemoveRequestDTO {
    private int id;

    public TodoRemoveRequestDTO() {
    }

    public TodoRemoveRequestDTO(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
