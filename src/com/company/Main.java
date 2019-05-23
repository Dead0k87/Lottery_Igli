package com.company;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Lottery lottery = Lottery.getInstance();
        Attendee a1 = new Attendee("1");
        Attendee a2 = new Attendee("2");
        Attendee a3 = new Attendee("3");
        Thread game = new Thread(lottery);
        game.start();

        Thread.sleep(Lottery.roundTime - Lottery.roundTime + 1000);

        while (true) { // everybody checks who is the winner
            if (lottery.isWinnerSelected) {

                System.out.println();
                System.out.println("Attendees start to check who is the winner: ");
                new Thread(a1).start();
                new Thread(a2).start();
                new Thread(a3).start();

                Thread.sleep(Lottery.roundTime); //- 3000
            }
        }
    }
}


///Volatile task

//Implement a simple lottery game (command line Java application) which displays in real time the
// winner of the lottery (max attendees 10). Winner is picked up randomly, and is updated every 5 minutes.

//Different users are able to check the lottery results at the same time. All users should see the same result.