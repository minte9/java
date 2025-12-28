/**
 * Clipboard app:
 * Use Ctrl-C to copy text in clipboard
 * The clipboard text will be displayed on runtime.
 */
package com.minte9.io.clipboard;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ClipboardApp {

    public static void main(String[] args) throws InterruptedException {

        StringSelection ss = new StringSelection("abc");
        Clipboard cp = Toolkit.getDefaultToolkit().getSystemClipboard();
        cp.setContents(ss, null);

        while(true) {
            String text = getFromClipboard();
            System.out.println(text);
            Thread.sleep(2000);
        }       
    }

    public static void copyToClipboard(String text) {
        Toolkit.getDefaultToolkit().getSystemClipboard()
            .setContents(new StringSelection(text), null);
    }

    public static String getFromClipboard() {

        Transferable t = 
            Toolkit.getDefaultToolkit().getSystemClipboard()
                .getContents(null);

        try {
            if (t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                return t.getTransferData(DataFlavor.stringFlavor).toString();
            }
        } catch (UnsupportedFlavorException | IOException ex) {}

        return null;
    }
}
