package gremlins.setup;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Class representing details of a game level, including its layout.
 */
public class LevelDetails {
    private String filename; // File name of the level layout
    private HashMap<Integer, char[]> layout; // HashMap to store the level layout

    /**
     * Constructor to initialize LevelDetails with a filename.
     * @param filename The filename of the level layout file
     */
    public LevelDetails(String filename) {
        this.filename = filename;
        this.layout = new HashMap<>();
        this.makeLayout(filename); // Load layout from file upon initialization
    }

    /**
     * Get the filename of the level layout.
     * @return The filename of the level layout
     */
    public String getFileName() {
        return this.filename;
    }

    /**
     * Get the layout of the level as a HashMap.
     * @return The layout of the level
     */
    public HashMap<Integer, char[]> getLayout() {
        return this.layout;
    }

    /**
     * Set the filename of the level layout.
     * @param filename The new filename of the level layout
     */
    public void setFileName(String filename) {
        this.filename = filename;
    }

    /**
     * Load the layout from the specified file and populate the layout HashMap.
     * @param filename The filename of the level layout file
     */
    public void makeLayout(String filename) {
        HashMap<Integer, char[]> map = new HashMap<>();
        try {
            File f = new File(filename);
            Scanner s = new Scanner(f);
            int index = 0;
            while (s.hasNextLine()) {
                map.put(index, s.nextLine().toCharArray());
                index++;
            }
            s.close();  // Close the scanner when done
        } catch (Exception e) {
            System.out.println("File not Found or Error reading file.");
            e.printStackTrace();  // Print stack trace for debugging
            System.exit(1);  // Exit the program on error
        }
        this.layout = map;
    }
}
