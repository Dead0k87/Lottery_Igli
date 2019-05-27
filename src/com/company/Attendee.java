package com.company;

import java.util.Objects;

public class Attendee implements Runnable {
    private String name;
    private Lottery lottery;

    public Lottery getLottery() {
        return lottery;
    }

    public void setLottery(Lottery lottery) {
        this.lottery = lottery;
    }

    public Attendee(String name) {
        this.name = name;
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
        if (winner == null) {
            System.out.println("Nobody is selected yet.");
        } else if (winner.equals(this)) {
            System.out.println(Thread.currentThread().getName() + ": I am the winner :D \\o/");

        } else if (!winner.equals(this)) {
            System.out.println(Thread.currentThread().getName() + ": hmm sad... I am not a winner :(");
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
