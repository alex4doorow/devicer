package com.afa.devicer.core.integration;

import com.afa.devicer.core.errors.CoreException;

public interface Consumer {
	void consume(Payload payload) throws CoreException;
	default void init() throws Exception {};
}
