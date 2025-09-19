import java.util.Scanner;

class Menu{
	public int startMenu(){
		Scanner sc = new Scanner(System.in);
		int input = 0;
		boolean validInput = false;
		while(!validInput){
			displayStartMenu();
			try{
				input = sc.nextInt();
				if(input < 0 || input > 3){
					System.out.println("Must be a valid number from the menu options");
					System.out.println("--------------------------------------------\n");
				}else{ validInput = true; }
			}catch(Exception e){
				System.err.println("ERROR: Value input must be an Integer!");
			       	sc.nextLine();
			}
		}
		return input;
	}

	private void displayStartMenu(){
		System.out.println("Enter only the number for the option you select in the menu.");
		System.out.println("-------------Menu:----------------");
		System.out.println("(1): Single link download");
		System.out.println("(2): Multiple links download");
		System.out.println("(3): View Download History");
		System.out.println("(0): QUIT");
	}
}
