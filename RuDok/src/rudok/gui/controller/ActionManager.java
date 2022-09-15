package rudok.gui.controller;

import lombok.Getter;

@Getter
public class ActionManager {

    private NewProjectAction newProjectAction;
    private ExitAction exitAction;
    private AboutAction aboutAction;
    private DeleteProjectAction deleteProjectAction;
    private OpenTabAction openTabAction;
    private RectangleAction rectangleAction;
    private CircleAction circleAction;
    private SelectAction selectAction;
    private TriangleAction triangleAction;
    private MoveAction moveAction;
    private ResizeAction resizeAction;
    private RotateAction rotateAction;
    private DeleteSlotAction deleteSlotAction;
    private UndoAction undoAction;
    private RedoAction redoAction;
    private SaveProjectAction saveProjectAction;
    private OpenProjectAction openProjectAction;

    public ActionManager(){
        initialiseActions();
    }

    private void initialiseActions(){
        newProjectAction = new NewProjectAction();
        exitAction = new ExitAction();
        aboutAction = new AboutAction();
        deleteProjectAction = new DeleteProjectAction();
        openTabAction = new OpenTabAction();
        rectangleAction = new RectangleAction();
        circleAction = new CircleAction();
        selectAction = new SelectAction();
        triangleAction = new TriangleAction();
        moveAction = new MoveAction();
        resizeAction = new ResizeAction();
        rotateAction = new RotateAction();
        deleteSlotAction = new DeleteSlotAction();
        undoAction = new UndoAction();
        redoAction = new RedoAction();
        saveProjectAction = new SaveProjectAction();
        openProjectAction = new OpenProjectAction();

    }

}
