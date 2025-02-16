package com.example.paint;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * FileHandler provides static methods to save and open images.
 * It uses JFileChooser to let users select the location and file format.
 */
public class FileHandler {
    /**
     * Saves the given BufferedImage to a file.
     * Supports PNG and JPG formats.
     */
    public static void saveImage(BufferedImage image, Component parent) {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(parent);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                // Determine file extension from the filename; default to PNG.
                String fileName = file.getName().toLowerCase();
                String format = "png";
                if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
                    format = "jpg";
                }
                if (!fileName.contains(".")) {
                    file = new File(file.getAbsolutePath() + "." + format);
                }
                ImageIO.write(image, format, file);
                JOptionPane.showMessageDialog(parent, "Image saved successfully.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(parent, "Error saving image: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Opens an image file and returns it as a BufferedImage.
     */
    public static BufferedImage openImage(Component parent) {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(parent);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                return ImageIO.read(file);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(parent, "Error opening image: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }
}
