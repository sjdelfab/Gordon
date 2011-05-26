package org.sjd.gordon.client.navigation;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class ShowStockEvent extends GwtEvent<ShowStockEvent.ShowStockHandler> { 

  public interface HasShowStockHandlers extends HasHandlers {
    HandlerRegistration addShowStockHandler(ShowStockHandler handler);
  }

  public interface ShowStockHandler extends EventHandler {
    public void onShowStock(ShowStockEvent event);
  }

  private static final Type<ShowStockHandler> TYPE = new Type<ShowStockHandler>();

  public static void fire(HasHandlers source, org.sjd.gordon.shared.navigation.StockName stock) {
    source.fireEvent(new ShowStockEvent(stock));
  }

  public static Type<ShowStockHandler> getType() {
    return TYPE;
  }

  org.sjd.gordon.shared.navigation.StockName stock;

  public ShowStockEvent(org.sjd.gordon.shared.navigation.StockName stock) {
    this.stock = stock;
  }

  protected ShowStockEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<ShowStockHandler> getAssociatedType() {
    return TYPE;
  }

  public org.sjd.gordon.shared.navigation.StockName getStock() {
    return stock;
  }

  @Override
  protected void dispatch(ShowStockHandler handler) {
    handler.onShowStock(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    ShowStockEvent other = (ShowStockEvent) obj;
    if (stock == null) {
      if (other.stock != null)
        return false;
    } else if (!stock.equals(other.stock))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (stock == null ? 1 : stock.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "ShowStockEvent["
                 + stock
    + "]";
  }
}
