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
	public static final int PROD_SLOTS = 10;
	public static java.io.File txtProducts;
	public static Scanner inputFile;
	public static Scanner userIn = new Scanner (System.in);
	
	public static void main( String[ ] args ) throws FileNotFoundException, IOException
	{
		Product [] productArray = new Product [10];
		
		String listFileName = "StoreProducts.txt";
		int numOfProducts;
		
		fileCheck(listFileName);
		int i = 0;
		String newProductName;
		int newProductStock;
		int newProductPrice;
		String newProductEffects;
		String newProductFlammability;
		Product product = new Product ( );
		while ( inputFile.hasNext ( ) )
		{
			
			product.setProduct ( inputFile.nextLine ( ) );
			product.setPricePerGram ( Integer.parseInt ( inputFile.nextLine ( ) ) );
			product.setQuantity ( Integer.parseInt ( inputFile.nextLine ( ) ) );
			product.setEffects ( inputFile.nextLine( ) );
			product.setFlammable ( inputFile.nextLine ( ) );
			productArray[i] = product;
			i++;
			
		}
		
		System.out.print("\n\nIf you would like to add more items to our stock,"
				+ "\nplease enter a name, and further details will be requested.\n");
		newProductName = userIn.nextLine ( );
		System.out.println ( "The new product is " + newProductName );
		
		System.out.println ( "How many will be in stock?" );
		newProductStock = userIn.nextInt ( );
		System.out.println ( "The amount in stock is " + newProductStock );
		
		System.out.println ( "\nHow much will it cost?" );
		newProductPrice = userIn.nextInt ( );
		System.out.println ( "The amount in stock is " + newProductPrice );
		
		System.out.println ( "\nWhat are the effects?" );
		userIn.nextLine( );
		newProductEffects = userIn.nextLine ( );
		System.out.println ( "The effects is/are " + newProductEffects );
		
		System.out.println ( "\nIs it flammable?" );
		newProductFlammability = userIn.nextLine ( );
		while ( newProductFlammability.equals ("no") || newProductFlammability.equals ( "yes" ) )
		{
			System.out.println ( "Are you sure?" );
			newProductFlammability = userIn.nextLine ( );
		}
		if ( !newProductFlammability.equals ( "yes" ) || !newProductFlammability.equals ( "no" ) )
		{
			System.out.println ( "The product is probably explodable\n" );
			newProductFlammability = "Explodable";
		}
		System.out.println ( "product name: " + newProductName );
		System.out.println ( "product in stock: " + newProductStock );
		System.out.println ( "product price: " + newProductPrice );
		System.out.println ( "product effects: " + newProductEffects );
		System.out.println ( "product flammability: " + newProductFlammability );
		//addItems(productNamesArray, kgPricesArray, productStocksArray, descriptionsArray, numOfProducts);
	}
	
	
	
	
	/**
	** fileCheck : checks to see that the txt file that lists products exists
	** 
	** @param listFileName
	** @throws FileNotFoundException
	**/
	public static void fileCheck (String listFileName) throws FileNotFoundException, IOException
	{
		System.out.print("Checking for list of products...\n");
		txtProducts = new java.io.File (listFileName);
		inputFile = new Scanner(txtProducts);
		if (!txtProducts.exists())
		{
			System.out.print ("List not found, please check that " + listFileName + " exists in the project directory.");
			System.exit (-1);
		}
		else
		{
			System.out.print("List found!\n\n");
		}
	}
	
	public static void itemCheck ( )
	{
		
	}
	
	
	
	
	public static void addItems //-->
		(String productNamesArray[],
		float kgPricesArray[],
		int productStocksArray[],
		String descriptionsArray[],
		int numOfProducts)
	{
		productNamesArray[numOfProducts] = userIn.nextLine();
		System.out.println("What is the price (in USD) of " + productNamesArray[numOfProducts] + " per kilogram?");
		kgPricesArray[numOfProducts] = userIn.nextFloat();
		System.out.println("How many kg of " + productNamesArray[numOfProducts] + " are you adding?");
		productStocksArray[numOfProducts] = userIn.nextInt();
		System.out.println("What is your desired description?");
		descriptionsArray[numOfProducts] = userIn.nextLine();
		
		//TODO : fix it skipping the line below
		System.out.println(descriptionsArray[numOfProducts]);
		numOfProducts++;
	}

}
