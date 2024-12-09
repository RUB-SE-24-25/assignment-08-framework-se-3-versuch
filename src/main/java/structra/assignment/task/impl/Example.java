package structra.assignment.task.impl;

import structra.assignment.framework.llm.KeyProvider;
import structra.assignment.framework.llm.model.Mimic;
import structra.assignment.framework.provide.ModelQuestionProvider;

import java.awt.*;

import javax.swing.*;

public class Example{

    /**
     * Create the GUI and show it. For thread safety, this method should be invoked from the
     * event-dispatching thread.
     */
    KeyProvider keyProvider = new keyProviderImplementation();
    Mimic mimic = new Mimic(keyProvider);
    ModelQuestionProvider modelQuestionProvider = new ModelQuestionProvider();
    private static void createAndShowGUI() {
        // Create and set up the window
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add the "Hello World" label to the center of the window
        JLabel label = new JLabel("Hello World", SwingConstants.CENTER);
        frame.getContentPane().add(label);

        //Button

        JButton button = new JButton("next",modelQuestionProvider.next());
        // Adjust position of the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        int frameWidth = (int) (width / 2);
        int frameHeight = (int) (height / 4);
        frame.setLocation((int) (width - frameWidth) / 2, (int) (height - frameHeight) / 2);
        frame.setSize(frameWidth, frameHeight);

        // Display the window
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(Example::createAndShowGUI);
    }

    @Override
    public String getApiKey() {
        return "structra-1343abnc-dGhpcyBpcyBub3Qgb3VyIGFwaSBrZXksIG5pY2UgdHJ5IHRobyA6KQ==";
    }
}
