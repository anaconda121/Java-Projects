import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class User {
    
    private String first_name;
    private String last_name;
    private String uuid;
    private byte pin_hash[];
    private ArrayList<Account> accounts;

    //constructor
    public User(String first_name, String last_name, String pin, Bank bank) {
        this.first_name = first_name;
        this.last_name = last_name;

        //hashing method to store user pin
        try {        
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pin_hash = md.digest(pin.getBytes()); //hashing pin by retriving current id from mem location and apply md5 hash to it
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.exit(1);
        }

        //generating uuid
        this.uuid = bank.getNewUserUUID();

        //creating account list
        this.accounts = new ArrayList<Account>();

        System.out.printf("New user %s, %s with ID %s created.\n", last_name, first_name, this.uuid);
    }

    public String getUUID() {
        return this.uuid;
    }

    public boolean validatePin(String pin) {
        try {        
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(pin.getBytes()), this.pin_hash);
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return false;
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public String getFirstName() {
        return this.first_name;
    }

    public void printAccountSummary() {
        System.out.printf("\n\n%s's accounts summary\n", this.first_name);

        for (int a = 0; a < this.accounts.size(); a++) {
			System.out.printf("%d) %s\n", a+1, this.accounts.get(a).getSummaryLine());
		}
		System.out.println();
    }

    public int numAccounts() {
        return this.accounts.size();
    }

    public void printAcctTransHistory(int account_num) {
        this.accounts.get(account_num).printTransHistory();
    }

    public String getAcctUUID(int account_num) {
        return this.accounts.get(account_num).getUUID();
    }

    public void addAcctTransaction(int acctIdx, double amount, String memo) {
		this.accounts.get(acctIdx).addTransaction(amount, memo);
    }

    public double getAcctBalance(int acctIdx) {
		return this.accounts.get(acctIdx).getBalance();
	}
    
}