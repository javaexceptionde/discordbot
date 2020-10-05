package de.javaexceptionghg.bot;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.*;
import java.util.Properties;

@EqualsAndHashCode(callSuper = false)
@ToString
public class IniFile extends Properties {

    private final long VERSION_ID = 2495627423L;
    private File dest;

    public IniFile(File dest) {
        if (dest.exists()) loadFromFile(dest);
        this.dest = dest;
    }

    public IniFile(String dest) {
        this(new File(dest));
    }

    public void addDefault(String key, String value) {
        if (!containsKey(key)) {
            setProperty(key, value);
            saveToFile();
        }
    }

    public boolean saveToFile() {
        OutputStreamWriter writer = null;
        boolean success = false;
        try {
            if (!dest.exists()) success = dest.createNewFile();
            writer = new OutputStreamWriter(new FileOutputStream(dest));
            store(writer, "");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return success;
    }

    public void loadFromFile(File file) {
        clear();
        Reader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(file), "UTF8");
            load(reader);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}

