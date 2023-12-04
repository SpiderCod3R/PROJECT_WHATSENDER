package bot.application.config.helpers;

public abstract class FormatterHelper {
    public static String formatPhoneNumber(String phonenumber){
        return phonenumber.
            replace("+", "").
            replace("(", "").
            replace(")", "").
            replace("-", "");
    }
}
