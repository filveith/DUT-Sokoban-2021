public class Screen {

    private int size;
    private final char[][] image;


    char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
                       'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                       'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
                    };


    Screen(int size_) {
        size = size_;
        this.image = new char[size][size];
    }

    public void clear() {
        for(int r = 0; r < size; r++) {
            for(int c = 0; c < size ; c++) {
                image[c][r] = ' ';
                }
            }
        }


    public void display() {
        displayBar();
        for(int r = 0; r < size ;r++) {
            System.out.print("|");
            for(int c = 0; c < size ; c++) {
                System.out.print(image[c][r]);
                }
            System.out.println("|");
            }
            displayBar();
        }
    
    private void displayBar() {
        System.out.print("+");
        for(int c = 0; c < size ; c++) {
            System.out.print("-");
            }
        System.out.println("+");
        }
    }
