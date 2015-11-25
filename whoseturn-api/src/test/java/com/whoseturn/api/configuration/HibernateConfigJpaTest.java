package com.whoseturn.api.configuration;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.whoseturn.api.configuration.db.HibernateConfiguration;
import com.whoseturn.api.entities.Flight;

/**
 * Tests to illustrate the very basics use of JPA with spring.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
@ActiveProfiles({"dev"})
public class HibernateConfigJpaTest {

	@Inject
	/**EntityManagerFactory is central in JPA, see Hibernate configuration.*/
	EntityManagerFactory entityManagerFactory;

	/**
	 * Save a flight in database.
	 */
	@Test
	public void firstHibernateWithTransactionTest() {
		int sizeBefore = getNumberOfFlights();
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		entityManager.getTransaction().begin();
		Flight flight = new Flight();
		entityManager.persist(flight);
		entityManager.getTransaction().commit();
		entityManager.close();
		int size = getNumberOfFlights();
		Assert.assertEquals(sizeBefore + 1, size);

	}

	/**
	 * Nothing happen outside a transaction.
	 */
	@Test
	public void firstHibernateWithoutTransactionTest() {
		int sizeBefore = getNumberOfFlights();
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		Flight flight = new Flight();
		entityManager.persist(flight);
		entityManager.close();
		int size = getNumberOfFlights();

		Assert.assertEquals(sizeBefore, size);
	}

	/**
	 * @return number of element in table Flight.
	 */
	@SuppressWarnings("unchecked")
	private int getNumberOfFlights() {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		entityManager.getTransaction().begin();
		List<Flight> result = entityManager.createQuery("from Flight")
				.getResultList();
		int size = result.size();
		// for (Flight contact : result) {
		// System.out.println(contact.toString());
		// }
		entityManager.getTransaction().commit();
		entityManager.close();
		return size;
	}

	// /**
	// * Delete all element in table Flight.
	// */
	// private void truncateTableFlight() {
	// EntityManager entityManager = entityManagerFactory
	// .createEntityManager();
	// entityManager.getTransaction().begin();
	// entityManager.createQuery("delete from Flight").executeUpdate();
	// entityManager.getTransaction().commit();
	// entityManager.close();
	// }

}