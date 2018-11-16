package edu.pushnoe.student.dao;

import edu.pushnoe.student.domain.CountryArea;
import edu.pushnoe.student.domain.PassportOffice;
import edu.pushnoe.student.domain.RegisterOffice;
import edu.pushnoe.student.domain.Street;
import edu.pushnoe.student.exception.DaoException;

import java.util.List;

public interface DictionaryDoa {

    List<Street> findStreets(String pattern) throws DaoException;
    List<PassportOffice> findPassportOffice(String areaId) throws DaoException;
    List<RegisterOffice> findRegisterOffice(String areaId) throws DaoException;
    List<CountryArea> findAreas(String areaId) throws DaoException;
}
