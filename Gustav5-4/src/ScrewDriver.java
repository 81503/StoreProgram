/*
					Connor Rajotte, Gustav Didier
							  April 2019
Screw Driver CRGD

Loading ExistentialMichaelStevens.txt...
Loaded successfully!

Hey VSauce, Michael here. But... what is a Michael? Is it a person? A name? What is a name then? It can encompass... so many things!
Like your name can be the sound produced when someone says it, your identification with other human beings, the physical print of its letter on a physical object...
but then a name must be multiple things. I can't be one thing... because it's a group of things. But a single thing can be multiple things. Your body, for instance...
is a single entity. Yes, it may be made of so many trillions of trillions of quadrillions of little tiny molecu-

Closing ExistentialMichaelStevens.txt...
Closed successfully!

Loading ScrewDriver.java...
Loaded successufully!

This program is suppose to simulate a store that sells gases and vapors. It has gone through a lot of abuse and trauma, but it has survived these challenges.
 */

import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ScrewDriver
{
	// HA
	// UPDATING
	// THE
	// STORE
	// UI
	// FOR
	// THE
	// NEW
	// PRODUCT
	// WAS
	// NEVER
	// IN
	// THE
	// DIRECTIONS
	// >:D
	// IMMA
	// DO
	// THAT
	// LATER
	// OKAY
	// SORRY
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

			txtProducts = new java.io.File ( listFileName );
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
					totalProfit,
					listFileName
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
	float totalProfit,
	String listFileName
	) throws IOException
	{
		String testWriteFile = "ProductListInputHistory.txt";
		FileWriter ofStream = new FileWriter (testWriteFile, true);
		String newProductName = null;
		float newProductPrice = 0;
		int newProductQuantity = 0;
		int newProductRating = 0;
		float newProductTemp = 0;
		
		
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



		int adminChoice = -1;
		float fundIncrement = 0;
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
			userIn.nextLine();
			System.out.print (adminRow + "| Please enter the name of the additional product.\n|--> ");
			//System.out.println (newProductDesc);
			newProductName = userIn.nextLine();
			//System.out.println (newProductDesc);
			ofStream.write("\n" + newProductName);
			
			System.out.print ("|\n| Please enter the price per kilogram for " + newProductName + ".\n|--> ");
			//System.out.println (newProductPrice);
			newProductPrice = userIn.nextFloat();
			//System.out.println (newProductPrice);
			ofStream.write("\n" + newProductPrice);
			
			System.out.print ("|\n| Please enter the number of kilograms of " + newProductName + " being added.\n|--> ");
			//System.out.println (newProductQuantity);
			newProductQuantity = userIn.nextInt();
			//System.out.println (newProductQuantity);
			ofStream.write("\n" + newProductQuantity);
			
			System.out.print ("|\n| Please enter the hazard rating for " + newProductName + ".\n|--> ");
			//System.out.println (newProductRating);
			newProductRating = userIn.nextInt();
			//System.out.println (newProductRating);
			ofStream.write("\n" + newProductRating);
			
			System.out.print ("|\n| Please enter the storing temperature for " + newProductName + " in Celsius.\n|--> ");
			//System.out.println (newProductTemp);
			newProductTemp = userIn.nextFloat();
			//System.out.println (newProductTemp);
			ofStream.write("\n" + newProductTemp);

			ofStream.close();
			
			ofStream = new FileWriter (listFileName, true);
			ofStream.write("\n" + newProductName);
			ofStream.write("\n" + newProductPrice);
			ofStream.write("\n" + newProductQuantity);
			ofStream.write("\n" + newProductRating);
			ofStream.write("\n" + newProductTemp);
			ofStream.close();
			
			System.out.print ("|\n| " + newProductName + " has been added to the store's inventory!\n"
					+ "| If you would like to remove this item and its details, please locate " + listFileName + ".\n"
					+ "| Remove the information manually until a removal feature is introduced.\n\n");
		}
		else if (adminChoice == 1)
		{
			//TODO : CREATE "SET PRICE" FEATURE
		}
		else if (adminChoice == 2)
		{
			System.out.printf (adminRow + "| The store's current funding is: $%,.2f. How much would you like to deposit/withdraw?"
				+ "\n| [positive=deposit/negative=withdraw]\n", storeFunds);
			while (fundIncrement == 0)
			{	
				System.out.print("|--> ");
				fundIncrement = userIn.nextFloat();
				storeFunds += fundIncrement;
				if (fundIncrement < 0)
				{
					System.out.printf("|\n| %,.2f was withdrawn from the Noble Gases Co. funds.\n", fundIncrement);
				}
				if (fundIncrement > 0)
				{
					System.out.printf("|\n| %,.2f was deposited into the Noble Gases Co. funds.\n", fundIncrement);
				}
				else if (fundIncrement == 0)
				{
					System.out.print("|\n| Please enter a non-zero amount to withdraw/deposit.\n");
				}
			}
			fundIncrement = 0;
		}
		else if (adminChoice == 3)
		{
			//TODO : CREATE "CHANGE PASS" FEATURE
		}
		else if (adminChoice == 4)
		{
			System.out.println ("Exiting administrator mode...\n");
		}
	}
}
