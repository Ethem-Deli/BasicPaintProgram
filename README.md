# BasicPaintProgram
Basic Paint Program: Create a simple paint program with drawing tools like brushes, shapes, and colors also additional make for children add tracing pads, videos and educational things
# Basic Paint Program

## Overview
This is a basic Java paint program designed with educational features for children. It includes core drawing functionalities such as brush, eraser, shape drawing (rectangle, circle, line), and color/brush size selection. Additionally, it provides file operations for saving and loading images, as well as educational features including a tracing pad, drawing lessons, and an embedded video player.

## Features
- **Core Drawing Tools**: Brush, eraser, shapes (rectangle, circle, line), color selection, and adjustable brush size.
- **File Operations**: Save drawings as PNG or JPG, open existing images, and clear the canvas.
- **Educational Tools**:
  - **Tracing Pad**: Preloaded background image for tracing.
  - **Drawing Lessons**: Step-by-step drawing instructions.
  - **Video Player**: Embedded media player to play educational videos.
- **User Interface**: Built using Java Swing with a child-friendly layout and toolbar.

## Installation
1. **Prerequisites**:
   - Java Development Kit (JDK) 8 or higher.
   - If using JavaFX (for the video player), ensure that JavaFX libraries are available. (For JDK 11 and above, you may need to download OpenJFX separately.)
2. **Project Structure**:
   - `src/`: Contains the Java source files.
   - `resources/`: Contains resource files such as `tracing_background.png` and `sample_video.mp4`.
3. **Build Instructions**:
   - Compile the source files using your preferred IDE or via the command line. For example:
     ```bash
     javac -cp . src/com/example/paint/*.java
     java -cp . com.example.paint.BasicPaintProgram
     ```

## Usage
- **Paint Tab**: Use the toolbar to select different drawing tools, choose a color, and adjust the brush size. File operations (Save, Open, Clear) are available on the toolbar.
- **Tracing Pad Tab**: Trace over the preloaded image.
- **Lessons Tab**: Read through the drawing lessons.
- **Videos Tab**: Watch the embedded educational video.

## Dependencies
- **Java Swing**: For UI components.
- **JavaFX**: For embedded video playback (via `JFXPanel`). Ensure JavaFX libraries are properly set up if using JDK 11 or later.

## Code Structure
- **BasicPaintProgram.java**: Main class that initializes the application and tabs.
- **PaintPanel.java**: Handles core drawing operations.
- **TracingPadPanel.java**: Provides the tracing pad feature.
- **LessonsPanel.java**: Contains drawing lessons.
- **VideoPlayerPanel.java**: Embeds a video player using JavaFX.
- **FileHandler.java**: Handles file save and open operations.

## License
This project is provided as-is for educational purposes.
