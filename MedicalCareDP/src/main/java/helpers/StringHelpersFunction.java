package helpers;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

public class StringHelpersFunction {
    public static Optional<String> reduceListStrings(ArrayList<String> argument, String operator){
        return argument.stream().reduce((s1, s2) -> s1 + operator + s2 );
    }
    public static String stringOfActualTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

}
