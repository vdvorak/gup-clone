package ua.com.itproekt.gup.api.rest.Queue2;

public class Food implements Comparable<Food> {

    public int vkus;

    Food(int vkus){
        this.vkus = vkus;
    }

    public int compareTo(Food f){
        if(f.vkus > vkus)
            return -1;
        if(vkus > f.vkus)
            return 1;
        return 0;
    }
}
