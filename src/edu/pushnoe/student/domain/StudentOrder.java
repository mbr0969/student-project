package edu.pushnoe.student.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentOrder {


   private long studentOrderId;
   private Adult husbend;
   private Adult wife;
   private List<Child> children;
   private String marriageCertificateId;
   private String marriageOffice;
   private LocalDate marriageDate;


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

   public Adult getWife() {
      return wife;
   }

   public void setWife(Adult whife) {
      this.wife = whife;
   }

   public List<Child> getChildren() {
      return children;
   }


   public void addChild(Child child){

       if (children == null){

           children = new ArrayList<>(5);
       }

       children.add(child);

   }

   public String getMarriageCertificateId() {
      return marriageCertificateId;
   }

   public void setMarriageCertificateId(String marriageCertificateId) {
      this.marriageCertificateId = marriageCertificateId;
   }

   public String getMarriageOffice() {
      return marriageOffice;
   }

   public void setMarriageOffice(String marriageOffice) {
      this.marriageOffice = marriageOffice;
   }

   public LocalDate getMarriageDate() {
      return marriageDate;
   }

   public void setMarriageDate(LocalDate marriageDate) {
      this.marriageDate = marriageDate;
   }

}
