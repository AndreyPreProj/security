package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.PersonDao;
import ru.kata.spring.boot_security.demo.models.Person;
import ru.kata.spring.boot_security.demo.security.AdminRole;
import ru.kata.spring.boot_security.demo.security.UserRole;

import java.util.Optional;

@Service
public class RegistrationService {
    private final PersonDao personDao;
    private final AdminRole adminRole = new AdminRole();
    private final UserRole userRole = new UserRole();


    @Autowired
    public RegistrationService(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Transactional
    public void register(Person person) {
        person.addRole(userRole.getAuthority());
        person.addRole(adminRole.getAuthority());

        personDao.save(person);
    }
}
