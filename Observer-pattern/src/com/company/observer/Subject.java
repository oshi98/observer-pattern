package com.company.observer;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

//newspaper agency
public class Subject {

    List<Observer> observers = new ArrayList<>();

    //subscribe
    public void subscribe(Observer observer){
        //call the database and run a sql query to insert this observer
        observers.add(observer);
    }

    //unsubscribe
    public void unsubscribe(Observer observer){
        //call the database and run a sql query to delete this observer
        observers.remove(observer);
    }

    //when a new newspaper is available
    public void notifyObservers(String news){

        for (Observer observer : observers) {
            observer.notifyObserver(news);
        }
    }
}
