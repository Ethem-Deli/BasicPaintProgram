package com.example.paint;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * PaintPanel provides a drawing area with core features:
 * - Freehand drawing (brush)
 * - Eraser tool (draws in white)
 * - Shapes: rectangle, circle, and line
 * - Color selection and brush size adjustment
 * - File operations: Save, Open, and Clear canvas
 */
public class PaintPanel extends JPanel {
    // Enumeration for drawing tools
    public enum Tool {
        BRUSH, ERASER, RECTANGLE, CIRCLE, LINE
    }

    private BufferedImage canvas;
    private Graphics2D g2d;
    private Point startPoint, endPoint;
    private Color currentColor = Color.BLACK;
    private int brushSize = 5;
    private Tool currentTool = Tool.BRUSH;

    // Toolbar components
    private JButton colorButton, eraserButton, brushButton, rectangleButton, circleButton, lineButton;
    private JSlider brushSizeSlider;
    private JButton clearButton;
    private JButton saveButton, openButton;

    public PaintPanel() {
        setLayout(new BorderLayout());
        initCanvas();
        initToolbar();

        // Mouse listener for drawing events
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
                if (currentTool == Tool.BRUSH || currentTool == Tool.ERASER) {
                    drawLine(e.getPoint(), e.getPoint());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (currentTool == Tool.RECTANGLE || currentTool == Tool.CIRCLE || currentTool == Tool.LINE) {
                    endPoint = e.getPoint();
                    drawShape();
                }
                startPoint = null;
                endPoint = null;
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (currentTool == Tool.BRUSH || currentTool == Tool.ERASER) {
                    Point currentPoint = e.getPoint();
                    drawLine(startPoint, currentPoint);
                    startPoint = currentPoint;
                }
            }
        });
    }

    // Initialize the canvas as a BufferedImage and fill it with a white background.
    private void initCanvas() {
        canvas = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        g2d = canvas.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        g2d.setStroke(new BasicStroke(brushSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }

    // Create a toolbar with buttons for tool selection and file operations.
    private void initToolbar() {
        JPanel toolbar = new JPanel();

        // Brush tool
        brushButton = new JButton("Brush");
        brushButton.addActionListener(e -> currentTool = Tool.BRUSH);
        toolbar.add(brushButton);

        // Eraser tool
        eraserButton = new JButton("Eraser");
        eraserButton.addActionListener(e -> currentTool = Tool.ERASER);
        toolbar.add(eraserButton);

        // Rectangle shape
        rectangleButton = new JButton("Rectangle");
        rectangleButton.addActionListener(e -> currentTool = Tool.RECTANGLE);
        toolbar.add(rectangleButton);

        // Circle shape
        circleButton = new JButton("Circle");
        circleButton.addActionListener(e -> currentTool = Tool.CIRCLE);
        toolbar.add(circleButton);

        // Line tool
        lineButton = new JButton("Line");
        lineButton.addActionListener(e -> currentTool = Tool.LINE);
        toolbar.add(lineButton);

        // Color chooser button
        colorButton = new JButton("Color");
        colorButton.addActionListener(e -> {
            Color selectedColor = JColorChooser.showDialog(this, "Choose a color", currentColor);
            if (selectedColor != null) {
                currentColor = selectedColor;
            }
        });
        toolbar.add(colorButton);

        // Brush size slider
        brushSizeSlider = new JSlider(1, 20, brushSize);
        ChangeListener sliderListener = e -> {
            brushSize = brushSizeSlider.getValue();
            g2d.setStroke(new BasicStroke(brushSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        };
        brushSizeSlider.addChangeListener(sliderListener);
        toolbar.add(new JLabel("Brush Size:"));
        toolbar.add(brushSizeSlider);

        // Clear canvas button
        clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            clearCanvas();
            repaint();
        });
        toolbar.add(clearButton);

        // Save image button
        saveButton = new JButton("Save");
        saveButton.addActionListener(e -> FileHandler.saveImage(canvas, this));
        toolbar.add(saveButton);

        // Open image button
        openButton = new JButton("Open");
        openButton.addActionListener(e -> {
            BufferedImage img = FileHandler.openImage(this);
            if (img != null) {
                g2d.drawImage(img, 0, 0, null);
                repaint();
            }
        });
        toolbar.add(openButton);

        add(toolbar, BorderLayout.NORTH);
    }

    // Draw a freehand line between two points.
    private void drawLine(Point p1, Point p2) {
        if (currentTool == Tool.ERASER) {
            g2d.setColor(Color.WHITE);
        } else {
            g2d.setColor(currentColor);
        }
        g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        repaint();
    }

    // Draw a shape (rectangle, circle, or line) based on the start and end points.
    private void drawShape() {
        if (startPoint == null || endPoint == null)
            return;
        g2d.setColor(currentColor);
        int x = Math.min(startPoint.x, endPoint.x);
        int y = Math.min(startPoint.y, endPoint.y);
        int width = Math.abs(startPoint.x - endPoint.x);
        int height = Math.abs(startPoint.y - endPoint.y);
        switch (currentTool) {
            case RECTANGLE:
                g2d.drawRect(x, y, width, height);
                break;
            case CIRCLE:
                int diameter = Math.min(width, height);
                g2d.drawOval(x, y, diameter, diameter);
                break;
            case LINE:
                g2d.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
                break;
            default:
                break;
        }
    }

    // Clears the canvas by filling it with white.
    public void clearCanvas() {
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(canvas, 0, 0, null);
    }
}
