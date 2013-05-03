package com.vthacker.luceneExamples;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AutosuggestTest extends TestCase {
  
  /**
   * Create the test case
   * 
   * @param testName name of the test case
   */
  public AutosuggestTest(String testName) {
    super(testName);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite(AutosuggestTest.class);
  }

  /**
   * Rigourous Test :-)
   */
  public void testAutosuggest() {
    assertTrue(true);
  }
}
