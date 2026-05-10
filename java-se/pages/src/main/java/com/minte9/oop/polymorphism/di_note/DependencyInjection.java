package com.minte9.oop.polymorphism.di_note;

public class DependencyInjection {
    public static void main(String[] args) {
        Item csv = new Csv();
        Item xml = new Xml();

        FileOpener csvOpener = new FileOpener(csv);
        FileOpener xmlOpener = new FileOpener(xml);

        csvOpener.open();  // CSV opened
        xmlOpener.open();  // XML opened
       
    }
}

abstract class Item {
    public abstract void open();
}

class Csv extends Item {
    @Override
    public void open() {
        System.out.println("CSV opened");
    }
}

class Xml extends Item {
    @Override
    public void open() {
        System.out.println("XML opened");
    }
}

class FileOpener {
    private final Item item;  // dependency

    // Dependency Injection via constructor
    public FileOpener(Item item) {
        this.item = item;
    }

    public void open() {
        item.open();
    }
}