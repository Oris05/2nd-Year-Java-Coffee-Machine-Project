
package coffeeType;

import logging.AssignmentLogger;
//The abstract coffee class
public abstract class CoffeeType {
	String name;
	double basePrice;
	
	public CoffeeType(String name, double basePrice) {
		// TODO Auto-generated constructor stub
		AssignmentLogger.logConstructor(this);
		this.name = name;
        this.basePrice = basePrice;
	}

	/**
	 * CalcPrice: calculates the price of the cost of the drink
	 */
	public abstract double calcPrice();
}
