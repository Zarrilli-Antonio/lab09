package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JFileChooser;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private final JFrame frame = new JFrame();

    private SimpleGUIWithFileChooser(final Controller controller) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel panel = new JPanel();
        final LayoutManager layout = new BorderLayout();
        final JTextArea textArea = new JTextArea();
        
        panel.setLayout(layout);
        //Save button
        final JButton save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.save(textArea.getText());
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(frame, "Cannot save the file.");
                }
            }
        });

        panel.add(textArea, BorderLayout.CENTER);
        panel.add(save, BorderLayout.SOUTH);

        // Add the file chooser
        final JTextField filePath = new JTextField(controller.getFilePath());
        final JButton fileChooser = new JButton("Choose file");
        fileChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final String file = JOptionPane.showInputDialog(frame, "Insert the file path", controller.getFilePath());
                final JFileChooser fileChooser = new JFileChooser();
                fileChooser.setSelectedFile(controller.getDest());
                final int result = fileChooser.showSaveDialog(frame);
                switch (result){
                    case JFileChooser.APPROVE_OPTION:
                        filePath.setText(file);
                        controller.setDest(fileChooser.getSelectedFile());
                        break;
                    case JFileChooser.CANCEL_OPTION:
                        break;
                }
            }
        });

        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.add(filePath, BorderLayout.CENTER);
        panel2.add(fileChooser, BorderLayout.EAST);
        panel.add(panel2, BorderLayout.NORTH);

        frame.setContentPane(panel);
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 8, sh / 8);
        frame.setLocationByPlatform(true);
    }

    private void display() {
        frame.setVisible(true);
    }

    public static void main(final String... args) {
        final Controller controller = new Controller();
        new SimpleGUIWithFileChooser(controller).display();
    }

}
