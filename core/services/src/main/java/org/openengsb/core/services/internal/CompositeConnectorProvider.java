/**
 * Licensed to the Austrian Association for Software Tool Integration (AASTI)
 * under one or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information regarding copyright
 * ownership. The AASTI licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openengsb.core.services.internal;

import org.openengsb.core.api.descriptor.ServiceDescriptor;
import org.openengsb.core.api.descriptor.ServiceDescriptor.Builder;
import org.openengsb.core.common.AbstractConnectorProvider;

public class CompositeConnectorProvider extends AbstractConnectorProvider {

    @Override
    public ServiceDescriptor getDescriptor() {
        Builder builder = ServiceDescriptor.builder(strings);
        builder.id("external-connector-proxy");
        builder.name("proxy.name", "[proxy-name]");
        builder.description("proxy.description");
        builder.attribute(builder.newAttribute().id("portId").name("proxy.port.id")
            .description("proxy.port.description").build());
        builder.attribute(builder.newAttribute().id("destination").name("proxy.destination.name")
            .description("proxy.destination.description").build());
        builder.attribute(builder.newAttribute().id("serviceId").name("proxy.serviceId.name")
            .description("proxy.serviceId.description").build());
        return builder.build();
    }
}