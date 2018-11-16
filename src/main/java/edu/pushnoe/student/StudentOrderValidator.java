package edu.pushnoe.student;

import edu.pushnoe.student.dao.DictionaryDaoImpl;
import edu.pushnoe.student.dao.StudentDaoIpml;
import edu.pushnoe.student.domain.*;
import edu.pushnoe.student.domain.children.AnswerChildren;
import edu.pushnoe.student.domain.register.AnswerCityRegister;
import edu.pushnoe.student.domain.student.AnswerStudent;
import edu.pushnoe.student.domain.wedding.AnswerWedding;
import edu.pushnoe.student.exception.DaoException;
import edu.pushnoe.student.mail.SenderMail;
import edu.pushnoe.student.validator.ChildrenValidator;
import edu.pushnoe.student.validator.CityRegisterValidator;
import edu.pushnoe.student.validator.StudentValodator;
import edu.pushnoe.student.validator.WeddingValidator;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class StudentOrderValidator {

    private CityRegisterValidator cityRegisterVal;
    private ChildrenValidator childrenVal;
    private WeddingValidator weddingVal;
    private StudentValodator studentVal;
    private SenderMail senderMail;

    public StudentOrderValidator(){

        cityRegisterVal =   new CityRegisterValidator();
        childrenVal     =   new ChildrenValidator();
        weddingVal      =   new WeddingValidator();
        studentVal      =   new StudentValodator();
        senderMail      =   new SenderMail();

    }

    public static void main(String[] args) throws Exception {


        List<CountryArea> ca1 =  new DictionaryDaoImpl().findAreas("");

        for (CountryArea area : ca1){
            System.out.println(area.getAreaId() + " : " +area.getAreaName());
        }

        System.out.println("----->");
        List<CountryArea> ca2 =  new DictionaryDaoImpl().findAreas("020000000000");
        for (CountryArea area : ca2){
            System.out.println(area.getAreaId() + " : " +area.getAreaName());
        }

        System.out.println("----->");
        List<CountryArea> ca3 =  new DictionaryDaoImpl().findAreas("020010000000");
        for (CountryArea area : ca3){
            System.out.println(area.getAreaId() + " : " +area.getAreaName());
        }

        System.out.println("----->");
        List<CountryArea> ca4 =  new DictionaryDaoImpl().findAreas("020010010000");
        for (CountryArea area : ca4){
            System.out.println(area.getAreaId() + " : " +area.getAreaName());
        }
//       // Class.forName("org.postgresql.Driver");
//       Connection connection = DriverManager.getConnection(
//                "jdbc:postgresql://localhost:5432/js_student",
//                "papa","777555"
//        );
//
//        Statement stmt = connection.createStatement();
//        ResultSet rs = stmt.executeQuery("SELECT * FROM jc_street");
//
//        while (rs.next()){
//            System.out.println(rs.getLong(1) +" : " + rs.getString(2));
//        }



//
//        try {
//            StudentDao studentDao = new StudentDao();
//            studentDao.updateStreet(5,"Школьная");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//        StudentOrderValidator sov = new StudentOrderValidator();
//        sov.checkAll();
    }

    /**
     *
     */
    public void checkAll() throws SQLException, DaoException {

          List<StudentOrder> soList = readStudentOrders();


        for (StudentOrder so : soList ) {
            //System.out.println();
            checkOneOrder(so);
        }
    }

    /**
     * @return
     */
    static List<StudentOrder> readStudentOrders() throws SQLException, DaoException {

        return new StudentDaoIpml().getStudentOrders();
    }

    public void checkOneOrder(StudentOrder so){

        AnswerCityRegister cityAnswer = checkCityRegister(so);
     //   AnswerWedding weddingAnswer = checkWedding(so);
     //   AnswerChildren childrenAnswer = checkChildren(so);
     //   AnswerStudent studentAnser = checkStudent(so);

     //   sendMail(so);
    }


    /**
     * @param so
     * @return
     */
    public AnswerCityRegister checkCityRegister(StudentOrder so){
        return cityRegisterVal.checkCityRegister(so) ;
    }

    /**
     * @param so
     * @return
     */
    public AnswerWedding checkWedding(StudentOrder so){

        return weddingVal.checkWedding(so);
    }

    /**
     * @param so
     * @return
     */
    public AnswerChildren checkChildren(StudentOrder so){

        return childrenVal.checkChildren(so);
    }

    /**
     * @param so
     * @return
     */
    public AnswerStudent checkStudent(StudentOrder so){

        return studentVal.checkStudent(so);
    }

    /**
     * @param so
     */
    public void sendMail(StudentOrder so) {

        senderMail.sendMail(so);
    }

}
