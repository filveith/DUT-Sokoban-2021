package jeu;

public class Screen {

    private int size;
    private static Case[][] image;
    
    char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
                       'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                       'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
                    };


    Screen(int size_) {
        size = size_;
        image = new Case[size*2][size];
    }

    public static Case[][] getImage() {
        return image;
    }

    //verifie si la valeur entree par l'utilisateur est valable
    public boolean checkUserInput(char letter, int chiffre){
        int value = new String(alphabet).indexOf(letter);
        if(value != -1 && 0 < chiffre && chiffre <= size){
            return true;
        }
        return false;
    }

    //set la position d'un pion sur le plateau avec sa nature
    public int setPoint(char letter, int chiffre, char nature){
        int x = getPostionOfLetter(letter);
        if (x == 0){
            x++;
        } else if(x == 1){
            x= x + 2;
        } else {
            x=x*2+1;
        }
        image[x][chiffre-1].setNature(nature);
        return 0;
    }

    //vide le terrain des pions
    public void clear() {
        for(int r = 0; r < size; r++) {
            for(int c = 0; c < size*2; c++) {
                image[c][r] = new Case(' ');
            }
        }
    }

    //dessine le terrain avec les pions joués
    public void display() {
        displayLetters();
        displayBar();
        for(int r = 0; r < size ;r++) {
            String lineNum = Integer.toString(r+1);
            if(r<9) {
                lineNum = (" "+Integer.toString(r+1));
            }
            System.out.print(lineNum+"|");
            for(int c = 0; c < size*2 ; c++) {
                System.out.print(image[c][r].nature);
            }
            System.out.println(" |");
        }
        displayBar();
    }
    
    //dessine la barre superieure et inferieur (ex:  +-------------------+)
    private void displayBar() {
        System.out.print("  +");
        for(int c = 0; c < size*2 ; c++) {
            System.out.print("-");
        }
        System.out.println("-+");
    }

    //dessine les lettres des colonnes  
    private void displayLetters(){
        System.out.print("   ");
        for(int i = 0; i<size; i++){
            System.out.print(" "+alphabet[i]);
        }
        System.out.println("");
    }

    //retourn la position d'un char dans l'alphabet
    private int getPostionOfLetter(char letter){
        return(new String(alphabet).indexOf(letter));
    }

    //ROMEO HELP
    public void getPosFromInt(int a, int b) {
        System.out.println(alphabet[a/2] + String.valueOf(b+1));
    }

    //ROMEO HELP
    public void getCaseFromInt(int a, int b) {
        System.out.println(alphabet[a/2] + String.valueOf(b+1));
    }
}