import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String choice = " ";
        System.out.println("Hello User! Welcome to the Covid Hero Chatbot! With this chatbot You can search up a country, state, or town and see the people who have contributed to stoping the spread of Covid-19! Enjoy!");
        System.out.println("Type help to view a list of commands you can perform with the Covid Hero Chatbot: ");
        choice = sc.nextLine();
        if(choice.toLowerCase().equals("help")){
            help();
            System.out.println("What area of the world would you like to search?");
        }else{
            System.out.println("What area of the world would you like to search?");
        }

    }

    public static void help(){
        System.out.println("----------------------------------------"); //40 dashes
        System.out.println("\tEnter the name of a town, then followed by the state to see if there are any covid-heroes in that area");
        System.out.println("\tEnter the name of a continent to see if there are any covid-heroes in that continent");
        System.out.println("\tEnter the name of a state to see if there are any covid-heroes in that state");
        System.out.println("\tType quit to exit the program");
        System.out.println("----------------------------------------"); //40 dashes
    } 
}