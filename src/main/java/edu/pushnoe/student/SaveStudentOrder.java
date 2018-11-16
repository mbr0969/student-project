package edu.pushnoe.student;

import edu.pushnoe.student.dao.StudentDaoIpml;
import edu.pushnoe.student.dao.StudentOrderDao;
import edu.pushnoe.student.domain.*;
import edu.pushnoe.student.exception.DaoException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class SaveStudentOrder implements Runnable {


    public static void main(String[] args) {

        SaveStudentOrder order = new SaveStudentOrder();
        order.run();
    }


    /**
     * @param studentOrder
     * @return
     */
   private static long saveStudentOrder(StudentOrder studentOrder){

        long answer = 199;
        System.out.println("saveStudentOrder is running...");
        return answer;

    }


    /**
     *
     */
   @Override
   public void run() {

       //StudentOrder soc = buildStudentOrder(1);
       StudentOrderDao dao = new StudentDaoIpml();
       try {
     //    long id = dao.saveStudentOrder(soc);
//         System.out.println(id);
           List<StudentOrder> soList = dao.getStudentOrders();

           for (StudentOrder so : soList ){

               System.out.println(so.getStudentOrderId());
               //System.out.println(s.getMarriageCertificateId());
               //System.out.println(s.getStudentOrderStatus());
               //System.out.println(s.getStudentOrderDate());

           }

       } catch (DaoException e) {
           e.printStackTrace();
       } catch (SQLException e) {
           e.printStackTrace();
       }

//          Long id = null;
//          id = dao.saveStudentOrder(so);
//          System.out.println(id);
//          buildStudentOrder(1).toString();
//       List<Street> d = null;
//       try {
//           d = new DictionaryDaoImpl().findStreets("про");
//       } catch (DaoException e) {
//           e.printStackTrace();
//       }
//
//       for (Street s : d ){
//           System.out.println(s.getStreetName());
//       }



       //FinanceStudentOrder.sendToFinance();

   }






    static void printStudentOrder(StudentOrder so) {
       // System.out.println(so.getStudentOrderId());
    }


}
