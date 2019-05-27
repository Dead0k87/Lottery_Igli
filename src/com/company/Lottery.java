package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public final class Lottery implements Runnable {
    public static int roundTime = 60 * 5 * 1000; // round time (ms) 7 * 1000
    private static final int listCapacity = 10;
    private CopyOnWriteArrayList<Attendee> attendeeList = new CopyOnWriteArrayList<Attendee>();// new ArrayList<>(listCapacity); // !!! change to concurrent Colletction
    private volatile static Lottery instance;
    private Object lock = new Object();
    public volatile boolean isWinnerSelected = false;
    private volatile Attendee winner = null;
    int round = 1;

    private Lottery() {
    }

    public static Lottery getInstance() {
        Lottery result = instance;
        if (result == null) {
            synchronized (Lottery.class) {
                result = instance;
                if (result == null) {
                    instance = result = new Lottery();
                }
            }
        }
        return instance;
    }

    public boolean takePartInLottery(Attendee attendee) {
        if (attendee != null) {
            if (attendeeList.size() != listCapacity) {
                attendeeList.add(attendee);
                return true;
            } else {
                System.out.println("Sorry! List is full. Wait for next round");
                return false;
            }
        }
        return false;
    }


    public void run() {
        while (true) {
            try {
                System.out.println("Round #" + round + ": ");
                isWinnerSelected = false;
                Random random = new Random();
                int randomNum = random.nextInt(attendeeList.size());

                winner = attendeeList.get(randomNum);
                System.out.println("Random winner in Round #" + round + " is: " + winner);
                System.out.println("* * *");
                isWinnerSelected = true;
                round++;
                Thread.sleep(roundTime); //60 * 1000 * 5
            } catch (InterruptedException e) {
            }
        }
    }

    public Attendee getWinner() {
        if (isWinnerSelected) {
           // System.out.println("Winner is: " + winner + "(from thread " + Thread.currentThread().getName() + ")");
            return winner;
        } else {
            System.out.println("Winner is not selected yet.");
            return null;
        }
    }
}
