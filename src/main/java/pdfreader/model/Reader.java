package pdfreader.model;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
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


    public BufferedImage renderPdfAt100Percent(int pageIndex, int frameWidth, int frameHeight) throws IOException {
        PDRectangle pageSize = document.getPage(pageIndex).getMediaBox();
        float widthInPoints = pageSize.getWidth();
        float heightInPoints = pageSize.getHeight();

        // Conversão de pontos para pixels, assumindo 72 pontos por polegada
        float widthInInches = widthInPoints / 72;
        float heightInInches = heightInPoints / 72;

        // Calcula o DPI necessário para ajustar a página ao tamanho da janela
        float widthDPI = frameWidth / widthInInches;
        float heightDPI = frameHeight / heightInInches;
        float dpi = Math.min(widthDPI, heightDPI);

        // Limita o DPI a um valor máximo prático
        dpi = Math.min(dpi, 300);

        // Renderiza o PDF
        PDFRenderer renderer = new PDFRenderer(document);
        return renderer.renderImageWithDPI(pageIndex, dpi);
    }



    public int getTotalPages() {
        return document.getNumberOfPages();
    }

    public void close() throws IOException {
        document.close();
    }
}

























