package com.example.validation;




public class AutomobileValid {

    public static final String numberExp = "\\d+";

    public boolean validateByYear(String year) {
        if(year.matches(numberExp)) {
            return true;
        }
        return false;
    }

    public boolean validateByProbeg(String probeg){
        if(probeg.matches(numberExp)) {
            return true;
        }
        return false;
    }

    public boolean validateByPowerty(String powerty) {
        if(powerty.matches(numberExp)) {
            return true;
        }
        return false;
    }

    public boolean validateByRentpay(String rentpay) {
        if(rentpay.matches(numberExp)) {
            return true;
        }
        return false;
    }
}
