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

import java.io.InputStream;
import java.net.URL;


/**
 * A location that a resource can be loaded from.
 *
 */
public interface Location {
	/**
	 * Get a resource as an input stream
	 *
	 * @param location The location to the resource to retrieve.
	 * @return A stream from which the resource can be read or null if the
	 * resource can't be found in this location.
	 */
	InputStream getResourceAsStream(String location);

	/**
	 * Get a resource as a URL
	 *
	 * @param location The location to the resource to retrieve.
	 * @return A URL from which the resource can be read.
	 */
	URL getResource(String location);
}
