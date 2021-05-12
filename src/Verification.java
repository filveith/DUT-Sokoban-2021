import java.util.Scanner;
public class Verification {
    
	static private Scanner in = new Scanner(System.in);
	
	public static String userInput(int selection) {
		int maxBoardNameLenght = 12;
		String userInput = "";
		if (selection == 0) {
			System.out.println("Ou voulez vous jouer ? L R U D");
			userInput = in.nextLine().trim();
			userInput = checkUserMovement(userInput);
		} else if (selection == 1){
			System.out.println("Que voulez vous faire ?\n"
					+ "- Create new database (Create)\n"
					+ "- List boards (List)\n"
					+ "- Show board (Show)\n"
					+ "- Add board from file (Add)\n"
					+ "- Remove board from file (Remove)\n"
					+ "- Quit (Quit)");
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
				if(userInput != "" && userInput != null && userInput.length() < maxBoardNameLenght) return userInput;
				System.out.println("Fichier inexistant, verifier que le fichier est bien dans le dossier board");
			}
		} else if (selection == 4){
			while(true){
				System.out.println("ID du plateau à ajouter ? (Longueur max 8)");
				userInput = in.nextLine().trim();
				if(userInput != "" && userInput != null && userInput.length() < maxBoardNameLenght) return userInput;
				System.out.println("Nom du plateau non-possible.");
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
