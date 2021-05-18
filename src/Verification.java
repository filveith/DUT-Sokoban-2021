import java.util.Scanner;
public class Verification {
    
	static private Scanner in = new Scanner(System.in);
	
	public static String userInput(int selection) {
		FileBoardBuilder f = new FileBoardBuilder();
		Administrator a = new Administrator();
		int maxBoardNameLenght = 12;
		String userInput = "";


		switch (selection) {
			case 0:
				System.out.println("Ou voulez vous jouer ? L(eft) R(ight) U(p) D(own)");
				userInput = in.nextLine().trim();
				userInput = checkUserMovement(userInput);
				break;
			case 1:
				System.out.println("Que voulez vous faire ?\n"
								+ "- Create new database (Create)\n"
								+ "- List boards (list)\n"
								+ "- Show board (show)\n"
								+ "- Add board from file (add)\n"
								+ "- Remove board from file (remove)\n"
								+ "- Quit (quit)");
				userInput = in.nextLine().trim();
				userInput = checkUserCommandDataBase(userInput);
				break;
			case 2:
				while(true){
					System.out.println("Quel est le nom de la nouvelle base de données ?");
					userInput = in.nextLine().trim();
					if(userInput != "" && userInput != null) return userInput;
					System.out.println("Nom de base non-possible.");
				}
			case 3:
				while(true){
					System.out.println("Quel est le nom du fichier texte à ajouter ?");
					userInput = in.nextLine().trim();
					if(f.checkIfFileExist(userInput+".txt") ) return userInput; //&& userInput.length() < maxBoardNameLenght
					System.out.println("Fichier inexistant ou nom trop grand, verifier que le fichier est bien dans le dossier board");
				}
			case 4:
				while(true){
					System.out.println("ID du plateau à ajouter ? (Longueur max 8)");
					userInput = in.nextLine().trim();
					if(userInput != "" && userInput != null && !a.checkIfIDExist(userInput) && userInput.length() < maxBoardNameLenght) return userInput;
					System.out.println("ID du plateau non-possible");
			}
			case 5:
				while(true){
					System.out.println("ID du plateau que voulez vous regarder ? ");
					userInput = in.nextLine().trim();
					if(a.checkIfIDExist(userInput)) return userInput;
					System.out.println("Le plateau n'existe pas");
			}
			case 6:
				while(true){
					System.out.println("Quel plateau voulez vous supprimer ? ");
					userInput = in.nextLine().trim();
					if(a.checkIfIDExist(userInput)) return userInput;
					System.out.println("Le plateau n'existe pas");
				}
			case 7:
				System.out.println("Que voulez vous faire ?\n"
								+ "- List boards (list)\n"
								+ "- Show board (show)\n"
								+ "- Quit (quit)");
				userInput = in.nextLine().trim();
				userInput = checkPlayerCommandDataBase(userInput);
				break;
			default:
				break;
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

	private static String checkPlayerCommandDataBase(String input) {
		input = input.toLowerCase();
		String output = "noMatching";
		
		if (input.equals("list") || input.equals("show") || input.equals("quit")) {
			return input;
		} else {
			System.out.println("Commande inconnue : "+input);
		}
		
		return output;
	}

	public static Board playerMenu(Board b){
		FileBoardBuilder f = new FileBoardBuilder();
		System.out.println("Nom du plateau auquel vous voulez jouer");
		String fileName = in.nextLine().trim();
		Administrator a = new Administrator();
		a.playerAdministrator(b);
		b = f.readFile(fileName+".txt", b);
		return b;
	}
	
    private static String checkUserMovement(String input){
        String output = "";
		input = input.toUpperCase();

        for (char movement : input.toCharArray()) {
            switch (movement) {
				case 'H':
					System.out.println("Comment jouer ?\n"+
										"-Pour se deplacer vous avez le choix entre: L(=gauche)\n"+
										"					    R(=droite)\n"+
										"					    D(=haut)\n"+
										"					    U(=bas)\n"+
										"-Pour ouvrir le panel d'aide: H(=aide)\n"+
										"-Pour quitter le jeu: Q(=quitter)\n"+
										"-Pour redemarrer la partie: N(=redémarrer)\n"+
										"-Pour changer de plateau: P(=plateau)\n");
					break;
				case 'Q':
					System.out.println("\nMerci d'avoir jouer au sokoban fait par Fil Veith\nA Bientôt");
					System.exit(0);
					break;
				case 'N':
					Player p = new Player();
					p.restartGame();
					break;
				case 'P':
					
					break;
				default:
					if(movement == 'L' || movement == 'R' || movement == 'U' || movement == 'D'){
						output += movement;
					}
					break;
			}
        }
        return output;
    }
}
