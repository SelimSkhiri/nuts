package net.thevpc.nuts.runtime.standalone.io.terminal;

import net.thevpc.nuts.NutsSession;
import net.thevpc.nuts.text.NutsTextStyles;
import net.thevpc.nuts.spi.NutsSystemTerminalBase;

public class DefaultSystemTerminal extends AbstractSystemTerminalAdapter {

    private final NutsSystemTerminalBase base;

    public DefaultSystemTerminal(NutsSystemTerminalBase base) {
        this.base = base;
    }

    @Override
    public NutsSystemTerminalBase getBase() {
        return base;
    }

    @Override
    public void setStyles(NutsTextStyles styles, NutsSession session) {
        base.setStyles(styles,session);
    }
}
