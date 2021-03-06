/*
 * Copyright 2016 Carnegie Mellon University All Rights Reserved.
 *
 * THIS SOFTWARE IS PROVIDED "AS IS," WITH NO WARRANTIES WHATSOEVER. CARNEGIE
 * MELLON UNIVERSITY EXPRESSLY DISCLAIMS TO THE FULLEST EXTENT PERMITTEDBY LAW
 * ALL EXPRESS, IMPLIED, AND STATUTORY WARRANTIES, INCLUDING, WITHOUT
 * LIMITATION, THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, AND NON-INFRINGEMENT OF PROPRIETARY RIGHTS.

 * This Program is distributed under a BSD license.  Please see LICENSE file
 * or permission@sei.cmu.edu for more information.
 *
 * DM-0003520
 */

package edu.cmu.sei.aaspe.propagations;

import java.util.ArrayList;
import java.util.List;

import org.osate.aadl2.ComponentCategory;
import org.osate.aadl2.instance.ComponentInstance;

import edu.cmu.sei.aaspe.model.Propagation;
import edu.cmu.sei.aaspe.utils.ComponentUtils;

public class SharedMemory extends AbstractPropagation {


	@Override
	public List<Propagation> getPropagations(ComponentInstance component)
	{
		ArrayList<Propagation> result;

		result = new ArrayList<Propagation> ();

		/**
		 * If the component impacted is a memory, a security issue will then propagate through
		 * all the system.
		 */
//		if ( (component.getCategory() == ComponentCategory.MEMORY) || (component.getCategory() == ComponentCategory.VIRTUAL_MEMORY))
		if (component.getCategory() == ComponentCategory.MEMORY)

		{
			List<ComponentInstance> boundComponents;
			boundComponents = ComponentUtils.getBoundComponentsToMemory(component);
			for (ComponentInstance boundComponent : boundComponents)
			{
				if (boundComponent.getCategory() != ComponentCategory.THREAD)
				{
					addPropagation(result, component, Propagation.PROPAGATION_MEMORY, boundComponent);
					
				}
			}
		}
		return result;
	}
}
