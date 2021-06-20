package com.company.observer;

public class TypeObserver implements Observer{
    //accept the email address as a constructor parameter
    private String email;

    @Override
    public void notifyObserver(String news) {
        //send an actual email to the observer
        System.out.println("Reading the newspaper: " + news);
    }
}
