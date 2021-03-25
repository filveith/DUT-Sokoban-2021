package jeu;

public class Screen {

    private int size;
    private final char[][] image;
    

    char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
                       'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                       'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
                    };


    Screen(int size_) {
        size = size_;
        this.image = new char[size*2][size*2];
    }

    public boolean checkUserInput(char letter, int chiffre){
        int value = new String(alphabet).indexOf(letter);
        if( value != -1 && 0 < chiffre && chiffre <= size){
            return true;
        }
        return false;
    }

    public int setPoint(char letter, int chiffre){
        int x = getPostionOfLetter(letter);
        if (x == 0){
            x++;
        } else if(x == 1){
            x= x + 2;
        } else {
            x=x*2+1;
        }
        this.image[x][chiffre-1] = 'X';
        return 0;
    }

    private int getPostionOfLetter(char letter){
        return(new String(alphabet).indexOf(letter));
    }

    public void clear() {
        for(int r = 0; r < size; r++) {
            for(int c = 0; c < size*2; c++) {
                image[c][r] = ' ';
            }
        }
    }

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
                System.out.print(image[c][r]);
            }
            System.out.println(" |");
        }
        displayBar();
    }
    
    private void displayBar() {
        System.out.print("  +");
        for(int c = 0; c < size*2 ; c++) {
            System.out.print("-");
        }
        System.out.println("-+");
    }

    private void displayLetters(){
        System.out.print("   ");
        for(int i = 0; i<size; i++){
            System.out.print(" "+alphabet[i]);
        }
        System.out.println("");
    }
}