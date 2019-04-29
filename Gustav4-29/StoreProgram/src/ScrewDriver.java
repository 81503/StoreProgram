
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
	public static final int PROD_NUMS_INIT = 5;
	public static final int PROD_SLOTS = 10;
	public static java.io.File txtProducts;
	public static Scanner inputFile;
	public static Scanner userIn = new Scanner ( System.in );

	public static void main( String[ ] args ) throws FileNotFoundException, IOException
	{
		//System.out.println("�");
		Product[] productArray = new Product[PROD_SLOTS];
		String listFileName = "StoreProducts.txt";

		fileCheck ( listFileName );
		String productName[] = new String [PROD_SLOTS];
		int productStock[] = new int [PROD_SLOTS];
		float productPrice[] = new float [PROD_SLOTS];
		String productEffects[] = new String [PROD_SLOTS];
		String productFlammability[] = new String [PROD_SLOTS];
		
		Product p = new Product ( );
		int prodNums = PROD_NUMS_INIT;
		int prodSelected = -1;
		int i = 0;

		while ( inputFile.hasNext ( ))
		{
			p.setProductName ( inputFile.nextLine ( ) );
			productName[i] = p.getProductName();
			p.setPricePerGram ( Integer.parseInt ( inputFile.nextLine ( ) ) );
			productPrice[i] = p.getPricePerGram ( );
			p.setQuantity ( Integer.parseInt ( inputFile.nextLine ( ) ) );
			productStock[i] = p.getQuantity();
			p.setEffects ( inputFile.nextLine ( ) );
			productEffects[i] = p.getEffects ( );
			p.setFlammable ( inputFile.nextLine ( ) );
			productFlammability[i] = p.getFlammable();
			productArray[i] = p;
			i++;
		}

		printStore(prodNums, productName, productPrice);
		//System.out.print ( "\n\nIf you would like to add more items to our stock,"
		//		+ "\nplease enter a name, and further details will be requested.\n" );
		
		String productNamePlacer = userIn.nextLine ( );
		for (int j = 0; j < (prodNums - 1); j++)
		{
			//System.out.println(productNamePlacer);
			if (productName[j].equals( productNamePlacer))
			{
				prodSelected = j;
			}
			else
			{
				prodSelected = -1;
			}
		}
		
		if (prodSelected == -1)
		{
			prodNums += 1;
			productName[prodNums] = productNamePlacer;
			System.out.println ( "The new product is " + productName );
	
			System.out.println ( "How many will be in stock?" );
			productStock[prodNums] = userIn.nextInt ( );
			System.out.println ( "The amount in stock is " + productStock );
	
			System.out.println ( "\nHow much will it cost?" );
			productPrice[prodNums] = userIn.nextInt ( );
			System.out.println ( "It will cost " + productPrice );
	
			System.out.println ( "\nWhat are the effects?" );
			userIn.nextLine ( );
			productEffects[prodNums] = userIn.nextLine ( );
			System.out.println ( "The effects is/are " + productEffects );
	
			System.out.println ( "\nIs it flammable?" );
			productFlammability[prodNums] = userIn.nextLine ( );
			
			while ( productFlammability.equals ( "no" ) || productFlammability.equals ( "yes" ) )
			{
				System.out.println ( "Are you sure?" );
				productFlammability[prodNums] = userIn.nextLine ( );
			}
			if ( !productFlammability.equals ( "yes" ) || !productFlammability.equals ( "no" ) )
			{
				System.out.println ( "The product is probably explodable\n" );
				productFlammability[prodNums] = "Explodable";
			}
			System.out.println ( "product name: " + productName );
			System.out.println ( "product in stock: " + productStock );
			System.out.println ( "product price: " + productPrice );
			System.out.println ( "product effects: " + productEffects );
			System.out.println ( "product flammability: " + productFlammability );
			// addItems(productNamesArray, kgPricesArray, productStocksArray, descriptionsArray, numOfProducts);
		}
		else
		{
			System.out.println("This product is already currently available on our shelves.\n"
					+ "Adding to existing stock. How many kilograms are you adding?");
			productStock[prodSelected] += userIn.nextInt ( );
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
	
	
	
	

	public static void itemCheck( )
	{

	}

	
	
	
	
	public static void addItems // -->
	( String productNamesArray[], float kgPricesArray[], int productStocksArray[], String descriptionsArray[],
			int numOfProducts )
	{
		productNamesArray[numOfProducts] = userIn.nextLine ( );
		System.out.println ( "What is the price (in USD) of " + productNamesArray[numOfProducts] + " per kilogram?" );
		kgPricesArray[numOfProducts] = userIn.nextFloat ( );
		System.out.println ( "How many kg of " + productNamesArray[numOfProducts] + " are you adding?" );
		productStocksArray[numOfProducts] = userIn.nextInt ( );
		System.out.println ( "What is your desired description?" );
		descriptionsArray[numOfProducts] = userIn.nextLine ( );

		// TODO : fix it skipping the line below
		System.out.println ( descriptionsArray[numOfProducts] );
		numOfProducts++;
	}
	
	
	
	
	
	public static void printStore (int prodNums, String productName[], float productPrice[])
	{
		String mainRow = "+-----------------------------------------------------------------------------+";
		String mainRow2 = "+-------------------------------+----------+-------+--------+-----------------+";
		String emptyRow = "|                                                                             |";
		
		System.out.println(mainRow + "\n" + emptyRow +
				"\n|                         Third State O' Matter Inc.                          |\n" +
				emptyRow + "\n" + mainRow2 + 
				"\n|           Items for           |  Price   | Stock | Hazard |     Storing     |" +
				"\n|             Sale              | (per kg) | (kg)  | Rating |   Temperature   |\n" +
				mainRow2 + "\n" + emptyRow);
		for(int i = 0; i < prodNums; i++)
		{
		System.out.printf ("| %d" + ". %-26s | $%-7.2f |\n", i, productName[i], productPrice[i]);
		}
	}
	

}
