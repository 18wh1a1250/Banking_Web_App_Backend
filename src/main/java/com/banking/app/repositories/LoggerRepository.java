package com.banking.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.banking.app.entities.Logger;

public interface LoggerRepository extends CrudRepository<Logger, Integer> {

}