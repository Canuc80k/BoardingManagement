package constant;

import java.awt.Color;

public class PaymentState {
    public final static int DAY_HASNT_COME_YET = 0;
    public final static int HASNT_PAY = 1;
    public final static int HAS_PAY_HASNT_PAYBACK = 2;
    public final static int HAS_PAYBACK = 3;

    public final static Color DAY_HASNT_COME_YET_COLOR = Color.GRAY;
    public final static Color HASNT_PAY_COLOR = Color.RED;
    public final static Color HAS_PAY_HASNT_PAYBACK_COLOR = Color.BLUE;
    public final static Color HAS_PAYBACK_COLOR = Color.GREEN;

    public static Color getColor(int state) {
        if (state == 0) return DAY_HASNT_COME_YET_COLOR;
        if (state == 1) return HASNT_PAY_COLOR;
        if (state == 2) return HAS_PAY_HASNT_PAYBACK_COLOR;
        if (state == 3) return HAS_PAYBACK_COLOR;
        return null;
    }
}
