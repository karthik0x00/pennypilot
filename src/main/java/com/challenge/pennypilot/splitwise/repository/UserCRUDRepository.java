package com.challenge.pennypilot.splitwise.repository;

import com.challenge.pennypilot.splitwise.model.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserCRUDRepository extends ListCrudRepository<User, Long> {
    Optional<User> findFirstByEmailIdIgnoreCase(String emailId);
    boolean existsByEmailId(String emailId);
}
