package com.example.validation;




public class ClientValid {

    public static boolean validateByTelephone(String telephone)  {
       if(telephone == null || telephone.isEmpty()) {return false;}
       for(int i = 0; i < telephone.length(); i++) {
           if(!Character.isDigit(telephone.charAt(i))) {return false;}
       }

       return true;
    }

    public static boolean validateByPassword(String password) {
        if(password.isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean validateByUsername(String username) {
        if("".equals(username)) {
            return false;
        }
        return true;
    }
}
