package com.example.paint;

import javax.swing.*;
import java.awt.*;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.*;
import javafx.scene.media.*;
import java.io.File;
import java.util.HashMap;

/**
 * VideoPlayerPanel allows selecting and playing multiple videos using JavaFX's
 * MediaPlayer.
 */
public class VideoPlayerPanel extends JPanel {
    private JFXPanel jfxPanel;
    private MediaPlayer mediaPlayer;
    private JComboBox<String> videoSelector;
    private HashMap<String, String> videoPaths; // Stores video titles and file paths

    public VideoPlayerPanel() {
        setLayout(new BorderLayout());
        jfxPanel = new JFXPanel(); // JavaFX content panel
        add(jfxPanel, BorderLayout.CENTER);

        // Initialize video list
        initializeVideoList();

        // Create a dropdown for video selection
        videoSelector = new JComboBox<>(videoPaths.keySet().toArray(new String[0]));
        videoSelector.addActionListener(e -> playSelectedVideo());
        add(videoSelector, BorderLayout.NORTH); // Add dropdown at the top

        // Initialize JavaFX and play the first video by default
        Platform.runLater(() -> playSelectedVideo());
    }

    // Initialize available videos
    private void initializeVideoList() {
        videoPaths = new HashMap<>();
        videoPaths.put("Lesson 1", "D:/BYU/cse310/BasicPaintProgram/resources/1.mp4");
        videoPaths.put("Faceless Video (15)", "D:/BYU/cse310/BasicPaintProgram/resources/Faceless Video (15).mp4");
        videoPaths.put("Sample Video", "D:/BYU/cse310/BasicPaintProgram/resources/paint.mp4");
        videoPaths.put("Lesson 2", "D:/BYU/cse310/BasicPaintProgram/resources/2.mp4");
        videoPaths.put("Lesson 3", "D:/BYU/cse310/BasicPaintProgram/resources/3.mp4");
    }

    // Play the selected video
    private void playSelectedVideo() {
        String selectedVideo = (String) videoSelector.getSelectedItem();
        if (selectedVideo != null) {
            String videoPath = videoPaths.get(selectedVideo);
            playVideo(videoPath);
        }
    }

    // Play video from the given file path
    private void playVideo(String filePath) {
        Platform.runLater(() -> {
            try {
                if (mediaPlayer != null) {
                    mediaPlayer.stop(); // Stop any currently playing video
                }

                File videoFile = new File(filePath);
                String videoURI = videoFile.toURI().toString();
                Media media = new Media(videoURI);
                mediaPlayer = new MediaPlayer(media);
                MediaView mediaView = new MediaView(mediaPlayer);
                mediaView.setFitWidth(800);
                mediaView.setFitHeight(600);

                Group root = new Group();
                Scene scene = new Scene(root, 800, 600);
                root.getChildren().add(mediaView);

                jfxPanel.setScene(scene);
                mediaPlayer.play();
            } catch (Exception ex) {
                SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(this,
                        "Video file not found or cannot be played.", "Error", JOptionPane.ERROR_MESSAGE));
            }
        });
    }
}
