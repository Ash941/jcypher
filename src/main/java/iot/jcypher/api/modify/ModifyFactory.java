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

package iot.jcypher.api.modify;

import iot.jcypher.api.APIObject;
import iot.jcypher.api.APIObjectAccess;
import iot.jcypher.api.collection.DoConcat;
import iot.jcypher.ast.modify.ModifyExpression;
import iot.jcypher.ast.modify.ModifyLabels;
import iot.jcypher.ast.modify.PropertiesCopy;
import iot.jcypher.ast.modify.ModifyExpression.ModifyAction;
import iot.jcypher.values.JcElement;
import iot.jcypher.values.JcLabel;
import iot.jcypher.values.JcNode;
import iot.jcypher.values.JcProperty;
import iot.jcypher.values.ValueAccess;
import iot.jcypher.values.ValueElement;

public class ModifyFactory {

	public static ModifyTerminal createModifyTerminal(APIObject from) {
		ModifyExpression mx = (ModifyExpression) APIObjectAccess.getAstNode(from);
		return new ModifyTerminal(mx);
	}
	
	public static Set<DoConcat> setPropertyInFOREACH(JcProperty jcProperty, DoConcat connector) {
		ModifyExpression mx = new ModifyExpression(ModifyAction.SET);
		mx.setToModify(jcProperty);
		return new Set<DoConcat>(mx, connector);
	}
	
	public static Set<ModifyTerminal> setPropertyInDO(JcProperty jcProperty) {
		ModifyExpression mx = new ModifyExpression(ModifyAction.SET);
		mx.setToModify(jcProperty);
		return new Set<ModifyTerminal>(mx, new ModifyTerminal(mx));
	}
	
	public static CopyProperties<DoConcat> copyPropertiesFromInFOREACH(JcElement jcElement, DoConcat connector) {
		ModifyExpression mx = new ModifyExpression(ModifyAction.SET);
		PropertiesCopy pc = new PropertiesCopy();
		pc.setSource(jcElement);
		mx.setPropertiesCopy(pc);
		return new CopyProperties<DoConcat>(mx, connector);
	}
	
	public static CopyProperties<ModifyTerminal> copyPropertiesFromInDO(JcElement jcElement) {
		ModifyExpression mx = new ModifyExpression(ModifyAction.SET);
		PropertiesCopy pc = new PropertiesCopy();
		pc.setSource(jcElement);
		mx.setPropertiesCopy(pc);
		return new CopyProperties<ModifyTerminal>(mx, new ModifyTerminal(mx));
	}
	
	public static ModifyTerminal setLabel(JcLabel label) {
		ModifyExpression mx = new ModifyExpression(ModifyAction.SET);
		ModifyLabels mls = new ModifyLabels();
		ValueElement pred = label;
		while (pred instanceof JcLabel) {
			mls.addLabel(0, ValueAccess.getValue((JcLabel)pred).toString());
			pred = ValueAccess.getPredecessor(pred);
		}
		mls.setTargetNode((JcNode) pred);
		mx.setModifyLabels(mls);
		return new ModifyTerminal(mx);
	}
	
	public static ModifyTerminal removeProperty(JcProperty jcProperty) {
		ModifyExpression mx = new ModifyExpression(ModifyAction.REMOVE);
		mx.setToModify(jcProperty);
		return new ModifyTerminal(mx);
	}
	
	public static ModifyTerminal removeLabel(JcLabel label) {
		ModifyExpression mx = new ModifyExpression(ModifyAction.REMOVE);
		ModifyLabels mls = new ModifyLabels();
		ValueElement pred = label;
		while (pred instanceof JcLabel) {
			mls.addLabel(0, ValueAccess.getValue((JcLabel)pred).toString());
			pred = ValueAccess.getPredecessor(pred);
		}
		mls.setTargetNode((JcNode) pred);
		mx.setModifyLabels(mls);
		return new ModifyTerminal(mx);
	}
	
	public static ModifyTerminal deleteElement(JcElement jcElement) {
		ModifyExpression mx = new ModifyExpression(ModifyAction.DELETE);
		mx.setElementToDelete(jcElement);
		return new ModifyTerminal(mx);
	}
}