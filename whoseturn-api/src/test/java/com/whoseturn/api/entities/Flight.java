package com.whoseturn.api.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Simpliest entity for testing.
 */
@Entity
public class Flight implements Serializable {

	private static final long serialVersionUID = -5367815793411408013L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + "]";
	}
}