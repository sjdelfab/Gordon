package org.sjd.gordon.client.security;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class ChangeUserNameEvent extends GwtEvent<ChangeUserNameEvent.ChangeUserNameHandler> { 

  public interface HasChangeUserNameHandlers extends HasHandlers {
    HandlerRegistration addChangeUserNameHandler(ChangeUserNameHandler handler);
  }

  public interface ChangeUserNameHandler extends EventHandler {
    public void onChangeUserName(ChangeUserNameEvent event);
  }

  private static final Type<ChangeUserNameHandler> TYPE = new Type<ChangeUserNameHandler>();

  public static void fire(HasHandlers source, java.lang.String newName) {
    source.fireEvent(new ChangeUserNameEvent(newName));
  }

  public static Type<ChangeUserNameHandler> getType() {
    return TYPE;
  }

  java.lang.String newName;

  public ChangeUserNameEvent(java.lang.String newName) {
    this.newName = newName;
  }

  protected ChangeUserNameEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<ChangeUserNameHandler> getAssociatedType() {
    return TYPE;
  }

  public java.lang.String getNewName() {
    return newName;
  }

  @Override
  protected void dispatch(ChangeUserNameHandler handler) {
    handler.onChangeUserName(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    ChangeUserNameEvent other = (ChangeUserNameEvent) obj;
    if (newName == null) {
      if (other.newName != null)
        return false;
    } else if (!newName.equals(other.newName))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (newName == null ? 1 : newName.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "ChangeUserNameEvent["
                 + newName
    + "]";
  }
}
