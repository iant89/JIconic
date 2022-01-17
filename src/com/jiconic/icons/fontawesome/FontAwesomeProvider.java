package com.jiconic.icons.fontawesome;
/*
 * Copyright (c) 2022 JIconic
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 * @author Ian Thomas
 */

import com.jiconic.builders.IconBuilder;
import com.jiconic.providers.IconProvider;
import com.jiconic.utils.UnicodeUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public abstract class FontAwesomeProvider implements IconProvider {

    protected HashMap<String, String> iconMap;

    private String ttfFile;
    private String datFile;

    public FontAwesomeProvider(String ttfFile, String datFile) {
        iconMap = new HashMap<>();

        this.ttfFile = ttfFile;
        this.datFile = datFile;


        loadIcons();
    }

    public String[] getIcons() {

        String[] icons = new String[iconMap.size()];

        int index = 0;
        for(Map.Entry<String, String> entry : iconMap.entrySet()) {
            icons[index++] = entry.getKey();
        }

        return icons;
    }

    private void loadIcons() {
        FileReader fr = null;
        BufferedReader br = null;

        iconMap = new HashMap<>();

        try {
            fr = new FileReader(datFile);
            br = new BufferedReader(fr);

            String line = br.readLine();

            while (line != null) {
                if(line.length() > 0) {
                    if(line.contains(",")) {

                        String[] split = line.split(",");

                        if(split.length == 2) {
                            // Regular, Light, Solid, Brand
                            split[0] = split[0].toLowerCase().trim();
                            split[1] = split[1].toLowerCase().trim();

                            iconMap.put(split[0], UnicodeUtils.escapeUnicode(split[1]));
                        } else if(split.length == 3) {
                            // Duotone
                            split[0] = split[0].toLowerCase().trim();
                            split[1] = split[1].toLowerCase().trim();
                            split[2] = split[2].toLowerCase().trim();

                            iconMap.put(split[0], UnicodeUtils.escapeUnicode(split[1]) + "," + UnicodeUtils.escapeUnicode(split[2]));
                        }
                    }

                    line = br.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br != null) {
                    br.close();
                }
                if(fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public InputStream getResourceAsStream() {
        String baseDirectory = new File(".").getAbsolutePath();
        baseDirectory = baseDirectory.substring(0, baseDirectory.length() - 1);

        baseDirectory = baseDirectory + ttfFile;

        try {
            FileInputStream inputStream = new FileInputStream(baseDirectory);

            if(inputStream != null) {
                return inputStream;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean hasIcon(String icon) {
        if(!icon.toLowerCase().startsWith(getPrefix() + "-")) {
            return false;
        }

        icon = icon.replace(getPrefix() + "-", "");

        return iconMap.containsKey(icon);
    }

    @Override
    public String getCodePoint(String icon) {

        icon = icon.replace(getPrefix() + "-", "");

        if(!iconMap.containsKey(icon)) {
            return null;
        }

        return iconMap.get(icon.toLowerCase());
    }

    public IconBuilder getBuilder() {
        return new FontAwesomeBuilder();
    }
}
