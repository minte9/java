/**
 * Add bullets:
 * Get text from clipboard and add bullets to every line.
 * Use Ctrl-C to copy text in clipboard
 * The clipboard text will be displayed on runtime.
 */
package com.minte9.io.clipboard;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.Arrays.asList;

public class BulletsApp {

    public static void main(String[] args) throws InterruptedException {

        CP.copyToClipboard(" abc \n def");

        List<String> A;
        String text;

        while (true) {
            text = CP.getFromClipboard();

            A = asList(text.split("\n"));
            text = A.stream()
                    .map(x -> "* " + x)
                    .collect(Collectors.joining("\n"));
            System.out.println(text);
            Thread.sleep(2000);
        }
    }
}

class CP {
    public static void copyToClipboard(String text) {
        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(new StringSelection(text), null);
    }

    public static String getFromClipboard() {

        Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard()
                .getContents(null);

        try {
            if (t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                return t.getTransferData(DataFlavor.stringFlavor).toString();
            }
        } catch (UnsupportedFlavorException | IOException ex) {
        }

        return null;
    }
}