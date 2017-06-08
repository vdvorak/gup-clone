package ua.com.itproekt.gup.rental.algorithm.calculator;


import com.google.gson.Gson;

public class Aaa {

    public static void main(String[] args) {
        Gson gson = new Gson();

        String value = "5901b9f18218d6960747599d";
        String[] values = {"58ee044d4c8e83648c2f1aec","58edf17a4c8e83648c2f1aa3","58ed61ab4c8ebd9459277498","58ede8da4c8ebd94592774c3","5901b9f18218d6960747599d","58ede40c4c8ebd94592774be","58f64c754c8e83648c2f1f34","5922e12c06545131b96bcdbf","5922eb8e0f6650c6c07ec174","58ed606e4c8ebd9459277495","5912f7a6d25402527cb7c342","58edf1314c8ebd94592774ca"};

//        System.out.println( gson.to(value) );
        System.out.println( gson.toJson(values) );
    }

}
