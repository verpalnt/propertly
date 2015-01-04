package de.adito.propertly.core.api.base;

import de.adito.propertly.core.api.*;
import de.adito.propertly.core.hierarchy.HierarchyPitFactory;

import javax.annotation.Nonnull;

/**
 * @author PaL
 *         Date: 07.02.13
 *         Time: 21:44
 */
public abstract class AbstractMutablePPP
    <P extends IPropertyPitProvider, S extends IMutablePropertyPitProvider<P, S, T>, T>
    extends AbstractMutablePropertyPitProviderBase<P, S, T>
{
  private final IMutablePropertyPit<P, S, T> pit;

  public AbstractMutablePPP(Class<T> pAllowedChildType)
  {
    this(HierarchyPitFactory.getInstance(), pAllowedChildType);
  }

  protected AbstractMutablePPP(IMutablePropertyPitFactory pFactory, Class<T> pAllowedChildType)
  {
    //noinspection unchecked
    pit = pFactory.create((S) this, pAllowedChildType);
  }

  @Nonnull
  @Override
  public final IMutablePropertyPit<P, S, T> getPit()
  {
    return pit;
  }
}
