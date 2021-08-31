package com.kbware.simpleapp.user;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.kbware.simpleapp.user.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CosmosRepository<User, Long> {
}
