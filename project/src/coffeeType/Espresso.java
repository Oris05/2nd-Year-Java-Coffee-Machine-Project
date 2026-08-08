package coffeeType;

import logging.AssignmentLogger;
/** 
 * espresso subclass 
 */
public class Espresso extends CoffeeType {

	public Espresso() {
		// TODO Auto-generated constructor stub
		super("Espresso", 2.00);
		AssignmentLogger.logConstructor(this);
	}

	@Override
	/**
	 * CalcPrice: calculates the price of the cost of the drink
	 */
	public double calcPrice() {
		// TODO Auto-generated method stub
		AssignmentLogger.logMethodEntry(this);
        AssignmentLogger.logMethodExit(this);
        return basePrice;
	}

}
