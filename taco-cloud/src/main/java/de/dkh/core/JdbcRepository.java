package de.dkh.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import lombok.Getter;

public class JdbcRepository {

	@Getter
	private JdbcTemplate jdbc;

	@Autowired
	public JdbcRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

}
