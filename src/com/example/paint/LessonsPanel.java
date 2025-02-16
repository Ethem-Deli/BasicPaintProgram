package com.example.paint;

import javax.swing.*;
import java.awt.*;

/**
 * LessonsPanel provides a comprehensive text area with step-by-step drawing
 * lessons.
 * These instructions help children learn basic drawing techniques and
 * progressively
 * develop their skills.
 */
public class LessonsPanel extends JPanel {
    public LessonsPanel() {
        setLayout(new BorderLayout());
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setText("Drawing Lessons:\n\n" +
                "1. **Start with Simple Shapes**:\n" +
                "   - Practice drawing basic shapes like circles, squares, and triangles.\n" +
                "   - Focus on achieving smooth and consistent outlines.\n\n" +
                "2. **Combine Shapes to Create Objects**:\n" +
                "   - Use basic shapes as building blocks to form more complex images.\n" +
                "   - For example, combine circles and rectangles to draw a simple house.\n\n" +
                "3. **Explore Line Variations**:\n" +
                "   - Practice drawing straight, curved, and zigzag lines.\n" +
                "   - Experiment with line thickness to understand how it affects your drawing.\n\n" +
                "4. **Understand Proportions**:\n" +
                "   - Study the size relationships between different parts of an object.\n" +
                "   - Practice by drawing simple human figures or animals, paying attention to proportion.\n\n" +
                "5. **Experiment with Shading**:\n" +
                "   - Learn to create depth by adding shading to your drawings.\n" +
                "   - Practice techniques like hatching and cross-hatching to depict light and shadow.\n\n" +
                "6. **Use References**:\n" +
                "   - Draw from real-life objects or photographs to improve observation skills.\n" +
                "   - Start with simple objects like fruits or everyday items.\n\n" +
                "7. **Practice Perspective Drawing**:\n" +
                "   - Understand the basics of one-point and two-point perspective.\n" +
                "   - Practice by drawing simple 3D shapes like cubes and cylinders.\n\n" +
                "8. **Incorporate Details Gradually**:\n" +
                "   - Start with the basic outline of your subject.\n" +
                "   - Add details step by step, such as textures and patterns.\n\n" +
                "9. **Explore Different Mediums**:\n" +
                "   - Try using pencils, colored pencils, markers, or paints.\n" +
                "   - Understand how each medium affects your drawing style and outcome.\n\n" +
                "10. **Keep a Sketchbook**:\n" +
                "    - Maintain a sketchbook to practice regularly and track your progress.\n" +
                "    - Use it to jot down ideas, practice techniques, and experiment freely.\n\n" +
                "Remember, drawing is a skill developed over time with consistent practice and patience. " +
                "Encourage creativity and personal expression in every drawing session.");
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }
}
