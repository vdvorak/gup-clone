package ua.com.gup.model.profiles;

import java.util.Arrays;


public class CheckMainPhone {
    private String[] mainPhones;

    public String[] getMainPhones() {
        return mainPhones;
    }

    public void setMainPhones(String[] mainPhones) {
        this.mainPhones = mainPhones;
    }

    @Override
    public String toString() {
        return "CheckMainPhone{" +
                "mainPhones=" + Arrays.toString(mainPhones) +
                '}';
    }
}
