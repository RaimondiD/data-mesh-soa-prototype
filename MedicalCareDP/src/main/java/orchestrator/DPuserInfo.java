package orchestrator;

import javax.swing.*;
import java.io.Reader;
import java.util.Scanner;

public class DPuserInfo {

    public String dpUser = "admin";
    public String dpPassword;

    private static DPuserInfo user_info;
    private DPuserInfo() {
        final String message = "Enter password";
        final JPasswordField pf = new JPasswordField();
        dpPassword = JOptionPane.showConfirmDialog(null, pf, message,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION
                ? new String(pf.getPassword()) : "";

    }
    public static synchronized DPuserInfo getInstance()
    {
        if (user_info == null)
            user_info = new DPuserInfo();

     return user_info;
    }
}
