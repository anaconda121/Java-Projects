import java.util.Scanner;
public class ATM {
    
    public static void main(String []args) {
        Scanner sc = new Scanner(System.in);
        
        //default for testing
        Bank bank = new Bank("Tanish Tyagi Bank");
		User user = bank.addUser("Tanish", "Tyagi", "1234");
		Blackjack bj = new Blackjack();

        Account account = new Account("Checking", user, bank);
        user.addAccount(account);
        bank.addAccount(account);

        User cUser;
        while (true) {
            cUser = ATM.mainMenuPrompt(bank, sc);
            ATM.printUserMenu(cUser, sc);
        }
    }

    public static User mainMenuPrompt(Bank bank, Scanner sc) {
        String user_id;
        String pin;
        User auth_user;

        do {
            System.out.printf("\n\nWelcome to %s\n\n", bank.getName());		
			System.out.print("Enter user ID: ");
			user_id = sc.nextLine();
			System.out.print("Enter pin: ");
            pin = sc.nextLine();
            
            //creating user obj corresponding to id and pin
            auth_user = bank.userLogin(user_id, pin);

            if (auth_user == null) {
                System.out.println("Incorrect Pin/Id combination. Please try again!");
            }

        } while(auth_user == null);    

        //login successful
        return auth_user;
    }

    public static void printUserMenu(User user, Scanner sc) {
        //print user's accounts
        user.printAccountSummary();
        int choice;

        do {
            System.out.printf("\n\nWelcome %s, what would you like to do?\n\n", user.getFirstName());	
            System.out.println("  1) Show account transaction history");
			System.out.println("  2) Withdraw");
			System.out.println("  3) Deposit");
			System.out.println("  4) Transfer");
			System.out.println("  5) Quit/Gamble");
            System.out.println();
            
            System.out.print("Enter choice: ");
			choice = sc.nextInt();
			
			if (choice < 1 || choice > 6) {
				System.out.println("Invalid choice. Please choose 1-5.");
			}
        } while (choice < 1 || choice > 6);

        //process choice 
        switch(choice) {
            case 1:
                ATM.showHistory(user, sc);
                break;
            case 2:
                ATM.withdrawFunds(user, sc);
                break;
            case 3:
                ATM.depositFunds(user, sc);
                break;
            case 4:
                ATM.transfer(user, sc);
				break;
			case 5:
				System.out.println("Off you go to gambling! Good Luck! \n");
				break;
			
        }

        //show menu again unless user wants to quit
        if (choice != 5) {
            ATM.printUserMenu(user, sc);
        }
    }

    public static void showHistory(User user, Scanner sc) {
        int account;

        // get account whose transactions to print
        do {
            System.out.printf("Enter the number (1-%d) of the account\nwhose " + "transactions you want to see: ", user.numAccounts());
            account = sc.nextInt() - 1; //subtract b/c first index is 0
            if (account < 0 || account >= user.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (account < 0 || account >= user.numAccounts());

        // print the transaction history
        user.printAcctTransHistory(account);
    }

    public static void transfer(User theUser, Scanner sc) {
        int fromAcct;
		int toAcct;
		double amount;
		double acctBal;
		
		// get account to transfer from
		do {
			System.out.printf("Enter the number (1-%d) of the account to " + "transfer from: ", theUser.numAccounts());
			fromAcct = sc.nextInt()-1;
			if (fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again.");
			}
		} while (fromAcct < 0 || fromAcct >= theUser.numAccounts());
		acctBal = theUser.getAcctBalance(fromAcct);
		
		// get account to transfer to
		do {
			System.out.printf("Enter the number (1-%d) of the account to " + "transfer to: ", theUser.numAccounts());
			toAcct = sc.nextInt()-1;
			if (toAcct < 0 || toAcct >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again.");
			}
		} while (toAcct < 0 || toAcct >= theUser.numAccounts());
		
		// get amount to transfer
		do {
			System.out.printf("Enter the amount to transfer (max $%.02f): $", acctBal);
			amount = sc.nextDouble();
			if (amount < 0) {
				System.out.println("Amount must be greater than zero.");
			} else if (amount > acctBal) {
				System.out.printf("Amount must not be greater than balance " +"of $.02f.\n", acctBal);
			}
		} while (amount < 0 || amount > acctBal);
		
		// finally, do the transfer 
		theUser.addAcctTransaction(fromAcct, -1*amount, String.format("Transfer to account %s", theUser.getAcctUUID(toAcct)));
		theUser.addAcctTransaction(toAcct, amount, String.format("Transfer from account %s", theUser.getAcctUUID(fromAcct)));
    }

    public static void withdrawFunds(User theUser, Scanner sc) {
		
		int fromAcct;
		double amount;
		double acctBal;
		String memo;
		
		// get account to withdraw from
		do {
			System.out.printf("Enter the number (1-%d) of the account to " + 
					"withdraw from: ", theUser.numAccounts());
			fromAcct = sc.nextInt()-1;
			if (fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again.");
			}
		} while (fromAcct < 0 || fromAcct >= theUser.numAccounts());
		acctBal = theUser.getAcctBalance(fromAcct);
		
		// get amount to transfer
		do {
			System.out.printf("Enter the amount to withdraw (max $%.02f): $", 
					acctBal);
			amount = sc.nextDouble();
			if (amount < 0) {
				System.out.println("Amount must be greater than zero.");
			} else if (amount > acctBal) {
				System.out.printf("Amount must not be greater than balance " +
						"of $%.02f.\n", acctBal);
			}
		} while (amount < 0 || amount > acctBal);
		
		// gobble up rest of previous input
		sc.nextLine();
		
		// get a memo
		System.out.print("Enter a memo: ");
		memo = sc.nextLine();
		
		// do the withdrwal
		theUser.addAcctTransaction(fromAcct, -1*amount, memo);
		
    }
    
    public static void depositFunds(User theUser, Scanner sc) {
		
		int toAcct;
		double amount;
		String memo;
		
		// get account to withdraw from
		do {
			System.out.printf("Enter the number (1-%d) of the account to " + 
					"deposit to: ", theUser.numAccounts());
			toAcct = sc.nextInt()-1;
			if (toAcct < 0 || toAcct >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again.");
			}
		} while (toAcct < 0 || toAcct >= theUser.numAccounts());
		
		// get amount to transfer
		do {
			System.out.printf("Enter the amount to deposit: $");
			amount = sc.nextDouble();
			if (amount < 0) {
				System.out.println("Amount must be greater than zero.");
			} 
		} while (amount < 0);
		
		// gobble up rest of previous input
		sc.nextLine();
		
		// get a memo
		System.out.print("Enter a memo: ");
		memo = sc.nextLine();
		
		// do the deposit
		theUser.addAcctTransaction(toAcct, amount, memo);
		
	}
}