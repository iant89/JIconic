package com.jiconic;
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

import java.awt.*;

public class IconProperties {

    private int iconAngle = 0;
    private float iconSize = 10f;
    private Color primaryColor = Color.BLACK;
    private Color secondaryColor = Color.WHITE;


    private int primaryOpacity = 100;
    private int secondaryOpacity = 100;

    private boolean iconOpaque = false;

    public IconProperties() {

    }

    public IconProperties setRotation(int angle) {
        iconAngle = angle;

        return this;
    }

    public IconProperties inverse() {
        setPrimaryColor(Color.WHITE);
        setSecondaryColor(Color.BLACK);

        return this;
    }

    public int getRotation() {
        return iconAngle;
    }

    public IconProperties setSize(float size) {
        iconSize = size;

        return this;
    }

    public float getSize() {
        return iconSize;
    }

    public IconProperties setSecondaryColor(Color color) {
        secondaryColor = color;

        return this;
    }

    public IconProperties setPrimaryColor(Color color) {
        primaryColor = color;

        return this;
    }

    public IconProperties setOpaque(boolean opaque) {
        iconOpaque = opaque;

        return this;
    }

    public Color getPrimary() {
        return primaryColor;
    }

    public Color getSecondary() {
        return secondaryColor;
    }

    public int getPrimaryOpacity() {
        return primaryOpacity;
    }

    public int getSecondaryOpacity() {
        return secondaryOpacity;
    }

    public IconProperties setPrimaryOpacity(int opacity) {
        if(opacity >= 100) {
            opacity = 100;
        } else if(opacity <= 0) {
            opacity = 0;
        }

        primaryOpacity = opacity;

        return this;
    }

    public IconProperties setSecondaryOpacity(int opacity) {
        if(opacity >= 100) {
            opacity = 100;
        } else if(opacity <= 0) {
            opacity = 0;
        }

        secondaryOpacity = opacity;

        return this;
    }

    public boolean isOpaque() {
        return iconOpaque;
    }
}
