import java.util.Scanner;


public class Casino {
    
    public static void main(String [] args) {
        System.out.println("Hello User! Please deposit money into your checkings account so you could start to play!!");
        //ATM integration for users to deposit money
        Scanner sc = new Scanner(System.in);
        
        //default for testing
        Bank bank = new Bank("Tanish Tyagi Bank");
        User user = bank.addUser("Tanish", "Tyagi", "1234");
        Account account = new Account("Checking", user, bank);
        ATM atm = new ATM();

        user.addAccount(account);
        bank.addAccount(account);

        User cUser;
        while (true) {
            cUser = ATM.mainMenuPrompt(bank, sc);
            ATM.printUserMenu(cUser, sc);
            break;
        }

        //casino 
        System.out.println("Welcome to the Tyagi Casino!");
        double checking_balance = account.getBalance(); //checkings account
        System.out.println("Money you have deposited into your gambling account $" + checking_balance);

        int choice;
        do {
            System.out.printf("\n\nWelcome %s, what would you like to do?\n\n", user.getFirstName());	
            System.out.println("  1) BlackJack");
			System.out.println("  2) Poker");
			System.out.println("  3) Roulette");
			System.out.println("  4) ATM");
			System.out.println("  5) Quit");
            System.out.println();
            
            System.out.print("Enter choice: ");
			choice = sc.nextInt();
			
			if (choice < 1 || choice > 5) {
				System.out.println("Invalid choice. Please choose 1-5.");
			}
        } while (choice < 1 || choice > 5);

        switch(choice) {
            case 1:
                play();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
				break;
			case 5:
				break;
        }

        double bal = get_checking_bal(account);
        System.out.println("\nBalance $" + bal);

        Scanner gamble = new Scanner(System.in);

        if (bal == 0) {
            System.out.println("You have not deposited any money! You cannot play!!");
            while (true) {
                user = atm.mainMenuPrompt(bank, gamble);
                atm.printUserMenu(user, gamble);
                break;
            }
        } else {
            System.out.println("You are ready to play! Would you like to know the rules of Blackjack? (Y/N)");
            String rules = gamble.nextLine();

            if (rules.toLowerCase().equals("y") || rules.toLowerCase().equals("yes")) {
                rules();
            }
        }
    }

    public static double get_checking_bal(Account account) {
        double checking_balance = account.getBalance();
        return checking_balance;
    }

    public static void printCasinoMenu(User user, Scanner sc){

    }

    public static void play() {
        
    }

    public static void rules() {
        //rules
        System.out.println("\nThe object of the game is for the player is to draw cards totaling closer to 21 than the dealer's cards, all without going over 21.");
        System.out.println("Each card denomination is assigned a point value. Cards 2 through 10 are worth their face value, face cards (kings, queens, and jacks) are each worth 10, and aces may be used as either 1 or 11. ");
        System.out.println("Play begins when you place a bet in the betting square on the table directly in front of you. ");
        System.out.println("After all bets have been placed, each player and the dealer are given two cards. ");
        System.out.println("A player's cards are both dealt face up. In the dealer's case, only one of their cards is dealt face up, and the other face down. ");
        System.out.println("When the dealer's up card is an ace, players generally have the option to take Insurance, which is a side bet restricted to half of the original bet. Insurance pays at 2:1 if the dealer does hit Blackjack. ");
        System.out.println("If the dealer has a ten or an ace showing, then he will peek at his face down card to see if he has a Blackjack. If he does, then he will turn it over immediately.");
        System.out.println("If the dealer does have a Blackjack, then all hands will lose, unless a player also has a Blackjack, which will result in a push. \n\n");

        //options
        System.out.println("Player Options:");
        System.out.println("----------------------------------------"); //40 dashes
        System.out.println("Hit: Take another card from the dealer.");
        System.out.println("Stand: Take no more cards");
        System.out.println("Double down: Players are allowed to double their initial bet, but then receive only one more card. ");
        System.out.println("Split (only available as the first decision of a hand): If the first two cards have the same value, the player can split them into two hands, by moving a second bet equal to the first into an area outside the betting box. The dealer separates the two cards and draws a card to each. The player then plays out the two hands. ");
        System.out.println("Surrender (only available as first decision of a hand): When the player surrenders, the house takes half the player's bet and returns the other half to the player, which ends the players hand.");
    }

}