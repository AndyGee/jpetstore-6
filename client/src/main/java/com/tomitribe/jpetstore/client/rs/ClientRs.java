/*
 *
 *  * Licensed to the Apache Software Foundation (ASF) under one or more
 *  * contributor license agreements.  See the NOTICE file distributed with
 *  * this work for additional information regarding copyright ownership.
 *  * The ASF licenses this file to You under the Apache License, Version 2.0
 *  * (the "License"); you may not use this file except in compliance with
 *  * the License.  You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */
package com.tomitribe.jpetstore.client.rs;

import com.tomitribe.ee.rest.ComplexType;
import org.apache.cxf.jaxrs.client.WebClient;

import javax.ws.rs.core.MediaType;
import java.net.URI;

public class ClientRs {

    private URI uri;

    public ClientRs setUri(final URI uri) {
        this.uri = uri;
        return this;
    }

    public ComplexType getComplexType() {

        final WebClient webClient = WebClient.create(uri);
        webClient.accept(MediaType.APPLICATION_JSON);

        return webClient.path("api/myrest/complex").get(ComplexType.class);
    }
}
