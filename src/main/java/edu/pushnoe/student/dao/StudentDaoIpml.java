package edu.pushnoe.student.dao;

import edu.pushnoe.student.config.Config;
import edu.pushnoe.student.domain.*;
import edu.pushnoe.student.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
public class StudentDaoIpml implements StudentOrderDao {

    private static final Logger logger = LoggerFactory.getLogger(StudentDaoIpml.class);

    private static final String INSERT_ORDER  = "INSERT INTO jc_student_order("+
            "student_order_status, student_order_date, h_sur_name," +
            "h_given_name, h_patronymic, h_date_of_birth, h_passport_seria," +
            "h_passport_number, h_passport_date, h_passport_office_id, h_post_index," +
            "h_street_code,h_building, h_extension, h_apartment,h_university_id, h_student_number,w_sur_name," +
            "w_given_name, w_patronymic, w_date_of_birth, w_passport_seria," +
            "w_passport_number, w_passport_date, w_passport_office_id, w_post_index," +
            "w_street_code,w_building, w_extension,w_apartment,w_university_id, w_student_number, certificate_id," +
            "register_office_id, marriage_date)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
            " ?, ?, ?, ?, ?, ?, ?,?,?,?,?);";

    private static final String INSERT_CHILD = "INSERT INTO jc_student_child(" +
            "student_order_id, c_sur_name, c_given_name, c_patronymic, " +
            "c_date_of_birth, c_certificate_number, c_certificate_date, c_register_office_id, " +
            "c_post_index, c_street_code, c_building, c_extension, c_apartment)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?);";

    private static final String SELECT_ORDERS =
            "SELECT ro.r_office_area_id, ro.r_office_name, so.*, " +
            "po_h.p_office_area_id AS h_p_area_id, po_h.p_office_name AS h_p_office_name, " +
            "po_w.p_office_area_id AS w_p_area_id, po_w.p_office_name AS w_p_office_name  " +
            "FROM jc_student_order so " +
            "INNER JOIN jc_register_office ro ON ro.r_office_id = so.register_office_id " +
            "INNER JOIN jc_passport_office po_h ON po_h.p_office_id = so.h_passport_office_id " +
            "INNER JOIN jc_passport_office po_w ON po_w.p_office_id = so.w_passport_office_id "  +
            "WHERE student_order_status = ? ORDER BY student_order_date LIMIT ?;";

    private static final String SELECT_CHILD = "SELECT soc.*, ro.r_office_area_id, ro.r_office_name "+
            "FROM jc_student_child soc "+
            "INNER JOIN jc_register_office ro ON ro.r_office_id = soc.c_register_office_id "+
            "WHERE soc.student_order_id IN";


    private static final String SELECT_ORDERS_FULL =
            "SELECT ro.r_office_area_id, ro.r_office_name, so.*, " +
                    "po_h.p_office_area_id AS h_p_area_id, po_h.p_office_name AS h_p_office_name, " +
                    "po_w.p_office_area_id AS w_p_area_id, po_w.p_office_name AS w_p_office_name,  " +
                    "soc.*, ro_c.r_office_area_id, ro_c.r_office_name "+
                    "FROM jc_student_order so " +
                    "INNER JOIN jc_register_office ro ON ro.r_office_id = so.register_office_id " +
                    "INNER JOIN jc_passport_office po_h ON po_h.p_office_id = so.h_passport_office_id " +
                    "INNER JOIN jc_passport_office po_w ON po_w.p_office_id = so.w_passport_office_id "  +
                    "INNER JOIN jc_student_child soc ON soc.student_order_id = so.student_order_id "+
                    "INNER JOIN jc_register_office ro_c ON ro_c.r_office_id = soc.c_register_office_id "+
                    "WHERE student_order_status = ? ORDER BY so.student_order_id LIMIT ?;";

//===========Сохраняем данные=================

    /**
     * @param so
     * @return
     * @throws SQLException
     */
    @Override
    public Long saveStudentOrder(StudentOrder so) throws SQLException, DaoException {

        Long result = -1L;

        logger.debug("SO {}", so);


        try(Connection connection = ConnectDb.getConnections();
            PreparedStatement stmt = connection.prepareStatement(INSERT_ORDER, new String[]{"student_order_id"})){

            connection.setAutoCommit(false);

            try {
                //загаловок
                stmt.setInt(1, StudentOrderStatus.START.ordinal());
                stmt.setTimestamp(2, java.sql.Timestamp.valueOf(LocalDateTime.now()));

                //муж
                setParamsForAdult(stmt, 3, so.getHusbend());
                //жена
                setParamsForAdult(stmt, 18, so.getWife());

                //Данные о браке
                stmt.setString(33, so.getMarriageCertificateId());
                stmt.setLong(34, so.getMarriageOffice().getOfficeId());
                stmt.setDate(35, java.sql.Date.valueOf(so.getMarriageDate()));

                stmt.executeUpdate();

                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    result = rs.getLong(1);
                }


                saveChildren(connection, so, result);
                connection.commit();
                //connection = null;
                rs.close();

            }catch (SQLException ex){
                connection.rollback();
                throw new SQLException(ex);
            }

        }catch (SQLException ex){
            logger.error(ex.getMessage() , ex);
            throw new SQLException(ex);
        }
        return result;
    }


    /**
     * @param connection
     * @param so
     * @param soId
     * @throws SQLException
     */
    private void saveChildren(Connection connection, StudentOrder so, Long soId) throws SQLException {

        try(PreparedStatement stmt = connection.prepareStatement(INSERT_CHILD) ){

            for (Child child : so.getChildren()){
                stmt.setLong(1, soId);
                setParamsForChild(stmt, child);
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    /**
     * @param stmt
     * @param child
     * @throws SQLException
     */
    private void setParamsForChild(PreparedStatement stmt, Child child) throws SQLException{
        int start = setParamsForPerson(stmt, 2, child);
        stmt.setString(start+=1,child.getCertificateNumber());
        stmt.setDate(start +=1,java.sql.Date.valueOf(child.getIssueDate()));
        stmt.setLong(start+=1,child.getIssueDepartment().getOfficeId());
        start =setParamsForAddress(stmt, start, child);
    }

    /**
     * @param stmt
     * @param start
     * @param adult
     * @throws SQLException
     */
    private void setParamsForAdult(PreparedStatement stmt, int start, Adult adult) throws SQLException {
        start = setParamsForPerson(stmt, start, adult);
        stmt.setString(start +=1 ,adult.getPassportSeria());
        stmt.setString(start +=1, adult.getPassportNumber());
        stmt.setDate(start +=1, java.sql.Date.valueOf(adult.getIssueDate()));
        stmt.setLong(start +=1, adult.getIssueDepartment().getOfficeId());
        start= setParamsForAddress(stmt, start, adult);
        stmt.setLong(start +=1, adult.getUniversity().getUniversityId());
        stmt.setString(start +=1, adult.getStudentId() );
    }

    /**
     * @param stmt
     * @param start
     * @param person
     * @throws SQLException
     */
    private int setParamsForAddress(PreparedStatement stmt, int start, Person person) throws SQLException {
        Address person_address = person.getAddress();
        stmt.setString(start +=1, person_address.getPostCode());
        stmt.setLong(start +=1, person_address.getStreet().getStreetCode());
        stmt.setString(start +=1, person_address.getBuilding());
        stmt.setString(start +=1, person_address.getExtension());
        stmt.setString(start += 1, person_address.getApartment());
        return start;
    }

    /**
     * @param stmt
     * @param start
     * @param person
     * @return
     * @throws SQLException
     */
    private int setParamsForPerson(PreparedStatement stmt, int start, Person person) throws SQLException {
        stmt.setString(start, person.getSurName());
        stmt.setString(start +=1,person.getGivenName());
        stmt.setString(start +=1,person.getPatronymic());
        stmt.setDate(start +=1, java.sql.Date.valueOf(person.getDateOfBirth()));
        return start;
    }



//================== получаем данные ==================

    /**
     * @return
     * @throws DaoException
     * @throws SQLException
     */
    @Override
    public List<StudentOrder> getStudentOrders() throws DaoException, SQLException {

        //return getStudentOrdersOneSelect();
        return getStudentOrdersTwoSelect();
    }

    /**
     * @return
     */
    private List<StudentOrder> getStudentOrdersTwoSelect() {
        List<StudentOrder> result = new LinkedList<>();
        //Connection connection =  ConnectDb.getInstance();
        try(Connection connection =  ConnectDb.getConnections();//ConnectDb.getConntens();
            PreparedStatement stmt = connection.prepareStatement(SELECT_ORDERS)){

            stmt.setInt(1,StudentOrderStatus.START.ordinal());
            stmt.setInt(2,Integer.parseInt(Config.getProperty(Config.DB_LIMIT)));

            ResultSet rs =  stmt.executeQuery();
            while (rs.next()){

                StudentOrder so = getFullStudentOrder(rs);

                result.add(so);
            }

            findChildren(connection,result);


            rs.close();
        }catch (SQLException ex){
            logger.error(ex.getMessage() , ex);
            System.out.println(ex.getMessage());
        }

        return result;
    }


    /**
     * @return
     */
    private List<StudentOrder> getStudentOrdersOneSelect() {

        List<StudentOrder> result = new LinkedList<>();

        try(Connection connection =  ConnectDb.getConnections();//ConnectDb.getConntens();
            PreparedStatement stmt = connection.prepareStatement(SELECT_ORDERS_FULL)){

            Map<Long, StudentOrder> maps = new HashMap<>();

            stmt.setInt(1,StudentOrderStatus.START.ordinal());
            int limit = Integer.parseInt(Config.getProperty(Config.DB_LIMIT));
            stmt.setInt(2,limit);

            ResultSet rs =  stmt.executeQuery();
            int counter = 0;

            while (rs.next()){

                Long soId = rs.getLong("student_order_id");
                if (!maps.containsKey(soId)) {
                    StudentOrder so = getFullStudentOrder(rs);
                    result.add(so);
                    maps.put(soId,so);
                }

                StudentOrder so = maps.get(soId);
                so.addChild(fillChild(rs));
                counter++;
            }

            if (counter >= limit){
                result.remove(result.size()-1);

            }

            rs.close();
        }catch (SQLException ex){
            logger.error(ex.getMessage() , ex);
            System.out.println(ex.getMessage());
        }

        return result;
    }

    /**
     * @param rs
     * @return
     * @throws SQLException
     */
    private StudentOrder getFullStudentOrder(ResultSet rs) throws SQLException {
        StudentOrder so = new StudentOrder();

        fillStudentOrder(rs, so);
        fillMarriage(rs, so);

        so.setHusbend(fillAdult(rs, "h_"));
        so.setWife(fillAdult(rs, "w_"));
        return so;
    }

    /**
     * @param connection
     * @param result
     * @throws SQLException
     */
    private void findChildren(Connection connection, List<StudentOrder> result) throws SQLException {

        String cl = "("+result.stream().map(studentOrder -> String.valueOf(studentOrder.getStudentOrderId()))
                        .collect(Collectors.joining(","))+")";

        Map<Long,StudentOrder> maps = result.stream().collect(Collectors
                            .toMap(studentOrder->studentOrder.getStudentOrderId(),
                            studentOrders->studentOrders));

        try (PreparedStatement stmt = connection.prepareStatement(SELECT_CHILD+ cl)){

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Child ch = fillChild(rs);
                StudentOrder so =maps.get(rs.getLong("student_order_id"));
                so.addChild(ch);
            }
        }

    }

    /**
     * @param rs
     * @param prefix
     * @return
     * @throws SQLException
     */
    private Adult fillAdult(ResultSet rs, String prefix) throws SQLException {

        Adult adult = new Adult();

        adult.setSurName(rs.getString(prefix+"sur_name"));
        adult.setGivenName(rs.getString(prefix+"given_name"));
        adult.setPatronymic(rs.getString(prefix+"patronymic"));
        adult.setDateOfBirth(rs.getDate(prefix+"date_of_birth").toLocalDate());
        adult.setPassportSeria(rs.getString(prefix+"passport_seria"));
        adult.setPassportNumber(rs.getString(prefix+"passport_number"));
        adult.setIssueDate(rs.getDate(prefix+"passport_date").toLocalDate());

        Long poId = rs.getLong(prefix+"passport_office_id");
        String poArea = rs.getString(prefix+"p_area_id");
        String poName = rs.getString(prefix+"p_office_name");
        PassportOffice po = new PassportOffice(poId,poArea,poName);
        adult.setIssueDepartment(po);
        Address address = new Address();
        Street street = new Street(rs.getLong(prefix+"street_code"),"");
        address.setPostCode(rs.getString(prefix + "post_index"));
        address.setStreet(street);
        address.setBuilding(rs.getString(prefix+"building"));
        address.setExtension(rs.getString(prefix+"extension"));
        address.setApartment(rs.getString(prefix+"apartment"));
        adult.setAddress(address);

        University university = new University(rs.getLong(prefix+"university_id"),"");
        adult.setUniversity(university);
        adult.setStudentId(rs.getString(prefix+"student_number"));

        return adult;

    }

    /**
     * @param rs
     * @param so
     * @throws SQLException
     */
    private void fillStudentOrder(ResultSet rs, StudentOrder so) throws SQLException {

        so.setStudentOrderId(rs.getLong("student_order_id"));
        so.setStudentOrderDate(rs.getTimestamp("student_order_date").toLocalDateTime());
        so.setStudentOrderStatus(StudentOrderStatus.fromValue(rs.getInt("student_order_status")));

    }

    /**
     * @param rs
     * @param so
     * @throws SQLException
     */
    private void fillMarriage(ResultSet rs, StudentOrder so) throws SQLException {

        so.setMarriageCertificateId(rs.getString("certificate_id"));
        so.setMarriageDate(rs.getDate("marriage_date").toLocalDate());

        Long roId = rs.getLong("register_office_id");
        String areaId = rs.getString("r_office_area_id");
        String name = rs.getString("r_office_name");
        RegisterOffice ro = new RegisterOffice(roId,areaId,name);
        so.setMarriageOffice(ro);

    }

    /**
      * @param rs
     * @return
     * @throws SQLException
     */
    private Child fillChild(ResultSet rs) throws SQLException {

        String surName = rs.getString("c_sur_name");
        String givenName = rs.getString("c_given_name");
        String patronimicName = rs.getString("c_patronymic");
        LocalDate dateBirth = rs.getDate("c_date_of_birth").toLocalDate();
        Child child = new Child(surName,givenName,patronimicName,dateBirth);

        child.setCertificateNumber(rs.getString("c_certificate_number"));
        child.setIssueDate(rs.getDate("c_certificate_date").toLocalDate());
        child.setCertificateNumber(rs.getString("c_register_office_id"));

        Long roId = rs.getLong("c_register_office_id");
        String roArea = rs.getString("r_office_area_id");
        String roName = rs.getString("r_office_name");
        RegisterOffice ro = new RegisterOffice(roId,roArea,roName);
        child.setIssueDepartment(ro);

        Address address = new Address();
        Street street = new Street(rs.getLong("c_street_code"),"");
        address.setPostCode(rs.getString("c_post_index"));
        address.setStreet(street);
        address.setBuilding(rs.getString("c_building"));
        address.setExtension(rs.getString("c_extension"));
        address.setApartment(rs.getString("c_apartment"));
        child.setAddress(address);

        return child;
    }

}
