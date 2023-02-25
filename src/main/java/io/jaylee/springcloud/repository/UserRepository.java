package io.jaylee.springcloud.repository;

import io.jaylee.springcloud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends JpaRepository<User, String> {
}
