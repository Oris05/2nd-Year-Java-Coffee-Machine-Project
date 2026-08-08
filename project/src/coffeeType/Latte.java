package coffeeType;

import logging.AssignmentLogger;
/**
 * Latte subclass
 */
public class Latte extends CoffeeType {

	public Latte() {
		// TODO Auto-generated constructor stub
		super("Latte", 2.00);
        AssignmentLogger.logConstructor(this);
	}

	@Override
	/**
	 * CalcPrice: calculates the price of the cost of the drink
	 */
	public double calcPrice() {
		// TODO Auto-generated method stub
		AssignmentLogger.logMethodEntry(this);
	    double price = basePrice + 0.80; // milk cost
	    AssignmentLogger.logMethodExit(this);
	    return price;
	}
}
