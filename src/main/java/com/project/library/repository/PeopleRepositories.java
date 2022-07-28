package com.project.library.repository;

import com.project.library.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepositories extends JpaRepository<Person, Integer> {
    Optional<Person> findByFullName(String fullName);
}
