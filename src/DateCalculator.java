public class DateCalculator {

    final static int DAYS_IN_NORMAL_YEAR = 365;

    public static Date addToDate(Date date, int num) {
        return addToDate_recursion_years(date, num, num >= 0);
    }


    public static Date addToDate_recursion_years(Date curr_date, int days_left, boolean is_positive)
    {
        if (days_left < DAYS_IN_NORMAL_YEAR && days_left > -DAYS_IN_NORMAL_YEAR){
            return addToDate_recursion (curr_date, days_left, 0, is_positive);
        }

        Date new_date;
        if (is_positive){
            new_date = addToDate_recursion (curr_date,DAYS_IN_NORMAL_YEAR, 0, true);
            return addToDate_recursion_years(new_date,days_left - DAYS_IN_NORMAL_YEAR, true);
        }
        else{
            new_date = addToDate_recursion (curr_date, -DAYS_IN_NORMAL_YEAR, 0, false);
            return addToDate_recursion_years(new_date,days_left + DAYS_IN_NORMAL_YEAR, false);
        }
    }

    public static Date addToDate_recursion(Date curr_date, int num, int counter, boolean is_positive){
        if(counter == num){
            return curr_date;
        }

        if(is_positive){
            return addToDate_recursion(addOneDay(curr_date), num, counter + 1, true);
        }
        return addToDate_recursion(SubtractOneDay(curr_date), num, counter - 1, false);
    }

    private static Date addOneDay(Date curr_date){
        int day = curr_date.getDay();
        int month = curr_date.getMonth();
        int year = curr_date.getYear();

        if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
            if(day == 31){
                day = 1;
                if (month == 12) {
                    month = 1;
                    year++;
                }
                else{
                    month++;
                }
            }
            else{
                day++;
            }
        }
        else if(month == 4 || month == 6 || month == 9 || month == 11){
            if(day == 30){
                day = 1;
                month++;
            }
            else{
                day++;
            }
        }
        else if (month == 2) {
            if ((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)) {
                if (day == 29) {
                    day = 1;
                    month++;
                }
                else {
                    day++;
                }
            } else {
                if (day == 28) {
                    day = 1;
                    month++;
                } else {
                    day++;
                }
            }
        }
        return new Date(day, month, year);
    }

    private static Date SubtractOneDay(Date curr_date){
        int day = curr_date.getDay();
        int month = curr_date.getMonth();
        int year = curr_date.getYear();

        if (day == 1) {
            if (month == 1) {
                month = 12;
                year--;
                day = 31;
            }
            else{
                month--;
                if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                    day = 31;
                }
                else if (month == 4 || month == 6 || month == 9 || month == 11) {
                    day = 30;
                }
                else if (month == 2) {
                    if ((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)) {
                        day = 29;
                    }
                    else {
                        day = 28;
                    }
                }
            }
        }
        else{
            day--;
        }
        return new Date(day, month, year);

    }
}
