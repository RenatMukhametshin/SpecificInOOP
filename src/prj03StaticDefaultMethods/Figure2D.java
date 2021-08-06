package prj03StaticDefaultMethods;

public interface Figure2D {
    default Double getSquare(){
        return 0.0;
    }

    static Figure2D getFigure2D(){
        return null;
    }
}
