package menus;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import coffeeType.Americano;
import coffeeType.CoffeeType;
import coffeeType.Espresso;
import coffeeType.Latte;
import exceptions.CustomException;
import logging.AssignmentLogger;

/**
 * The Coffee Machine class is the main body of code for this project. The main method is wrapped in a while true method which makes the code never end.
 *  The program starts by asking the user If they would like coffee or to change language, The user can select between English and lithuanian.
 *  When the user selects coffee they are presented with multiple coffee options such as latte, espresso or americano.
 *  The user selects the drink they wish to order and the program will begin the process of playing the .WAV file sound,
 *  accompanied by messages such as preparing, complete, product price and a custom exception that checks if the file path is correct.
 * 
 * @author Orestas Zeigis
 */
public class Coffee_Machine{
	/**
	 * This constructor is used to initialize the assignment logger.
	 */
	public Coffee_Machine() {
		AssignmentLogger.logConstructor(this);
	}
	/**
	 * The main method contains the while true loop, making the program never stop working.
	 * @param args (not used)
	 */
	public static void main(String[] args) {
		//Creating this class object makes the assignment logger work.
		new Coffee_Machine();
		AssignmentLogger.logStaticMethodEntry();
		
		//The default locale of the project is English.
		Locale currentLocale = new Locale("en");
		ResourceBundle bundle = ResourceBundle.getBundle("translation", currentLocale);

		//Never ending loop to simulate a real coffee machine.
		while(true) {
			//runs the intro_menu method which displays the initial options for the user.
			Locale newLocale = intro_menu(bundle);
			//runs the bundle and changes the language for the customer depending on their choice.
			if (newLocale != null) {
		        bundle = ResourceBundle.getBundle("translation", newLocale);
		    }
		}
	}
	/**
	 * Intro_menu is the Introduction menu for the machine, The user is presented with two 
	 * options before they can go ahead and order, the first option is to go ahead and make a coffee 
	 * and the second option is to change the language.
	 * 
	 * @param bundle: Allows the code to return each text in the correct language
	 * @return Returns the change_locale method if the user selects number 2.
	 */
	public static Locale intro_menu(ResourceBundle bundle){
		AssignmentLogger.logStaticMethodEntry();
		
		System.out.println(bundle.getString("welcome"));// welcome message
		
		Scanner scanner = new Scanner(System.in); 
		//scanner to make it so the user picks the correct numbers
		int choice = 0;
		//Doesn't allow for customer to choose anything other than 1 or 2
		while (choice < 1 || choice > 2) {
			System.out.println("1. "+bundle.getString("coffee"));
			System.out.println("2. "+bundle.getString("language"));
			System.out.print(bundle.getString("choose"));
	        choice = scanner.nextInt();
	    }
		 if (choice == 1) {// if the choice is 1 the program moves forward and calls other methods
			 String path = make_a_drink(bundle);// make a drink method that prints out a menu
			 
			 try {//file check to see if the path exists
				 System.out.println(fileCheck(path, bundle));
				 
				 System.out.print(bundle.getString("preparing"));//confirmation message
				 play_a_sound(path, bundle);//playing the sound
				 System.out.print(bundle.getString("done"));// completion message
			 }catch(CustomException e) {//if its wrong throw custom exception
				 System.out.print(bundle.getString("custom_incorrect"));
			 }
			 
			 AssignmentLogger.logStaticMethodExit();
			 return null;//Null so nothing is changed with locale
		}else {
			AssignmentLogger.logStaticMethodExit();
			return change_locale(bundle);//call change locale method
		}
	}
	/**
	 * This method allows the user to change their locale based on the number they pressed.
	 * @param bundle: this parameter is the current locale.
	 * @return the locale that was selected.
	 */
	public static Locale change_locale(ResourceBundle bundle) {
		AssignmentLogger.logStaticMethodEntry();
		Scanner scanner = new Scanner(System.in);
		int choice = 0;

		while (choice < 1 || choice > 2) {
			System.out.println("1. "+bundle.getString("change_lan_en"));
			System.out.println("2. "+bundle.getString("change_lan_lt"));
			System.out.print(bundle.getString("choose"));
	        choice = scanner.nextInt();
	    }

	    if (choice == 1) {//English
	    	AssignmentLogger.logStaticMethodExit();
	        return new Locale("en");
	    } else {//Lithuanian
	    	AssignmentLogger.logStaticMethodExit();
	        return new Locale("lt");
	    }
	}
	/**
	 * make_a_drink method refers to the the menu that is presented to the customer. It allows them to select between three drinks.
	 * and based on the number picked it returns a string with the path of the WAV sound file.
	 * @param bundle determines which locale should be used.
	 * @return A string which is the file path to the sound file.
	 */
	public static String make_a_drink(ResourceBundle bundle) {
		AssignmentLogger.logStaticMethodEntry();
		
		Scanner scanner = new Scanner(System.in);
		int choice = 0;
		
		while (choice < 1 || choice > 3) { //drinks
			System.out.println(bundle.getString("pick"));
			System.out.println("1. LATTE");
			System.out.println("2. AMERICANO");
			System.out.println("3. ESPRESSO");
			System.out.print(bundle.getString("choose"));
			choice = scanner.nextInt();
		}
		CoffeeType drink = null; //price of drinks
		String soundPath = null; //string of file path
		
		if (choice == 1) {
			drink = new Latte();
			soundPath = "sounds/Latte.wav";
        }else if (choice == 2){
        	drink = new Americano();
        	soundPath = "sounds/Americano.wav";
        }else if (choice == 3){
        	drink = new Espresso();
        	soundPath = "sounds/Expresso.wav";
        }
		double price = drink.calcPrice();
	    System.out.println(bundle.getString("price")+" " + price);
	    
	    AssignmentLogger.logStaticMethodExit();
		return soundPath;
	}
	/**
	 * Play a sound method is a method designed to play a sound based on the path that was passed from the make a drink method
	 * @param path is the string that was returned by the make_a_drink method.
	 * @param bundle determines what locale is used
	 */
	public static void play_a_sound(String path, ResourceBundle bundle) {
		AssignmentLogger.logStaticMethodEntry();
		try {
			File file = new File(path);
			file.toURI();
			System.out.println(file.toURI().toString());
			
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(file.toURI().toURL());
			Clip clip = AudioSystem.getClip();
			
			clip.open(audioIn);
			clip.start();
			Thread.sleep(6000);
			clip.stop();
			clip.close();
		} 
		catch (LineUnavailableException | UnsupportedAudioFileException | IOException | InterruptedException e) {
			e.printStackTrace();
			System.out.print(bundle.getString("error"));
		}
		AssignmentLogger.logStaticMethodExit();
	}
	/**
	 * Custom Exception class which checks if the file path to the WAV file is correct.
	 * @param path is the path of the file.
	 * @param bundle is the locale which is used.
	 * @return returns the string if its false or true.
	 * @throws CustomException if false prints the message.
	 */
	public static String fileCheck(String path, ResourceBundle bundle) throws CustomException {
		if(path.equalsIgnoreCase("sounds/Latte.wav")||path.equalsIgnoreCase("sounds/Americano.wav")||path.equalsIgnoreCase("sounds/Expresso.wav")) {
			return bundle.getString("custom_correct");
		}
		else {
			throw new CustomException(bundle.getString("custom_incorrect"));
		}		
	}
}
