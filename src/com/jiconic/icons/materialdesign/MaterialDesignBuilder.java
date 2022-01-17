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
import com.jiconic.builders.IconBuilder;
import com.jiconic.logging.Logger;
import com.jiconic.providers.IconProvider;
import com.jiconic.utils.UnicodeUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class MaterialDesignBuilder implements IconBuilder {

    protected static Logger logger = new Logger();

    @Override
    public Icon buildIcon(IconProvider provider, String icon, IconProperties properties) {
        BufferedImage image = build(provider, icon, properties);

        if(image == null) {
            return null;
        }

        return new ImageIcon(image);
    }

    @Override
    public BufferedImage buildImage(IconProvider provider, String icon, IconProperties properties) {
        return build(provider, icon, properties);
    }

    private BufferedImage build(IconProvider provider, String icon, IconProperties properties) {
        if(provider.getClass().getName().equals(MaterialDesignTwoToneProvider.class.getName())) {
            return buildTwoTone(provider, icon, properties);
        }

        Font iconFont = buildFont(provider, properties.getSize());

        if(iconFont == null) {
            return null;
        }

        if(icon == null) {
            return null;
        }

        JLabel iconLabel = new JLabel("");

        iconLabel.setForeground(properties.getPrimary());
        iconLabel.setFont(iconFont);
        iconLabel.setText(Character.toString(Character.toChars(Integer.parseInt(("\\u" + provider.getCodePoint(icon)).substring(2), 16))[0]));

        iconLabel.setPreferredSize(iconLabel.getMinimumSize());
        Dimension labelDimensions = iconLabel.getPreferredSize();

        int width = (int) (labelDimensions.getWidth());
        int height = (int) (labelDimensions.getHeight());

        AffineTransform rotationTransform = null;

        if(properties.getRotation() > 0) {
            rotationTransform = AffineTransform.getRotateInstance(Math.toRadians(properties.getRotation()), width / 2D, height / 2D);

            Shape rotatedShape = rotationTransform.createTransformedShape(new Rectangle(0, 0, width, height));

            width = (int) rotatedShape.getBounds2D().getWidth();
            height = (int) rotatedShape.getBounds2D().getHeight();
        }

        iconLabel.setBounds(0, 0, width, height);

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = bufferedImage.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);

        if(rotationTransform != null) {
            g2.setTransform(rotationTransform);
        }

        iconLabel.print(g2);

        g2.dispose();

        return bufferedImage;
    }

    private BufferedImage buildTwoTone(IconProvider provider, String icon, IconProperties properties) {
        Font iconFont = buildFont(provider, properties.getSize());

        if(iconFont == null) {
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
        firstLabel.setFont(iconFont);

        firstLabel.setText(UnicodeUtils.convertUnicodeToString(Integer.parseInt(unicodes[0], 16)));
        firstLabel.setPreferredSize(firstLabel.getMinimumSize());

        // Second Layer
        secondLabel.setForeground(properties.getSecondary());
        secondLabel.setFont(iconFont);

        secondLabel.setText(UnicodeUtils.convertUnicodeToString(Integer.parseInt(unicodes[1], 16)));
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



    protected static Font buildFont(IconProvider provider, float size) {
        if(provider == null) {
            logger.error("icon provider is null");
            throw new IllegalArgumentException("icon provider is null");
        }

        try {
            InputStream inputStream = provider.getResourceAsStream();

            if(inputStream == null) {
                logger.error("font resource input stream is null");
                throw new IllegalArgumentException("font resource input stream is null");
            }

            Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);

            return font.deriveFont(size);
        } catch (Exception ex) {
            logger.error("font failed to load");
            throw new RuntimeException(ex);
        }
    }
}
