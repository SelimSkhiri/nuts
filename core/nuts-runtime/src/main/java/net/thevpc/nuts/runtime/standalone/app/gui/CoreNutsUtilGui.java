package net.thevpc.nuts.runtime.standalone.app.gui;

import net.thevpc.nuts.Nuts;
import net.thevpc.nuts.NutsMessage;
import net.thevpc.nuts.NutsSession;
import net.thevpc.nuts.text.NutsTexts;

import javax.swing.*;

public final class CoreNutsUtilGui {

    public static boolean isGraphicalDesktopEnvironment() {
        try {
            if (java.awt.GraphicsEnvironment.isHeadless()) {
                return false;
            }
            try {
                java.awt.GraphicsDevice[] screenDevices = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
                if (screenDevices == null || screenDevices.length == 0) {
                    return false;
                }
            } catch (java.awt.HeadlessException e) {
                return false;
            }
            return true;
        } catch (UnsatisfiedLinkError e) {
            //exception may occur if the sdk is built in headless mode
            return false;
        } catch (Exception e) {
            //exception may occur if the sdk is built without awt package for instance!
            return false;
        }
    }

    public static String inputString(NutsMessage message, NutsMessage title, NutsSession session) {
        try {
            NutsTexts text = NutsTexts.of(session);
            if (title == null) {
                title = NutsMessage.ofCstyle("Nuts Package Manager - %s", Nuts.getVersion());
            }
            String line = javax.swing.JOptionPane.showInputDialog(
                    null,
                    text.ofText(message).filteredText(), text.ofText(title).filteredText(), javax.swing.JOptionPane.QUESTION_MESSAGE
            );
            if (line == null) {
                line = "";
            }
            return line;
        } catch (UnsatisfiedLinkError e) {
            //exception may occur if the sdk is built in headless mode
            session.err().printf("[Graphical Environment Unsupported] %s%n", title);
            return session.getTerminal().readLine(message.toString());
        }
    }

    public static String inputPassword(NutsMessage message, NutsMessage title, NutsSession session) {
        if (title == null) {
            title = NutsMessage.ofCstyle("Nuts Package Manager - %s", Nuts.getVersion());
        }
        if (message == null) {
            message = NutsMessage.ofPlain("");
        }
        NutsTexts text = NutsTexts.of(session);
        String messageString = text.ofText(message).filteredText();
        String titleString = text.ofText(title).filteredText();
        try {
            javax.swing.JPanel panel = new javax.swing.JPanel();
            javax.swing.JLabel label = new javax.swing.JLabel(messageString);
            javax.swing.JPasswordField pass = new javax.swing.JPasswordField(10);
            panel.add(label);
            panel.add(pass);
            String[] options = new String[]{"OK", "Cancel"};
            int option = javax.swing.JOptionPane.showOptionDialog(null, panel, titleString,
                    javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.PLAIN_MESSAGE,
                    null, options, options[1]);
            if (option == 0) {
                return new String(pass.getPassword());
            }
            return "";
        } catch (UnsatisfiedLinkError e) {
            //exception may occur if the sdk is built in headless mode
            session.err().printf("[Graphical Environment Unsupported] %s%n", title);
            return session.getTerminal().readLine(message.toString());
        }
    }

    public static void showMessage(NutsMessage message, NutsMessage title, NutsSession session) {
        if (title == null) {
            title = NutsMessage.ofCstyle("Nuts Package Manager - %s", Nuts.getVersion());
        }
        NutsTexts text = NutsTexts.of(session);
        String messageString = text.ofText(message==null?"":message).filteredText();
        String titleString = text.ofText(title).filteredText();
        try {
            javax.swing.JOptionPane.showMessageDialog(null, messageString, titleString, JOptionPane.QUESTION_MESSAGE);
        } catch (UnsatisfiedLinkError e) {
            //exception may occur if the sdk is built in headless mode
            session.err().printf("[Graphical Environment Unsupported] %s%n", title);
        }
    }
}
