package exceptions;
/**
 * This is a class made to be a custom exception which extends the general exception
 * and send as message to its constructor. The purpose of this exception is to throw a 
 * custom exception if the network passed is incorrect.
 */
public class CustomException extends Exception {
	//Extend general exception.	
	//A super constructor is simply the constructor of the parent class, so
	//because I extend Exception and I write super(message) i am sending the said
	//exception up to the Exception classes constructor, which allows my custom exception
	// behave like a regular exception.
	public CustomException(String message) {
		super(message);
	}
}
