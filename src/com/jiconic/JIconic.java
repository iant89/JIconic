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

import com.jiconic.logging.Logger;
import com.jiconic.providers.IconProvider;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class JIconic {
    private static Logger logger = new Logger();

    private static ArrayList<IconProvider> providerList = new ArrayList<>();

    /**
     * Registers a Icon Provider
     *
     * @param provider      IconProvider
     */
    public static void registerProvider(IconProvider provider) {
        if(provider == null) {
            throw new IllegalArgumentException("The provider is null.");
        }

        if(providerList.contains(provider)) {
            logger.warn("The provider `" + provider.getName() + "` is already registered.");
            return;
        }

        providerList.add(provider);
        logger.info("Registered Icon Provider: " + provider.getName());
    }

    /**
     * Gets a icon provider by the given name.
     *
     * @param name  name of provider
     * @return      The IconProvider, or null if not found.
     */
    public static IconProvider getProvider(String name) {
        for (IconProvider iconProvider : providerList) {
            if (iconProvider.getName().equalsIgnoreCase(name)) {
                return iconProvider;
            }
        }

        return null;
    }

    /**
     * Gets all registered icon providers
     *
     * @return      A Array of all currently registered icon providers.
     */
    public static IconProvider[] getProviders() {
        return providerList.toArray(new IconProvider[0]);
    }

    /**
     * Unregister a Icon Provider
     *
     * @param provider  the icon provider that is being unregistered.
     */
    public static void unregisterProvider(IconProvider provider) {
        if(provider == null) {
            return;
        }

        if(providerList.contains(provider)) {
            providerList.remove(provider);
            logger.info("unregistered icon provider `" + provider.getName() + "`.");
        }
    }

    /**
     * Unregister a Icon Provider by its name
     *
     * @param provider  the name of the icon provider that is being unregistered.
     */
    public static void unregisterProvider(String provider) {
        if(provider == null || provider.length() == 0) {
            return;
        }

        Iterator<IconProvider> providerIterator = providerList.iterator();
        while(providerIterator.hasNext()) {
            IconProvider iconProvider = providerIterator.next();

            if(iconProvider.getName().equalsIgnoreCase(provider)) {
                providerIterator.remove();
                logger.info("unregistered icon provider `" + iconProvider.getName() + "`.");
                return;
            }
        }
    }

    /**
     * Gets the Icon Provider for the given icon
     *
     * @param icon      name of icon
     * @return          IconProvider
     */
    public static IconProvider getProviderForIcon(String icon) {
        if(icon == null || icon.length() == 0) {
            return null;
        }

        for (IconProvider iconProvider : providerList) {
            if (iconProvider.hasIcon(icon)) {
                return iconProvider;
            }
        }

        return null;
    }


    /**
     * Creates a Image of the icon
     *
     * @param icon          name of icon
     * @param properties    icon properties
     * @return              Image
     */
    public static Image buildImage(String icon, IconProperties properties) {
        IconProvider provider = getProviderForIcon(icon);

        if(provider == null) {
            return null;
        }

        return provider.getBuilder().buildImage(provider, icon, properties);
    }

    /**
     * Creates a Icon of the icon
     *
     * @param icon          name of icon
     * @param properties    icon properties
     * @return              Icon
     */
    public static Icon buildIcon(String icon, IconProperties properties) {
        IconProvider provider = getProviderForIcon(icon);

        if(provider == null) {
            throw new IllegalArgumentException("no provider was found for the icon `" + icon + "`");
        }

        return provider.getBuilder().buildIcon(provider, icon, properties);
    }
}
