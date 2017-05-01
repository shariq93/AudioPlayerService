package catchroom.dell.audioplayerservice.Activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codekidlabs.storagechooser.StorageChooser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import catchroom.dell.audioplayerservice.R;
import catchroom.dell.audioplayerservice.Utils.UserPrefs;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_dir_path)
    TextView tvDirPath;
    @BindView(R.id.btn_requestDir)
    Button btnRequestDir;
    @BindView(R.id.et_timeInterval)
    EditText etTimeInterval;
    UserPrefs userPrefs;
    @BindView(R.id.btn_set_interval)
    Button btnSetInterval;
    @BindView(R.id.btn_schedule_service)
    Button btnScheduleService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        chooser = new StorageChooser.Builder()
                .withActivity(MainActivity.this)
                .withFragmentManager(getSupportFragmentManager())
                .withMemoryBar(true)
                .allowCustomPath(true)
                .setType(StorageChooser.DIRECTORY_CHOOSER)
                .build();
        userPrefs = new UserPrefs(this);
        if (!userPrefs.getStorageDirectory().isEmpty()) {
            tvDirPath.setText(userPrefs.getStorageDirectory());
        } else {
            tvDirPath.setText("[NO DIRECTORY SELECTED YET]");
        }
        etTimeInterval.setText(String.valueOf(userPrefs.getInterval()));
    }

    /**
     * @requestForDirectory is to call getDirctory path
     */
    StorageChooser chooser;

    private void requestForDirectory() {
        chooser.show();
        chooser.setOnSelectListener(new StorageChooser.OnSelectListener() {
            @Override
            public void onSelect(String path) {
                tvDirPath.setText(path);
                userPrefs.setStorageDirectory(path);
                Log.e("SELECTED_PATH", path);
            }
        });
    }

    @OnClick(R.id.btn_requestDir)
    public void onBtnRequestDirClicked() {
        if (isReadStorageAllowed()) {
            requestForDirectory();
        } else {
            requestStoragePermission();
        }
    }

    public static final int PERMISSION_CODE = 100;
    public static final int REQUEST_DIRECTORY = 101;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                recreate();
            } else {

                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void requestStoragePermission() {

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_CODE);
    }

    private boolean isReadStorageAllowed() {
        //Getting the permission status
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;

        //If permission is not granted returning false
        return false;
    }

    @OnClick(R.id.btn_set_interval)
    public void onSetIntervalClicked() {

        if (!etTimeInterval.getText().toString().isEmpty()) {
            int interval = Integer.parseInt(etTimeInterval.getText().toString());
            userPrefs.setInterval(interval);
            Toast.makeText(this, "Interval Set Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Invalid Interval", Toast.LENGTH_SHORT).show();
        }


    }

    @OnClick(R.id.btn_schedule_service)
    public void onViewClicked() {
    }
}
