package tasknotes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.*;

public class ToDoListController extends JPanel {
    
    private int HOUR_OF_DAY=24;//hours in a day
    private int DAYS=7;//number of days a week
    private final Tasks[][] tasks;
    private final boolean[][] done_tasks; // boolean two-dimensional array which hold the current state of a task
                                          // based on the same indexes

    private SomeTasksToDo some_tasks;
    private String[] toDraw;

    public ToDoListController(SomeTasksToDo do_tasks) {
        
        some_tasks= new SomeTasksToDo();
        tasks = new Tasks[7][HOUR_OF_DAY];
        done_tasks = new boolean[7][HOUR_OF_DAY];
    }

    public void run(JFrame frame) {
        do {
            String input = JOptionPane.showInputDialog("Give the action:\n 0 To add a task,\n 1 To finish or delete a task,\n 2 To list the tasks,"
                                     + "\n 3 To add more tasks\n 4 To see your pending and completed tasks \n Another number to terminate the program");

            if ((input == null )||input.equals(""))
            {  
                //JOptionPane.showMessageDialog(null, "You have clicked OK or Cancel");
                //System.out.println("ok works");
                System.exit(0);
               
            }     
            int action = Integer.parseInt(input);
            if (action == 4) {
                JOptionPane.showMessageDialog(null, "You have to do: " + howManyLeft() + " tasks ,you have finished " + howManyFinished());
            } else {
                switch (action) {
                    case 0:
                        some_tasks.newTask1(frame);
                        printTasks();
                        break;
                    case 1:
                        taskDone();
                        printTasks();
                        break;
                    case 2:
                        listTasks();
                        break;
                    case 3:
                        some_tasks.generalTasks(frame);
                        printTasks();
                        break;
                    default:
                        return;
                }
            }
            
              
        }  while (true); 
            
    }
        
        
   /**taskDone based in the users input assigns boolean value to the boolean two diimensional array
     *with the same row's and column's indexes*/
    public void taskDone() {
        String input = JOptionPane.showInputDialog("Assign the task day and hour in the format day:hour (X:XX) as int:int that you've finished or you want to delete");
       
        if ((input == null )||input.equals(""))
           {   
             System.exit(0);
               
           }     
        String[] values = input.split(":");
       
        if (values.length == 2) {

            int day = Integer.parseInt(values[0]);
            int hour = Integer.parseInt(values[1]);

            if (day < 1 || day > DAYS+1 || hour < 0 || hour > HOUR_OF_DAY-1 ) // ||tasks[day][hour]==null
            {
                JOptionPane.showMessageDialog(null, "Bad inputs for days: " + day + "\n or hours: " + hour);
            } else {
                     if(done_tasks[day-1][hour])
                      {JOptionPane.showMessageDialog(null,"You have finished this task: "+done_tasks[day-1][hour]);}
                     else {
                             if(some_tasks.contentsOfTasks(day-1,hour)==null)
                               JOptionPane.showMessageDialog(null," You have no tasks to do ");

                       else
                             { done_tasks[day - 1][hour] = true;}
                          }
                   }
        }
        
          else { System.out.println("Error,bad inputs");}
        }
        
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.BLACK);
        g.setFont(new Font("Serif", Font.BOLD, 14));
      
            for (int i = 0; i < toDraw.length; i++) 
            {
                
                g.drawString(toDraw[i], 20, i * 20);
            }
       

    }
    /**printTasks prints tasks based on the current state of two-dimensional arrays containers*/
    public void printTasks() {
        String result = "";
        for (int i = 1; i <= DAYS; i++) {
            String dayFormated = Tasks.getDayFormated(i);
            String dayTask = getTasksString(i);

            if (dayTask.length() != 0) {
                result += "\n" + dayFormated + " " + dayTask + "\n";
            } else {
                result += dayFormated + "\n" + dayTask + "\n";
            }
        }
        if (howManyLeft() == 0) {
            toDraw = result.split("\n");
        } else{
            toDraw = result.split("\n");    
        }
        this.repaint();
    }
    /**listTasks lists task based on their contents
     *for each week day*/

    public void listTasks() {
        String dayString = JOptionPane.showInputDialog("For which day do you want to list the tasks(Input as integer 1-7)");
         
        if ((dayString == null )||dayString.equals(""))
           {   
             System.exit(0);
               
           }   
          
        int day = Integer.parseInt(dayString);
        String dayFormated = Tasks.getDayFormated(day);
        String result = "";

        if (day > 0 && day < DAYS+1) {
            result = getTasksString(day);
        }

        JOptionPane.showMessageDialog(null, dayFormated + ":\n" + result);
    }
   /**getTasksString holds contents of tasks for an specific day
     *@param day -the specific day
     *@return result as a string for tasks that are finished or not*/

    public String getTasksString(int day) {
        String result = "";
        for (int hour = 0; hour < HOUR_OF_DAY; hour++) {
            if ((some_tasks.contents(hour, (day - 1)) != null) && (done_tasks[day - 1][hour] == true)) 
            {
                result += "\n Hour: " + (some_tasks.contents(hour, (day - 1))).getHour() + " " + some_tasks.contentsOfTasks((day - 1), hour) + "+  you have finished this ";
            } else if ((some_tasks.contents(hour, (day - 1)) != null) && (done_tasks[day - 1][hour] == false)) {
                result += "\n Hour: " + (some_tasks.contents(hour, (day - 1))).getHour() + " "
                                         + some_tasks.contentsOfTasks((day - 1), hour) + "--> you have to do this ";
            } else if (tasks[day - 1][hour] != null) {
                result += "\n Hour: " + tasks[day - 1][hour].getHour() + " " + some_tasks.contentsOfTasks((day - 1), hour);
            }
        }

        return result;
    }
    /**howManyLeft() counts the unfinished tasks
    *@return unfinished tasks number*/

    public int howManyLeft() {
        int count = 0;
        for (int i = 0; i != tasks.length; i++) {
            for (int j = 0; j != tasks[0].length; j++) {
                if (some_tasks.contentsOfTasks(i, j) != null&&(done_tasks[i][j]==false)) {
                    count++;
                }
            }
        }
        return count;
    }
    /**howManyFinished counts the finished tasks
      *@return number of finished tasks*/

    public int howManyFinished() {
        int count = 0;
        for (int i = 0; i != done_tasks.length; i++) {
            for (int j = 0; j != tasks[0].length; j++) {
                if (done_tasks[i][j] == true) {
                    count++;
                }
            }
        }
        return count;
    }
  

}
