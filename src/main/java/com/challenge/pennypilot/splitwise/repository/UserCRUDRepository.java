package com.challenge.pennypilot.splitwise.repository;

import com.challenge.pennypilot.splitwise.model.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserCRUDRepository extends ListCrudRepository<User, Long> {

}
