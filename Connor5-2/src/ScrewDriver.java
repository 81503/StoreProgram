
/*
					Connor Rajotte, Gustav Didier
							  April 2019
Screw Driver CRGD
the purpose of this is to create a list of words to read into the file
 */

import java.util.Scanner;
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
		float total = 0;
		int productSales = 0;
		int missedSales = 0;
		String shutDown = "no";
		boolean repeatMem = false;

		while ( ! (shutDown.equals ( "yes" ) ) )
		{
			String mainRow = "+-------------------------------------------------------------------------------+";
			String mainRow2 = "+-------------------------------+----------+-------+--------+-------------------+";
			String emptyRow = "|                                                                               |";
			String emptyRow2 = "|                               |          |       |        |                   |";

			System.out.println ( "Welcome, </enter customer name here>! Enter the number of people buying today!" );
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
					adminReport ( productSales, missedSales );
				}
				else
				{
					System.out.println ( "Incorrect Password, if you are not the admin then go buy something.\n"
								+ "Enter the number of people buying today" );
					familyNum = userIn.nextInt ( );
				}
				
			}
			
			printStore ( mainRow, mainRow2, emptyRow, emptyRow2, prodNums, productArray );
			
			System.out.print ( "\n\n" + mainRow
					+ "\n|           Choose the item number of the product you wish to purchase          |\n" + mainRow
					+ "\n|");
			
			for ( i = 0; i < familyNum; i++ )
			{
				System.out.print ("|\n| Family Member " + (i + 1) + ":\n|--> " );

				int buy = userIn.nextInt ( );

				if ( productArray[buy].getQuantity ( ) <= 0 )
				{
					System.out.println ( mainRow + "\n| That item is out of stock" );
					System.out.print( "| Please choose another option");
					i --;
					
					if (repeatMem == false)
						missedSales++;

					repeatMem = true;
					//System.out.println( missedSales);
					
				}
				else
				{
					productArray[buy].setQuantity ( productArray[buy].getQuantity ( ) - 1 );
					productSales += 1;
					System.out.println ( "| You chose " + productArray[buy].getProductName() );
					System.out.println ( "| Your price is: $" + productArray[buy].getPricePerKGram ( ) );
					total += productArray[buy].getPricePerKGram ( );
					productSales++;
					repeatMem = false;
				}
			}
			System.out.printf ( "| Your total is $%3.2f\n"
					+ "| We have automatically withdrawn this money from the hivemind.\n\n", total );
			total = 0;
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

	public static void printStore( String mainRow, String mainRow2, String emptyRow, String emptyRow2, int prodNums,
			Product[ ] a )
	{
		float productTempF[] = new float[prodNums];

		for ( int i = 0; i < prodNums; i++ )
		{
			productTempF[i] = (float) ( a[i].getTemp ( ) * 1.8 ) + 32;
		}

		System.out.println ( mainRow + "\n" + emptyRow
				+ "\n|                                Noble Gases Co.                                |\n" + emptyRow + "\n"
				+ mainRow2 + "\n|           Items for           |  Price   | Stock | Hazard |      Storing      |"
				+ "\n|             Sale              | (per kg) | (kg)  | Rating |    Temperature    |\n" + mainRow2 + "\n"
				+ emptyRow2 );
		for ( int i = 0; i < prodNums; i++ )
		{
			System.out.printf ( "| %d" + ". %-26s | $%-7.2f | %-5d | %02d/10  | %06.1f°C/%06.1f°F |\n" + emptyRow2 + "\n",
					i, a[i].getProductName ( ), a[i].getPricePerKGram ( ), a[i].getQuantity ( ), a[i].getRating ( ),
					a[i].getTemp ( ), productTempF[i] );
		}
		System.out.print ( mainRow );
	}

	public static void adminReport ( int productSales, int missedSales )
	{
		System.out.println ( "products sold are " + productSales );
		System.out.println ( "Products we missed from lack of inventory: " + missedSales );
	}
}
