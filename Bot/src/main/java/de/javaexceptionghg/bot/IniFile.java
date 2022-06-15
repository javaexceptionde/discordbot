/*
 *
 *  *     Copyright (C) 2022  Jonathan Benedikt Bull<jonathan@jbull.dev>
 *  *
 *  *     This program is free software: you can redistribute it and/or modify
 *  *     it under the terms of the GNU General Public License as published by
 *  *     the Free Software Foundation, either version 3 of the License, or
 *  *     (at your option) any later version.
 *  *
 *  *     This program is distributed in the hope that it will be useful,
 *  *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  *     GNU General Public License for more details.
 *  *
 *  *     You should have received a copy of the GNU General Public License
 *  *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

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

