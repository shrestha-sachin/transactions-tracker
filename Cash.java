package Assignment3;

public class Cash extends Asset {
	// The Bank where the DI holds this cash account 
	private String Bank_Name;   
	private double Balance_Amount; // Amount of money in this cash account  
	//------------------------------------------------------------------- 
	// This is a non-default constructor for this  
	// Cash account.  It assumes all arguments are legal.  
	// params: (String, double) 
	//----------------------------------------------------------------- 
	Cash (String bankName, double Amount) 
	{ 
		this.Bank_Name = bankName;
		this.Balance_Amount = Amount;
	} 
	//------------------------------------------------------------------- 
	// This method returns the current market value of this Cash.   
	// Cash's market value is always worth exactly its own amount. 
	// params: (none) 
	//----------------------------------------------------------------- 
	public double getMarketValue()  
	{ 
		return Balance_Amount;
	} 
	//------------------------------------------------------------------- 
	// This method returns the string representation of this Cash account   
	// params: (none) 
	//----------------------------------------------------------------- 
	public String toString() 
	{ 
		return String.format("%15s%24.2f", Bank_Name, Balance_Amount); 
	} 
	
	//------------------------------------------------------------------- 
	// This method returns the name of the bank of this Cash account    
	// params: (none) 
	//----------------------------------------------------------------- 
	public String getName( ) 
	{ 
		return Bank_Name;
	} 
	//-------------------------------------------------------------------           
	// This method updates the balance amount of this Cash account with the 
	// data from AnotherCash that inputted along with the C command, and          
	// displays a notification message.  
	// If the balance amount of AnotherCash has the negative sign, then the              
	// transaction is a withdrawal process, otherwise the transaction is a     
	  // depositing one.  
	// In the case of deposit, the method deposits the balance amount of       
	// AnotherCash into this Cash account, and then prints a successful         
	//  message.  
	// In case of withdraw, the method withdraws the balance amount of            
	// AnotherCash from this Cash account. If current balance of this Cash     
	// account does not have enough money, the method does nothing to this    
	// Cash account but displays a notification message (see the sample         
	// output). 
	// Please consider Example2 to gain more ideas about how to implement                         
	// this method. 
	// params: (Cash) 
	//----------------------------------------------------------------- 
	public double Transaction(Cash AnotherCash) {
        if (AnotherCash.Balance_Amount >= 0) {
            Balance_Amount += AnotherCash.Balance_Amount;
            System.out.println("Transaction completed.");
        } else {
            if (Balance_Amount + AnotherCash.Balance_Amount >= 0) {
                Balance_Amount += AnotherCash.Balance_Amount;
                System.out.println("Transaction completed.");
            } else {
                System.out.println("Transaction NOT completed. Withdrawal amount exceeds available cash being monitored.");
            }
        }
        return Balance_Amount;
    }

}
