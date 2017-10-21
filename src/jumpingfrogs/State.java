package jumpingfrogs;

import java.util.Arrays;

/**
 * @author Beatris Bunova <bibunova@gmail.com>
 */
public class State {

    public static final int LEFT_FROG = 1;
    public static final int RIGHT_FROG = 2;
    public static final int EMPTY_SPACE = 0;

    private final int[] frogs;
    private final int zeroPosition;

    private static int[] goal;
    private static int numberOfFrogs;

    public State(int[] frogs, int zeroPosition) {
        this.frogs = frogs;
        this.zeroPosition = zeroPosition;
    }

    public static State generateInitialState(int numberOfFrogs) {
        State.numberOfFrogs = numberOfFrogs;

        int[] frogs = new int[numberOfFrogs * 2 + 1];
        Arrays.fill(frogs, 0, numberOfFrogs, LEFT_FROG);
        frogs[numberOfFrogs] = EMPTY_SPACE;
        Arrays.fill(frogs, numberOfFrogs + 1, numberOfFrogs * 2 + 1, RIGHT_FROG);

        return new State(frogs, numberOfFrogs);
    }

    public void print() {
        System.out.println(State.getStateAsString(this.frogs));
    }

    public static void initialiseGoal(int numberOfFrogs) {
        State.numberOfFrogs = numberOfFrogs;

        int[] frogs = new int[numberOfFrogs * 2 + 1];
        Arrays.fill(frogs, 0, numberOfFrogs, RIGHT_FROG);
        frogs[numberOfFrogs] = EMPTY_SPACE;
        Arrays.fill(frogs, numberOfFrogs + 1, numberOfFrogs * 2 + 1, LEFT_FROG);

        State.goal = frogs;
    }

    public boolean isGoal() {
        if (this.zeroPosition != numberOfFrogs) {
            return false;
        }

        for (int i = 0; i < frogs.length; i++) {
            if (i < numberOfFrogs) {
                if (this.frogs[i] == LEFT_FROG) {
                    return false;
                }
            } else if (this.frogs[i] == RIGHT_FROG) {
                return false;
            }
        }

        return true;
    }

    private static String getStateAsString(int[] frogs) {
        StringBuilder stringState = new StringBuilder();

        for (int frog : frogs) {
            switch (frog) {
                case LEFT_FROG:
                    stringState.append("L");
                    break;
                case RIGHT_FROG:
                    stringState.append("R");
                    break;
                default:
                    stringState.append("_");
                    break;
            }
        }

        return stringState.toString();
    }

    public boolean canLeftFrogMoveRight() {
        if ((this.zeroPosition >= 1) && (this.frogs[this.zeroPosition - 1] == LEFT_FROG)) {
            return true;
        }

        return false;
    }

    public boolean canLeftFrogJumpRight() {
        if ((this.zeroPosition >= 2) && (this.frogs[this.zeroPosition - 2] == LEFT_FROG)) {
            return true;
        }

        return false;
    }

    public boolean canRightFrogMoveLeft() {
        if ((this.zeroPosition <= State.numberOfFrogs * 2 - 1) && (this.frogs[this.zeroPosition + 1] == RIGHT_FROG)) {
            return true;
        }

        return false;
    }

    public boolean canRightFrogJumpLeft() {
        if ((this.zeroPosition <= State.numberOfFrogs * 2 - 2) && (this.frogs[this.zeroPosition + 2] == RIGHT_FROG)) {
            return true;
        }

        return false;
    }

    public State moveLeftFrogRight() {
        int[] frogsCopy = Arrays.copyOf(this.frogs, numberOfFrogs * 2 + 1);
        frogsCopy[this.zeroPosition] = LEFT_FROG;
        frogsCopy[this.zeroPosition - 1] = EMPTY_SPACE;

        return new State(frogsCopy, this.zeroPosition - 1);
    }

    public State jumpLeftFrogRight() {
        int[] frogsCopy = Arrays.copyOf(this.frogs, numberOfFrogs * 2 + 1);
        frogsCopy[this.zeroPosition] = LEFT_FROG;
        frogsCopy[this.zeroPosition - 2] = EMPTY_SPACE;

        return new State(frogsCopy, this.zeroPosition - 2);
    }

    public State moveRightFrogLeft() {
        int[] frogsCopy = Arrays.copyOf(this.frogs, numberOfFrogs * 2 + 1);
        frogsCopy[this.zeroPosition] = RIGHT_FROG;
        frogsCopy[this.zeroPosition + 1] = EMPTY_SPACE;

        return new State(frogsCopy, this.zeroPosition + 1);
    }

    public State jumpRightFrogLeft() {
        int[] frogsCopy = Arrays.copyOf(this.frogs, numberOfFrogs * 2 + 1);
        frogsCopy[this.zeroPosition] = RIGHT_FROG;
        frogsCopy[this.zeroPosition + 2] = EMPTY_SPACE;

        return new State(frogsCopy, this.zeroPosition + 2);
    }
}
