package pdfreader.view;

import pdfreader.controller.PdfController;
import pdfreader.model.Reader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class StartFrame extends JFrame {

    public StartFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(300, 200));
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        JButton chooseButton = new JButton("choose");

        chooseButton.setBounds(100,50,100,40);
        this.add(chooseButton);


        chooseButton.addActionListener(e -> choosePDF());
    }


    public void choosePDF() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("test");
        int result = chooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File file = chooser.getSelectedFile();
                ReaderFrame frame = new ReaderFrame();
                Reader reader = new Reader(file);
                PdfController test = new PdfController(frame, reader);
//                test.fullSize();
                frame.setVisible(true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
