package com.example.validation;




public class AutomobileValid {

    public static final String numberExp = "\\d+";

    public boolean validateByYear(String year) {
       if(year.isEmpty()) {
           return false;
       }
        if(year.matches(numberExp)) {
            return true;
        }
        return false;
    }

    public boolean validateByProbeg(String probeg){
       if(probeg.isEmpty()) {
           return false;
       }
        if(probeg.matches(numberExp)) {
            return true;
        }
        return false;
    }

    public boolean validateByPowerty(String powerty) {
       if(powerty.isEmpty()) {
           return false;
       }
        if(powerty.matches(numberExp)) {
            return true;
        }
        return false;
    }

    public boolean validateByRentpay(String rentpay) {
       if(rentpay.isEmpty()) {
           return false;
       }
        if(rentpay.matches(numberExp)) {
            return true;
        }
        return false;
    }
}
