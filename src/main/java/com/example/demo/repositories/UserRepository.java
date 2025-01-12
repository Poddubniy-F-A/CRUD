package com.example.demo.repositories;

import com.example.demo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private static final RowMapper<User> userRowMapper = (r, i) -> {
        User rowObject = new User();
        rowObject.setId(r.getInt("id"));
        rowObject.setFirstName(r.getString("firstName"));
        rowObject.setLastName(r.getString("lastName"));
        return rowObject;
    };

    private final JdbcTemplate jdbc;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public User getById(int id) {
        return jdbc.query("SELECT * FROM userTable WHERE id = ?", new Object[]{id}, userRowMapper).
                stream().findFirst().orElse(null);
    }

    public List<User> findAll() {
        return jdbc.query("SELECT * FROM userTable", userRowMapper);
    }

    public void save(User user) {
        jdbc.update(
                "INSERT INTO userTable (firstName,lastName) VALUES (?, ?)",
                user.getFirstName(), user.getLastName()
        );
    }

    public void deleteById(int id) {
        jdbc.update("DELETE FROM userTable WHERE id=?", id);
    }

    public void update(User user) {
        jdbc.update(
                "UPDATE userTable SET firstName = ?, lastName = ? WHERE id = ?",
                user.getFirstName(), user.getLastName(), user.getId()
        );
    }
}