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

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.ziplock.IO;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.descriptor.api.Descriptors;
import org.jboss.shrinkwrap.descriptor.api.webapp30.WebAppDescriptor;
import org.jboss.shrinkwrap.descriptor.api.webcommon30.WebAppVersionType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Application;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

@RunWith(Arquillian.class)
public class TestArquillianHolyMoly {

    @ArquillianResource
    public URL url;

    @Deployment(testable = false)
    public static WebArchive deploy() {

        /**
         * This looks nasty at first glance, but what we are doing here
         */

        final String xml = Descriptors.create(WebAppDescriptor.class)
            .version(WebAppVersionType._3_0)
            .getOrCreateServlet()
            .servletName("jaxrs")
            .servletClass(Application.class.getName())
            .createInitParam()
            .paramName(Application.class.getName())
            .paramValue(Application.class.getName())
            .up()
            .up()
            .getOrCreateServletMapping()
            .servletName("jaxrs")
            .urlPattern("/api")
            .up()
            .exportAsString();

        return ShrinkWrap.create(WebArchive.class, TestArquillianBasic.class.getName()) //Name is just convenient
            .addClass(TestArquillianBasic.class) //Add our classes to test
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml") //Turn on CDI
            .setWebXML(new StringAsset(                xml));
    }

    @Test
    public void invokeWebService() throws Exception {

        final CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {

            final HttpPost post = new HttpPost(new URI(url.toExternalForm() + "webservices/Bean"));
            post.setEntity(new StringEntity("" +
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <ns1:hello xmlns:ns1=\"http://wsrs.jaxws.tests.arquillian.openejb.apache.org/\"/>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>"));

            final HttpResponse response = httpClient.execute(post);
            final String body = asString(response);

            final String expected = "" +
                "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                "<soap:Body>" +
                "<ns:helloResponse xmlns:ns=\"http://wsrs.jaxws.tests.arquillian.openejb.apache.org/\">" +
                "<return>hola</return>" +
                "</ns:helloResponse>" +
                "</soap:Body>" +
                "</soap:Envelope>";

            Assert.assertEquals(expected, body.replaceAll("ns[0-9]*", "ns"));

        } finally {
            httpClient.close();
        }
    }

    @Test
    public void invokeRest() throws Exception {

        final CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {

            final HttpResponse response = httpClient.execute(new HttpGet(new URI(url.toExternalForm() + "api/myrest")));
            final String body = asString(response);
            Assert.assertEquals("hola", body);

        } finally {
            httpClient.close();
        }
    }

    public static String asString(final HttpResponse execute) throws IOException {
        final InputStream in = execute.getEntity().getContent();
        try {
            return IO.slurp(in);
        } finally {
            in.close();
        }
    }
}
