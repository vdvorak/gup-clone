package ua.com.itproekt.gup.sms_api;

/**
 * Created by Optical Illusion on 30.10.2015.
 */
public class Phones {
    public String idMessage;
    public String varaibles;
    public String phone;

    public Phones(String idMessage,String variables,String phone){
        this.phone=phone;
        this.varaibles=variables;
        this.idMessage=idMessage;
    }

    public String getIdMessage(){
        return this.idMessage;
    }

    public String getVariable(){
        return this.varaibles;
    }

    public String getPhone(){
        return this.phone;
    }

}
