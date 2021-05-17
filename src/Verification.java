import java.nio.channels.SelectableChannel;
import java.util.Scanner;
public class Verification {
    
	static private Scanner in = new Scanner(System.in);
	
	public static String userInput(int selection) {
		FileBoardBuilder f = new FileBoardBuilder();
		Administrator a = new Administrator();
		int maxBoardNameLenght = 12;
		String userInput = "";
		if (selection == 0) {
			System.out.println("Ou voulez vous jouer ? L R U D");
			userInput = in.nextLine().trim();
			userInput = checkUserMovement(userInput);
		} else if (selection == 1){
			System.out.println("Que voulez vous faire ?\n"
					+ "- Create new database (Create)\n"
					+ "- List boards (list)\n"
					+ "- Show board (show)\n"
					+ "- Add board from file (add)\n"
					+ "- Remove board from file (remove)\n"
					+ "- Quit (quit)");
			userInput = in.nextLine().trim();
			userInput = checkUserCommandDataBase(userInput);
		} else if (selection == 2){
			while(true){
				System.out.println("Quel est le nom de la nouvelle base de données ?");
				userInput = in.nextLine().trim();
				if(userInput != "" && userInput != null) return userInput;
				System.out.println("Nom de base non-possible.");
			}
		} else if (selection == 3){
			while(true){
				System.out.println("Quel est le nom du fichier texte à ajouter ?");
				userInput = in.nextLine().trim();
				if(f.checkIfFileExist(userInput+".txt") ) return userInput; //&& userInput.length() < maxBoardNameLenght
				System.out.println("Fichier inexistant ou nom trop grand, verifier que le fichier est bien dans le dossier board");
			}
		} else if (selection == 4){
			while(true){
				System.out.println("ID du plateau à ajouter ? (Longueur max 8)");
				userInput = in.nextLine().trim();
				if(userInput != "" && userInput != null && !a.checkIfIDExist(userInput) && userInput.length() < maxBoardNameLenght) return userInput;
				System.out.println("ID du plateau non-possible");
			}
		} else if (selection == 5){
			while(true){
				System.out.println("Quel plateau voulez vous regarder ? ");
				userInput = in.nextLine().trim();
				if(true) return userInput;
			}
		}
    	return userInput;
	}
	
	private static String checkUserCommandDataBase(String input) {
		input = input.toLowerCase();
		String output = "noMatching";
		
		if (input.equals("create") || input.equals("list") || input.equals("show") || input.equals("add") || input.equals("remove") || input.equals("quit")) {
			return input;
		} else {
			System.out.println("Commande inconnue : "+input);
		}
		
		return output;
	}
	
    private static String checkUserMovement(String input){
        String output = "";

        for (char movement : input.toCharArray()) {
            if(movement == 'L' || movement == 'R' || movement == 'U' || movement == 'D'){
                output += movement;
            }
        }
        return output;
    }
    
    
}
