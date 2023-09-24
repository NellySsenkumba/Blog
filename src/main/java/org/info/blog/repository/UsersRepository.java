package org.info.blog.repository;

import org.info.blog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
//    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

//    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
