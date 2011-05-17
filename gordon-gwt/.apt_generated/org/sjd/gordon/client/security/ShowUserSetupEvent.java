package org.sjd.gordon.client.security;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class ShowUserSetupEvent extends GwtEvent<ShowUserSetupEvent.ShowUserSetupHandler> { 

  public interface HasShowUserSetupHandlers extends HasHandlers {
    HandlerRegistration addShowUserSetupHandler(ShowUserSetupHandler handler);
  }

  public interface ShowUserSetupHandler extends EventHandler {
    public void onShowUserSetup(ShowUserSetupEvent event);
  }

  private static final Type<ShowUserSetupHandler> TYPE = new Type<ShowUserSetupHandler>();

  public static void fire(HasHandlers source) {
    source.fireEvent(new ShowUserSetupEvent());
  }

  public static Type<ShowUserSetupHandler> getType() {
    return TYPE;
  }


  public ShowUserSetupEvent() {
  }

  @Override
  public Type<ShowUserSetupHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(ShowUserSetupHandler handler) {
    handler.onShowUserSetup(this);
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public String toString() {
    return "ShowUserSetupEvent["
    + "]";
  }
}
