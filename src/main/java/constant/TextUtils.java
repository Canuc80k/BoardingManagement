
package constant;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class TextUtils {

    public static String removeDiacritics(String text) {
        text = text.replaceAll("Đ", "D").replaceAll("đ", "d");
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("");
    }
}
