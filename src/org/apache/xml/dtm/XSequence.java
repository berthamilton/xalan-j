/*
 * The Apache Software License, Version 1.1
 *
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Xalan" and "Apache Software Foundation" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation and was
 * originally based on software copyright (c) 1999, Lotus
 * Development Corporation., http://www.lotus.com.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */
 
// %REVIEW% Should this move to the XPath packages? 
package org.apache.xml.dtm;

import java.util.Vector;


/** This interface provides an API representation for the XPath 2 Data Model's "Sequences"
 * -- which are the basic representation for typed values. Only built-in types, 
 * types derived from built-ins, and sets thereof are returned directly by the XPath2
 * DM; complex schema-types must be accessed through the document tree.
 * 
 * It also defines a manefest constant, EMPTY, which is a singleton immutable 
 * XSequence with no contents. Per the XPath2 spec, this should be returned rather
 * than null when no typed value exists.
 * 
 * %REVIEW% Should this be moved to the XPath package?
 * */
public interface XSequence {
	/** @return the number of members in this sequence. */
	public int getLength();
	
	/** Retrieve the value for this member of the sequence. Since values may be
	 * a heterogenous mix, and their type may not be known until they're examined,
	 * they're returned as Objects. This is storage-inefficient if the value(s)
	 * is/are builtins that map to Java primitives. Tough.
	 * 
	 * @param index 0-based index into the sequence.
	 * @return the specified value
	 * @throws exception if index <0 or >=length	 
	 * */
	public Object getValue(int index);
	
	/** Retrieve the datatype namespace URI for this member of the sequence.
	 * 
	 * @param index 0-based index into the sequence.
	 * @return the namespace for the specified value's type
	 * @throws exception if index <0 or >=length	 
	 * */
	public String getTypeNS(int index);
	
	/** Retrieve the datatype namespace URI for this member of the sequence.
	 * 
	 * %REVIEW% Do we really need type -- 
	 * or can we just tell folks to do instanceOf on the Java value objects?	
	 * 
	 * @param index 0-based index into the sequence.
	 * @return the localname of the specified value's type
	 * @throws exception if index <0 or >=length	 
	 * */
	public String getTypeLocalName(int index);
	
	/** Ask whether this member's datatype equals or is derived from a specified
	 * schema NSURI/localname pair.
	 * 
	 * @param index 0-based index into the sequence.
	 * @return true if the type is an instance of this schema datatype,
	 * false if it isn't.
	 * @throws exception if index <0 or >=length	 
	 * */
	public boolean isSchemaType(int index, String namespace, String localname);
	
	public static final XSequence EMPTY= new XSequence() {
		public int getLength()
		{ return 0; }
		
		public Object getValue(int index)
		{ throw new java.lang.ArrayIndexOutOfBoundsException(); }
		
		public String getTypeNS(int index)
		{ throw new java.lang.ArrayIndexOutOfBoundsException(); }
		
		public String getTypeLocalName(int index)
		{ throw new java.lang.ArrayIndexOutOfBoundsException(); }
		
		public boolean isSchemaType(int index, String namespace, String localname)
		{ throw new java.lang.ArrayIndexOutOfBoundsException(); }
	};
}

