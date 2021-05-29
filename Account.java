package lab2;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Lab2 {
      
    static public class Account{	
	private String n;
	private int id;
	private double b;
	private static double air;
	private Date dc;
	private ArrayList<Transaction> t;

	
	Account() {
		n = "";
		id = 0;
		b = 0;
		air = 0;
		dc = new Date(); 
		t = new ArrayList<Transaction>();
	}

	
	Account(int id, double b) {
		n = "";
		this.id = id;
		this.b = b;
		dc = new Date();
		t = new ArrayList<Transaction>();
	}

	
	Account(String name, int id, double b) {
		this(id, b);
		this.n = n;
	}

	
	public void setName(String n) {
		this.n = n;
	}

	public String getName() {
		return n;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setBalance(double b) {
		this.b = b;
	}

	public void setAnnualInterestRate(double air) {
		this.air = air;
	}

	
	public int getId() {
		return id;
	}


	public double getBalance() {
		return b;
	}

	public double getAnnualInterestRate() {
		return air;
	}

	public String getDateCreated() {
		return dc.toString();
	}

	public double getMonthlyInterestRate() {
		return air / 12;
	}

	public double getMonthlyInterest() {
		return b * (getMonthlyInterestRate() / 100);
	}

	public void withdraw(double amount) {
		b -= amount;
		t.add(new Transaction('W', amount, b, 
			"Withdrawal from account"));
	}

        
	public void deposit(double amount) {
		b += amount;
		t.add(new Transaction('D', amount, b, 
			"Deposit to account"));
	}

	public ArrayList<Transaction> getTransactions() {
		return t;
	}
        @Override
        public String toString() {
		return "\nAccount ID: " + id + "\nDate created: " + getDateCreated()
			+ "\nBalance: $" + String.format("%.2f", b) + 
			"\nMonthly interest: $" + String.format("%.2f", getMonthlyInterest());
	}
    }
        
         static class Transaction {
    
    
    private java.util.Date date;
	private char type;
	private double amount;
	private double b;
	private String desc;
        
	public Transaction(char type, double amount, 
		double b, String desc) 
        {
		date = new java.util.Date();
		this.type = type;
		this.amount = amount;
		this.b = b;
		this.desc = desc;
	}

	
	public String getDate() {
		return date.toString();
	}
	
        
	public void setType(char type) {
		this.type = type;
	}

	
	public char getType() {
		return type;
	}

	
	public void setAmount(double amount) {
		this.amount = amount;
	}

	
	public double getAmount() {
		return amount;
	}

	
	public void setBalance(double b) {
		this.b = b;
	}

	
	public double getBalance() {
		return b;
	}

	
	public void setDescription(String desc) {
		this.desc = desc;
	}

	
	public String getDescription() {
		return desc;
	}
    
}
        
 
 static public class CheckingAccount
		extends Account {

	private double odl;

	
	public CheckingAccount() {
		super();
		odl = -20;
	}


	public CheckingAccount(int id, double b, double odl) {
		super(id, b);
		this.odl = odl;
	}

	
	public void setOverdraftLimit(double odl) {
		this.odl = odl;
	}

	
	public double getOverdraftLimit() {
		return odl;
	}


	public void withdraw(double amount) {
		if (getBalance() - amount > odl) {
			setBalance(getBalance() - amount);
		}
		else
			System.out.println("Error! Amount exceeds overdraft limit.");
	}

	
	public String toString() {
		return super.toString() + "\nOverdraft limit: $" + 
		String.format("%.2f", odl);
	}
}
 static public class SavingsAccount
		extends Account {
	
	
	public SavingsAccount() {
		super();
	}

	
	public SavingsAccount(int id, double b) {
		super(id, b);
	}

	
	public void withdraw(double amount) {
		if (amount < getBalance()) {
			setBalance(getBalance() - amount);
		}
		else
			System.out.println(
				"Error! Savings account overdrawn transtaction rejected");
	}
        
       
}public static void main(String[] args) 
	{
		Account[] account = new Account[10];
		Scanner s = new Scanner(System.in);
		
                
		for (int i = 0; i < account.length; i++)
			account[i] = new Account(101 , 500);
               
		
		while (true)
		{
                    System.out.print("Enter your Password: ");
			int id = s.nextInt();
                    int maxAttempts = 0;
                        
			while ((id < 0 || id > 9) && maxAttempts < 2)
			{
                            
                            
				System.out.print("Pass is incorrect, try again: ");
				id = s.nextInt();
                                 maxAttempts++;
			}
			
			while (true)
			{
				displayMenu();
				
				if (!Menu(account[id], s))
				{
					System.out.println();
					break;
				}
				
				System.out.println("\n");
			}
                    
		}
	}
	
	public static boolean Menu(Account account, Scanner s)
	{
		int option = s.nextInt();
		
		if (option == 1)
		{
			System.out.print("The balance is " + account.getBalance());
			return true;
		}
		else if (option == 2)
		{
			System.out.print("Enter an amount to withdraw: ");
			account.withdraw(s.nextDouble());
			return true;
		}
		else if (option == 3)
		{
			System.out.print("Enter an amount to deposit: ");
			account.deposit(s.nextDouble());
			return true;
		}
                
                else if (option == 4){
                 
                    SavingsAccount savings = new SavingsAccount(101,2000);
                  System.out.print("Saving Account Balance"+ savings.getBalance());  
                  
                 System.out.print("\n Set the annual interest Rate ");  
                 savings.setAnnualInterestRate(s.nextDouble());
                 
                  System.out.print("\n Enter an amount to withdraw  ");  
                 savings.withdraw(s.nextDouble());
                 
                  System.out.print("\n Enter an amount to deposit  ");  
                 savings.deposit(s.nextDouble());
                          
                 
                 System.out.println(savings.toString());
                 return true;
                 
                }
                
                else if (option == 5 )
                {
                
                CheckingAccount checking = new CheckingAccount(101, 2000, -20);
                 System.out.print("Checking Account  Balance"+ checking.getBalance()); 
                 
                 
                System.out.print("Set the annual interest Rate "); 
                checking.setAnnualInterestRate(s.nextDouble());
                
                 System.out.print("Enter an amount to withdraw  "); 
                checking.withdraw(s.nextDouble());
                
                System.out.print("Enter an amount to deposit  ");  
                 checking.deposit(s.nextDouble());
                 
                 
                 System.out.println(checking.toString());
                 return true;
                }
                
                
                
                else if (option == 6)
                {
                         System.out.println("\n     List of transactions");
                          System.out.println("\n");
		
                for (int i = 0; i < account.getTransactions().size(); i++) {
                    
                    
			System.out.println("Date: " + 
				(account.getTransactions()).get(i).getDate());
                        
                        
			System.out.println("Type: " + 
				(account.getTransactions()).get(i).getType());
                        
                        
			System.out.println("Amount: " + 
				(account.getTransactions()).get(i).getAmount());
                        
                        
			System.out.println("Balance: " + 
				(account.getTransactions()).get(i).getBalance());
                        
                        
			System.out.println("Description: " + 
				(account.getTransactions()).get(i).getDescription());
                        
                        
			System.out.println();
		}
                return true;
                
                
                }
                else if (option == 7)
                {
                    return false;
                }
		
		else
			System.out.print("Invalid option.");
		
		return true;
	}
	
	public static void displayMenu()
	{
		System.out.print("Main menu "
				+ "\n1: check balance"
				+ "\n2: withdraw"
				+ "\n3: deposit"
                                + "\n4: Saving Account "
                                + "\n5: Checking Account"
                                + "\n6: Transaction"
				+ "\n7: exit"
                                
				+ "\nEnter a choice: ");
	}
}