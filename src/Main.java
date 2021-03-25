package robot;

public class Main {
    
    public static void main(String[] args) {
        test();
    }

    static void test() {
        Robot r2d2 = new Robot(17, 5) ;
        
        Command[] program = {
            moveTo(1, 5), drawRight(3), drawUp(2),
            drawRight(2), drawUp(2), changeCar('x'),
            moveTo(6, 5), drawRight(2), drawUp(3)
        };
        
        
        r2d2.run(program) ;
    }

    static Command drawRight(int nbPas){
        return new DrawLineCommand(nbPas, 1, 0);
    }

    static Command drawLeft(int nbPas){
        return new DrawLineCommand(nbPas, -1, 0);
    }

    static Command drawUp(int nbPas){
        return new DrawLineCommand(nbPas, 0, -1);
    }

    static Command drawDown(int nbPas){
        return new DrawLineCommand(nbPas, 0, 1);
    }

    static Command moveTo(int row, int col) {
        return new MoveToCommand(row, col) ;
    }

    static Command changeCar(char point){
        return new ChangeCar(point);
    }
}
