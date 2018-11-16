package edu.pushnoe.student.validator;

import edu.pushnoe.student.domain.Person;
import edu.pushnoe.student.domain.register.AnswerCityRegister;
import edu.pushnoe.student.domain.Child;
import edu.pushnoe.student.domain.register.AnswerCityRegisterItem;
import edu.pushnoe.student.domain.register.CityRegisterResponse;
import edu.pushnoe.student.domain.StudentOrder;
import edu.pushnoe.student.exception.CityRegisterException;
import edu.pushnoe.student.exception.TransportException;
import edu.pushnoe.student.validator.register.CityRegisterChecker;
import edu.pushnoe.student.validator.register.FakeCityRegisterChecker;

public class CityRegisterValidator {

    public static final String IN_CODE = "NO_GRN";

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

       AnswerCityRegisterItem.CityStatus status = null;
       AnswerCityRegisterItem.CityError  error = null;

       try {

          CityRegisterResponse tmp = personChecker.checkerPerson(person);
          status = tmp.isExisting() ?
                  AnswerCityRegisterItem.CityStatus.YES :
                  AnswerCityRegisterItem.CityStatus.NO;

       }catch (CityRegisterException ex){
           ex.printStackTrace(System.out);
           status = AnswerCityRegisterItem.CityStatus.ERROR;
           error = new AnswerCityRegisterItem.CityError(ex.getCode(), ex.getMessage());
       }catch (TransportException ex){
           ex.printStackTrace(System.out);
           status = AnswerCityRegisterItem.CityStatus.ERROR;
           error = new AnswerCityRegisterItem.CityError(IN_CODE, ex.getMessage());
       }

       AnswerCityRegisterItem ans = new  AnswerCityRegisterItem(status, person, error);

       return ans;
   }

}
