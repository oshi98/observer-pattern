package com.company.observer;

//wrapping the food using the news paper
public class Type2Observer implements Observer{

    @Override
    public void notifyObserver(String news) {
        System.out.println("Wrap the foods using the newspaper: " + news);
    }
}
