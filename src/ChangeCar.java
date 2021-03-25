package robot;

/**
 *
 * @author fil
 */
public class ChangeCar implements Command{
    private final char point; 

    public ChangeCar(char point){
        this.point = point;
    }

    @Override
    public void execute(Robot robot){
        robot.setChar(point);
    }
}
