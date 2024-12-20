package pdfreader.controller;

import pdfreader.model.Reader;
import pdfreader.view.MainFrame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PdfController {
    private BufferedImage pageImage;
    private final MainFrame view;
    private final Reader model;

    public PdfController(MainFrame view, Reader model) {
        this.view = view;
        this.model = model;

        changePage();

        view.getFullSize().addActionListener(e -> fullSize());
        view.getNextButton().addActionListener(e -> nextPage());
        view.getPreviousButton().addActionListener(e -> previousPage());
        view.getZoomInButton().addActionListener(e -> zoomIn());
        view.getZoomOutButton().addActionListener(e -> zoomOut());

        updatePage();
//        enableTextSelection();
    }

    private void changePage() {
        view.getPdfLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickX = e.getX();
                int labelWidth = view.getPdfLabel().getWidth();
                if (clickX < labelWidth / 2) {
                    previousPage();
                } else {
                    nextPage();
                }
            }
        });
    }

    private void fullSize() {
        try {
            BufferedImage resetFullSize =
                    model.renderPdfAt100Percent(
                            model.getCurrentPage(),
                            view.getWidth(),
                            view.getHeight());
            view.getPdfLabel().setIcon(new ImageIcon(resetFullSize));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void nextPage() {
        if (model.getCurrentPage() < model.getTotalPages() - 1) {
            model.pagePP();
            updatePage();
        }
    }

    private void previousPage() {
        if (model.getCurrentPage() > 0) {
            model.pageLL();
            updatePage();
        }
    }

    private void zoomIn() {
        model.zoomPP();
        updatePage();
    }

    private void zoomOut() {
        if (model.getZoom() > 0.4f) { // Evita zoom muito pequeno
            model.zoomLL();
            updatePage();
        }
    }

    private void enableTextSelection() {
        view.getPdfLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent event) {
                int mouseX = event.getX();
                int mouseY = event.getY();
//                boolean isOverTex = model.isMouseOver(model.getCurrentPage(), mouseX, mouseY, zoom);

            }
        });
    }

    private void showDocumentInfo() {
        int totalPages = model.getTotalPages();
//        int currentPage2 = this.currentPage; // Supondo que o controlador rastreia a página atual
//        double progress = model.getReadingProgress(currentPage2);

        System.out.println("Total Pages: " + totalPages);
//        System.out.printf("Reading Progress: %.2f%%%n", progress);
    }


    ///////////////////////////////////////////////////////////////////////////////////////
    private void updatePage() {
        try {
            pageImage = model.renderPage(model.getCurrentPage(), 150 * model.getZoom());
            view.getPdfLabel().setIcon(new ImageIcon(pageImage));

            System.out.println("Zoom value: " + model.getZoom());
            System.out.println("Width: " + view.getWidth() + " Height: " + view.getHeight());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(view, "Error rendering page: " + e.getMessage());
        }
    }
}

