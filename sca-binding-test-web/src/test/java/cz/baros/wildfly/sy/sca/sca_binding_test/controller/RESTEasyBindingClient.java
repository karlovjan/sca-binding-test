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

import org.switchyard.component.test.mixins.http.HTTPMixIn;

/**
 * Client for RESTEasy binding.
 *
 * @author Magesh Kumar B <mageshbk@jboss.com> (C) 2012 Red Hat Inc.
 */
public class RESTEasyBindingClient {

    private static final int DEFAULT_PORT = 8080;
    private static final String KEY_PORT = "org.switchyard.component.resteasy.client.port";

    public static void main(String[] args) throws Exception {
        int port = DEFAULT_PORT;
        String portstr = System.getProperty(KEY_PORT);
        if (portstr != null) {
            port = Integer.parseInt(portstr);
        }
        String baseUrl = "http://localhost:" + port + "/rest";

        
            HTTPMixIn http = new HTTPMixIn();
            http.initialize();
            
            String name = "Mirarraarrrr";
            
			System.out.println(http.sendString(baseUrl + "/greetings/" + name , "", HTTPMixIn.HTTP_GET));
            
    }
}
