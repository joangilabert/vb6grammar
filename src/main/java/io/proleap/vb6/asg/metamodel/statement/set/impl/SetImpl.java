/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.set.impl;

import io.proleap.vb6.VisualBasic6Parser.SetStmtContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.call.Call.CallType;
import io.proleap.vb6.asg.metamodel.impl.ScopedElementImpl;
import io.proleap.vb6.asg.metamodel.statement.StatementType;
import io.proleap.vb6.asg.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.asg.metamodel.statement.set.Set;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;

public class SetImpl extends ScopedElementImpl implements Set {

	protected final SetStmtContext ctx;

	protected Call leftHandCall;

	protected ValueStmt rightHandValueStmt;

	protected final StatementType statementType = StatementTypeEnum.SET;

	public SetImpl(final Module module, final Scope scope, final SetStmtContext ctx) {
		super(module.getProgram(), module, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public SetStmtContext getCtx() {
		return ctx;
	}

	@Override
	public Call getLeftHandCall() {
		return leftHandCall;
	}

	@Override
	public ValueStmt getRightHandValueStmt() {
		return rightHandValueStmt;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public boolean isSettingReturnVariable() {
		return CallType.RETURN_VALUE_CALL.equals(leftHandCall.getCallType());
	}

	@Override
	public void setLeftHandCall(final Call leftHandCall) {
		this.leftHandCall = leftHandCall;
	}

	@Override
	public void setRightHandValueStmt(final ValueStmt rightHandValueStmt) {
		this.rightHandValueStmt = rightHandValueStmt;
	}

	@Override
	public String toString() {
		return "leftHandCall=[" + leftHandCall + "]";
	}

}
