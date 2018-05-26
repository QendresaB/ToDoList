package tasknotes;

import javax.swing.*;

/**
 * Record models a Library Book
 */
public class SomeTasksToDo {
    private int HOUR_OF_DAY=24; //hours in a day
    private int DAYS=7;//number of days a week
    Tasks[][] tasks; //holds the object which returns the hour and current day
    String[][] to_do; // tasks to do

    public SomeTasksToDo() {
        tasks = new Tasks[DAYS][HOUR_OF_DAY]; //has rows equal to the number of days of 24(hours in a day) elemnets
        to_do = new String[DAYS][HOUR_OF_DAY];
    }
    /**newTask1 asks the user to add a task
      *appends the task into the Tasks array
      *@param frame */
    public void newTask1(JFrame frame) 
    {
        String input = JOptionPane.showInputDialog("Assign the task day and hour in the format day:hour (X:XX) as int:int");
        if ((input == null )||input.equals(""))
           {   
             System.exit(0);
               
           }   
        String[] values = input.split(":"); //string method to split input based on a delimiter
        if(2==values.length) 
            {
             int day = Integer.parseInt(values[0]);
             int hour = Integer.parseInt(values[1]);
                
        if (day < 1 || day > DAYS || hour < 0 || hour > HOUR_OF_DAY)
        {
            JOptionPane.showMessageDialog(null, "Day :" + day + " or hour input :" + hour + " out of range");
        } 
        else{
              if (tasks[day - 1][hour] != null) {
            JOptionPane.showMessageDialog(null, "You already have a task in day : " + Tasks.getDayFormated(day)  + " hour " + hour);
        } else
           {
            tasks[day - 1][hour] = new Tasks(day - 1, hour);
            input = JOptionPane.showInputDialog("Type the task content");
            if ((input == null )||input.equals(""))
           {   
             System.exit(0);
               
           }   
            frame.setVisible(true);
            appendToTasks(input, day - 1, hour);
           }
      }
    }  
    else {JOptionPane.showMessageDialog(null, "Error,bad inputs:");}
    }
    
    /**appendToTasks appends tasks to the string array
     *@param task the string as a task
     *@param day the task's day
     *@param hour the task's hour */

    public void appendToTasks(String task, int day, int hour) {
        task = task.toLowerCase().trim();
        to_do[day][hour] = task.substring(0, 1).toUpperCase() + task.substring(1).toLowerCase();
    }
     /**generalTasks ask the user to put some Tasks as general in a row of days
       *in a specific hou
     * @param frame */
     public void generalTasks(JFrame frame)
  { 
       
        String str = JOptionPane.showInputDialog("Type the hour and the days interval as in hour,day,day");
        if ((str== null )||str.equals(""))
           {   
             System.exit(0);
               
           }   
        String array1[]= str.split(",");
        int hour = Integer.parseInt(array1[0]);
        int the_first = Integer.parseInt(array1[1]);
        int the_last = Integer.parseInt(array1[2]);
        
      if(the_first < 1 ||the_first>DAYS||the_last<1 ||the_last > DAYS || hour < 0 || hour > HOUR_OF_DAY-1)
        {JOptionPane.showMessageDialog(null,"Bad inputs for days or hours!");}
        
      else
          { String input=JOptionPane.showInputDialog("Type your task");
            if ((input == null )||input.equals(""))
           {   
             System.exit(0);
               
           }   
            for(int i=the_first-1;i!=the_last;i++)
               {
               if(tasks[i][hour]!=null)     
                 {JOptionPane.showMessageDialog(null,"Error!\nYou already have a task in day : "+Tasks.getDayFormated(i+1)+" hour "+hour +"\nBut u can proceed for the other days");}
              else
                  { 
                    Tasks newTask = new Tasks(i, hour);
                    tasks[i][hour] = newTask;
                    frame.setVisible(true);
                    appendToTasks(input,i,hour);                     
                  }
               }
           }
       }

    /** contents  returns the current state of the Tasks objects
      * @param hour a Task's hour
      * @param day a Task's day
      * @return  a Task object */

    public Tasks contents(int hour, int day) {
        return tasks[day][hour];
    }
   
    /** contents  returns the current state of the tasks as a String object
     * @param day 
     * @param hour
     * @return String an element of the a 2D array string */

    public String contentsOfTasks(int day, int hour) {
        return to_do[day][hour];
    }
}
