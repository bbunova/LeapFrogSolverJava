package jumpingfrogs;

import java.util.Scanner;

/**
 * @author Beatris Bunova <bibunova@gmail.com>
 */
public class Solver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numberOfFrogs;

        System.out.println("Enter number of frogs by one side: ");

        Scanner input = new Scanner(System.in);
        numberOfFrogs = input.nextInt();

        State initialState = State.generateInitialState(numberOfFrogs);;

        Solver solver = new Solver();
        solver.dfsRecursive(initialState);
    }

    public boolean dfsRecursive(State state) {
        if (state.isGoal()) {
            return true;
        }

        if (state.canLeftFrogMoveRight()) {
            if (this.dfsRecursive(state.moveLeftFrogRight())) {
                state.print();
                return true;
            }
        }
        if (state.canRightFrogMoveLeft()) {
            if (this.dfsRecursive(state.moveRightFrogLeft())) {
                state.print();
                return true;
            }
        }
        if (state.canLeftFrogJumpRight()) {
            if (this.dfsRecursive(state.jumpLeftFrogRight())) {
                state.print();
                return true;
            }
        }
        if (state.canRightFrogJumpLeft()) {
            if (this.dfsRecursive(state.jumpRightFrogLeft())) {
                state.print();
                return true;
            }
        }

        return false;
    }

}
