package com.whisman.modules.test.shiro;

import static org.junit.Assert.*;

import com.whisman.modules.test.security.shiro.ShiroTestUtils;
import org.apache.shiro.SecurityUtils;
import org.junit.Test;

public class ShiroTestUtilsTest {

	@Test
	public void mockSubject() {
		ShiroTestUtils.mockSubject("foo");
		assertTrue(SecurityUtils.getSubject().isAuthenticated());
		assertEquals("foo", SecurityUtils.getSubject().getPrincipal());

		ShiroTestUtils.clearSubject();

		ShiroTestUtils.mockSubject("bar");
		assertTrue(SecurityUtils.getSubject().isAuthenticated());
		assertEquals("bar", SecurityUtils.getSubject().getPrincipal());

	}

}
