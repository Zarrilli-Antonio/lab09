package it.unibo.mvc;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {
    private static final String PRINT = "Print";
    private static final String SHOW_HISTORY = "Show History";
    private final JFrame frame = new JFrame();
    private final Controller controller;

    public SimpleGUI(final Controller controller) {
        this.controller = controller;
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        final JTextField textField = new JTextField();
        panel.add(textField, BorderLayout.NORTH);
        final JTextArea textArea = new JTextArea();
        panel.add(textArea, BorderLayout.CENTER);
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        final JButton printButton = new JButton(PRINT);
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                SimpleGUI.this.controller.setNextString(textField.getText());
                SimpleGUI.this.controller.printString();
                textField.setText("");
            }
        });
        buttonPanel.add(printButton);
        final JButton showHistoryButton = new JButton(SHOW_HISTORY);
        showHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final List<String> history = SimpleGUI.this.controller.getPrintedStrings();
                textArea.setText("");
                for (final String s : history) {
                    textArea.append(s + "\n");
                }
            }
        });
        buttonPanel.add(showHistoryButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        this.frame.setContentPane(panel);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setLocationByPlatform(true);
    }

    private void display() {
        frame.setVisible(true);
    }

    /**
     * 
     * @param args
     *            ignored
     */
    public static void main(final String[] args) {
        new SimpleGUI(new SimpleController()).display();
    }

}
