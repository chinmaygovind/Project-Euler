package problems;

public class Problem019 {

    private static int totalFirstMonthlySundays = 0;

    private static int day = 0;
    private static int month = 0;
    private static int year = 1901;
    private static int dayOfWeek = 0;
    //private static String[] daysOfWeek = new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    //private static String[] months = new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private static int[] monthLengths = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static void main(String[] args){
        //increment days
        while (day != 31 || month != 11 || year != 2000){
            day++;
            dayOfWeek = (dayOfWeek + 1) % 7;
            //check months
            if (day > monthLengths[month]){
                month++;
                day = 1;
                if (month == 12){
                    year++;
                    month = 0;
                    //check leap years
                    if ((year%4 == 0 && year%100 != 0) || year%400 == 0){
                        monthLengths[1] = 29;
                    } else {
                        monthLengths[1] = 28;
                    }
                }
            }

            //check sundays
            if (day == 1 && dayOfWeek == 6){
                totalFirstMonthlySundays++;
            }

            //System.out.println(daysOfWeek[dayOfWeek] + ", " + months[month] + " " + day + ", " + year);
        }
        System.out.println("The number of Sundays on the first of the month in the twentieth century is: " + totalFirstMonthlySundays);


    }
}
