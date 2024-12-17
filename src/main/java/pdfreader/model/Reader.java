package pdfreader.model;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Reader {
    private PDDocument document;
    private PDFRenderer renderer;

    public Reader(String pdfPath) throws IOException {
        document = PDDocument.load(new File(pdfPath));
        renderer = new PDFRenderer(document);
    }

    public BufferedImage renderPage(int pageIndex, float dpi) throws IOException {
        return renderer.renderImageWithDPI(pageIndex, dpi);
    }

    public int getTotalPages() {
        return document.getNumberOfPages();
    }

    public void close() throws IOException {
        document.close();
    }
}

























