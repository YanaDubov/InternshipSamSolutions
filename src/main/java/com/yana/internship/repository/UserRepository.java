package com.yana.internship.repository;

import com.yana.internship.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository //TODO[rfisenko 11/21/19]: I as remember this annotation is not required. Please check it and remove for all classes
public interface UserRepository extends CrudRepository<User, Long> {
}
