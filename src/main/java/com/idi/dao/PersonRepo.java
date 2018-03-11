package com.idi.dao;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
@JaversSpringDataAuditable
public interface PersonRepo extends CrudRepository<Person, String> {}