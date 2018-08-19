package edu.pushnoe.student;

import edu.pushnoe.student.domain.*;
import edu.pushnoe.student.domain.children.AnswerChildren;
import edu.pushnoe.student.domain.register.AnswerCityRegister;
import edu.pushnoe.student.domain.student.AnswerStudent;
import edu.pushnoe.student.domain.wedding.AnswerWedding;
import edu.pushnoe.student.mail.SenderMail;
import edu.pushnoe.student.validator.ChildrenValidator;
import edu.pushnoe.student.validator.CityRegisterValidator;
import edu.pushnoe.student.validator.StudentValodator;
import edu.pushnoe.student.validator.WeddingValidator;

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


    public static void main(String[] args) {

        StudentOrderValidator sov = new StudentOrderValidator();
        sov.checkAll();


    }

    /**
     *
     */
    public void checkAll() {

          List<StudentOrder> soList = readStudentOrders();


        for (StudentOrder so : soList ) {
            //System.out.println();
            checkOneOrder(so);
        }
    }

    /**
     * @return
     */
    static List<StudentOrder> readStudentOrders() {

        List<StudentOrder> soList = new LinkedList<StudentOrder>();

        for (int i = 0; i < 5; i++){
            StudentOrder so = SaveStudentOrder.buildStudentOrder(i);
            soList.add(so);
        }

        return soList;
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
