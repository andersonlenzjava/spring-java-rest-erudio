package br.com.erudio.springjavaerudio.repository;

import br.com.erudio.springjavaerudio.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
