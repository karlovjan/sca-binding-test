/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cz.baros.wildfly.sy.sca.sca_binding_test.controller;

//import org.jboss.resteasy.client.ProxyFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.test.BeforeDeploy;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;
import org.switchyard.test.SwitchYardTestKit;
import org.switchyard.component.resteasy.resource.ResourcePublisherFactory;
import org.switchyard.component.test.mixins.cdi.CDIMixIn;
import org.switchyard.component.test.mixins.http.HTTPMixIn;
import org.switchyard.transform.config.model.TransformSwitchYardScanner;

/**
 * Tests for RESTEasy binding.
 *
 * @author Magesh Kumar B <mageshbk@jboss.com> (C) 2012 Red Hat Inc.
 */
@SwitchYardTestCaseConfig(
    config = SwitchYardTestCaseConfig.SWITCHYARD_XML,
    mixins = { CDIMixIn.class, HTTPMixIn.class },
    scanners = TransformSwitchYardScanner.class)
@RunWith(SwitchYardRunner.class)
public class RESTEasyBindingTest {


    private static String BASE_URL = "http://localhost:8081/scatest";

    private HTTPMixIn http;

    private SwitchYardTestKit testKit;
    
    @BeforeDeploy
    public void setProperties() {
        System.setProperty("org.switchyard.component.resteasy.standalone.port", "8081");
        System.setProperty("org.switchyard.component.resteasy.standalone.path", "");
        if (ResourcePublisherFactory.ignoreContext()) {
            // Context paths are irrelevant for NettyJaxrs server
            BASE_URL = "http://localhost:8081";
        }
    }

    @Test
    public void test404() throws Exception {
        // Runtime error
        int status = http.sendStringAndGetStatus(BASE_URL + "/notFound", "", HTTPMixIn.HTTP_GET);
        Assert.assertEquals(404, status);
    }

    /**
     * This Quickstart test exclusively uses Netty server due to buggy JDK HttpServer.
     * See https://issues.jboss.org/browse/RESTEASY-734
     */
    @Test
    public void orderServiceRESTEndpoint() throws Exception {

    	testKit.removeService("SayHelloToREF");
    	
    	testKit.registerInOutService("SayHelloToREF");
    	
        // Create our inventory
        String response = null;
        response = http.sendString(BASE_URL + "/greetings/Mira", "", HTTPMixIn.HTTP_GET);
        Assert.assertEquals("Mira", response);
        /* WarehouseResource warehouseProxy = ProxyFactory.create(WarehouseResource.class, BASE_URL);
        warehouseProxy.addItem(new Item(1, "Hydrogen Atom - No, we are not kidding!"));
        warehouseProxy.addItem(new Item(2, "Handcrafted Copper Plate"));
        warehouseProxy.addItem(new Item(3, "Einstein's Bust - Talks about your future :)"));
        warehouseProxy.addItem(new Item(4, "Time Machine"));*/

        /*
        // Create an order
        response = http.sendString(BASE_URL + "/order", "", HTTPMixIn.HTTP_POST);

        SwitchYardTestKit.compareXMLToString(response, ORDER);

        // Add a new item or update order
        response = http.sendString(BASE_URL + "/order/item", ORDER1, HTTPMixIn.HTTP_PUT);
        Assert.assertEquals(SUCCESS, response);

        // Add some more items or update order
        response = http.sendString(BASE_URL + "/order/item", ORDER2, HTTPMixIn.HTTP_PUT);
        Assert.assertEquals(SUCCESS, response);

        // Look at our order
        response = http.sendString(BASE_URL + "/order/1", "", HTTPMixIn.HTTP_GET);
        SwitchYardTestKit.compareXMLToString(response, ORDER3);

        // Delete the first item
        response = http.sendString(BASE_URL + "/order/1:1", "", HTTPMixIn.HTTP_DELETE);
        Assert.assertEquals(SUCCESS, response);

        // Try to delete item with wrong composite ID
        int status = http.sendStringAndGetStatus(BASE_URL + "/order/1", "", HTTPMixIn.HTTP_DELETE);
        Assert.assertEquals(400, status);

        // Look at our order
        response = http.sendString(BASE_URL + "/order/1", "", HTTPMixIn.HTTP_GET);
        SwitchYardTestKit.compareXMLToString(response, ORDER4);

        // Update item descriptions in our inventory
        response = http.sendString(BASE_URL + "/inventory/update", "", HTTPMixIn.HTTP_OPTIONS);
        Assert.assertEquals(SUCCESS, response);

        // Look at our order
        response = http.sendString(BASE_URL + "/order/1", "", HTTPMixIn.HTTP_GET);
        SwitchYardTestKit.compareXMLToString(response, ORDER5);

        // Look at non existing order
        status = http.sendStringAndGetStatus(BASE_URL + "/order/" + Integer.MAX_VALUE, "", HTTPMixIn.HTTP_GET);
        Assert.assertEquals(404, status);

        // Get item
        status = http.sendStringAndGetStatus(BASE_URL + "/warehouse/get/26", "", HTTPMixIn.HTTP_GET);
        Assert.assertEquals(404, status);

        // Destroy our inventory
        response = http.sendString(BASE_URL + "/inventory/remove", "", HTTPMixIn.HTTP_OPTIONS);
        Assert.assertEquals(SUCCESS, response);

        // Test 404 returned with complete url
        response = http.sendString(BASE_URL + "/order/404", "", HTTPMixIn.HTTP_GET);
        Assert.assertTrue(response.contains("Error at [http://localhost:8081/404/404/]"));
        
        */
    }

//    @Test
    public void memberServiceRESTEndpoint() throws Exception {

        // get all members
        String response = null;
        response = http.sendString("http://localhost:8081/scatest/rest" + "/members", "", HTTPMixIn.HTTP_GET);
        Assert.assertEquals("", response);
        
    }
}
