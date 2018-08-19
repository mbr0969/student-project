package edu.pushnoe.student.validator;

import edu.pushnoe.student.domain.children.AnswerChildren;
import edu.pushnoe.student.domain.StudentOrder;

public class ChildrenValidator {


    /**
     * @param so
     * @return
     */
    public static AnswerChildren checkChildren(StudentOrder so){
        System.out.println("checkChildren is running...");

        return new AnswerChildren();
    }

}
