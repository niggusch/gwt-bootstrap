package com.pragamtics.gwtp.client;

import com.google.gwt.junit.client.GWTTestCase;

public class SandboxGwtTest extends GWTTestCase {
    @Override
    public String getModuleName() {
        return "com.pragmatics.gwtp.gwtp-bootstrap";
    }

    public void testSandbox() {
        assertTrue(true);
    }
}