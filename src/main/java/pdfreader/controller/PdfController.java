package pdfreader.controller;

import pdfreader.model.DocInformation;
import pdfreader.model.Reader;
import pdfreader.view.MainFrame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PdfController {
    private final MainFrame view;
    private final Reader model;
    private DocInformation information;

    public PdfController(MainFrame view, Reader model) {
        this.view = view;
        this.model = model;
        information = new DocInformation(model);

        view.getFullSize().addActionListener(e -> fullSize());
        view.getNextButton().addActionListener(e -> nextPage());
        view.getPreviousButton().addActionListener(e -> previousPage());
        view.getZoomInButton().addActionListener(e -> zoomIn());
        view.getZoomOutButton().addActionListener(e -> zoomOut());
        view.getJumpToPageButton().addActionListener(e -> changePageTo());
        changePage();
        updatePage();
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

    public void changePageTo() {
//        model.setCurrentPage(4);
        try {
            String getValue = view.getCurrentPageFrame().getText();
            model.findPage(Integer.parseInt(getValue));
            updatePage();

        } catch (NumberFormatException exception) {
            System.out.println("deu ruim: " + exception);
        }


//        int value = model.findPage(v2);
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

    ///////////////////////////////////////////////////////////////////////////////////////
    private void updatePage() {
        try {
            BufferedImage pageImage = model.renderPage(model.getCurrentPage(), 150 * model.getZoom());
            view.getPdfLabel().setIcon(new ImageIcon(pageImage));

            information.showDocumentInfo();
            System.out.println();
            System.out.println("Zoom value: " + model.getZoom());
            System.out.println("Width: " + view.getWidth() + " Height: " + view.getHeight());
            System.out.println("----------------------------------------------------------");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(view, "Error rendering page: " + e.getMessage());
        }
    }
}