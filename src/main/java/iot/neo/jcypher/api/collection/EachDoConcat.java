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

package iot.neo.jcypher.api.collection;

import iot.neo.jcypher.api.APIObject;
import iot.neo.jcypher.ast.collection.CollectExpression;

public class EachDoConcat extends APIObject {

	EachDoConcat(CollectExpression cx) {
		super();
		this.astNode = cx;
	}
	
	/**
	 * <div color='red' style="font-size:24px;color:red"><b><i><u>JCYPHER</u></i></b></div>
	 * <div color='red' style="font-size:18px;color:red"><i>start the DO part of a FOREACH expression</i></div>
	 * <div color='red' style="font-size:18px;color:red"><i>e.g. ...<b>DO()</b>
				.SET(n.property("marked")).to(true)...</i></div>
	 * <br/>
	 */
	public Do DO() {
		Do ret = new Do((CollectExpression)this.astNode);
		return ret;
	}
}
