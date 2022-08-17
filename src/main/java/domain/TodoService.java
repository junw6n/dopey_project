package domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Service
@Transactional
public class TodoService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TodoService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Todo> todoRowMapper = new RowMapper<Todo>() {
        @Override
        public Todo mapRow(ResultSet resultSet, int i) throws SQLException {
            Todo todo = new Todo();
            todo.setId(resultSet.getInt("id"));
            todo.setTitle(resultSet.getString("title"));
            todo.setDescription(resultSet.getString("description"));
            todo.setStatus(TodoStatus.valueOf(resultSet.getInt("status")));
            return todo;
        }
    };


    public int add(Todo todo) {
        if (todo.getId() == null)
            return this.jdbcTemplate.update("insert into todo (title, description) values (?, ?)", todo.getTitle(), todo.getDescription());
        else
            return this.jdbcTemplate.update("insert into todo (id, title, description) values (?, ?, ?)", todo.getId(), todo.getTitle(), todo.getDescription());
    }

    public int remove(Todo todo) {
        return this.jdbcTemplate.update("delete from todo where id=?", todo.getId());
    }

    public int setStatus(Todo todo) {
        return this.jdbcTemplate.update("update todo set status=? where id=?", todo.getStatus().intValue(), todo.getId());
    }

    @Transactional(readOnly = true)
    public Todo get(int id) {
        return this.jdbcTemplate.queryForObject("select * from todo where id=?", this.todoRowMapper, id);
    }

    @Transactional(readOnly = true)
    public List<Todo> getAll() {
        return this.jdbcTemplate.query("select * from todo", this.todoRowMapper);
    }

    @Transactional(readOnly = true)
    public Integer getCount() {
        return this.jdbcTemplate.queryForObject("select count(*) from todo", Integer.class);
    }
}
