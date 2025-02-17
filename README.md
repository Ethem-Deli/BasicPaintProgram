                                                               Basic Paint Program
Overview
The Basic Paint Program is an interactive Java-based drawing application tailored for children. It includes essential drawing tools such as brushes, shapes, and colors. Additionally, it provides educational features, including a tracing pad, drawing lessons, and an embedded video player.
This project is built using Java Swing for the UI and JavaFX for multimedia functionalities, with dependencies such as iText for PDF export.

⚙ Features
🎨 Core Drawing Tools
Freehand brush tool
Eraser tool
Shape drawing (Rectangle, Circle, Line
Color selection and brush size adjustment

📂 File Operations
Save drawings as PNG, JPG, and PDF
Load existing images
Clear canvas

🎓 Educational Features
Tracing Pad: Preloaded background image for tracing
Drawing Lessons: Step-by-step instructional guides
Embedded Video Player: Educational video playback

🛠 Installation & Setup
📉 Prerequisites
Ensure you have the following installed:
Java Development Kit (JDK 11) (or higher)
Apache Maven (for dependency management)
JavaFX SDK (for video functionality)

📁 Project Structure
BasicPaintProgram/
|-- src/main/java/com/example/paint/
|   |-- BasicPaintProgram.java        # Main class
|   |-- PaintPanel.java               # Core drawing functions
|   |-- TracingPadPanel.java          # Tracing pad feature
|   |-- LessonsPanel.java             # Drawing lessons
|   |-- VideoPlayerPanel.java         # Embedded video player
|   |-- FileHandler.java              # Handles file operations
|
|-- resources/                        # Assets folder
|   |-- tracing_background.png        # Background image for tracing
|   |-- sample_video.mp4              # Video for educational purposes
|
|-- pom.xml                            # Maven build configuration
|-- README.md                          # Project documentation

📦 Dependencies
The project uses the following dependencies in Maven (pom.xml):
<dependencies>
    <!-- iText PDF Library for PDF export -->
    <dependency>
        <groupId>com.itextpdf</groupId>
        <artifactId>itextpdf</artifactId>
        <version>5.5.13.4</version>
    </dependency>

    <!-- JavaFX Dependencies -->
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>21</version>
    </dependency>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-media</artifactId>
        <version>21</version>
    </dependency>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-swing</artifactId>
        <version>21</version>
    </dependency>
</dependencies>

🚀 Running the Application
🔧 Build & Run Instructions
Open a terminal and navigate to the project directory:
cd D:\BYU\cse310\BasicPaintProgram
Set up Java 11 environment:
set JAVA_HOME="C:\Program Files\Eclipse Adoptium\jdk-11.0.26.4-hotspot"
set PATH=%JAVA_HOME%\bin;%PATH%
Check if Maven is properly set up:
mvn -version
Build the project (only needed if you've modified the code):
mvn clean install
Run the program:
mvn javafx:run

Alternatively, you can run the compiled JAR file:
java -jar target/BasicPaintProgram-1.0-SNAPSHOT.jar

📝 Usage Guide
🎨 Paint Tab
Select different drawing tools
Choose colors and adjust brush size
Save, Open, or Clear canvas

📜 Tracing Pad Tab
Trace over a preloaded background image

📚 Lessons Tab
Follow step-by-step drawing tutorials

📹 Videos Tab
Watch educational videos embedded in the program

🛠 Troubleshooting
Issue: JavaFX Not Found
✅ Ensure you have JavaFX SDK installed and set up correctly.
Issue: "Unsupported file format" when saving
✅ Ensure file extensions are properly added when saving.
Issue: "TracingPadPanel.java is not on the classpath"
✅ Update .vscode/settings.json with:
{
    "java.project.sourcePaths": ["src/main/java"]
}
✅ Then, restart VS Code and clean the project:

mvn clean compile

📄 License
This project is provided for educational purposes and is open-source.

📉 Author
Developed as part of CSE310 - BYU Java Programming Course 🏫