package jumpingfrogs;

import java.util.Scanner;
import java.util.Stack;

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

        State initialState = State.generateInitialState(numberOfFrogs);
        State.initialiseGoal(numberOfFrogs);

        Solver solver = new Solver();
        solver.dfsRecursive(initialState);
    }

    public void dfs(State frogs) {
        Stack<State> stack = new Stack<>();
        stack.push(frogs);

        State state;
        while (!stack.empty()) {
            state = stack.pop();
            
            if (state.isGoal()) {
                state.printPath();
                return;
            }

            if (state.canLeftFrogMoveRight()) {
                stack.push(state.moveLeftFrogRight());
            }
            if (state.canRightFrogMoveLeft()) {
                stack.push(state.moveRightFrogLeft());
            }
            if (state.canLeftFrogJumpRight()) {
                stack.push(state.jumpLeftFrogRight());
            }
            if (state.canRightFrogJumpLeft()) {
                stack.push(state.jumpRightFrogLeft());
            }
        }
    }

    public boolean dfsRecursive(State state) {
        if (state.isGoal()) {
            state.printPath();
            return true;
        }

        if (state.canLeftFrogMoveRight()) {
            if (this.dfsRecursive(state.moveLeftFrogRight())) {
                return true;
            }
        }
        if (state.canRightFrogMoveLeft()) {
            if (this.dfsRecursive(state.moveRightFrogLeft())) {
                return true;
            }
        }
        if (state.canLeftFrogJumpRight()) {
            if (this.dfsRecursive(state.jumpLeftFrogRight())) {
                return true;
            }
        }
        if (state.canRightFrogJumpLeft()) {
            if (this.dfsRecursive(state.jumpRightFrogLeft())) {
                return true;
            }
        }

        return false;
    }

}
