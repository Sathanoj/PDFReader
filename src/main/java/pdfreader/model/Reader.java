package pdfreader.model;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Reader {
    private final PDDocument document;
    private final PDFRenderer renderer;
    private int currentPage = 0;
    private float zoom = 1.0f;

    public Reader(String pdfPath) throws IOException {
        document = PDDocument.load(new File(pdfPath));
        renderer = new PDFRenderer(document);
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

    public String getTextMousePosition(int pageIndex, int x, int y) {
        PDPage currentPage2 = document.getPage(pageIndex);
        try {
            PDFTextStripperByArea stripper = new PDFTextStripperByArea();

            Rectangle2D region = new Rectangle2D.Double(x, y, 50, 50);
            stripper.addRegion("mouseLocal ", region);
            stripper.extractRegions(currentPage2);

            System.out.println(stripper.getTextForRegion("mouseLocal"));
            return stripper.getTextForRegion("mouseLocal");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isMouseOver(int currentPage, int mouseX, int mouseY, float zoom) {
        PDPage page = document.getPage(currentPage);
        try {
            PDFTextStripperByArea stripper = new PDFTextStripperByArea();
//            Rectangle2D.Float area = new Rectangle2D.Float((mouseX / zoom), (mouseY / zoom));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
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
}

























