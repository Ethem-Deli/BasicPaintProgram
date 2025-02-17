package com.example.paint;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * TracingPadPanel provides a drawing area with a preloaded background image.
 */
public class TracingPadPanel extends JPanel {
    private BufferedImage backgroundImage;
    private BufferedImage canvas;
    private Graphics2D g2d;
    private Point lastPoint;
    private Color currentColor = Color.BLUE;
    private int brushSize = 5;

    public TracingPadPanel() {
        setLayout(new BorderLayout());
        loadBackgroundImage();
        initCanvas();
        initToolbar();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastPoint = e.getPoint();
                drawLine(lastPoint, lastPoint);
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point currentPoint = e.getPoint();
                drawLine(lastPoint, currentPoint);
                lastPoint = currentPoint;
            }
        });
    }

    // Load a background image from the resources folder.
    private void loadBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(new File("resources/tracing_background.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Background image not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Initialize the drawing canvas.
    private void initCanvas() {
        canvas = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        g2d = canvas.createGraphics();
        g2d.setComposite(AlphaComposite.SrcOver);
        g2d.setStroke(new BasicStroke(brushSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        repaint();
    }

    // Create a toolbar for changing color, brush size, clearing the canvas, and
    // saving the drawing.
    private void initToolbar() {
        JPanel toolbar = new JPanel();

        JButton colorButton = new JButton("Color");
        colorButton.addActionListener(e -> {
            Color selected = JColorChooser.showDialog(this, "Choose color", currentColor);
            if (selected != null) {
                currentColor = selected;
            }
        });
        toolbar.add(colorButton);

        JSlider brushSizeSlider = new JSlider(1, 20, brushSize);
        brushSizeSlider.addChangeListener(e -> {
            brushSize = brushSizeSlider.getValue();
            g2d.setStroke(new BasicStroke(brushSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        });
        toolbar.add(new JLabel("Brush Size:"));
        toolbar.add(brushSizeSlider);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            initCanvas();
            repaint();
        });
        toolbar.add(clearButton);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveDrawing());
        toolbar.add(saveButton);

        add(toolbar, BorderLayout.NORTH);
    }

    // Draw a freehand line over the canvas.
    private void drawLine(Point p1, Point p2) {
        g2d.setColor(currentColor);
        g2d.setStroke(new BasicStroke(brushSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
        g.drawImage(canvas, 0, 0, null);
    }

    // Save the current drawing to a file.
    private void saveDrawing() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Drawing");

        // Add file filters
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PNG Image", "png"));
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("JPEG Image", "jpg"));
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PDF Document", "pdf"));
        fileChooser.setAcceptAllFileFilterUsed(false);

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String extension = getFileExtension(fileToSave);

            // ✅ Fix: Automatically append correct extension if missing
            if (extension.isEmpty()) {
                String selectedFilter = fileChooser.getFileFilter().getDescription().toLowerCase();
                if (selectedFilter.contains("png")) {
                    fileToSave = new File(fileToSave.getAbsolutePath() + ".png");
                    extension = "png";
                } else if (selectedFilter.contains("jpeg") || selectedFilter.contains("jpg")) {
                    fileToSave = new File(fileToSave.getAbsolutePath() + ".jpg");
                    extension = "jpg";
                } else if (selectedFilter.contains("pdf")) {
                    fileToSave = new File(fileToSave.getAbsolutePath() + ".pdf");
                    extension = "pdf";
                }
            }

            try {
                switch (extension) {
                    case "png":
                    case "jpg":
                        saveAsImage(fileToSave, extension);
                        break;
                    case "pdf":
                        saveAsPDF(fileToSave);
                        break;
                    default:
                        JOptionPane.showMessageDialog(this, "Unsupported file format.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException | DocumentException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Get the file extension from the selected file.
    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndex = name.lastIndexOf(".");
        if (lastIndex > 0) {
            return name.substring(lastIndex + 1).toLowerCase();
        }
        return "";
    }

    // Save the drawing as an image file (PNG or JPG).
    private void saveAsImage(File file, String format) throws IOException {
        BufferedImage combined = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = combined.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, combined.getWidth(), combined.getHeight());
        g.drawImage(canvas, 0, 0, null);
        g.dispose();

        ImageIO.write(combined, format, file);
        JOptionPane.showMessageDialog(this, "File saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    // Save the drawing as a PDF file.
    private void saveAsPDF(File file) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();

        // Convert BufferedImage to iText Image
        File tempImageFile = new File(file.getAbsolutePath() + ".png");
        saveAsImage(tempImageFile, "png");
        Image image = Image.getInstance(tempImageFile.getAbsolutePath());
        document.add(image);
        document.close();
        tempImageFile.delete(); // Remove temporary image file

        JOptionPane.showMessageDialog(this, "PDF saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
