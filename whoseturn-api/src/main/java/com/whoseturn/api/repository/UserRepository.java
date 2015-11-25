/**
 * 
 */
package com.whoseturn.api.repository;

import com.whoseturn.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.inject.Named;
import javax.transaction.Transactional;

@Named
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

}
