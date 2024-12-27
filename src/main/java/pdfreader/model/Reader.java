package pdfreader.model;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.rendering.PDFRenderer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Reader {
    private final PDDocument document;
    private final PDFRenderer renderer;
    private int currentPage = 0;
    private float zoom = 1.0f;
    private boolean isFullSize;


    public Reader(File pdfPath) throws IOException {
        document = PDDocument.load(pdfPath);
        renderer = new PDFRenderer(document);
        isFullSize = false;
    }

    public BufferedImage renderPage(int pageIndex, float dpi) throws IOException {
        return renderer.renderImageWithDPI(pageIndex, dpi);
    }

    public BufferedImage renderPdfAt100Percent(int pageIndex, int frameWidth, int frameHeight) throws IOException {
        PDRectangle pageSize = document.getPage(pageIndex).getMediaBox();
        float widthInPoints = pageSize.getWidth();
        float heightInPoints = pageSize.getHeight();

        float widthInInches = widthInPoints / 72;
        float heightInInches = heightInPoints / 72;

        float widthDPI = frameWidth / widthInInches;
        float heightDPI = frameHeight / heightInInches;
        float dpi = Math.min(widthDPI, heightDPI);

        dpi = Math.min(dpi, 300);

        PDFRenderer renderer = new PDFRenderer(document);

        return renderer.renderImageWithDPI(pageIndex, dpi);
    }

    public int findPage(int newPage) {
        currentPage = newPage;
        return currentPage;
    }

    public double getReadingProgress() {
        int totalPages = getTotalPages();
        return (currentPage / (double) totalPages) * 100;

//        if (currentPage < 1 || currentPage > totalPages) {
//
//        }
    }

    public void pagePP() {
        currentPage ++;
    }

    public void pageLL() {
        currentPage --;
    }

    public void zoomPP() {
        zoom += 0.2f;
    }

    public void zoomLL() {
        zoom -= 0.2f;
    }

    public int getTotalPages() {
        if (document == null) {
            throw new IllegalStateException("Document not found");
        }
        return document.getNumberOfPages();
    }

    public void close() throws IOException {
        document.close();
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public float getZoom() {
        return zoom;
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }

    public boolean isFullSize() {
        return isFullSize;
    }

    public void setFullSize(boolean fullSize) {
        isFullSize = fullSize;
    }
}

























