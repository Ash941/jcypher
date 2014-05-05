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

package iot.neo.jcypher.factories.xpression;

import iot.neo.jcypher.api.collection.CFactory;
import iot.neo.jcypher.api.collection.CFrom;
import iot.neo.jcypher.api.collection.CTerminal;
import iot.neo.jcypher.api.collection.CWhere;
import iot.neo.jcypher.api.collection.CollectFrom;
import iot.neo.jcypher.api.collection.Collection;
import iot.neo.jcypher.api.collection.EXProperty;
import iot.neo.jcypher.api.collection.ExtractExpression;
import iot.neo.jcypher.api.collection.ReduceTo;
import iot.neo.jcypher.values.JcCollection;

/**
 * <div color='red' style="font-size:24px;color:red"><b><i>JCYPHER FACTORY FOR COLLECTION EXPRESSIONS</i></b></div>
 */
public class C {

	/**
	 * <div color='red' style="font-size:24px;color:red"><b><i><u>JCYPHER</u></i></b></div>
	 * <div color='red' style="font-size:18px;color:red"><i>collect properties from a collection of property containers (nodes or relations)</i></div>
	 * <div color='red' style="font-size:18px;color:red"><i>e.g. ...<b>COLLECT()</b>.property("name").from(p.nodes)</i></div>
	 * <br/>
	 */
	public static EXProperty<CollectFrom> COLLECT() {
		return CFactory.COLLECT();
	}
	
	/**
	 * <div color='red' style="font-size:24px;color:red"><b><i><u>JCYPHER</u></i></b></div>
	 * <div color='red' style="font-size:18px;color:red"><i>go through a collection, run an expression on every element of the collection,
	 * <br/>and return the results as a collection of these values</i></div>
	 * <div color='red' style="font-size:18px;color:red"><i>e.g. ...<b>EXTRACT()</b>.valueOf(n.numberProperty("age").div(2)).fromAll(n).IN_nodes(p)</i></div>
	 * <br/>
	 */
	public static ExtractExpression EXTRACT() {
		return CFactory.EXTRACT();
	}
	
	/**
	 * <div color='red' style="font-size:24px;color:red"><b><i><u>JCYPHER</u></i></b></div>
	 * <div color='red' style="font-size:18px;color:red"><i>return all the elements in a collection that hold true for a predicate expression</i></div>
	 * <div color='red' style="font-size:18px;color:red"><i>e.g. ...<b>FILTER()</b>.fromAll(x).IN(a.collectionProperty("array")).<b>WHERE()</b>...</i></div>
	 * <br/>
	 */
	public static CFrom<CWhere> FILTER() {
		return CFactory.FILTER();
	}
	
	/**
	 * <div color='red' style="font-size:24px;color:red"><b><i><u>JCYPHER</u></i></b></div>
	 * <div color='red' style="font-size:18px;color:red"><i>run an expression against individual elements of a collection
	 * </br>and store the result of the expression in an accumulator</i></div>
	 * <div color='red' style="font-size:18px;color:red"><i>e.g. ...<b>REDUCE()</b><br/>.fromAll(n).IN_nodes(p)<br/>.to(totalAge)<br/>.by(totalAge.plus(n.numberProperty("age")))<br/>.startWith(0)</i></div>
	 * <br/>
	 */
	public static CFrom<ReduceTo> REDUCE() {
		return CFactory.REDUCE();
	}
	
	/**
	 * <div color='red' style="font-size:24px;color:red"><b><i><u>JCYPHER</u></i></b></div>
	 * <div color='red' style="font-size:18px;color:red"><i>return all but the first element in a collection</i></div>
	 * <div color='red' style="font-size:18px;color:red"><i>e.g. ...<b>TAIL()</b>.FILTER()...</i></div>
	 * <br/>
	 */
	public static Collection TAIL() {
		return CFactory.TAIL();
	}
	
	/**
	 * <div color='red' style="font-size:24px;color:red"><b><i><u>JCYPHER</u></i></b></div>
	 * <div color='red' style="font-size:18px;color:red"><i>return all but the first element in a collection</i></div>
	 * <div color='red' style="font-size:18px;color:red"><i>e.g. ...<b>TAIL(p.nodes())</b></i></div>
	 * <br/>
	 */
	public static CTerminal TAIL(JcCollection collection) {
		return CFactory.TAIL(collection);
	}
}
