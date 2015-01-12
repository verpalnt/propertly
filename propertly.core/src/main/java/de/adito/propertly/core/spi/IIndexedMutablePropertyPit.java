package de.adito.propertly.core.spi;

import javax.annotation.Nonnull;
import java.util.Comparator;

/**
 * IIndexedMutablePropertyPit is an IMutablePropertyPit that has a defined order for it's properties and gives access
 * to them by index. Further it's IProperty objects can be reordered.
 *
 * @author PaL
 *         Date: 13.07.14
 *         Time. 21:27
 */
public interface IIndexedMutablePropertyPit<P extends IPropertyPitProvider, S extends IIndexedMutablePropertyPitProvider<P, S, T>, T>
    extends IMutablePropertyPit<P, S, T>, IIndexedMutablePropertyPitProvider<P, S, T>
{

  /**
   * @return the number of IProperty objects in this IIndexedMutablePropertyPit.
   */
  int getSize();

  /**
   * @param pIndex the index for an IProperty.
   * @return the IProperty at the given index.
   */
  @Nonnull
  IProperty<S, T> getProperty(int pIndex);

  /**
   * Adds an IProperty at a given index.
   *
   * @param pIndex               the index where the IProperty shall be inserted.
   * @param pPropertyDescription the IPropertyDescription describing the new IProperty.
   * @return the created IProperty.
   */
  @Nonnull
  IProperty<S, T> addProperty(int pIndex, IPropertyDescription<S, T> pPropertyDescription);

  /**
   * @param pIndex the index for the IProperty to be removed.
   */
  void removeProperty(int pIndex);

  /**
   * Reorders the IProperty objects in this IIndexedMutablePropertyPit.
   *
   * @param pComparator is used for oredering.
   */
  void reorder(Comparator<IProperty<S, T>> pComparator);

}