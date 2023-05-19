/*
 * Copyright (c) 2019, Lucas <https://github.com/Lucwousin>
 * All rights reserved.
 *
 * This code is licensed under GPL3, see the complete license in
 * the LICENSE file in the root directory of this submodule.
 *
 * Copyright (c) 2016-2017, Adam <Adam@sigterm.info>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.deob;

import net.runelite.asm.*;

import java.util.ArrayList;
import java.util.List;

public class SyncRuneLitePatches
{

	public static void run(ClassGroup group1, ClassGroup group2)
	{
		for (final ClassFile classFile : group1)
		{
			ClassFile other = findMatchingClass(classFile, group2);

			if (other != null) {
				List<Method> otherMethods = other.getMethods();
				ArrayList<Method> sortedMethods = new ArrayList<>();

				for (Method method : classFile.getMethods()) {
					for (Method otherMethod : otherMethods) {
						if (method.getDescriptor().equals(otherMethod.getDescriptor())) {
							sortedMethods.add(otherMethod);
							break;
						}
					}
				}

				other.setMethods(sortedMethods);
			}
		}
	}

	private static ClassFile findMatchingClass(ClassFile cf, ClassGroup group2) {
		for (ClassFile other : group2.getClasses()) {
			if (other.getClassName().equals(cf.getClassName())) {
				return other;
			}
		}
		return null;
	}
}
