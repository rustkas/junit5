/*
 * Copyright 2015-2017 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.junit.jupiter.engine.descriptor;

import static org.junit.platform.commons.meta.API.Usage.Internal;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Optional;

import org.junit.jupiter.engine.execution.AbstractExtensionContext;
import org.junit.jupiter.engine.execution.ThrowableCollector;
import org.junit.platform.commons.meta.API;
import org.junit.platform.engine.EngineExecutionListener;

/**
 * @since 5.0
 */
@API(Internal)
public final class MethodExtensionContext extends AbstractExtensionContext<MethodTestDescriptor> {

	private final ThrowableCollector throwableCollector;

	public MethodExtensionContext(AbstractExtensionContext<?> parent, EngineExecutionListener engineExecutionListener,
			MethodTestDescriptor testDescriptor, ThrowableCollector throwableCollector) {

		super(parent, engineExecutionListener, testDescriptor);

		this.throwableCollector = throwableCollector;
	}

	@Override
	public Optional<AnnotatedElement> getElement() {
		return Optional.of(getTestDescriptor().getTestMethod());
	}

	@Override
	public Optional<Class<?>> getTestClass() {
		return Optional.of(getTestDescriptor().getTestClass());
	}

	@Override
	public Optional<Method> getTestMethod() {
		return Optional.of(getTestDescriptor().getTestMethod());
	}

	@Override
	public Optional<Throwable> getExecutionException() {
		return Optional.ofNullable(this.throwableCollector.getThrowable());
	}

}
