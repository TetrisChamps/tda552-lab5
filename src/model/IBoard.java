package model;

/**
 * An interface for an object which has a board that can be lowered and raised
 */
public interface IBoard {

    /**
     * Raises the board
     */
    void raise();

    /**
     * Lowers the board
     */
    void lower();
}
