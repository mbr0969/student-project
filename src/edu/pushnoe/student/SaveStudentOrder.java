package edu.pushnoe.student;

import edu.pushnoe.student.domain.Adult;
import edu.pushnoe.student.domain.StudentOrder;

public class SaveStudentOrder implements Runnable {


    public static void main(String[] args) {

        SaveStudentOrder save = new SaveStudentOrder();
        save.run();

    }


    /**
     * @param studentOrder
     * @return
     */
   private static long saveStudentOrder(StudentOrder studentOrder){

        long answer = 199;
        System.out.println("saveStudentOrder is running..." );
        return answer;

    }


    /**
     *
     */
   @Override
   public void run() {
//
//        StudentOrder so = buildStudentOrder();
//        long ans = saveStudentOrder(so);
//        ScheduleStudentOrder.run();
        FinanceStudentOrder.sendToFinance();

   }

    /**
     * @return
     */
   static StudentOrder buildStudentOrder(long id){

       StudentOrder so = new StudentOrder();
       so.setStudentOrderId(id);

       Adult husbend = new Adult("Борзовы","Вася","ЛЬвович", null,null);
//       Adult husband = new Adult();
//       husband.setGivenName("Михаил");
//       husband.setSurName("Борзов");
//       husband.setPassportNumber("959621");
//       so.setHusbend(husband);
//       Adult wife = new Adult();
//       wife.setGivenName("Наташа");
//       wife.setSurName("Григорьева");
//       wife.setPassportNumber("888666");
//       so.setWhife(wife);
//
//      String ans =  husband.getPursontString();
//      String wf = wife.getPursontString();
//      System.out.println( ans);
//      System.out.println(wf);

       return so;
   }
}
