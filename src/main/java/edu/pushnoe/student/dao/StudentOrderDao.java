package edu.pushnoe.student.dao;

import edu.pushnoe.student.domain.StudentOrder;
import edu.pushnoe.student.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface StudentOrderDao {

    Connection connection =  ConnectDb.getInstance();

    Long saveStudentOrder(StudentOrder so) throws  SQLException,DaoException;

    List<StudentOrder>  getStudentOrders()throws DaoException, SQLException;

}
