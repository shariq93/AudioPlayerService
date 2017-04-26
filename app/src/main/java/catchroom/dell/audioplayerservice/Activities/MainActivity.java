package catchroom.dell.audioplayerservice.Activities;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import net.rdrei.android.dirchooser.DirectoryChooserActivity;
import net.rdrei.android.dirchooser.DirectoryChooserConfig;

import catchroom.dell.audioplayerservice.R;
import catchroom.dell.audioplayerservice.Utils.Constants;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    /*
      @requestForDirectory is to call getDirctory path
     */
    private void requestForDirectory() {
        final Intent chooserIntent = new Intent(this, DirectoryChooserActivity.class);

        final DirectoryChooserConfig config = DirectoryChooserConfig.builder()
                .newDirectoryName(Constants.DEF_FOLDER)
                .allowReadOnlyDirectory(true)
                .allowNewDirectoryNameModification(true)

                .build();

        chooserIntent.putExtra(DirectoryChooserActivity.EXTRA_CONFIG, config);

// REQUEST_DIRECTORY is a constant integer to identify the request, e.g. 0
        startActivityForResult(chooserIntent, Constants.REQUEST_DIRECTORY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_DIRECTORY) {
            if (resultCode == DirectoryChooserActivity.RESULT_CODE_DIR_SELECTED) {
// Directory Path Will return here in @data.getStringExtra(DirectoryChooserActivity.RESULT_SELECTED_DIR)


            } else {
                Toast.makeText(this, "Invalid or Restricted Directory", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Req failed: " + requestCode, Toast.LENGTH_SHORT).show();
        }
    }
}
