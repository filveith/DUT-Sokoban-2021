public class Screen {
    private int size;


    char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
                       'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                       'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
                    };


    Screen(int size_) {
        size = size_;
    }


    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.print('-');
        }
    }
}
