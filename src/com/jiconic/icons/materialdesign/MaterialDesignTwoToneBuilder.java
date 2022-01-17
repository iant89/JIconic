package com.jiconic.icons.materialdesign;
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

import com.jiconic.IconProperties;
import com.jiconic.JIconic;
import com.jiconic.logging.Logger;
import com.jiconic.providers.IconProvider;
import com.jiconic.utils.UnicodeUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class MaterialDesignTwoToneBuilder extends MaterialDesignBuilder {

    /**
     * Checks to see if the required providers are loaded
     *
     * @return  true if loaded, false otherwise.
     */
    private boolean hasRequiredProviders() {
        if(!JIconic.hasProvider("Material Design Sharp")) {
            return false;
        }

        if(!JIconic.hasProvider("Material Design Outlined")) {
            return false;
        }

        return true;
    }

    @Override
    public Icon buildIcon(IconProvider provider, String icon, IconProperties properties) {
        if(!hasRequiredProviders()) {
            throw new IllegalArgumentException("Material Design TwoTone requires Material Design Sharp and Material Design Outlined to be registered.");
        }

        BufferedImage image = build(provider, icon, properties);

        if(image == null) {
            return null;
        }

        return new ImageIcon(image);
    }

    @Override
    public BufferedImage buildImage(IconProvider provider, String icon, IconProperties properties) {
        if(!hasRequiredProviders()) {
            throw new IllegalArgumentException("Material Design TwoTone requires Material Design Sharp and Material Design Outlined to be registered.");
        }

        BufferedImage image = build(provider, icon, properties);

        if(image == null) {
            return null;
        }

        return image;
    }

    private BufferedImage build(IconProvider provider, String icon, IconProperties properties) {

        Font primaryFont = buildFont(JIconic.getProvider("Material Design Outlined"), properties.getSize());
        Font secondaryFont = buildFont(JIconic.getProvider("Material Design Regular"), properties.getSize());

        if(primaryFont == null) {
            return null;
        }

        if(secondaryFont == null) {
            return null;
        }

        if(icon == null) {
            return null;
        }

        JLabel firstLabel = new JLabel("");
        JLabel secondLabel = new JLabel("");

        String bothUnicodes = provider.getCodePoint(icon);

        String[] unicodes;
        if(bothUnicodes.indexOf(",") > 0) {
            unicodes = bothUnicodes.split(",");
        } else {
            unicodes = new String[] { bothUnicodes };
        }

        // First Layer
        firstLabel.setForeground(properties.getPrimary());
        firstLabel.setFont(primaryFont);

        firstLabel.setText(UnicodeUtils.convertUnicodeToString(Integer.parseInt(unicodes[0], 16)));
        firstLabel.setPreferredSize(firstLabel.getMinimumSize());

        // Second Layer
        secondLabel.setForeground(properties.getSecondary());
        secondLabel.setFont(secondaryFont);

        secondLabel.setText(UnicodeUtils.convertUnicodeToString(Integer.parseInt(unicodes[0], 16)));
        secondLabel.setPreferredSize(secondLabel.getMinimumSize());

        Dimension fLDimensions = firstLabel.getPreferredSize();
        Dimension sLDimensions = secondLabel.getPreferredSize();

        int width;
        int height;

        if(fLDimensions.getWidth() > sLDimensions.getWidth()) {
            width = (int) fLDimensions.getWidth();
        } else {
            width = (int) sLDimensions.getWidth();
        }

        if(fLDimensions.getHeight() > sLDimensions.getHeight()) {
            height = (int) fLDimensions.getHeight();
        } else {
            height = (int) sLDimensions.getHeight();
        }

        AffineTransform rotationTransform = null;

        if(properties.getRotation() != 0) {
            rotationTransform = AffineTransform.getRotateInstance(Math.toRadians(properties.getRotation()), width / 2D, height / 2D);

            Shape rotatedShape = rotationTransform.createTransformedShape(new Rectangle(0, 0, width, height));

            width = (int) rotatedShape.getBounds2D().getWidth();
            height = (int) rotatedShape.getBounds2D().getHeight();
        }

        firstLabel.setBounds(0, 0, width, height);
        secondLabel.setBounds(0, 0, width, height);

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = bufferedImage.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);

        if(rotationTransform != null) {
            g2.setTransform(rotationTransform);
        }

        secondLabel.print(g2);
        firstLabel.print(g2);

        g2.dispose();

        return bufferedImage;
    }
}
