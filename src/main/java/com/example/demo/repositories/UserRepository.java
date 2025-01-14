package com.example.demo.repositories;

import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserRepository {
    private static final RowMapper<User> userRowMapper = (r, _) -> {
        User rowObject = new User();
        rowObject.setId(r.getInt("id"));
        rowObject.setFirstName(r.getString("firstName"));
        rowObject.setLastName(r.getString("lastName"));
        return rowObject;
    };

    private final JdbcTemplate jdbc;
    private final UsersSQLQueries queries;

    public User getById(int id) {
        return jdbc.query(queries.getGetById(), new Object[]{id}, userRowMapper).
                stream().findFirst().orElse(null);
    }

    public List<User> findAll() {
        return jdbc.query(queries.getGetAll(), userRowMapper);
    }

    public void save(User user) {
        jdbc.update(queries.getInsert(), user.getFirstName(), user.getLastName());
    }

    public void deleteById(int id) {
        jdbc.update(queries.getDeleteById(), id);
    }

    public void update(User user) {
        jdbc.update(queries.getUpdateById(), user.getFirstName(), user.getLastName(), user.getId());
    }
}
