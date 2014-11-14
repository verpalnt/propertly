package de.verpalnt.propertly.core.hierarchy;

import de.verpalnt.propertly.core.api.*;

import javax.annotation.*;
import java.util.*;

/**
 * @author PaL
 *         Date: 03.10.11
 *         Time: 22:02
 */
public class PropertyPit<S extends IPropertyPitProvider, T> implements IPropertyPit<S, T>
{

  private S source;
  private INode node;


  PropertyPit(S pSource)
  {
    source = pSource;
  }

  public static <S extends IPropertyPitProvider> PropertyPit<S, Object> create(S pCreateFor)
  {
    return new PropertyPit<S, Object>(pCreateFor);
  }

  @Override
  @Nonnull
  public S getSource()
  {
    return source;
  }

  @Override
  @Nullable
  public final IPropertyPitProvider<?, ?> getParent()
  {
    INode parent = getNode().getParent();
    return parent == null ? null : (IPropertyPitProvider) parent.getProperty().getValue();
  }

  @Nonnull
  @Override
  public IProperty<?, S> getOwnProperty()
  {
    return getNode().getProperty();
  }

  @Nullable
  @Override
  public <E> IProperty<S, E> findProperty(IPropertyDescription<?, E> pPropertyDescription)
  {
    INode childNode = getNode().findNode(pPropertyDescription);
    //noinspection unchecked
    return childNode == null ? null : childNode.getProperty();
  }

  @Nonnull
  @Override
  public <E extends T> IProperty<S, E> getProperty(IPropertyDescription<? super S, E> pPropertyDescription)
  {
    IProperty<?, E> property = findProperty(pPropertyDescription);
    if (property == null)
      throw new RuntimeException("Property for " + pPropertyDescription + " doesn't exist at " + this + ".");
    //noinspection unchecked
    return (IProperty<S, E>) property;
  }

  @Override
  @Nullable
  public final <E extends T> E getValue(IPropertyDescription<? super S, E> pPropertyDescription)
  {
    return getProperty(pPropertyDescription).getValue();
  }

  @Override
  @Nullable
  public final <E extends T> E setValue(IPropertyDescription<? super S, E> pPropertyDescription, E pValue)
  {
    return getProperty(pPropertyDescription).setValue(pValue);
  }

  @Nonnull
  @Override
  public final Set<IPropertyDescription<S, T>> getPropertyDescriptions()
  {
    Set<IPropertyDescription<S, T>> set = new LinkedHashSet<IPropertyDescription<S, T>>();
    List<INode> children = getNode().getChildren();
    if (children != null)
      for (INode childNode : children)
        set.add(childNode.getProperty().getDescription());
    return set;
  }

  @Override
  @Nonnull
  public List<IProperty<S, T>> getProperties()
  {
    List<IProperty<S, T>> properties = new ArrayList<IProperty<S, T>>();
    List<INode> children = getNode().getChildren();
    if (children != null)
      for (INode childNode : children)
        properties.add(childNode.getProperty());
    return properties;
  }

  @Nonnull
  @Override
  public List<T> getValues()
  {
    List<T> values = new ArrayList<T>();
    List<INode> children = getNode().getChildren();
    if (children != null)
      for (INode childNode : children)
        values.add((T) childNode.getValue());
    return values;
  }

  @Override
  public Iterator<IProperty<S, T>> iterator()
  {
    return getProperties().iterator();
  }

  @Override
  public final void addPropertyEventListener(final IPropertyEventListener pListener)
  {
    getNode().addPropertyEventListener(pListener);
  }

  @Override
  public final void removePropertyEventListener(IPropertyEventListener pListener)
  {
    getNode().removePropertyEventListener(pListener);
  }

  public IPropertyPit<S, T> getPit()
  {
    return this;
  }

  void setNode(INode pNode)
  {
    node = pNode;
  }

  @Nonnull
  INode getNode()
  {
    if (node == null)
      throw new RuntimeException(this + " for " + source + " has not been initialized, yet.");
    return node;
  }

  boolean hasNode()
  {
    return node != null;
  }
}
