package com.vthacker.luceneExamples;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

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
   * @throws IOException 
   */
  public void testAutosuggest() throws IOException {
    
    String[] docs = {"foo","food","fail"};
    
    Directory dir = new RAMDirectory();
    IndexWriterConfig iwConfig = new IndexWriterConfig(Version.LUCENE_42, Autosuggest.getAnalyzer());
    
    IndexWriter iw = new IndexWriter(dir, iwConfig);
    
    
    for(int i=0; i<3; i++) {
      Document doc = new Document();
      doc.add(new TextField(Autosuggest.FIELD, new StringReader(docs[i])));
      iw.addDocument(doc);
    }
    iw.commit();
    
    
    Autosuggest suggestor = new Autosuggest();
    boolean success = suggestor.buildSuggestor(dir, Autosuggest.FIELD);
    if(success) {
      String[] results = suggestor.suggest("foo");
      assertEquals(2, results.length);
      assertEquals("foo", results[0]);
      assertEquals("food", results[1]);
    }
    else {
      assertTrue("Failed to build suggestor", false);
    }
    
  }
}
