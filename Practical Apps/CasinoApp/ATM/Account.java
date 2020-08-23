import java.util.ArrayList;
public class Account {
    
    private String name;
    private double balance;
    private String uuid;
    private User holder;
    private ArrayList<Transaction> transactions;

    public Account(String name, User holder, Bank bank) {
        this.name = name;
        this.holder = holder;

        this.uuid = bank.getNewAcountUUID();

        //transactions 
        this.transactions = new ArrayList<Transaction>();

    }

    public Account() {
	}

	public String getUUID() {
        return this.uuid;
    }

    public String getSummaryLine() {
        //get balance
        double balance = this.getBalance();

        //get format summary line, depending on balance
        if (balance >= 0) {
            return String.format("%s : $%.02f : %s", this.uuid, balance, this.name); //$.02f floating point num w/ 2 decimal points
        } else {
            return String.format("%s : $(%.02f) : %s", this.uuid, balance, this.name); //$.02f floating point num w/ 2 decimal points
        }
    }

    public double getBalance() {
        double balance = 0;
        for (Transaction t: this.transactions) {
            balance += t.getAmount();
        }
        return balance;
    }

    public void printTransHistory() {
        System.out.printf("\nTransaction history for account %s\n", this.uuid);

        //getting transaction list and printing
        for (int t = this.transactions.size()-1; t >= 0; t--) {
			System.out.println(this.transactions.get(t).getSummaryLine());
		}
		System.out.println();
    }

    public void addTransaction(double amount, String memo) {
		
		// create new transaction and add it to our list
		Transaction newTrans = new Transaction(amount, this);
		this.transactions.add(newTrans);
		
	}

}