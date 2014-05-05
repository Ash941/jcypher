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

package iot.neo.jcypher.api.returns;

import iot.neo.jcypher.api.APIObject;
import iot.neo.jcypher.api.APIObjectAccess;
import iot.neo.jcypher.api.collection.CFactory;
import iot.neo.jcypher.api.collection.ICollectExpression;
import iot.neo.jcypher.api.pattern.IElement;
import iot.neo.jcypher.api.pattern.XFactory;
import iot.neo.jcypher.api.predicate.Concatenator;
import iot.neo.jcypher.ast.collection.CollectExpression;
import iot.neo.jcypher.ast.collection.CollectionSpec;
import iot.neo.jcypher.ast.predicate.PredicateExpression;
import iot.neo.jcypher.ast.returns.ReturnBoolean;
import iot.neo.jcypher.ast.returns.ReturnCollection;
import iot.neo.jcypher.ast.returns.ReturnElement;
import iot.neo.jcypher.ast.returns.ReturnExpression;
import iot.neo.jcypher.ast.returns.ReturnPattern;
import iot.neo.jcypher.values.JcCollection;
import iot.neo.jcypher.values.JcValue;

public class RFactory {

	public static RTerminal ALL() {
		ReturnExpression rx = new ReturnExpression();
		ReturnElement elem = new ReturnElement();
		elem.setAll();
		rx.setReturnValue(elem);
		return new RTerminal(rx);
	}
	
	public static RSortable value(JcValue element) {
		ReturnExpression rx = new ReturnExpression();
		ReturnElement elem = new ReturnElement();
		elem.setElement(element);
		rx.setReturnValue(elem);
		return new RSortable(rx);
	}

	public static RElement<RElement<?>> resultOf(Concatenator predicateExpression) {
		CollectExpression cx = CFactory.getRootCollectExpression(predicateExpression);
		if (cx != null) {
			return resultOfCollection(cx);
		}
		
		ReturnExpression rx = new ReturnExpression();
		ReturnBoolean bool = new ReturnBoolean();
		PredicateExpression px = (PredicateExpression) APIObjectAccess.getAstNode(predicateExpression);
		bool.setPredicateExpression(px);
		rx.setReturnValue(bool);
		return new RElement<RElement<?>>(rx);
	}
	
	public static RElement<RElement<?>> existsPattern(IElement expression) {
		ReturnExpression rx = new ReturnExpression();
		ReturnPattern pat = new ReturnPattern();
		pat.setPatternExpression(
				XFactory.getExpression(expression));
		rx.setReturnValue(pat);
		return new RElement<RElement<?>>(rx);
	}
	
	public static RElement<RElement<?>> resultOf(ICollectExpression xpr) {
		return resultOfCollection(CFactory.getRootCollectExpression((APIObject)xpr));
	}
	
	public static RElement<RElement<?>> resultOf(JcCollection collection) {
		ReturnExpression rx = new ReturnExpression();
		ReturnCollection coll = new ReturnCollection();
		CollectionSpec cs = new CollectionSpec(collection);
		CollectExpression cx = new CollectExpression();
		cx.setCollectionToOperateOn(cs);
		coll.setCollectExpression(cx);
		rx.setReturnValue(coll);
		return new RElement<RElement<?>>(rx);
	}
	
	public static RCount count() {
		ReturnExpression rx = new ReturnExpression();
		rx.setCount();
		return new RCount(rx);
	}
	
	public static RDistinct DISTINCT() {
		ReturnExpression rx = new ReturnExpression();
		rx.setDistinct();
		return new RDistinct(rx);
	}
	
	private static RElement<RElement<?>> resultOfCollection(CollectExpression collXpr) {
		ReturnExpression rx = new ReturnExpression();
		ReturnCollection coll = new ReturnCollection();
		coll.setCollectExpression(collXpr);
		rx.setReturnValue(coll);
		return new RElement<RElement<?>>(rx);
	}
}
