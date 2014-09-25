/**
 * Tomitribe Confidential
 * <p/>
 * Copyright(c) Tomitribe Corporation. 2014
 * <p/>
 * The source code for this program is not published or otherwise divested
 * of its trade secrets, irrespective of what has been deposited with the
 * U.S. Copyright Office.
 * <p/>
 */
package com.tomitribe.ee;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.URL;

/**
 * Arquillian Step 9 - Our first Arquillian test
 */
@RunWith(Arquillian.class)
public class TestArquillianBasic {

    @ArquillianResource
    public URL url;

    @Deployment(testable = false)
    public static WebArchive deploy() {
        return ShrinkWrap.create(WebArchive.class, TestArquillianBasic.class.getName()) //Name is just convenient
            .addClass(TestArquillianBasic.class) //Add our classes to test
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml"); //Turn on CDI
    }

    @Test
    public void testRunningInDep1() throws IOException {
//        final String content = org.apache.ziplock.IO.slurp(url);
//        assertEquals("Hello from TomEE 1", content);
    }
}
