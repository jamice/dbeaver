/*
 * DBeaver - Universal Database Manager
 * Copyright (C) 2010-2016 Serge Rieder (serge@jkiss.org)
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (version 2)
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
/*
 * Created on Jul 18, 2004
 */
package org.jkiss.dbeaver.ext.erd.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.viewers.CellEditor;
import org.jkiss.dbeaver.ext.erd.part.AttributePart;

/**
 * EditPolicy for the direct editing of Column names
 * 
 * @author Serge Rieder
 */
public class ColumnDirectEditPolicy extends DirectEditPolicy
{

	private String oldValue;

	/**
	 * @see DirectEditPolicy#getDirectEditCommand(org.eclipse.gef.requests.DirectEditRequest)
	 */
	@Override
    protected Command getDirectEditCommand(DirectEditRequest request)
	{
/*
		AttributeResetNameTypeCommand cmd = new AttributeResetNameTypeCommand();
		ERDEntityAttribute column = (ERDEntityAttribute) getHost().getModel();
		cmd.setSource(column);
		cmd.setOldName(column.getName());
		cmd.setOldType(column.getType());
		CellEditor cellEditor = request.getCellEditor();
		cmd.setNameType((String) cellEditor.getValue());
		return cmd;
*/
        return null;
	}

	/**
	 * @see DirectEditPolicy#showCurrentEditValue(org.eclipse.gef.requests.DirectEditRequest)
	 */
	@Override
    protected void showCurrentEditValue(DirectEditRequest request)
	{
		String value = (String) request.getCellEditor().getValue();
		AttributePart attributePart = (AttributePart) getHost();
		attributePart.handleNameChange(value);
	}

	/**
	 * @param to
	 *            Revert request
	 */
	@Override
    protected void storeOldEditValue(DirectEditRequest request)
	{
		CellEditor cellEditor = request.getCellEditor();
		oldValue = (String) cellEditor.getValue();
	}

	/**
	 * @param request
	 */
	@Override
    protected void revertOldEditValue(DirectEditRequest request)
	{
		CellEditor cellEditor = request.getCellEditor();
		cellEditor.setValue(oldValue);
		AttributePart attributePart = (AttributePart) getHost();
		attributePart.revertNameChange(oldValue);
		
	}
}