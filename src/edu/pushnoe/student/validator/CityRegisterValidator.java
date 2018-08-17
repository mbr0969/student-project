package edu.pushnoe.student.validator;

import edu.pushnoe.student.domain.AnswerCityRegister;
import edu.pushnoe.student.domain.StudentOrder;

public class CityRegisterValidator {

    public String hostname;
    public String login;
    public String password;
    protected int port;

    /**
     * @param so
     * @return
     */
   public AnswerCityRegister checkCityRegister(StudentOrder so){

        System.out.println("checkCityRegister is running..." + hostname +", " + login + ", " + password);
        AnswerCityRegister ans = new AnswerCityRegister();
        ans.success = false;

        return ans;

   }

}
