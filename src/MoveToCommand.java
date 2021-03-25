package robot;

/**
 *
 * @author fil
 */

public class MoveToCommand implements Command {

    private int col;
    private int lig;

    public MoveToCommand(int col, int lig) {
        this.col = col;
        this.lig = lig;
    }

    @Override
    public void execute(Robot robot) {
        robot.absoluteMove(col, lig);
    }
}