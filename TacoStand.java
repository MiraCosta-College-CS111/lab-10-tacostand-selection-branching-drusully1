public class TacoStand
{
    /* CONSTANT VARIABLES */
	public static final String BAR = "----------------------------------------";
	public static final int STEAK_TYPE = 1;
	public static final int POLLO_TYPE = 2;
	public static final int LENGUA_TYPE = 3;
	public static final int ULT_TYPE = 4;
	public static final double STEAK_PRICE = 2.5;
	public static final double POLLO_PRICE = 1.75;
	public static final double LENGUA_PRICE = 3.0;
	public static final double ULT_PRICE = 18.0;

	/* STATIC VARIABLES */
	private static int numAsada = 0, numPollo = 0, numLengua = 0, numUltimate = 0;
	private static double totalFunds = 0;

	/**
	 * Sets the store to zero for use in automated testing.
	 */
	public static void initialize()
	{
		numAsada = numPollo = numLengua = numUltimate = 0;
		totalFunds = 0.0D;
	}

	/**
	 * Outputs menu options (kinds of tacos) customer can use to order
	 */
	public static void printMenu()
	{
		System.out.println("Menu options:\n" + TacoStand.BAR);
		System.out.printf("%2d. %-21s [$%5.2f]%n", STEAK_TYPE, "Carne Asada (Steak)", STEAK_PRICE);
		System.out.printf("%2d. %-21s [$%5.2f]%n", POLLO_TYPE, "Pollo Asado (Chicken)", POLLO_PRICE);
		System.out.printf("%2d. %-21s [$%5.2f]%n", LENGUA_TYPE, "Lengua (Beef Tongue)", LENGUA_PRICE);
		System.out.printf("%2d. %-21s [$%5.2f]%n", ULT_TYPE, "Ultimate Taco", ULT_PRICE);
		System.out.println(TacoStand.BAR);
	}
	
	/**
	* Returns a summary (all static variables) of the CURRENT status of the taco stand
	*
	* @return String containing current values related to taco stand, no new line at end
	*/
	public static String getStatus()
	{
		return String.format("%s%nMCC Taco Stand Status%n%s%n" +
						"%-23s$%-7.2f%n%s%n" +
						"%-23s%2d tacos%n" +
						"%-23s%2d tacos%n" +
						"%-23s%2d tacos%n" +
						"%-23s%2d tacos%n%s",
				TacoStand.BAR, TacoStand.BAR,
				"Funds Available:", TacoStand.totalFunds, TacoStand.BAR,
				"# of Asada Left:", TacoStand.numAsada,
				"# of Pollo Left:", TacoStand.numPollo,
				"# of Lengua Left:", TacoStand.numLengua,
				"# of Ultimate Left:",TacoStand.numUltimate, TacoStand.BAR);
	}

	/**
	 * Increases totalFunds static variable
	 * 
	 * @param funds assumes >0 value added to total funds available for cart
	 */
	public static void addTotalFunds(int funds)
	{
		TacoStand.totalFunds += funds;
	}
	
	/**
	 * Checks if proposed budget to order supplies can be used to buy more supplies. If within total funds,
	 * will update total funds and increment number of each option of tacos based on budget. Otherwise,
	 * no variables are changed.
	 * 
	 * @param budget funds to use to order supplies
	 * 
	 * @return boolean representing if supplies could be ordered (within total funds)
	 */
	public static boolean orderSupplies(double budget)
	{
		if (TacoStand.totalFunds < budget) return false;

		//tacos cost 75 cents each in supplies, keeping it simple
		int tacosEach = (int) (Math.round(budget / 0.75 / 4));

		TacoStand.totalFunds -= budget;

		TacoStand.numAsada += tacosEach;
		TacoStand.numPollo += tacosEach;
		TacoStand.numLengua += tacosEach;
		TacoStand.numUltimate += tacosEach;
		return true;
	}

	/**
	 * Adds funds to total (static variable) based on kind of taco (different prices) and number of tacos
	 * in order. Will also update appropriate number of tacos left (static variables).
	 * 
	 * @param tacoOption menu option (kind of taco)
	 * @param numTacos number of tacos as part of order, assume > 0
	 */
	public static void updateTotalFunds(int tacoOption, int numTacos)
	{
		if (!TacoStand.areTacosAvailable(tacoOption, numTacos)) return;

		switch (tacoOption) {
			case STEAK_TYPE:
				totalFunds += STEAK_PRICE * numTacos;
				TacoStand.numAsada -= numTacos;
				break;

			case POLLO_TYPE:
				totalFunds += POLLO_PRICE * numTacos;
				TacoStand.numPollo -= numTacos;
				break;

			case LENGUA_TYPE:
				totalFunds += LENGUA_PRICE * numTacos;
				TacoStand.numLengua -= numTacos;
				break;

			case ULT_TYPE:
				totalFunds += ULT_PRICE * numTacos;
				TacoStand.numUltimate -= numTacos;
				break;
		}
	}
	
	
	/**
	 * Determines if taco order can be fullfilled (number of tacos for specific kinda are available)
	 * 
	 * @param tacoOption menu option (kind of taco)
	 * @param numTacos number of tacos as part of order
	 * 
	 * @return boolean representing if specific kind of tacos, for the number in order, are available
	 */
	public static boolean areTacosAvailable(int tacoOption, int numTacos)
	{
		switch (tacoOption) {
			case STEAK_TYPE:
				return TacoStand.numAsada >= numTacos;
			case POLLO_TYPE:
				return TacoStand.numPollo >= numTacos;
			case LENGUA_TYPE:
				return TacoStand.numLengua >= numTacos;
			case ULT_TYPE:
				return TacoStand.numUltimate >= numTacos;
		}
		return false;
	}
}