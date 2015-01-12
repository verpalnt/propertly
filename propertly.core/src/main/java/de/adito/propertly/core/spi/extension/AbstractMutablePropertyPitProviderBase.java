package de.adito.propertly.core.spi.extension;

import de.adito.propertly.core.spi.IMutablePropertyPitProvider;
import de.adito.propertly.core.spi.IProperty;
import de.adito.propertly.core.spi.IPropertyDescription;
import de.adito.propertly.core.spi.IPropertyPitProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.annotation.Annotation;

/**
 * @author j.boesl, 30.10.14
 */
abstract class AbstractMutablePropertyPitProviderBase
    <P extends IPropertyPitProvider, S extends IMutablePropertyPitProvider<P, S, T>, T>
    extends AbstractPropertyPitProviderBase<P, S, T>
    implements IMutablePropertyPitProvider<P, S, T>
{
  public Class<T> getChildType()
  {
    return getPit().getChildType();
  }

  @Nonnull
  public <E extends T> IProperty<S, E> addProperty(@Nonnull E pValue)
  {
    return getPit().addProperty(pValue);
  }

  @Nonnull
  public <E extends T> IProperty<S, E> addProperty(@Nonnull String pName, @Nonnull E pValue)
  {
    return getPit().addProperty(pName, pValue);
  }

  @Nonnull
  public <E extends T> IProperty<S, E> addProperty(@Nonnull IPropertyDescription<S, E> pPropertyDescription)
  {
    return getPit().addProperty(pPropertyDescription);
  }

  @Nonnull
  public <E extends T> IProperty<S, E> addProperty(@Nonnull Class<E> pType, @Nonnull String pName,
                                                   @Nullable Iterable<? extends Annotation> pAnnotations)
  {
    return getPit().addProperty(pType, pName, pAnnotations);
  }

  public boolean removeProperty(@Nonnull IPropertyDescription<? super S, T> pPropertyDescription)
  {
    return getPit().removeProperty(pPropertyDescription);
  }

  public boolean removeProperty(@Nonnull IProperty<S, T> pProperty)
  {
    return getPit().removeProperty(pProperty);
  }
}