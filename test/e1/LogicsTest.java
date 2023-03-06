package e1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;


import static org.junit.jupiter.api.Assertions.*;

class LogicsTest {
    public static final int POSITION_NOT_FOUND_VALUE = -1;
    public static final int CHESSBOARD_SIZE = 2;
    private Logics logics;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        logics = new LogicsImpl(CHESSBOARD_SIZE);
    }

    @Test
    void testThereIsAKnight(){
        assertNotEquals(new Pair<>(POSITION_NOT_FOUND_VALUE, POSITION_NOT_FOUND_VALUE),
                getPositionOfFigure((i, j) -> logics.hasKnight(i, j)));
    }

    @Test
    void testThereIsAPawn(){
        assertNotEquals(new Pair<>(POSITION_NOT_FOUND_VALUE, POSITION_NOT_FOUND_VALUE),
                getPositionOfFigure((i, j) -> logics.hasPawn(i, j)));
    }

    @Test
    void testCanHit(){
        Pair<Integer, Integer> knightPosition = getPositionOfFigure((i, j) -> logics.hasKnight(i, j));
        Pair<Integer, Integer> pawnPosition = getPositionOfFigure((i, j) -> logics.hasPawn(i, j));
        int xDifferenceOfComponents = knightPosition.getX() - pawnPosition.getY();
        int yDifferenceOfComponents = knightPosition.getY() - pawnPosition.getY();
        assertEquals(logics.hit(pawnPosition.getX(), pawnPosition.getY()),
                xDifferenceOfComponents!=0 && yDifferenceOfComponents!=0 &&
                        Math.abs(xDifferenceOfComponents)+Math.abs(yDifferenceOfComponents)==3);
    }
    private Pair<Integer, Integer> getPositionOfFigure(BiPredicate<Integer, Integer> figureFunction){
        List<Pair<Integer, Integer>> positionsFounded = new ArrayList<>();
        for(int i = 0; i < CHESSBOARD_SIZE; i++){
            for (int j=0; j < CHESSBOARD_SIZE; j++){
                if(figureFunction.test(i,j)){
                    positionsFounded.add(new Pair<>(i,j));
                }
            }
        }
        if (positionsFounded.size() == 1) {
            return positionsFounded.get(0);
        } else {
            return new Pair<>(POSITION_NOT_FOUND_VALUE, POSITION_NOT_FOUND_VALUE);
        }
    }

}