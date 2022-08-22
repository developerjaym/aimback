/**
 * 
 */
package com.jaymansmann.chat.db.repository.auth;

import java.util.Optional;

/**
 * @author Jay
 *
 */
import com.jaymansmann.chat.db.entity.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
}