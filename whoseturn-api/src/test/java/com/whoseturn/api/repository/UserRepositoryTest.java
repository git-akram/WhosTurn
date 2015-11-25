package com.whoseturn.api.repository;

import com.whoseturn.api.configuration.db.HibernateConfiguration;
import com.whoseturn.api.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfiguration.class})
@ActiveProfiles({"dev"})
@Transactional
public class UserRepositoryTest {

	/** The DAO to test.*/
	@Inject
	UserRepository userRepository;

	/**
	 * Save a user in database.
	 */
	@Test
	public void testPersistUser() {
		User entity = new User();
		entity.setUsername("max");
		int size = userRepository.findAll().size();
		userRepository.save(entity);
		assertThat(userRepository.findAll().size()).isEqualTo(size + 1);

	}

	/**
	 * Save a user user  in database without name.
	 */
	@Test(expected = DataIntegrityViolationException.class)
	public void testPersistWrong() {
		User entity = new User();
		userRepository.save(entity);
	}

	/**
	 * Test find by, data is inserted at startup, see conf
	 * (hibernate.hbm2ddl.import_files).
	 */
	@Test
	public void testFindById() {
		assertThat(userRepository.findOne(1L).getUsername()).isEqualTo("toto");
	}

	/**
	 * Test find by, data is inserted at startup, see conf
	 * (hibernate.hbm2ddl.import_files).
	 */
	@Test
	public void testFindAll() {
		assertThat(userRepository.findAll()).hasAtLeastOneElementOfType(
				User.class);
	}
}
