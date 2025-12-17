package Utils;
public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    //AI behavior support
    public Direction opposite() {
        switch (this) {
            case UP:    return DOWN;
            case DOWN:  return UP;
            case LEFT:  return RIGHT;
            case RIGHT: return LEFT;
            default:    return null;
        }
    }
}
