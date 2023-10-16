package ch.makery.address.model;

public class ExeptionPerson extends Exception {
    String msg;

    public ExeptionPerson(){}
    public ExeptionPerson(String msg){
        this.msg = msg;
    }
    public String printMsg(){
        return this.msg;
    }
}
