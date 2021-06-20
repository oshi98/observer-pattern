package com.company.observer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.util.*;
import java.util.Scanner;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        //publisher->Subject
        //subscriber->Observer
        //types of observers->newsreader, foodwrapper

        //get user email
        Scanner getUserEmail = new Scanner(System.in);
        System.out.println("Enter your email to receive news alerts:");
        String userEmail = getUserEmail.nextLine();
        //get user email
        
        Observer observer1 = new TypeObserver();
        Observer observer2 = new Type2Observer();
        Observer observer3 = new Type3Observer();

        Subject subject = new Subject();
        subject.subscribe(observer1);
        subject.subscribe(observer2);

        //read text file from folder
        File directoryPath = new File("E:\\design-patterns-2021\\news");
        File filesList[] = directoryPath.listFiles();
        Scanner sc = null;

        for(File file : filesList) {
            sc= new Scanner(file);
            String input;
            StringBuffer sb = new StringBuffer();
            while (sc.hasNextLine()) {
                input = sc.nextLine();
                sb.append(input+" ");
            }

            subject.notifyObservers(sb.toString());

            Thread.sleep(10000);

            System.out.println("====================================");

            //observer 3 subscribes
            subject.subscribe(observer3);

            Thread.sleep(5000);//wait another 5 seconds

            //notify all subscribers
            subject.notifyObservers(sb.toString());

            Thread.sleep(5000);//wait another 5 seconds

            //observer 2 unsubscribes
            subject.unsubscribe(observer3);

            Thread.sleep(5000);//wait another 5 seconds

            System.out.println("====================================");

            //notify remaining subscribers
            subject.notifyObservers(sb.toString());
            
            //send email
            String to = userEmail;
            String from = "indigoorg83@gmail.com";
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", 587);

            Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator(){
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication("indigoorg83@gmail.com", "02bahpzoffeq");
                }
            });
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                message.setSubject("News Alert!");
                message.setText(sb.toString());
                Transport.send(message);
                System.out.println("Sent message successfully...");
            }catch (MessagingException mex){
                mex.printStackTrace();
            }
            //end send email

        }

        //old lines
//        subject.notifyObservers("file");
//
//        Thread.sleep(10000);
//        System.out.println("====================================");
//
//        subject.notifyObservers("Reversed : Curfew will not be imposed today");
//
//        Thread.sleep(5000);//wait another 5 seconds
//
//        subject.unsubscribe(observer2);
//
//        Thread.sleep(5000);//wait another 5 seconds
//        System.out.println("====================================");
//        subject.notifyObservers("Fuel prices gone up");
        //end old lines

        //create a folder in your machine called news
        //whenever you have news create a new text file and add news to it and copy the text file into news folder
        //subject class keep watching the news folder->whenever new news is available, it can call the notifyobservers method with the news content
        //then you can delete the news file
        //keep watching for more news

        //create a small gui
        //create a form to enter the name and email address of user
        //and submit->you can add user to observer list


    }

}
