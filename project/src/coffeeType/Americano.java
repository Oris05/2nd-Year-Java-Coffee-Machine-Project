package coffeeType;

import logging.AssignmentLogger;
/**
 * Americano coffee a subclass of the coffee type, it takes the base price of the espresso and adds the price of water to it.
 */
public class Americano extends CoffeeType {

	public Americano() {
		// TODO Auto-generated constructor stub
		super("Americano", 2.00);
		AssignmentLogger.logConstructor(this);
	}

	@Override
	/**
	 * CalcPrice: calculates the price of the cost of the drink
	 */
	public double calcPrice() {
		// TODO Auto-generated method stub
		AssignmentLogger.logMethodEntry(this);
		double price = basePrice + 0.50; //water cost
		AssignmentLogger.logMethodExit(this);
		return price;
	}

}
