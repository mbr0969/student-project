package edu.pushnoe.student.domain;

public class StudentOrder {


   private long studentOrderId;
   private Adult husbend;
   private Adult whife;
   private Child child;


   public long getStudentOrderId() {
      return studentOrderId;
   }

   public void setStudentOrderId(long studentOrderId) {
      this.studentOrderId = studentOrderId;
   }

   public Adult getHusbend() {
      return husbend;
   }

   public void setHusbend(Adult husbend) {
      this.husbend = husbend;
   }

   public Adult getWhife() {
      return whife;
   }

   public void setWhife(Adult whife) {
      this.whife = whife;
   }

   public Child getChild() {
      return child;
   }

   public void setChild(Child child) {
      this.child = child;
   }
}
