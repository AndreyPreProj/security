package ru.kata.spring.boot_security.demo.unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kata.spring.boot_security.demo.models.Person;
import ru.kata.spring.boot_security.demo.services.PersonValidateService;

@Component
public class PersonValidator implements Validator {
    private final PersonValidateService personValidateService;

    @Autowired
    public PersonValidator(PersonValidateService personValidateService) {
        this.personValidateService = personValidateService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        String name = person.getUsername();

        String name2 = personValidateService.loadUserByUsername(name).getUsername();


        if (name.equals(name2)) {
            errors.rejectValue("username", "", "[existed person]");
        }
    }
}
