package com.example.paint;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

/**
 * TracingPadPanel provides a drawing area with a preloaded background image.
 * Kids can trace over the image using similar freehand drawing features.
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
            // Use an absolute file path to load the image.
            backgroundImage = ImageIO
                    .read(new File("D:/BYU/cse310/BasicPaintProgram/resources/tracing_background.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Background image not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Initialize a transparent canvas for drawing.
    private void initCanvas() {
        canvas = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        g2d = canvas.createGraphics();
        // Fill with a transparent background so the image shows through.
        g2d.setComposite(AlphaComposite.Clear);
        g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        g2d.setComposite(AlphaComposite.SrcOver);
        g2d.setStroke(new BasicStroke(brushSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }

    // Create a toolbar for changing color, brush size, and clearing the canvas.
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

        add(toolbar, BorderLayout.NORTH);
    }

    // Draw a freehand line over the canvas.
    private void drawLine(Point p1, Point p2) {
        g2d.setColor(currentColor);
        g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image if available.
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
        }
        // Draw the tracing canvas on top.
        g.drawImage(canvas, 0, 0, null);
    }
}
