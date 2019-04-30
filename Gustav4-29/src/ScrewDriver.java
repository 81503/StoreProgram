
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
		//System.out.println("°");
		Product[] productArray = new Product[PROD_SLOTS];
		String listFileName = "StoreProducts.txt";

		fileCheck ( listFileName );
		String productName[] = new String [PROD_SLOTS];
		int productStock[] = new int [PROD_SLOTS];
		float productPrice[] = new float [PROD_SLOTS];
		int productRating[] = new int [PROD_SLOTS];
		float productTemp[] = new float [PROD_SLOTS];
		
		Product p = new Product ( );
		int prodNums = PROD_NUMS_INIT;
		int prodSelected = -1;
		int i = 0;

		while ( inputFile.hasNext ( ))
		{
			p.setProductName ( inputFile.nextLine ( ) );
			productName[i] = p.getProductName(); //System.out.println(productName[i]);
			p.setPricePerKGram ( Float.parseFloat ( inputFile.nextLine ( ) ) );
			productPrice[i] = p.getPricePerKGram ( ); //System.out.println(productPrice[i]);
			p.setQuantity ( Integer.parseInt ( inputFile.nextLine ( ) ) );
			productStock[i] = p.getQuantity(); //System.out.println(productStock[i]);
			p.setRating ( Integer.parseInt ( inputFile.nextLine ( ) ) );
			productRating[i] = p.getRating ( ); //System.out.println(productRating[i]);
			p.setTemp ( Float.parseFloat ( inputFile.nextLine ( ) ) );
			productTemp[i] = p.getTemp(); //System.out.println(productTemp[i]);
			productArray[i] = p;
			//System.out.println ( p.toString ( ) );
			i++;
		}

		printStore(prodNums, productName, productPrice, productStock, productRating, productTemp);
		
		/*
		String productNamePlacer = userIn.nextLine ( )
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
			productRating[prodNums] = userIn.nextInt( );
			System.out.println ( "The effects is/are " + productRating );
	
			System.out.println ( "\nIs it flammable?" );
			productTemp[prodNums] = userIn.nextFloat ( );
			
			System.out.println ( "product name: " + productName );
			System.out.println ( "product in stock: " + productStock );
			System.out.println ( "product price: " + productPrice );
			System.out.println ( "product effects: " + productRating );
			System.out.println ( "product flammability: " + productTemp );
			// addItems(productNamesArray, kgPricesArray, productStocksArray, descriptionsArray, numOfProducts);
		}
		else
		{
			System.out.println("This product is already currently available on our shelves.\n"
					+ "Adding to existing stock. How many kilograms are you adding?");
			productStock[prodSelected] += userIn.nextInt ( );
		}
	*/
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
	
	
	
	
	
	public static void printStore (
			int prodNums,
			String productName[],
			float productPrice[],
			int productStock[],
			int productRating[],
			float productTemp[])
	{
		String mainRow = "+-------------------------------------------------------------------------------+";
		String mainRow2 = "+-------------------------------+----------+-------+--------+-------------------+";
		String emptyRow = "|                                                                               |";
		String emptyRow2 = "|                               |          |       |        |                   |";
		float productTempF[] = new float [prodNums];
		
		for (int i = 0; i < prodNums; i++)
		{
			productTempF[i] = (float)(productTemp[i]*1.8) + 32;
		}
		
		System.out.println(mainRow + "\n" + emptyRow +
				"\n|                                Noble Gases Co.                                |\n" +
				emptyRow + "\n" + mainRow2 + 
				"\n|           Items for           |  Price   | Stock | Hazard |      Storing      |" +
				"\n|             Sale              | (per kg) | (kg)  | Rating |    Temperature    |\n" +
				mainRow2 + "\n" + emptyRow2);
		for(int i = 0; i < prodNums; i++)
		{
		System.out.printf ("| %d" + ". %-26s | $%-7.2f | %-5d | %02d/10  | %06.1f°C/%06.1f°F |\n" + emptyRow2 + "\n",
				i, productName[i], productPrice[i], productStock[i], productRating[i], productTemp[i], productTempF[i]);
		}
		System.out.print(mainRow2 + "\n\n" + mainRow + 
				"\n|             Choose the number of the item you wish to purchase                |\n" +
				mainRow + "\n|\n|--> ");
	}

}
