package edu.pushnoe.student.domain;

public enum  StudentOrderStatus {

    START, CHECKED;

    public static StudentOrderStatus fromValue(int value){
        for (StudentOrderStatus sos : StudentOrderStatus.values()){
            if(sos.ordinal() == value){
                return sos;
            }
        }
        throw new RuntimeException("Неизвестное занчение:" +value);
    }

}
