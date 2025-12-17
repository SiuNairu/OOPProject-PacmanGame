

package Entities;

import java.awt.Image;
import java.util.Random;
import Utils.Direction;

public class PacmanPlayer extends MovableBlock implements Updatable {

    private Direction pendingDirection = null;
    private final int speedFactor = 4;

    private final Image up, down, left, right;

    // The constructor takes 4 images instead of 1
    public PacmanPlayer(Image up, Image down, Image left, Image right, int x, int y, int width, int height) {
        super(right, x, y, width, height);
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    public Direction getPendingDirection() {
        return pendingDirection;
    }

    public void setPendingDirection(Direction pendingDirection) {
        this.pendingDirection = pendingDirection;
    }

    public void updateSpeed() {
        int step = getWidth() / speedFactor;
        updateVelocity(step);
    }
    @Override
    public void update(long now, boolean powerMode) {
        updateSpeed();
    }

    // Override the setDirection method of the parent (MovableBlock)
    // So that the image updates automatically whenever the direction changes
    @Override
    public void setDirection(Direction newDir) {
        super.setDirection(newDir); // Call super to handle velocity updates
        updateImage();
    }

    private void updateImage() {
        Direction dir = getDirection();
        if (dir == null) return;

        switch (dir) {
            case UP:    this.image = this.up;    break;
            case DOWN:  this.image = this.down;  break;
            case LEFT:  this.image = this.left;  break;
            case RIGHT: this.image = this.right; break;
        }
    }

    @Override
    public void reset() {
        super.reset();
        pendingDirection = null;
        this.image = this.right; // Reset image
    }
}