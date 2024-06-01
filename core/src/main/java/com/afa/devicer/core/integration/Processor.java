package com.afa.devicer.core.integration;

import com.afa.devicer.core.errors.CoreException;

public interface Processor {
	void process(Payload payload) throws CoreException;
	default void init() throws Exception {};
}
