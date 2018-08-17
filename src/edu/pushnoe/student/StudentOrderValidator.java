package edu.pushnoe.student;

import edu.pushnoe.student.domain.*;
import edu.pushnoe.student.mail.SenderMail;
import edu.pushnoe.student.validator.ChildrenValidator;
import edu.pushnoe.student.validator.CityRegisterValidator;
import edu.pushnoe.student.validator.StudentValodator;
import edu.pushnoe.student.validator.WeddingValidator;

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

          StudentOrder[] soArray = readStudentOrders();

          for (int i=0; i<soArray.length; i++){
              System.out.println();
              checkOneOrder(soArray[i]);
          }

        for (StudentOrder so : soArray ) {
            System.out.println();
            checkOneOrder(so);
        }
    }

    /**
     * @return
     */
    static StudentOrder[] readStudentOrders() {

        StudentOrder[] soArray = new StudentOrder[3];

        for (int i=0; i < soArray.length; i++ ){
            soArray[i] =SaveStudentOrder.buildStudentOrder(i);
        }

        return soArray;
    }

    public void checkOneOrder(StudentOrder so){

        AnswerCityRegister cityAnswer = checkCityRegister(so);
        AnswerWedding weddingAnswer = checkWedding(so);
        AnswerChildren childrenAnswer = checkChildren(so);
        AnswerStudent studentAnser = checkStudent(so);

        sendMail(so);
    }



    /**
     * @param so
     */
    public void sendMail(StudentOrder so) {

        senderMail.sendMail(so);
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

}
