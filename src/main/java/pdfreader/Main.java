package pdfreader;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import pdfreader.controller.PdfController;
import pdfreader.model.Reader;
import pdfreader.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                MainFrame frame = new MainFrame();
                Reader reader = new Reader("10 Livro Estrutura de Dados.pdf");
                new PdfController(frame, reader);
                frame.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error loading PDF: " + e.getMessage());
            }
        });
    }
















    private void createAndShowGUI() throws IOException {
        // Load the PDF file
        File pdfFile = new File("10 Livro Estrutura de Dados.pdf"); // Change this to your PDF file path
        PDDocument document = PDDocument.load(pdfFile);

        // Create the PDF renderer
        PDFRenderer renderer = new PDFRenderer(document);

        // Render the first page of the PDF as an image
        BufferedImage pageImage = renderer.renderImageWithDPI(0, 150); // Adjust DPI for quality

        // Display the image in a JFrame
        JFrame frame = new JFrame("PDF Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800, 600));

        // Create a JLabel to hold the image
        JLabel pdfLabel = new JLabel(new ImageIcon(pageImage));
        JScrollPane scrollPane = new JScrollPane(pdfLabel);

        frame.getContentPane().add(scrollPane);
        frame.setVisible(true);

        // Close the document when done
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                try {
                    document.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
