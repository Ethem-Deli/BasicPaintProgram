package com.example.paint;


import javax.swing.*;

/**
 * Main class to launch the Basic Paint Program.
 * This creates a JFrame with a tabbed pane including:
 * - Paint (drawing tools)
 * - Tracing Pad (for tracing images)
 * - Lessons (drawing instructions)
 * - Videos (embedded video player)
 */
public class BasicPaintProgram {
    public static void main(String[] args) {
        // Ensure UI creation runs on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Basic Paint Program");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            // Create tabbed pane and add each feature as a separate tab
            JTabbedPane tabbedPane = new JTabbedPane();

            // Paint tab: Core drawing features with file operations
            PaintPanel paintPanel = new PaintPanel();
            tabbedPane.addTab("Paint", paintPanel);

            // Tracing Pad tab: Preloaded background image for tracing
            TracingPadPanel tracingPadPanel = new TracingPadPanel();
            tabbedPane.addTab("Tracing Pad", tracingPadPanel);

            // Lessons tab: Step-by-step drawing lessons for kids
            LessonsPanel lessonsPanel = new LessonsPanel();
            tabbedPane.addTab("Lessons", lessonsPanel);

            // Videos tab: Embedded media player to play educational videos
            VideoPlayerPanel videoPlayerPanel = new VideoPlayerPanel();
            tabbedPane.addTab("Videos", videoPlayerPanel);

            frame.add(tabbedPane);
            frame.setLocationRelativeTo(null); // Center the frame
            frame.setVisible(true);
        });
    }
}
