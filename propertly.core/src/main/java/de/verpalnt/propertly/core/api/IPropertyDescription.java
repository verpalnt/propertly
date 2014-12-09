package de.verpalnt.propertly.core.api;

import de.verpalnt.propertly.core.common.IAnnotationProvider;

/**
 * @author PaL
 *         Date: 29.09.11
 *         Time: 21:39
 */
public interface IPropertyDescription<S extends IPropertyPitProvider, T> extends IAnnotationProvider
{

  Class<S> getSourceType();

  Class<T> getType();

  String getName();

}
