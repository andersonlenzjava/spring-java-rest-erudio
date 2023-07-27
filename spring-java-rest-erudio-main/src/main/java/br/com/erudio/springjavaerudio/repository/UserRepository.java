package br.com.erudio.springjavaerudio.repository;

import br.com.erudio.springjavaerudio.model.person.Person;
import br.com.erudio.springjavaerudio.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User WHERE u.user_name =:userName")
    User findByUsername(@Param("userName") String userName);


}
