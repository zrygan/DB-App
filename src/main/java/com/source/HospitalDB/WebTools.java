package com.source.HospitalDB;

public class WebTools {
    // verifies the name is valid and split's it
    // Surname, Firstname -> [Surname, Firstname]
    // name1, Firstname -> null (since surname has a number)
    public static String[] splitName(String completeName){
        String re = "^[a-zA-Z,\\s]+$";
        if (!completeName.matches(re) || completeName.trim().isEmpty()){
            return null;
        } else{
            return completeName.split(",");
        }
    }
}
