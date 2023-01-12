package de.dkh.springdemo;

public class MyApp {

    public static void main(String[] args) {

        Coach bCoach = new TrackCoach();
        System.out.println(bCoach.getDailyWorkout());
    }
}
