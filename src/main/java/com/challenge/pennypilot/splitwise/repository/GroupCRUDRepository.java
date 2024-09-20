package com.challenge.pennypilot.splitwise.repository;

import com.challenge.pennypilot.splitwise.model.Group;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface GroupCRUDRepository extends ListCrudRepository<Group, Long> {
}
