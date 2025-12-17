package Entities;

import java.awt.Image;

import Utils.Direction;

public abstract class MovableBlock extends Block {
    private Direction direction = Direction.UP;
    private int velocityX = 0;
    private int velocityY = 0;

    public MovableBlock(Image image, int x, int y, int width, int height) {
        super(image, x, y, width, height);
    }

    public Direction getDirection() { return direction; }
    public void setDirection(Direction direction) { this.direction = direction; }

    public int getVelocityX() { return velocityX; }
    public int getVelocityY() { return velocityY; }

    public void updateVelocity(int step) {
        if (direction == null) {
            velocityX = 0;
            velocityY = 0;
            return;
        }
        velocityX = 0;
        velocityY = 0;
        switch (direction) {
            case UP:    velocityY = -step; break;
            case DOWN:  velocityY = step; break;
            case LEFT:  velocityX = -step; break;
            case RIGHT: velocityX = step; break;
        }
    }

    public void move() {
        setX(getX() + velocityX);
        setY(getY() + velocityY);
    }

    public void undoMove() {
        setX(getX() - velocityX);
        setY(getY() - velocityY);
    }

    public boolean isAlignedToGrid() {
        int size = getWidth();
        if (size == 0) return true;
        return (getX() % (size / 4) == 0) && (getY() % (size / 4) == 0);
    }

    @Override
    public void reset() {
        super.reset();
        velocityX = 0;
        velocityY = 0;
        direction = Direction.UP;
    }
}