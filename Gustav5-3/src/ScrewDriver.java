
/*
					Connor Rajotte, Gustav Didier
							  April 2019
Screw Driver CRGD
the purpose of this is to create a list of words to read into the file
 */

import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ScrewDriver
{
	public static final int PROD_NUMS_INIT = 4;
	public static final int PROD_SLOTS = 10;
	public static java.io.File txtProducts;
	public static Scanner inputFile;
	public static Scanner userIn = new Scanner ( System.in );

	public static void main( String[ ] args ) throws FileNotFoundException, IOException
	{
		// System.out.println("°");
		Product[ ] productArray = new Product[PROD_SLOTS];
		String listFileName = "StoreProducts.txt";

		fileCheck ( listFileName );

		int prodNums = PROD_NUMS_INIT;
		int prodSelected = -1;
		int i = 0;
		int numOfBuyers = 0;
		int numOfSales = 0;
		int missedSales = 0;
		int numOutOfStock = 0;
		int unitsSold = 0;
		
		float storeFunds = 24852.83f;
		float total = 0;
		float totalProfit = 0;
		
		boolean repeatBuyer = false;
		
		String shutDown = "no";
		String mainRow = "+-------------------------------------------------------------------------------+\n";
		String mainRow2 = "+-------------------------------+----------+-------+--------+-------------------+\n";
		String emptyRow = "|                                                                               |\n";
		String emptyRow2 = "|                               |          |       |        |                   |\n";

		while ( ! (shutDown.equals ( "yes" ) ) )
		{
			
			System.out.println ( "Welcome, </customerName>! Enter the number of people buying today!" );
			int familyNum = userIn.nextInt ( );

			while ( inputFile.hasNext ( ) )
			{
				Product p = new Product ( );
				p.setProductName ( inputFile.nextLine ( ) );
				p.setPricePerKGram ( Float.parseFloat ( inputFile.nextLine ( ) ) );
				p.setQuantity ( Integer.parseInt ( inputFile.nextLine ( ) ) );
				p.setRating ( Integer.parseInt ( inputFile.nextLine ( ) ) );
				p.setTemp ( Float.parseFloat ( inputFile.nextLine ( ) ) );
				productArray[i] = p;
				i++;
			}

			if ( familyNum == -1 )
			{
				String adminPassword = "p";
				String personIn = "hi";
				System.out.println ( "Hello. Please enter the administrator password. c:" );
				personIn = userIn.nextLine ( );
				personIn = userIn.nextLine ( );

				if ( personIn.equals ( adminPassword ) )
				{
					adminUIPrint
					(
					prodNums,
					productArray,
					numOfBuyers,
					numOfSales,
					missedSales,
					numOutOfStock,
					unitsSold,
					storeFunds,
					totalProfit
					);
				}
				else
				{
					System.out.println ( "Incorrect Password, if you are not the admin then go buy something.\n");
				}
				
			}
			else
			{
				numOfBuyers += familyNum;
				numOfSales++;
				storeUIPrint ( mainRow, mainRow2, emptyRow, emptyRow2, prodNums, productArray );
				
				System.out.print ( "\n\n" + mainRow
						+ "|           Choose the item number of the product you wish to purchase          |\n" + mainRow
						+ "|");
				
				for ( i = 0; i < familyNum; i++ )
				{
					System.out.print ("|\n| Family Member " + (i + 1) + ":\n|--> " );
	
					int buy = userIn.nextInt ( );
	
					if ( productArray[buy].getQuantity ( ) <= 0 )
					{
						System.out.println ( mainRow + "\n| That item is out of stock" );
						System.out.print( "| Please choose another option");
						i --;
						
						if (repeatBuyer == false)
							missedSales++;
	
						repeatBuyer = true;
						//System.out.println( missedSales);
						
					}
					else
					{
						productArray[buy].setQuantity ( productArray[buy].getQuantity ( ) - 1 );
						numOfSales += 1;
						System.out.println ( "| You chose " + productArray[buy].getProductName() );
						System.out.println ( "| Your price is: $" + productArray[buy].getPricePerKGram ( ) );
						total += productArray[buy].getPricePerKGram ( );
						numOfSales++;
						repeatBuyer = false;
					}
				}
				System.out.printf ( "| Your total is $%3.2f\n"
						+ "| We have automatically withdrawn this money from the hivemind.\n\n", total );
				totalProfit += total;
				total = 0;
			}
		}
	}

	/**
	 ** fileCheck : checks to see that the txt file that lists products exists
	 ** 
	 ** @param listFileName
	 ** @throws FileNotFoundException
	 **/
	public static void fileCheck( String listFileName ) throws FileNotFoundException, IOException
	{
		System.out.print ( "Checking for list of products...\n" );
		txtProducts = new java.io.File ( listFileName );
		inputFile = new Scanner ( txtProducts );
		if ( !txtProducts.exists ( ) )
		{
			System.out.print ( "List not found, please check that " + listFileName + " exists in the project directory." );
			System.exit ( -1 );
		}
		else
		{
			System.out.print ( "List found!\n\n" );
		}
	}

	
	
	
	
	/**
	 ** printStore : prints the general store UI for customers
	 ** 
	 ** @param mainRow
	 ** @param mainRow2
	 ** @param emptyRow
	 ** @param emptyRow2
	 ** @param prodNums
	 ** @param prodA
	 **/
	public static void storeUIPrint
	(
	String mainRow,
	String mainRow2,
	String emptyRow,
	String emptyRow2,
	int prodNums,
	Product[] prodA
	)
	{
		float productTempF[] = new float[prodNums];

		for ( int i = 0; i < prodNums; i++ )
		{
			productTempF[i] = (float) ( prodA[i].getTemp ( ) * 1.8 ) + 32;
		}

		System.out.print( mainRow + emptyRow
				+ "|                                Noble Gases Co.                                |\n" + emptyRow
				+ mainRow2 + "|           Items for           |  Price   | Stock | Hazard |      Storing      |\n"
				+ "|             Sale              | (per kg) | (kg)  | Rating |    Temperature    |\n" + mainRow2
				+ emptyRow2 );
		for ( int i = 0; i < prodNums; i++ )
		{
			System.out.printf ( "| %d" + ". %-26s | $%-7.2f | %-5d | %02d/10  | %06.1f°C/%06.1f°F |\n" + emptyRow2,
					i,
					prodA[i].getProductName(),
					prodA[i].getPricePerKGram(),
					prodA[i].getQuantity(),
					prodA[i].getRating(),
					prodA[i].getTemp(),
					productTempF[i] );
		}
		System.out.print ( mainRow );
	}
	
	
	
	
	
	public static void adminUIPrint
	(
	int prodNums,
	Product[] prodA,
	int numOfBuyers,
	int numOfSales,
	int missedSales,
	int numOutOfStock,
	int unitsSold,
	float storeFunds,
	float totalProfit
	)
	{
		String adminRow = "+--------------------------------------------------+\n";
		String adminRow2 = "+-------------------------------+----------+-------+\n";
		String adminRow3 = "+---------------------------------------+----------+\n";
		String adminRow4 = "||||||||||||||||||||||||||||||||||||||||||||||||||||\n";
		String adminRowEmpty = "|                                                  |\n";
		String adminRowEmpty2 = "|                               |          |       |\n";
		String adminRowEmpty3 = "|                                       |          |\n";
		
		System.out.print(adminRow + adminRowEmpty
				+ "|    A D M I N I S T R A T O R      R E P O R T    |\n"
				+ adminRowEmpty + adminRow2
				+ "|             Store             |  Price   | Stock |\n"
				+ "|             Items             | (per kg) | (kg)  |\n"
				+ adminRow2 + adminRowEmpty2);
		for (int i = 0; i < prodNums; i++)
		{
			System.out.printf("| %-29s | $%-7.2f | %-5d |\n" + adminRowEmpty2,
					prodA[i].getProductName(),
					prodA[i].getPricePerKGram(),
					prodA[i].getQuantity() );
		}
		System.out.printf(adminRow2 + adminRow4 + adminRow4 + adminRow3 + adminRowEmpty3
				+ "| Number of Customers Today             | %08d |\n" + adminRowEmpty3
				+ "| Number of Sales Made                  | %08d |\n" + adminRowEmpty3
				+ "| Sales Missed from Lack of Stock       | %08d |\n" + adminRowEmpty3
				+ "| Products Currently Out of Stock       | %08d |\n" + adminRowEmpty3
				+ "| Total Item Units Sold                 | %08d |\n" + adminRowEmpty3
				+ "| Total Profit (USD)                    | %08.2f |\n" + adminRowEmpty3 + adminRow3,
				numOfBuyers,
				numOfSales,
				missedSales,
				numOutOfStock,
				unitsSold,
				totalProfit);
		adminInputUI(storeFunds);
	}
	
	
	
	
	
	public static void adminInputUI (float storeFunds)
	{
		int adminChoice = -1;
		System.out.print("|\n| What would you like to do?\n"
				+ "| 0. Buy more inventory\n"
				+ "| 1. Set a product price\n"
				+ "| 2. Deposit/withdraw store funds\n"
				+ "| 3. Change the administrator password\n"
				+ "| 4. Exit administrator UI\n"
				+ "|\n|--> ");
		adminChoice = userIn.nextInt ();
		
		if (adminChoice == 0)
		{
			//TODO : CREATE "ADD INVENTORY" FEATURE
		}
		
		else if (adminChoice == 1)
		{
			//TODO : CREATE "SET PRICE" FEATURE
		}
		
		else if (adminChoice == 2)
		{
			System.out.println();
		}
		
		else if (adminChoice == 3)
		{
			//TODO : CREATE "CHANGE PASS" FEATURE
		}
		
		else if (adminChoice == 4)
		{
			System.out.println ("Exiting administrator mode...");
		}
		
	}
}
