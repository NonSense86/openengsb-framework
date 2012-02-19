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

package org.openengsb.core.common.events;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openengsb.core.api.Event;
import org.openengsb.core.common.util.ModelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple wrapper class which is needed that Drools can work with our model proxies.
 */
public class EventWrapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventWrapper.class);
    private Event event;

    public EventWrapper(Event event) {
        this.event = event;
    }

    /**
     * Returns the wrapped event
     */
    public Event getEvent() {
        return event;
    }
    
    /**
     * Returns the name of the wrapped event
     */
    public String getName() {
        return event.getName();
    }
    
    /**
     * Returns the origin of the wrapped event
     */
    public String getOrigin() {
        return event.getOrigin();
    }
    
    /**
     * Returns the process id of the wrapped event
     * @return
     */
    public Long getProcessId() {
        return event.getProcessId();
    }
    
    /**
     * Returns the value of a property with the given name from the wrapped event object. Returns
     * null if any error occurs.
     */
    public Object getProperty(String propertyName) {
        Class<?> clazz = event.getClass();
        StringBuilder builder = new StringBuilder("get");
        builder.append(Character.toUpperCase(propertyName.charAt(0)));
        builder.append(propertyName.substring(1));
        Method method;
        try {
            method = clazz.getMethod(builder.toString());
            return method.invoke(event);
        } catch (SecurityException e) {
            LOGGER.warn("Unable to call " + builder.toString() + " due to SecurityException.", e);
        } catch (NoSuchMethodException e) {
            LOGGER.warn("Unable to call " + builder.toString() + " because its not existing.", e);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("Unable to call " + builder.toString() + " due to illegal arguments.", e);
        } catch (IllegalAccessException e) {
            LOGGER.warn("Unable to call " + builder.toString() + " due to an IllegalAccessException.", e);
        } catch (InvocationTargetException e) {
            LOGGER.warn("Unable to call " + builder.toString() + " due to an InvocationTargetException.", e);
        }
        return null;
    }

    /**
     * Returns the name of the real event type (not Proxy$x)
     */
    public String getType() {
        return getRealEventType().getSimpleName();
    }
    
    /**
     * Returns the name of the real event type
     */
    public Class<?> getEventType() {
        return getRealEventType();
    }
    
    /**
     * Gets the real event type of the wrapped event
     */
    private Class<?> getRealEventType() {
        return ModelUtils.getModelClassOfOpenEngSBModelObject(event.getClass());
    }
}
