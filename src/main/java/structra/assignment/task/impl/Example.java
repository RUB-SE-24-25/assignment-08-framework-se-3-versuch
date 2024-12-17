package structra.assignment.task.impl;

import structra.assignment.framework.llm.KeyProvider;
import structra.assignment.framework.llm.gen.questions.OpenQuestionTarget;
import structra.assignment.framework.llm.gen.questions.RandomTargetProvider;
import structra.assignment.framework.llm.gen.questions.TargetProvider;
import structra.assignment.framework.llm.model.Mimic;
import structra.assignment.framework.model.question.base.Question;
import structra.assignment.framework.provide.ModelQuestionProvider;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import javax.swing.*;

public class Example{

    /**
     * Create the GUI and show it. For thread safety, this method should be invoked from the
     * event-dispatching thread.
     */
    private static JLabel questionLabel;
    private static ModelQuestionProvider modelQuestionProvider;
    private static CompletableFuture<Question<?>> future;

    private static void createAndShowGUI() {
        //create Model and CompletableFuture
        KeyProvider keyProvider = new KeyProviderImplementation();
        Mimic mimic = new Mimic(keyProvider);
        TargetProvider provider = new RandomTargetProvider(new OpenQuestionTarget(Mimic.MULTIPLE_CHOICE));
        modelQuestionProvider = new ModelQuestionProvider(mimic, provider, new ArrayList<>());

        future = modelQuestionProvider.next();

        // Create and set up the window
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        questionLabel = new JLabel("Loading question...");
        frame.getContentPane().add(questionLabel, BorderLayout.CENTER);
        //Button
        JButton button = new JButton("Next");
        button.addActionListener(e -> SwingUtilities.invokeLater(Example::loadNextQuestion));
        frame.getContentPane().add(button, BorderLayout.SOUTH);

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

        // Load first question
        loadNextQuestion();
    }

    private static void loadNextQuestion() {
        if (modelQuestionProvider == null) {
            JOptionPane.showMessageDialog(null, "Model not initialised!");
            return;
        }
        future = modelQuestionProvider.next();

        future.thenAccept(question -> SwingUtilities.invokeLater(() -> {
            if (question != null) {
                questionLabel.setText("Question Text: " + question.getText());
            } else {
                questionLabel.setText("No Question found.");
            }
        })).exceptionally(ex -> {
            SwingUtilities.invokeLater(() -> questionLabel.setText("Error: " + ex.getMessage()));
            return null;
        });
    }



    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(Example::createAndShowGUI);
    }

}
