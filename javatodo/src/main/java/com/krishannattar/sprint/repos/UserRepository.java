package com.krishannattar.sprint.repos;

import com.krishannattar.sprint.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
