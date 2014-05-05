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

package iot.neo.jcypher.api.predicate;

import iot.neo.jcypher.api.APIObject;
import iot.neo.jcypher.api.collection.CFactory;
import iot.neo.jcypher.api.collection.IPredicateFunction;
import iot.neo.jcypher.api.pattern.Element;
import iot.neo.jcypher.api.pattern.IElement;
import iot.neo.jcypher.api.pattern.XFactory;
import iot.neo.jcypher.ast.collection.CollectExpression;
import iot.neo.jcypher.ast.predicate.BooleanOp;
import iot.neo.jcypher.ast.predicate.BooleanOp.Operator;
import iot.neo.jcypher.ast.predicate.ExistsPattern;
import iot.neo.jcypher.ast.predicate.IPredicateHolder;
import iot.neo.jcypher.ast.predicate.Negation;
import iot.neo.jcypher.ast.predicate.PredicateExpression;
import iot.neo.jcypher.ast.predicate.PredicateFunction;
import iot.neo.jcypher.ast.predicate.SubExpression;
import iot.neo.jcypher.values.IHas;
import iot.neo.jcypher.values.ValueElement;


public class Concat extends APIObject implements IBeforePredicate {

	Concat(PredicateExpression pe) {
		super();
		this.astNode = pe;
	}
	
	@Override
	public BooleanOperation valueOf(ValueElement val) {
		IPredicateHolder pc = getLastPredicateHolder();
		BooleanOp bo = new BooleanOp();
		bo.setOperand1(val);
		pc.setPredicate(bo);
		BooleanOperation vxpr = new BooleanOperation((PredicateExpression)this.astNode);
		return vxpr;
	}

	@Override
	public Concatenator has(IHas val) {
		IPredicateHolder pc = getLastPredicateHolder();
		BooleanOp bo = new BooleanOp();
		bo.setOperand1((ValueElement)val);
		bo.setOperator(Operator.HAS);
		pc.setPredicate(bo);
		Concatenator ret = new Concatenator((PredicateExpression)this.astNode);
		return ret;
	}

	@Override
	public IBeforePredicate NOT() {
		IPredicateHolder pc = getLastPredicateHolder();
		pc.setPredicate(new Negation());
		return this;
	}
	
	@Override
	public Concat BR_OPEN() {
		IPredicateHolder pc = getLastPredicateHolder();
		SubExpression subxpr = new SubExpression();
		PredicateExpression xpr = new PredicateExpression();
		subxpr.setPredicateExpression(xpr);
		xpr.setParent((PredicateExpression)this.astNode);
		pc.setPredicate(subxpr);
		Concat concat = new Concat(xpr);
		return concat;
	}
	
	@Override
	public Concatenator existsPattern(IElement X) {
		if (!(X instanceof Element<?>))
			throw new RuntimeException("invalid expression");
		IPredicateHolder pc = getLastPredicateHolder();
		ExistsPattern ep = new ExistsPattern();
		ep.setPatternExpression(XFactory.getExpression(X));
		pc.setPredicate(ep);
		Concatenator ret = new Concatenator((PredicateExpression)this.astNode);
		return ret;
	}
	
	@Override
	public Concatenator holdsTrue(IPredicateFunction I) {
		IPredicateHolder pc = getLastPredicateHolder();
		CollectExpression collXpr = CFactory.getRootCollectExpression((APIObject)I);
		PredicateFunction pf = new PredicateFunction();
		pf.setCollectExpression(collXpr);
		pc.setPredicate(pf);
		Concatenator ret = new Concatenator((PredicateExpression)this.astNode);
		return ret;
	}
	
	private IPredicateHolder getLastPredicateHolder() {
		return ((PredicateExpression)this.astNode).getLastPredicateHolder();
	}
}
