/************************************************************************
 * Copyright (c) 2014 IoT-Solutions e.U.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *  http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ************************************************************************/

package iot.neo.jcypher.values;

import iot.neo.jcypher.values.functions.FUNCTION;

public class JcCollection extends JcValue {
	
	JcCollection() {
		super();
	}

	/**
	 * <div color='red' style="font-size:24px;color:red"><b><i><u>JCYPHER</u></i></b></div>
	 * <div color='red' style="font-size:18px;color:red"><i>create a collection which is identified by a name</i></div>
	 * <br/>
	 */
	public JcCollection(String name) {
		super(name);
	}
	
	JcCollection(String name, ValueElement predecessor, IOperatorOrFunction opf) {
		super(name, predecessor, opf);
	}

	/**
	 * <div color='red' style="font-size:24px;color:red"><b><i><u>JCYPHER</u></i></b></div>
	 * <div color='red' style="font-size:18px;color:red"><i>return the size of a collection, return a <b>JcNumber</b></i></div>
	 * <br/>
	 */
	public JcNumber length() {
		return new JcNumber(null, this,
				new FunctionInstance(FUNCTION.Collection.LENGTH, 1));
	}
	
	/**
	 * <div color='red' style="font-size:24px;color:red"><b><i><u>JCYPHER</u></i></b></div>
	 * <div color='red' style="font-size:18px;color:red"><i>return the first element of a collection</i></div>
	 * <br/>
	 */
	public ValueElement head() {
		return new ValueElement(this, 
				new FunctionInstance(FUNCTION.Collection.HEAD, 1));
	}
	
	/**
	 * <div color='red' style="font-size:24px;color:red"><b><i><u>JCYPHER</u></i></b></div>
	 * <div color='red' style="font-size:18px;color:red"><i>return the last element of a collection</i></div>
	 * <br/>
	 */
	public ValueElement last() {
		return new ValueElement(this, 
				new FunctionInstance(FUNCTION.Collection.LAST, 1));
	}
}
