package pdfreader.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final JScrollPane scrollPane;
    private final JLabel pdfLabel;
    private final JButton nextButton,
                    previousButton,
                    zoomInButton,
                    zoomOutButton;

    public MainFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(600, 900));
        this.setLayout(new BorderLayout());

        pdfLabel = new JLabel();
        scrollPane = new JScrollPane(pdfLabel);
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        nextButton = new JButton("Next");
        previousButton = new JButton("Previous");
        zoomInButton = new JButton("Zoom In");
        zoomOutButton = new JButton("Zoom Out");
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

    public JLabel getPdfLabel() {
        return pdfLabel;
    }
}
