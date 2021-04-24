package net.thevpc.nuts.runtime.core.format.text.stylethemes;

import net.thevpc.nuts.NutsTextFormatTheme;
import net.thevpc.nuts.NutsTextStyle;

import java.util.ArrayList;
import java.util.List;
import net.thevpc.nuts.NutsSession;
import net.thevpc.nuts.NutsTextStyles;

public class NutsTextFormatThemeWrapper implements NutsTextFormatTheme {

    private NutsTextFormatTheme other;

    public NutsTextFormatThemeWrapper(NutsTextFormatTheme other) {
        this.other = other;
    }

    public String getName() {
        return other.getName();
    }

    @Override
    public NutsTextStyles toBasicStyles(NutsTextStyles styles, NutsSession session) {
        NutsTextStyles ret = NutsTextStyles.NONE;
        if (styles != null) {
            for (NutsTextStyle style : styles) {
                ret = ret.append(toBasicStyles(style,session));
            }
        }
        return ret;
    }

    public NutsTextStyles toBasicStyles(NutsTextStyle style,NutsSession session) {
        if (style == null) {
            return NutsTextStyles.NONE;
        }
        if (style.getType().basic()) {
            return NutsTextStyles.of(style);
        }
        NutsTextStyles t = other.toBasicStyles(NutsTextStyles.of(style), session);
        if (t == null) {
            return NutsTextStyles.NONE;
        }
        List<NutsTextStyle> rr = new ArrayList<>();
        for (NutsTextStyle s : t) {
            if (s.getType().basic()) {
                rr.add(s);
            } else {
                //ignore...
            }
        }
        return NutsTextStyles.NONE.append(rr.toArray(new NutsTextStyle[0]));
    }
}
