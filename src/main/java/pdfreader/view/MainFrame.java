package pdfreader.view;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MainFrame extends JFrame {
    private final JScrollPane scrollPane;
    private final JLabel pdfLabel;
    private final JButton nextButton,
                    previousButton,
                    zoomInButton,
                    zoomOutButton,
                    fullSize;

    public MainFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(628, 959));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        pdfLabel = new JLabel();
        scrollPane = new JScrollPane(pdfLabel);
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        nextButton = new JButton("Next");
        previousButton = new JButton("Previous");
        zoomInButton = new JButton("Zoom In");
        zoomOutButton = new JButton("Zoom Out");
        fullSize = new JButton("full");
        buttonPanel.add(fullSize);
        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(zoomInButton);
        buttonPanel.add(zoomOutButton);

        this.add(buttonPanel, BorderLayout.SOUTH);
    }




    public JButton getNextButton() {
        return nextButton;
    }

    public JButton getPreviousButton() {
        return previousButton;
    }

    public JButton getZoomInButton() {
        return zoomInButton;
    }

    public JButton getZoomOutButton() {
        return zoomOutButton;
    }

    public JButton getFullSize() {
        return fullSize;
    }

    public JLabel getPdfLabel() {
        return pdfLabel;
    }
}
