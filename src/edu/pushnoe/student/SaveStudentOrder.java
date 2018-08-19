package edu.pushnoe.student;

import edu.pushnoe.student.domain.Address;
import edu.pushnoe.student.domain.Adult;
import edu.pushnoe.student.domain.Child;
import edu.pushnoe.student.domain.StudentOrder;

import java.time.LocalDate;

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
        buildStudentOrder(1).toString();

        FinanceStudentOrder.sendToFinance();

   }

    /**
     * @return
     */
   public static StudentOrder buildStudentOrder(long id){

       Address address = new Address("195000", "Заневский пр.", "12", "", "142");

       StudentOrder so = new StudentOrder();
       so.setStudentOrderId(id);
       StudentOrder so1 = so;
       printStudentOrder(so1);
       so.setMarriageCertificateId("" + (123456000 + id));
       so.setMarriageDate(LocalDate.of(2016, 7, 4));
       so.setMarriageOffice("Отдел ЗАГС");

       // Муж
       Adult husband = new Adult("Петров", "Виктор", "Сергеевич", LocalDate.of(1997, 8, 24));
       husband.setPassportSeria("" + (1000 + id));
       husband.setPassportNumber("" + (100000 + id));
       husband.setIssueDate(LocalDate.of(2017, 9, 15));
       husband.setIssueDepartment("Отдел милиции №" + id);
       husband.setStudentId("" + (100000 + id));
       husband.setAddress(address);
       // Жена
       Adult wife = new Adult("Петрова", "Вероника", "Алекссевна", LocalDate.of(1998, 3, 12));
       wife.setPassportSeria("" + (2000 + id));
       wife.setPassportNumber("" + (200000 + id));
       wife.setIssueDate(LocalDate.of(2018, 4, 5));
       wife.setIssueDepartment("Отдел милиции №" + id);
       wife.setStudentId("" + (200000 + id));
       wife.setAddress(address);
       // Дети
       Child child1 = new Child("Петрова", "Ирина", "Викторовна", LocalDate.of(2018, 6, 29));
       child1.setCertificateNumber("" + (300000 + id));
       child1.setIssueDate(LocalDate.of(2018, 7, 19));
       child1.setIssueDepartment("ОТдел ЗАГС №" + id);
       child1.setAddress(address);

       Child child2 = new Child("Петров", "Петр", "Викторович", LocalDate.of(2013, 6, 29));
       child2.setCertificateNumber("" + (300000 + id));
       child2.setIssueDate(LocalDate.of(2013, 4, 2));
       child2.setIssueDepartment("ОТдел ЗАГС №" + id);
       child2.setAddress(address);


       so.setHusbend(husband);
       so.setWife(wife);
       so.addChild(child1);
       so.addChild(child2);

       return so;
   }




    static void printStudentOrder(StudentOrder so) {
       // System.out.println(so.getStudentOrderId());
    }


}
