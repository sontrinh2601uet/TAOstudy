package self.sontq.taostudy;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.textfield.TextInputLayout;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Response;
import self.sontq.taostudy.api.ServiceBuilder;

import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {
    private static final String[] REQUEST_PERMISSION = {
            Manifest.permission.INTERNET,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    EditText email, password;
    Button login;

    TextInputLayout emailError, passError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        emailError = findViewById(R.id.emailError);
        passError = findViewById(R.id.passError);

        requestPermission();
        login.setOnClickListener(v -> SetValidation());
        login.performClick();
    }

    public void SetValidation() {
        // Check for a valid email address.
        if (email.getText().toString().trim().isEmpty()) {
            emailError.setError(getResources().getString(R.string.error_invalid_email));
            return;
        }

        // Check for a valid password.
        if (password.getText().toString().trim().isEmpty()) {
            passError.setError(getResources().getString(R.string.password_error));
            return;
        }


        ServiceBuilder.getApiServiceAuthen().login(email.getText().toString(), password.getText().toString(), "Log in", 1).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                Headers headers = response.headers();
                String cookie = response.headers().get("Set-Cookie");
                System.out.println("this cookie: "+cookie);

                MainActivity.cookie = cookie;
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(LoginActivity.this, R.string.login_fail, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void requestPermission() {
        ActivityCompat.requestPermissions(this, REQUEST_PERMISSION, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 101:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //granted
                } else {
                    Toast.makeText(this, R.string.request_permission, Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
