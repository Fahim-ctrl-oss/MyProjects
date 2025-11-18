package gremlins.setup;

import processing.data.JSONObject;
import processing.data.JSONArray;
import java.util.ArrayList;

public class Configuration {
    // Public attributes for storing game configuration data
    public int lives; // Number of lives the player has
    public ArrayList<LevelDetails> Level_Configuration = new ArrayList<>(); // List of level details

    /**
     * Constructor to initialize configuration based on JSON data.
     * @param conf The JSONObject containing configuration data
     */
    public Configuration(JSONObject conf) {
        setLives(conf); // Initialize lives from JSON
        setLevel_Configuration(conf); // Initialize level configurations from JSON
    }

    /**
     * Sets the number of lives from the JSON configuration.
     * @param conf The JSONObject containing configuration data
     */
    public void setLives(JSONObject conf) {
        this.lives = conf.getInt("lives"); // Retrieve 'lives' value from JSON
    }

    /**
     * Loads layout details for the next level.
     * @param level The index of the level to load
     */
    public void nextLevel(int level) {
        Level_Configuration.get(level).makeLayout(Level_Configuration.get(level).getFileName());
        // Load layout for the specified level index using LevelDetails methods
    }

    /**
     * Sets up level configurations from JSON data.
     * @param conf The JSONObject containing configuration data
     */
    public void setLevel_Configuration(JSONObject conf) {
        JSONArray array_object = conf.getJSONArray("levels"); // Retrieve 'levels' array from JSON
        for (int i = 0; i < array_object.size(); i++) {
            JSONObject each_level = array_object.getJSONObject(i); // Get each level JSON object
            String layoutFileName = each_level.getString("layout"); // Retrieve layout file name
            LevelDetails level_details = new LevelDetails(layoutFileName); // Create LevelDetails object
            this.Level_Configuration.add(level_details); // Add to level configuration list
        }
    }
}
