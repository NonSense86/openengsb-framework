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

package org.openengsb.core.workflow.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.HashCodeBuilder;

public class ImportDeclaration implements Serializable {

    private static final long serialVersionUID = 4988417812120411259L;

    private String className;

    public ImportDeclaration(String className) {
        this.className = className;
    }

    public ImportDeclaration() {
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ImportDeclaration)) {
            return false;
        }
        if (className == null) {
            return true;
        }
        return className.equals(((ImportDeclaration) obj).className);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(className).toHashCode();
    }

}
