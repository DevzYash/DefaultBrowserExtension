package com.yash.defbrowask.defaultbrowserask;
import android.app.Activity;
import android.app.role.RoleManager;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.runtime.ActivityResultListener;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;


public class Defaultbrowserask extends AndroidNonvisibleComponent implements ActivityResultListener {

  RoleManager roleManager = null;
  public ComponentContainer mycontainer;
 public Context context;
  public Defaultbrowserask(ComponentContainer container) {
      super(container.$form());
      context = container.$context();
      mycontainer = container;
      if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
          roleManager = (RoleManager) context.getSystemService(Context.ROLE_SERVICE);
      }
  }

  @SimpleFunction(description = "Returns the sum of the given list of integers.")
  public void ask() {
    if (roleManager.isRoleAvailable(RoleManager.ROLE_BROWSER) && !roleManager.isRoleHeld(RoleManager.ROLE_BROWSER))
    {
      Intent intent = roleManager.createRequestRoleIntent(RoleManager.ROLE_BROWSER);
        mycontainer.$form().startActivityForResult(intent,100);
    }
//    else {
//      Intent i = new Intent(Settings.ACTION_MANAGE_DEFAULT_APPS_SETTINGS);
//      context.startActivity(i);
//
//    }
  }

    @SimpleFunction(description = "Returns the sum of the given list of integers.")
    public void opensettings() {
        Intent i = new Intent(Settings.ACTION_MANAGE_DEFAULT_APPS_SETTINGS);
        context.startActivity(i);
    }


  @SimpleFunction(description = "")
  public boolean isBrowserFacilityAvailable(){
    return roleManager.isRoleAvailable(RoleManager.ROLE_BROWSER);
  }

  @SimpleFunction(description = "")
  public boolean isDefaultAlready(){
    return roleManager.isRoleHeld(RoleManager.ROLE_BROWSER);
  }

    @SimpleEvent(description = "DeepHost - File_Picker Extension ")
    public void Result(boolean value) {
        EventDispatcher.dispatchEvent(this, "Result", value);
    }


    @Override
    public void resultReturned(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK){
            Result(true);
        }
        else Result(false);
    }


}
