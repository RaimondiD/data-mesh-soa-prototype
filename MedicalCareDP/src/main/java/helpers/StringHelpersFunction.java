package helpers;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
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

    public static String decodeJWTpayload(String jwt){
        String encoded_payload  = jwt.split("\\.")[1];
        Base64.Decoder decoder = Base64.getUrlDecoder();
        return new String(decoder.decode(encoded_payload));


    }

}
