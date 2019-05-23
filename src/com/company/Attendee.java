package com.company;

import java.util.Objects;

public class Attendee implements Runnable {
    private String name;
    private Lottery lottery = Lottery.getInstance();


    public Attendee(String name) {
        this.name = name;
        lottery.takePartInLottery(this);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Attendee #" + name;
    }

    @Override
    public void run() {
        Attendee winner = lottery.getWinner();
        if (winner.equals(this)) {
            System.out.println("I am the winner! Name: " + getName() + " from " + Thread.currentThread().getName());
            System.out.println("--------------------------------------------------------------");
        }


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attendee attendee = (Attendee) o;
        return Objects.equals(name, attendee.name) &&
                Objects.equals(lottery, attendee.lottery);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lottery);
    }
}
