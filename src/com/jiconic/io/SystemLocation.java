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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * A resource loading path that searches somewhere on the classpath.
 *
 */
public class SystemLocation implements Location {
	private File root;

	/**
	 * Create a new resource path based on the file system.
	 *
	 * @param root The root of the file system to search.
	 */
	public SystemLocation(File root) {
		this.root = root;
	}

	@Override
	public URL getResource(String path) {
		try {
			File file = new File(root, path);

			if (!file.exists())
				file = new File(path);
			if (!file.exists())
				return null;

			return file.toURI().toURL();
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public InputStream getResourceAsStream(String path) {
		try {
			File file = new File(root, path);

			if (!file.exists())
				file = new File(path);

			return new FileInputStream(file);
		} catch (IOException e) {
			return null;
		}
	}
}
