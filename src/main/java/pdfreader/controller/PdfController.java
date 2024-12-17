package pdfreader.controller;

import pdfreader.model.Reader;
import pdfreader.view.MainFrame;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PdfController {
    private final MainFrame view;
    private final Reader model;
    private int currentPage = 0;
    private float zoom = 1.0f;

    public PdfController(MainFrame view, Reader model) {
        this.view = view;
        this.model = model;
        view.getFullSize().addActionListener(e -> fullSize());
        view.getNextButton().addActionListener(e -> nextPage());
        view.getPreviousButton().addActionListener(e -> previousPage());
        view.getZoomInButton().addActionListener(e -> zoomIn());
        view.getZoomOutButton().addActionListener(e -> zoomOut());

        updatePage();
    }

    private void fullSize() {
        try {
            BufferedImage resetFullSize = model.renderPdfAt100Percent(currentPage, view.getWidth(), view.getHeight());
            view.getPdfLabel().setIcon(new ImageIcon(resetFullSize));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void nextPage() {
        if (currentPage < model.getTotalPages() - 1) {
            currentPage++;
            updatePage();
        }
    }

    private void previousPage() {
        if (currentPage > 0) {
            currentPage--;
            updatePage();
        }
    }

    private void zoomIn() {
        zoom += 0.2f;
        updatePage();
    }

    private void zoomOut() {
        if (zoom > 0.4f) { // Evita zoom muito pequeno
            zoom -= 0.2f;
            updatePage();
        }
    }

    private void updatePage() {
        try {
            BufferedImage pageImage = model.renderPage(currentPage, 150 * zoom);
            view.getPdfLabel().setIcon(new ImageIcon(pageImage));
            System.out.println("Zoom value: " + zoom);
            System.out.println("Width: " + view.getWidth() + " Height: " + view.getHeight());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(view, "Error rendering page: " + e.getMessage());
        }
    }
}

