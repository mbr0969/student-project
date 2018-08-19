package edu.pushnoe.student.validator;

import edu.pushnoe.student.domain.Person;
import edu.pushnoe.student.domain.register.AnswerCityRegister;
import edu.pushnoe.student.domain.Child;
import edu.pushnoe.student.domain.register.AnswerCityRegisterItem;
import edu.pushnoe.student.domain.register.CityRegisterResponse;
import edu.pushnoe.student.domain.StudentOrder;
import edu.pushnoe.student.exeption.CityRegisterException;
import edu.pushnoe.student.validator.register.CityRegisterChecker;
import edu.pushnoe.student.validator.register.FakeCityRegisterChecker;

import java.util.List;

public class CityRegisterValidator {

    private String hostname;
    private int port;
    private String login;
    private String password;


    private CityRegisterChecker personChecker;

    public CityRegisterValidator() {
        personChecker = new FakeCityRegisterChecker();

    }

    /**
     * @param so
     * @return
     */
   public AnswerCityRegister checkCityRegister(StudentOrder so){

       AnswerCityRegister ans = new AnswerCityRegister();

       ans.addItem(checkPerson(so.getHusbend()));
       ans.addItem(checkPerson(so.getWife()));

       for (Child child : so.getChildren()) {
           ans.addItem(checkPerson(child));
       }

       return ans;
   }

    /**
     *
     * @param person
     * @return
     */
   private AnswerCityRegisterItem checkPerson(Person person){


       try {
               CityRegisterResponse cans = personChecker.checkerPerson(person);
       }catch (CityRegisterException ex){
           ex.printStackTrace(System.out);
           // ex.printStackTrace(System.out);
       }

       return null;
   }

}
