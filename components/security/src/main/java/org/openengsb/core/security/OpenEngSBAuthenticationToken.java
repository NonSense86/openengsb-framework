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
package org.openengsb.core.security;

import org.apache.shiro.authc.AuthenticationToken;

public class OpenEngSBAuthenticationToken implements AuthenticationToken {

    /**
     * 
     */
    private static final long serialVersionUID = 3909132403539984150L;

    private org.openengsb.core.api.security.model.AuthenticationToken token;

    public OpenEngSBAuthenticationToken() {
    }

    public OpenEngSBAuthenticationToken(org.openengsb.core.api.security.model.AuthenticationToken token) {
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return token.getCredentials();
    }

    @Override
    public Object getPrincipal() {
        return token.getPrincipal();
    }

    public org.openengsb.core.api.security.model.AuthenticationToken getToken() {
        return token;
    }

}
