package com.company.observer;

//wrapping the food using the news paper
public class Type3Observer implements Observer{

    @Override
    public void notifyObserver(String news) {
        System.out.println("Throw the newspaper away: " + news);
    }
}
