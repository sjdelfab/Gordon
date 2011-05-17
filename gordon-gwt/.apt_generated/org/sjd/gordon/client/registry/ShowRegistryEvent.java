package org.sjd.gordon.client.registry;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class ShowRegistryEvent extends GwtEvent<ShowRegistryEvent.ShowRegistryHandler> { 

  public interface HasShowRegistryHandlers extends HasHandlers {
    HandlerRegistration addShowRegistryHandler(ShowRegistryHandler handler);
  }

  public interface ShowRegistryHandler extends EventHandler {
    public void onShowRegistry(ShowRegistryEvent event);
  }

  private static final Type<ShowRegistryHandler> TYPE = new Type<ShowRegistryHandler>();

  public static void fire(HasHandlers source, org.sjd.gordon.model.Exchange exchange) {
    source.fireEvent(new ShowRegistryEvent(exchange));
  }

  public static Type<ShowRegistryHandler> getType() {
    return TYPE;
  }

  org.sjd.gordon.model.Exchange exchange;

  public ShowRegistryEvent(org.sjd.gordon.model.Exchange exchange) {
    this.exchange = exchange;
  }

  protected ShowRegistryEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<ShowRegistryHandler> getAssociatedType() {
    return TYPE;
  }

  public org.sjd.gordon.model.Exchange getExchange() {
    return exchange;
  }

  @Override
  protected void dispatch(ShowRegistryHandler handler) {
    handler.onShowRegistry(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    ShowRegistryEvent other = (ShowRegistryEvent) obj;
    if (exchange == null) {
      if (other.exchange != null)
        return false;
    } else if (!exchange.equals(other.exchange))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (exchange == null ? 1 : exchange.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "ShowRegistryEvent["
                 + exchange
    + "]";
  }
}
