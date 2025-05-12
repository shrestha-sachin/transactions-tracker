package Assignment3;


import java.util.Scanner; 
import java.util.ArrayList; 
public class Portfolio { 
	private Scanner input = new Scanner(System.in); 
	// total profit or loss earned on the shares of all stocks being         
	// monitored by a DI. 
	private double profit; 
	// total cash balance of all bank accounts owned by a DI 
	private double totalCash; 
	// number of stocks currently being monitored by a DI 
	private int numStocks; 
	// number of cash accounts currently being monitored by a DI 
	private int numCashs; 
	// maximum number of stocks being monitored by a DI 
	private final int MAX_STOCKS = 6; 
	// maximum number of cash accounts being monitored by a DI 
	private final int MAX_CASHS = 6; 
	// The list of Assets that holds all stocks and cash accounts that are 
	// currently being monitored by a DI 
	private ArrayList <Asset> List = new ArrayList <Asset>(); 
	//------------------------------------------------------------------- 
	// This is a default constructor for this  
	// Portfolio.   
	// params: (none) 
	//----------------------------------------------------------------- 
	Portfolio() 
	{ 
		profit = 0.0;
        totalCash = 0.0;
        numStocks = 0;
        numCashs = 0;
	} 
	//------------------------------------------------------------------- 
	// This method reads commands from a DI. The method use a switch             
	// statement to process the inputted commands and calls the appropriate 
	// method.  
	// params: (none) 
	//----------------------------------------------------------------- 
	public void ProcessCommands () 
	{ 
		System.out.println("Enter command to complete Transaction:");
		while (true) {
            String command = input.next();

            if (command.equals("S")) {
                String name = input.next();
                double price = input.nextDouble();
                int shares = input.nextInt();
                ProcessStock(new Stock(name, price, shares));

            } else if (command.equals("C")) {
                String bank = input.next();
                double amount = input.nextDouble();
                ProcessCash(new Cash(bank, amount));

            } else if (command.equals("D")) {
                DisplayPortfolio();

            } else if (command.equals("V")) {
                DisplayPortfolioValue();

            } else if (command.equals("Q")) {
                break;

            } else {
                // Read rest of line to ignore bad input
                input.nextLine();
                System.out.println("Bad command.");
            }
        }
	} 
	//------------------------------------------------------------------- 
	// Here is a good method to have. It can do a bunch of heavy lifting for 
	// the S command. It will search for the argument AnotherStock inputted 
	// with the S command. If the AnotherStock isn’t in the list and the DI 
	// doesn’t reach the maximum allowable number of stocks, the method adds 
	// AnotherStock into the list and displays a successful transaction         
	// message. If AnotherStock isn’t in the list and the DI does reach the 
	// maximum allowable number of stocks, the method displays an unsuccessful 
	// transaction message. 
	// Otherwise, it will make use of the Transaction method in the class     
	// Stock. It will also update the total profit of this portfolio and       
	// display a successful transaction message. Be sure to charge the $5 for 
	// each successful Transaction. Besides that, if the number of shares      
	// owned or sold for a particular stock in the list becomes zero, the     
	// method removes it from the list of assets. 
	// params: (Stock) 
	//----------------------------------------------------------------- 
	public void ProcessStock (Stock AnotherStock) 
	{ 
		for (int i = 0; i < List.size(); i++) {
            if (List.get(i) instanceof Stock && List.get(i).getName().equals(AnotherStock.getName())) {
                Stock existing = (Stock) List.get(i);
                double transactionProfit = existing.Transaction(AnotherStock);
                profit += transactionProfit - 5.0;
                if (existing.getMarketValue() == 0) {
                    List.remove(i);
                    numStocks--;
                }
                System.out.println("Transaction completed.");
                return;
            }
        }

        if (numStocks < MAX_STOCKS) {
            List.add(AnotherStock);
            profit -= 5.0;
            numStocks++;
            System.out.println("Transaction completed.");
        } else {
            System.out.println("Transaction NOT completed. Maximum number of stocks being monitored.");
        }
            
	}    
	//------------------------------------------------------------------- 
	// Here is another good method to have. It can do a bunch of heavy lifting 
	// for the C command. It will search for the argument AnotherCash inputted 
	// with the C command. If the AnotherCash isn’t in the list and the DI    
	// doesn’t reach the maximum allowable number of cash accounts, the method 
	// adds AnotherCash into the list and displays a successful transaction           
	// message. If AnotherCash isn’t in the list and the DI does reach the    
	// maximum allowable number of Cash accounts, the method displays an       
	// unsuccessful transaction message. 
	// Otherwise, it will make use of the Transaction method in the class     
	// Cash. It will also update the total cash balance owned by a DI and       
	// display a successful transaction message. Besides that, if the balance 
	// for a particular cash account in the list becomes zero, the method      
	// removes it from the list of assets. 
	// params: (Cash) 
	//----------------------------------------------------------------- 
	public void ProcessCash(Cash AnotherCash) 
	{ 
		for (int i = 0; i < List.size(); i++) {
            if (List.get(i) instanceof Cash && List.get(i).getName().equals(AnotherCash.getName())) {
                Cash existing = (Cash) List.get(i);
                double prev = existing.getMarketValue();
                double updated = existing.Transaction(AnotherCash);
                totalCash += (updated - prev);
                if (existing.getMarketValue() == 0) {
                    List.remove(i);
                    numCashs--;
                }
                return;
            }
        }

        if (AnotherCash.getMarketValue() >= 0 && numCashs < MAX_CASHS) {
            List.add(AnotherCash);
            totalCash += AnotherCash.getMarketValue();
            numCashs++;
            System.out.println("Transaction completed.");
        } else if (AnotherCash.getMarketValue() < 0) {
            System.out.println("Transaction NOT completed. Withdrawal amount exceeds available cash being monitored.");
        } else {
            System.out.println("Transaction NOT completed. Maximum number of cashes being monitored.");
        }
    }

    public void DisplayPortfolio() {
        for (Asset asset : List) {
            System.out.println(asset);
        }
    }

    public void DisplayPortfolioValue() {
        System.out.printf("Profit so far: $ %.2f, Cash so far: $ %.2f%n", profit, totalCash);
    }
} 

