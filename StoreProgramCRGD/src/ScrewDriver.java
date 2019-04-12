
//Connor Rajotte, Gustav Didier
//4/2/19
//Screw Driver CRGD
//the purpose of this is to create a list of words to read into the file

public class ScrewDriver
{
	
	public static void main( String[ ] args )
	{
		Product NitrousOxide = new Product( "Nitrous Oxide", 1, 5, "Makes you laugh", "Not flammable");
		System.out.println ( NitrousOxide );
		
		Product SulfurHexafluoride = new Product( "Sulfur Hexafluoride", 1, 5, "Makes you sound like Darth Vader", "Not flammable");
		System.out.println ( SulfurHexafluoride );
		
		Product DihydrogenDioxide = new Product( "Dihydrogen Dioxide", 1, 5, "Not good for your health", "Explosive");
		System.out.println ( DihydrogenDioxide );
		
		Product CarbonMonoxide = new Product( "Carbon Monoxide", 1, 5, "Not good for your health", "Flammable");
		System.out.println ( CarbonMonoxide + "\nComes with complementary breathing mask in case of emergencies" );
	}

}
