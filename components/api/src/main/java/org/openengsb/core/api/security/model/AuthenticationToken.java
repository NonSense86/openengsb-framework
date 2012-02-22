package org.openengsb.core.api.security.model;


public interface AuthenticationToken {

    Object getPrincipal();

    Object getCredentials();

}
