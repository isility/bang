package kr.co.jhta.bang.finalproject.util;

public class MaskingIdUtil {

    public String MaskingIdUtil (String id) {

        int atSymbolIndex = id.indexOf("@");
        if (atSymbolIndex >= 5) {
            return id.substring(0, 5) + "*".repeat(atSymbolIndex - 5) + id.substring(atSymbolIndex);
        } else {
            return id;
        }
    }
}
