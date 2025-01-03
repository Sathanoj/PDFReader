package pdfreader;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import pdfreader.controller.PdfController;
import pdfreader.model.Reader;
import pdfreader.view.ReaderFrame;
import pdfreader.view.StartFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {

//                StartFrame startFrame = new StartFrame();
//                startFrame.setVisible(true);
                ReaderFrame frame = new ReaderFrame();
                Reader reader = new Reader(new File("Sigmund_Freud_O_Infamiliar.pdf"));
                PdfController test = new PdfController(frame, reader);
//                test.fullSize();
                frame.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error loading PDF: " + e.getMessage());
            }
        });
    }

}

































