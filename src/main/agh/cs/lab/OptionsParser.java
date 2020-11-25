package agh.cs.lab;

public class OptionsParser {

    public static MoveDirection[] parse(String[] moves) {
        int i = 0;
        MoveDirection[] moveDirections = new MoveDirection[moves.length];
        for (String move : moves) {
            switch (move) {
                case "forward":
                case "f":
                    moveDirections[i] = MoveDirection.FORWARD;
                    i++;
                    break;
                case "backward":
                case "b":
                    moveDirections[i] = MoveDirection.BACKWARD;
                    i++;
                    break;
                case "right":
                case "r":
                    moveDirections[i] = MoveDirection.RIGHT;
                    i++;
                    break;
                case "left":
                case "l":
                    moveDirections[i] = MoveDirection.LEFT;
                    i++;
                    break;
                default:
                    throw new IllegalArgumentException("\"" + move + "\"" + " is not legal move specification");
            }
        }

        return moveDirections;
    }

}
