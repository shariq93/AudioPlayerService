package catchroom.dell.audioplayerservice.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import java.io.File;

/**
 * Created by DeLL on 27-Apr-17.
 */

public class UserPrefs {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context context;

    public UserPrefs(Context context) {
        this.context = context;
        prefs = context.getSharedPreferences("userprefs",Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    /**
     * this is to set Directory path
     * @param dir
     */

    public void setStorageDirectory(String dir){
        editor.putString("dir",dir);
        editor.commit();
    }
    /**
    get audio file folder setted from activity
     */
    public String getStorageDirectory(){
        return prefs.getString("dir","");
    }

    public void setInterval(int interval){
        editor.putInt("interval",interval);
        editor.commit();
    }
    public int getInterval(){
        return prefs.getInt("interval",5);
    }
}
