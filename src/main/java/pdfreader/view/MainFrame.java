package pdfreader.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final JScrollPane scrollPane;
    private final JLabel pdfLabel;
    private final JButton nextButton,
                        previousButton,
                        zoomInButton,
                        zoomOutButton,
                        fullSize,
                        jumpToPageButton;
    private final JTextField currentPageFrame;

    public MainFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(707, 1028));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        pdfLabel = new JLabel();
        scrollPane = new JScrollPane(pdfLabel);
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        nextButton = new JButton("Next");
        previousButton = new JButton("Previous");
        zoomInButton = new JButton("Zoom +");
        zoomOutButton = new JButton("Zoom -");
        fullSize = new JButton("full");
        currentPageFrame = new JTextField(5);
        jumpToPageButton = new JButton("jumpTo");

        buttonPanel.add(fullSize);
        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(zoomInButton);
        buttonPanel.add(zoomOutButton);
        buttonPanel.add(currentPageFrame);
        buttonPanel.add(jumpToPageButton);

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

    public JButton getJumpToPageButton() {
        return jumpToPageButton;
    }

    public JTextField getCurrentPageFrame() {
        return currentPageFrame;
    }
}
