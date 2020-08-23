package blackjack;

import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;
public class Blackjack {
    private static int[] deck = new int[60];
    private static int[] playerHand = new int[5];
    private static int[] dealerHand = new int[5];
    private static Random rand = new Random();

    public static void createDeck() {
        int index = 0;
        for (int x = 0; x < 4; x++) {
            for (int i = 2; i < 15; i++) {
                deck[index] = i;
                index++;
            }
        }
    }
    
    public static void shuffle () {
        System.out.println("\n");
        //implementing fischer yates shuffle
        for (int i = deck.length - 1; i > 0; --i) {
            int number = rand.nextInt(deck.length - 1);
            deck[i] = deck[number]; 
        }
    }

    public static void deal(int[] player, String typePlayer) {
        shuffle();
        String finalOutput = "";
        String[] output = new String[2];
        int total = 0;
        for (int i = 0; i < 2; i++) {
            if (typePlayer.equals("Dealer") && i == 1) {
                break;
            }
            int number = rand.nextInt(deck.length - 1);
            player[i] = deck[number];
            //handling face cards
            if (player[i] == 11) {
                output[i] = "Jack (11), ";
                total += 10;
            } else if (player[i] == 12) {
                output[i] = "Queen (12), ";
                total += 10;
            } else if(player[i] == 13) {
                output[i] = "King (13), ";
                total += 10;
            } else if (player[i] == 14) {
                output[i] = "Ace (14), ";
                if (total >= 11) {
                    total -= player[i];
                    total += 1;
                }
            } else {
                total += player[i];
                output[i] = String.valueOf(player[i]) + ", ";
            }
        }
        for (int i = 0; i < output.length; i++) {
            finalOutput += output[i];
        }
        if (typePlayer.equals("Player")) {
            showStartHand("Player", finalOutput, total);
        } else if(typePlayer.equals("Dealer")) {
            showStartHand("Dealer", finalOutput, total);
        }
    }

    public static void showStartHand(String typePlayer, String output, int total) {
        if (typePlayer.equals("Dealer")) {
            System.out.println("the dealer is showing a " + total);
        } else {
            System.out.println(typePlayer + " Hand: " + output + "For a total of: " + total);
        }
    }

    public static int calcTotal(int[] player) {
        int total = 0;
        for (int i = 0; i < player.length; i++) {
            if (player[i] == 11) {
                total += 10;
            } else if (player[i] == 12) {
                total += 10;
            } else if(player[i] == 13) {
                total += 10;
            } else if (player[i] == 14) {
                if (total >= 11) {
                    total -= player[i];
                    total += 1;
                }
            } else {
                total += player[i];
            }
        }
        return total;
    }

    public static void outputTotals() {
        System.out.println("Player Total: " + calcTotal(playerHand));
        System.out.println("Dealer Total: " + calcTotal(dealerHand));
    }

    public static boolean hit(int[] player, int times, String typeOfPlayer) {
        int total = calcTotal(player);
        int number = rand.nextInt(deck.length - 1);
        player[1 + times] = deck[number];
        total += deck[number];
        //bust
        if (total > 21 && typeOfPlayer.equals("Player")) {
            System.out.println("Bust Player Loses! Player hand was: " + Arrays.toString(player) + ". Dealer hand was: " + Arrays.toString(dealerHand));
            outputTotals();
            return true;
        } else if(total > 21 && typeOfPlayer.equals("Dealer")) {
            System.out.println("Bust Dealer Loses! Player hand was: " + Arrays.toString(player) + ". Dealer hand was: " + Arrays.toString(dealerHand));
            outputTotals();
            return true;
        } 

        //blackjack
        if (total == 21 && typeOfPlayer.equals("Player")) {
            System.out.println("Blackjack Player WINS! Player hand was: " + Arrays.toString(player) + ". Dealer hand was: " + Arrays.toString(dealerHand));
            outputTotals();
            return true;
        } else if (total == 21 && typeOfPlayer.equals("Dealer")) {
            System.out.println("Blackjack Dealer WINS! Player hand was: " + Arrays.toString(player) + ". Dealer hand was: " + Arrays.toString(dealerHand));
            outputTotals();
            return true;
        } 
        if (total < 21) {
            System.out.println("Player hand is: " + Arrays.toString(player) + ". Player Total is: " + calcTotal(playerHand) + " . Dealer hand is: " + calcTotal(dealerHand));
            outputTotals();
            return false;
        }
        if (typeOfPlayer.equals("Dealer")) {
            if (calcTotal(playerHand) > calcTotal(dealerHand)) {
                System.out.println("Player WINS! Player hand was: " + Arrays.toString(player) + ". Dealer hand was: " + Arrays.toString(dealerHand));
                outputTotals();
                return true;
            } else if(calcTotal(playerHand) < calcTotal(dealerHand)) {
                System.out.println("Dealer WINS! Player hand was: " + Arrays.toString(player) + ". Dealer hand was: " + Arrays.toString(dealerHand));
                outputTotals();
                return true;
            }
        }
        return false;
    }

    public static boolean stand(int[] player, int times, String typeOfPlayer) {
        int total = calcTotal(player);
        boolean game = false;
        while (total < 21) {
            game = hit(player, times, typeOfPlayer);
            if (game == true) {
                return true;
            }
        }
        return false;
    }

    public static void play() {
        createDeck();
        shuffle();
        deal(playerHand, "Player");
        deal(dealerHand, "Dealer");

        //user interface
        boolean gameOver = false;
        boolean game = false;
        String playerChoice = "";
        Scanner sc = new Scanner(System.in);
        int hitCount = 0;
        int standCount = 0;
        while (gameOver == false) {
            System.out.println("\nDo you want to hit (h) or stand (s)?");
            playerChoice = sc.nextLine();
            if (playerChoice.toLowerCase().equals("h") || playerChoice.toLowerCase().equals("hit")) {
                hitCount++;
                game = hit(playerHand, hitCount, "Player");
                if (game == true) {
                    gameOver = true;
                }
            } else if (playerChoice.toLowerCase().equals("s") || playerChoice.toLowerCase().equals("stand")) {
                standCount++;
                game = stand(dealerHand, standCount, "Dealer");
                if (game == true) {
                    gameOver = true;
                }
            }
        } 
    }
}