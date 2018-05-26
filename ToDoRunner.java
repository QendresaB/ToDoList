package tasknotes;

import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
/** ToDoRunner connects classes to run the application*/

public class ToDoRunner {

    public static void main(String[] args) 
    {
        JFrame frame = new JFrame("TaskNote");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ToDoListController controller= new ToDoListController(new SomeTasksToDo());
        frame.add(controller);
        frame.setSize(400,700);
        controller.run(frame);
    }
}
