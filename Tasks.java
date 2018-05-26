package tasknotes;
/**Tasks holds methods for days and hours */
public class Tasks {
   public int day;
   public int hour;
   
   public Tasks(int day, int hour) {
      this.day = day;
      this.hour = hour;
   }
    /**getHour the hour for a Tasks object
     * @return int the hour */
    public int getHour() {
      return hour;
   }
  /**getDayFormated holds the week-days
    *@param day-a day of week as an int from 1-7
    *@return String the exact day based in the parameter*/

   public static String getDayFormated(int day) {
      switch(day)
     {
         case 1:
            return "Monday";
         case 2:
            return "Tuesday";
         case 3:
            return "Wednesday";
         case 4:
            return "Thursday";
         case 5:
            return "Friday";
         case 6:
            return "Saturday";
         case 7:
            return "Sunday";
         default:
            return "Unkown";
      }
  }  
}