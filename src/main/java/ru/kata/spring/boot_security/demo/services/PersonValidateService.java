package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.PersonDao;
import ru.kata.spring.boot_security.demo.models.Person;
import ru.kata.spring.boot_security.demo.security.AnonDetails;
import ru.kata.spring.boot_security.demo.security.PersonDetails;

import java.util.Optional;

@Service
public class PersonValidateService implements UserDetailsService {
    private final PersonDao personDao;

    @Autowired
    public PersonValidateService(PersonDao personDao) {
        this.personDao = personDao;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = personDao.findByUsername(username);

        if (person.isPresent()) {
            return new PersonDetails(person.get());
        }

            return new AnonDetails();
    }
}
