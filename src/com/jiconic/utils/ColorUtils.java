package com.jiconic.utils;

import java.awt.*;

/**
 * Utilities for performing common color-related tasks.
 * @author Ryan Ware
 *
 * modified for use in JIconic
 */
public class ColorUtils {

  /**
   * Returns the complimentary (opposite) color.
   *
   * @param color Color to return the compliment of
   * @return Color the complimentary color
   */
  public static Color getComplimentaryColor(Color color) {
    // get existing colors
    int alpha = color.getAlpha();
    int red = color.getRed();
    int blue = color.getBlue();
    int green = color.getGreen();

    // find compliments
    red = (~red) & 0xff;
    blue = (~blue) & 0xff;
    green = (~green) & 0xff;

    return new Color(red, green, blue, alpha);
  }

  /**
   * Returns the Contrast Color of the supplied color
   *
   * @param color
   * @return
   */
  public static Color getContrastColor(Color color) {
    double y = (299 * color.getRed() + 587 * color.getGreen() + 114 * color.getBlue()) / 1000;
    return y >= 128 ? Color.black : Color.white;
  }

  /**
   * Converts a color representation into a hexadecimal ARGB {@link String}.
   * @param color   the color
   * @return {@link String} hexadecimal color representation
   */
  public static String getHexStringForARGB(Color color) {
    String hexString = "#";
    hexString += ARGBToHex(color.getAlpha());
    hexString += ARGBToHex(color.getRed());
    hexString += ARGBToHex(color.getGreen());
    hexString += ARGBToHex(color.getBlue());

    return hexString;
  }

  /**
   * Converts an int R, G, or B value into a hexadecimal {@link String}.
   * @param rgbVal int R, G, or B value
   * @return {@link String} hexadecimal value
   */
  private static String ARGBToHex(int rgbVal) {
    String hexReference = "0123456789ABCDEF";

    rgbVal = Math.max(0,rgbVal);
    rgbVal = Math.min(rgbVal,255);
    rgbVal = Math.round(rgbVal);

    return String.valueOf( hexReference.charAt((rgbVal-rgbVal%16)/16) + "" + hexReference.charAt(rgbVal%16) );
  }
}