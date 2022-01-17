package com.jiconic.io;
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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * A utility for resource loading from different locations.
 *
 */
public class Loader {
	private static ArrayList<Location> locations = new ArrayList<>();

	static {
		locations.add(new ClasspathLocation());
		locations.add(new SystemLocation(new File(".")));
	}

	/**
	 * Add a location that will be searched for resources
	 *
	 * @param location The location that will be searched for resoruces
	 */
	public static void addLocation(Location location) {
		locations.add(location);
	}

	/**
	 * Remove a location that will be no longer be searched for resources
	 *
	 * @param location The location that will be removed from the search list
	 */
	public static void removeLocation(Location location) {
		locations.remove(location);
	}

	/**
	 * Remove all the locations, no resources will be found until new locations
	 * have been added
	 */
	public static void removeAllLocations() {
		locations.clear();
	}

	/**
	 * Loads a URL of a resource from the file system.
	 *
	 * @param path The path of the resource Returns The URL of a resource from
	 *             the file system.
	 */
	public static URL getResource(String path) {
		URL url = null;

		for (Location location : locations) {
			url = location.getResource(path);

			if (url != null)
				break;
		}

		if (url == null)
			throw new RuntimeException("Resource not found: " + path);

		return url;
	}

	/**
	 * Streams a resource from the file system.
	 *
	 * @param path The path of the resource Returns The InputStream of a
	 *             resource from the file system.
	 */
	public static InputStream getResourceAsStream(String path) {
		return streamFromUrl(getResource(path));
	}

	/**
	 * Converts a URL into an InputStream.
	 *
	 * @param url The URL to convert Returns The converted InputStream.
	 */
	public static InputStream streamFromUrl(URL url) {
		try {
			return new BufferedInputStream(url.openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
