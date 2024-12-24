package pdfreader.model;

import pdfreader.view.MainFrame;

public class DocInformation {

    private final Reader reader;
    private MainFrame frame;

    public DocInformation(Reader reader, MainFrame frame) {
        this.reader = reader;
        this.frame = frame;
    }

    public void showDocumentInfo() {
        System.out.println("Total Pages: " + reader.getTotalPages());
        System.out.println("current page: " + reader.getCurrentPage());
        System.out.printf("Reading Progress: %.2f%%%n", reader.getReadingProgress());
    }
}
