package model;

public interface Movable {
    int[] getPosition();
    void setPosition(int x, int y);
    void movePosition(int x, int y);
}
