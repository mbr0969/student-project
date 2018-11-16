package edu.pushnoe.student.dao;

import edu.pushnoe.student.domain.CountryArea;
import edu.pushnoe.student.domain.PassportOffice;
import edu.pushnoe.student.domain.RegisterOffice;
import edu.pushnoe.student.domain.Street;
import edu.pushnoe.student.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DictionaryDaoImpl implements DictionaryDoa {
    private static final Logger logger = LoggerFactory.getLogger(DictionaryDaoImpl.class);

    private static final String GET_STREET = "SELECT street_code, street_name FROM jc_street " +
            "WHERE UPPER(street_name) LIKE UPPER(?)";

    private static final String GET_PASSPORT = "SELECT * FROM jc_passport_office " +
            "WHERE p_office_area_id=?";

    private static final String GET_REGISTER = "SELECT * FROM jc_register_office " +
            "WHERE r_office_area_id=?";

    private static final String GET_AREA = "SELECT * FROM jc_country_struct " +
                                            "WHERE area_id like ? and area_id <> ?";


    @Override
    public List<Street> findStreets(String pattern) throws DaoException {

        List<Street> result = new LinkedList<>();


        try(Connection connection = ConnectDb.getConnections();
            PreparedStatement stmt = connection != null ? connection.prepareStatement(GET_STREET) : null) {


            assert stmt != null;
            stmt.setString(1,"%"+pattern+"%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                  Street street = new Street(rs.getLong("street_code"), rs.getString("street_name"));
                  result.add(street);
            }

        }catch (SQLException ex){
            logger.error(ex.getMessage() , ex);
            throw  new DaoException(ex);
        }
        return result;
    }


    @Override
    public List<PassportOffice> findPassportOffice(String areaId) throws DaoException {

        List<PassportOffice> result = new LinkedList<>();


        try(Connection connection =  ConnectDb.getConnections();
            PreparedStatement stmt = connection != null ? connection.prepareStatement(GET_PASSPORT) : null) {


            assert stmt != null;
            stmt.setString(1,areaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PassportOffice passportOffice = new PassportOffice(
                        rs.getLong("p_office_id"),
                        rs.getString("p_office_area_id"),
                        rs.getString("p_office_name")
                            );
                result.add(passportOffice);
            }

        }catch (SQLException ex){
            logger.error(ex.getMessage() , ex);
            throw  new DaoException(ex);
        }
        return result;
    }

    @Override
    public List<RegisterOffice> findRegisterOffice(String areaId) throws DaoException {

        List<RegisterOffice> result = new LinkedList<>();

        try(Connection connection = ConnectDb.getConnections();
            PreparedStatement stmt = connection != null ? connection.prepareStatement(GET_REGISTER) : null) {

            assert stmt != null;
            stmt.setString(1,areaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                RegisterOffice registerOffice = new RegisterOffice(
                        rs.getLong(  "r_office_id"),
                        rs.getString("r_office_area_id"),
                        rs.getString("r_office_name")
                );
                result.add(registerOffice);
            }

        }catch (SQLException ex){
            logger.error(ex.getMessage() , ex);
            throw  new DaoException(ex);
        }
        return result;
    }


    @Override
    public List<CountryArea> findAreas(String areaId) throws DaoException {
        List<CountryArea> result = new LinkedList<>();

        try(Connection connection =  ConnectDb.getConnections();
            PreparedStatement stmt = connection != null ? connection.prepareStatement(GET_AREA) : null) {

            String param1 = buildParam(areaId);
            String param2 = areaId;

            assert stmt != null;
            stmt.setString(1,param1);
            stmt.setString(2,param2);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CountryArea counrtyArea = new CountryArea(
                        rs.getString("area_id"),
                        rs.getString("area_name")
                );
                result.add(counrtyArea);
            }

        }catch (SQLException ex){
            logger.error(ex.getMessage() , ex);
            throw  new DaoException(ex);
        }
        return result;

    }

    private String buildParam(String areaId) throws SQLException{

        if (areaId == null || areaId.trim().isEmpty()){
            return "__0000000000";
        }else if (areaId.endsWith("0000000000")){
           return areaId.substring(0,2)+ "___0000000";
        }else if (areaId.endsWith("0000000")){
            return areaId.substring(0,5)+ "___0000";
        }else if(areaId.endsWith("0000")){
            return areaId.substring(0,8)+ "____";
        }
        logger.error(String.valueOf(new SQLException())) ;
        throw new SQLException("Invalid parameter 'areaId :" + areaId);
    }

}
