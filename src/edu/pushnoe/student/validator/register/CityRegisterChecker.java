package edu.pushnoe.student.validator.register;

import edu.pushnoe.student.domain.register.CityRegisterResponse;
import edu.pushnoe.student.domain.Person;
import edu.pushnoe.student.exeption.CityRegisterException;

public interface CityRegisterChecker {

   CityRegisterResponse checkerPerson(Person person) throws CityRegisterException;
}
