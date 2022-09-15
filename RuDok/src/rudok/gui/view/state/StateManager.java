package rudok.gui.view.state;

import rudok.gui.view.ProjectView;

public class StateManager {

    private State currentState;

    CircleState circleState;
    RectangleState rectangleState;
    TriangleState triangleState;
    SelectState selectState;
    MoveState moveState;
    RotateState rotateState;
    ResizeState resizeState;
    DeleteState deleteState;
    LassoState lassoState;

    public StateManager(ProjectView projectView){

        circleState = new CircleState(projectView);
        rectangleState = new RectangleState(projectView);
        triangleState = new TriangleState(projectView);
        selectState = new SelectState(projectView);
        moveState = new MoveState(projectView);
        rotateState = new RotateState(projectView);
        resizeState = new ResizeState(projectView);
        deleteState = new DeleteState(projectView);
        lassoState = new LassoState(projectView);

        currentState = selectState;
    }

    public void setSelectState() {
        System.out.println("Select state");
        currentState = selectState;
    }
    public void setRectangleState() {
        System.out.println("Rectangle state");
        currentState = rectangleState;
    }
    public void setCircleState() {
        System.out.println("Circle state");
        currentState = circleState;
    }
    public void setTriangleState() {
        System.out.println("Triangle state");
        currentState = triangleState;
    }
    public void setMoveState() {
        System.out.println("Move state");
        currentState = moveState;
    }
    public void setResizeState() {
        System.out.println("Resize state");
        currentState = resizeState;
    }
    public void setRotateState() {
        System.out.println("Rotate state");
        currentState = rotateState;
    }
    public void setDeleteState(){
        System.out.println("Delete state");
        currentState = deleteState;
    }
    public void setLassoState(){
        System.out.println("Lasso state");
        currentState = lassoState;
    }

    public State getCurrentState() { return currentState; }
}
