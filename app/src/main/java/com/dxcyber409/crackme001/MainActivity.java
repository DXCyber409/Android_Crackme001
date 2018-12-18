package com.dxcyber409.crackme001;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button btn_register;
    private EditText txt_urn;
    private EditText txt_pwd;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());

        txt_urn = findViewById(R.id.txt_urn);
        txt_pwd = findViewById(R.id.txt_pwd);
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String username = txt_urn.getText().toString();
                String password = txt_pwd.getText().toString();
                if (isLeagleAccount(username, password)) {
                    Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "用户名密码错误", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    private boolean isLeagleAccount(String username, String password) {
        return ("test".equals(username) && "123456".equals(password));
    }
}
