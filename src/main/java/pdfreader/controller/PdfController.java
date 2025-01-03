package pdfreader.controller;

import pdfreader.model.DocInformation;
import pdfreader.model.Reader;
import pdfreader.view.ReaderFrame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.BufferOverflowException;

public class PdfController {
    private final ReaderFrame readerFrame;
    private final Reader reader;
    private final DocInformation information;
    private boolean isDefaultZoom;

    public PdfController(ReaderFrame view, Reader model) {
        this.readerFrame = view;
        this.reader = model;
        information = new DocInformation(model);
        isDefaultZoom = true;

        view.getCheckBox().addActionListener(e -> fullSize());
        view.getJumpToPageButton().addActionListener(e -> changePageTo());
        view.getNextButton().addActionListener(e -> nextPage());
        view.getPreviousButton().addActionListener(e -> previousPage());
        changePage();
        zoomListener();
        updatePage();
    }

    private void zoomListener() {
        readerFrame.getZoomInButton().addActionListener(e -> {
            isDefaultZoom = false;
            zoomIn();
        });
        readerFrame.getZoomOutButton().addActionListener(e -> {
            isDefaultZoom = false;
            zoomOut();
        });
    }

    public void changePage() {
        readerFrame.getPdfLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickX = e.getX();
                int labelWidth = readerFrame.getPdfLabel().getWidth();
                if (clickX > labelWidth / 2) {
                    nextPage();
                } else {
                    previousPage();
                }
            }
        });
    }

    public void fullSize() {
            try {
                BufferedImage resetFullSize =
                        reader.renderPdfAt100Percent(
                                reader.getCurrentPage(),
                                readerFrame.getWidth(),
                                readerFrame.getHeight());
                readerFrame.getPdfLabel().setIcon(new ImageIcon(resetFullSize));
                System.out.println(readerFrame.getCheckBox().isSelected());


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    private void nextPage() {
        if (reader.getCurrentPage() < reader.getTotalPages() - 1) {
            reader.pagePP();
            updatePage();
        }
    }

    private void previousPage() {
        if (reader.getCurrentPage() > 0) {
            reader.pageLL();
            updatePage();
        }
    }

    private void zoomIn() {
        reader.zoomPP();
        updatePage();
    }

    private void zoomOut() {
        if (reader.getZoom() > 0.4f) { // Evita zoom muito pequeno
            reader.zoomLL();
            updatePage();
        }
    }

    public void changePageTo() {
        try {
            String getValue = readerFrame.getCurrentPageFrame().getText();
            reader.findPage(Integer.parseInt(getValue));
            updatePage();

        } catch (NumberFormatException exception) {
            System.out.println("choose a real number: " + exception);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    private void updatePage() {

        try {
            if (readerFrame.getCheckBox().isSelected()) {
                BufferedImage fullSize = reader.renderPdfAt100Percent(
                        reader.getCurrentPage(),
                        readerFrame.getWidth(),
                        readerFrame.getHeight());
                readerFrame.getPdfLabel().setIcon(new ImageIcon(fullSize));
                information.showDocumentInfo();
                System.out.println("--------- PDF on full page size ---------------");


                System.out.println("Zoom value: " + reader.getZoom());
                System.out.println("Width: " + readerFrame.getWidth() + " Height: " + readerFrame.getHeight());
                System.out.println("----------------------------------------------------------");
            } else {
                System.out.println("--------- Unchecked box to zoom edit -----------");
                BufferedImage pageImage = reader.renderPage(reader.getCurrentPage(), 150 * reader.getZoom());
                readerFrame.getPdfLabel().setIcon(new ImageIcon(pageImage));

                information.showDocumentInfo();
                System.out.println();
                System.out.println("Zoom value: " + reader.getZoom());
                System.out.println("Width: " + readerFrame.getWidth() + " Height: " + readerFrame.getHeight());
                System.out.println("----------------------------------------------------------");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(readerFrame, "Error rendering page: " + e.getMessage());
        }
    }
}