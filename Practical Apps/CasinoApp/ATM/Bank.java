import java.util.ArrayList;
import java.util.Random;
public class Bank {

    private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;

    public Bank(String name){
        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }

    public String getNewUserUUID() {
        String uuid;
        Random rand = new Random();
        int length = 6;
        boolean nonUnique = false;

        do {
            uuid = "";
            for (int c = 0; c < length; c++) {
                uuid += ((int)rand.nextInt(10));
            }

            //checking if unique
            nonUnique = false;
            for (User u : this.users) {
                if (uuid.compareTo(u.getUUID()) == 0){
                    nonUnique = true;
                    break;
                }
            }

        } while (nonUnique);

        return uuid;

    }

    public String getNewAcountUUID() {
        String uuid;
        Random rand = new Random();
        int length = 10;
        boolean nonUnique = false;

        do {
            uuid = "";
            for (int c = 0; c < length; c++) {
                uuid += ((int)rand.nextInt(10));
            }

            //checking if unique
            nonUnique = false;
            for (Account a : this.accounts) {
                if (uuid.compareTo(a.getUUID()) == 0){
                    nonUnique = true;
                    break;
                }
            }

        } while (nonUnique);

        return uuid;

    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }
    
    public User addUser(String first_name, String last_name, String pin) {
        User user = new User(first_name, last_name, pin, this);
        this.users.add(user);

        //creating account
        Account account = new Account("Savings", user, this);
        
        user.addAccount(account);
        this.addAccount(account);
        this.accounts.add(account);

        return user;
    }

    public User userLogin(String userID, String pin) {
        for (User u : this.users) {
            
            if(u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
                //pins match - login successful
                return u;
            } 
        }

        return null;
    }

    public String getName() {
        return this.name;
    }

}