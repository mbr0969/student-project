package edu.pushnoe.student.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StudentOrder {


   private long studentOrderId;
   private LocalDateTime studentOrderDate;
   private StudentOrderStatus studentOrderStatus;
   private Adult husbend;
   private Adult wife;
   private List<Child> children;
   private String marriageCertificateId;
   private RegisterOffice marriageOffice;
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

   public RegisterOffice getMarriageOffice() {
      return marriageOffice;
   }

   public void setMarriageOffice(RegisterOffice marriageOffice) {
      this.marriageOffice = marriageOffice;
   }

   public LocalDate getMarriageDate() {
      return marriageDate;
   }

   public void setMarriageDate(LocalDate marriageDate) {
      this.marriageDate = marriageDate;
   }

   public LocalDateTime getStudentOrderDate() {
      return studentOrderDate;
   }

   public void setStudentOrderDate(LocalDateTime studentOrderDate) {
      this.studentOrderDate = studentOrderDate;
   }

   public StudentOrderStatus getStudentOrderStatus() {
      return studentOrderStatus;
   }

   public void setStudentOrderStatus(StudentOrderStatus studentOrderStatus) {
      this.studentOrderStatus = studentOrderStatus;
   }

   public void setChildren(List<Child> children) {
      this.children = children;
   }

   @Override
   public String toString() {
      final StringBuffer sb = new StringBuffer("StudentOrder{");
      sb.append("studentOrderId=").append(studentOrderId);
      sb.append(", studentOrderDate=").append(studentOrderDate);
      sb.append(", studentOrderStatus=").append(studentOrderStatus);
      sb.append(", husbend=").append(husbend);
      sb.append(", wife=").append(wife);
      sb.append(", children=").append(children);
      sb.append(", marriageCertificateId='").append(marriageCertificateId).append('\'');
      sb.append(", marriageOffice=").append(marriageOffice);
      sb.append(", marriageDate=").append(marriageDate);
      sb.append('}');
      return sb.toString();
   }
}
