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

package org.openengsb.core.security.filter;

import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.openengsb.core.api.remote.FilterAction;
import org.openengsb.core.api.remote.FilterConfigurationException;
import org.openengsb.core.api.remote.MethodResult;
import org.openengsb.core.api.remote.MethodResult.ReturnType;
import org.openengsb.core.api.remote.MethodResultMessage;
import org.openengsb.core.api.security.model.AuthenticationToken;
import org.openengsb.core.api.security.model.SecureRequest;
import org.openengsb.core.api.security.model.SecureResponse;
import org.openengsb.core.common.remote.AbstractFilterChainElement;
import org.openengsb.core.security.SecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This filter does no actual transformation. It takes a {@link SecureRequest} extracts the
 * {@link org.apache.shiro.authc.AuthenticationInfo} and tries to authenticate. If authentication was succesful, the
 * filter-chain will proceed. The result of the next filter is just passed through.
 * 
 * This filter is intended for incoming ports.
 * 
 * <code>
 * <pre>
 *      [SecureRequest]  > Filter > [SecureRequest]    > ...
 *                                                        |
 *                                                        v
 *      [SecureResponse] < Filter < [SecureResponse]   < ...
 * </pre>
 * </code>
 */
public class MessageAuthenticatorFilter extends AbstractFilterChainElement<SecureRequest, SecureResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageAuthenticatorFilter.class);

    private FilterAction next;

    public MessageAuthenticatorFilter() {
        super(SecureRequest.class, SecureResponse.class);
    }

    @Override
    protected SecureResponse doFilter(SecureRequest input, Map<String, Object> metaData) {
        LOGGER.debug("recieved authentication info: " + input.getToken());
        try {
            // OsgiUtilsService serviceUtilsService = OpenEngSBCoreServices.getServiceUtilsService();
            // Filter filter =
            // serviceUtilsService.makeFilter(ClassloadingDelegate.class,
            // String.format("(%s=%s)", Constants.PROVIDED_CLASSES_KEY, className));
            // Class<? extends Credentials> credentialType =
            // (Class<? extends Credentials>) serviceUtilsService.getOsgiServiceProxy(filter,
            // ClassloadingDelegate.class).load(className);

            SecurityContext.login((AuthenticationToken) input.getToken());
        } catch (AuthenticationException e) {
            MethodResult methodResult = new MethodResult(e, ReturnType.Exception);
            MethodResultMessage methodResultMessage =
                new MethodResultMessage(methodResult, input.getMessage().getCallId());
            return SecureResponse.create(methodResultMessage);
        }
        LOGGER.debug("authenticated");
        return (SecureResponse) next.filter(input, metaData);
    }

    @Override
    public void setNext(FilterAction next) throws FilterConfigurationException {
        checkNextInputAndOutputTypes(next, SecureRequest.class, SecureResponse.class);
        this.next = next;
    }
}
