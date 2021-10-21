package com.example.appmusic;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmusic.api.ApiClient;

import com.example.appmusic.api.Register.RegisterResponse;
import com.example.appmusic.api.Register.RegisterRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    ImageView imgLogin;
    EditText fullname, username, email, password;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fullname = findViewById(R.id.editTextFullName);
        username = findViewById(R.id.editTextUsername);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        btnSignUp = findViewById(R.id.buttonSignUp);

        imgLogin = findViewById(R.id.imageViewAlreadyAccount);

        // Chuyển sang màn hình Login
        imgLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
            }

            private void doLogin() {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Kiểm tra rỗng
                if(TextUtils.isEmpty(fullname.getText().toString())
                        || TextUtils.isEmpty(username.getText().toString())
                        || TextUtils.isEmpty(email.getText().toString())
                        || TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(SignUp.this, "Do not Empty!", Toast.LENGTH_LONG).show();
                }else{

                    // Lưu tài khoản khi đăng ký thành công
                    saveRegister(createAcc());
                }
            }
        });
    }

    public RegisterRequest createAcc(){
        RegisterRequest registerRequest = new RegisterRequest();

        registerRequest.setFullname(fullname.getText().toString());
        registerRequest.setUsername(username.getText().toString());
        registerRequest.setEmail(email.getText().toString());
        registerRequest.setPassword(password.getText().toString());

        return registerRequest;
    }

    public void saveRegister(RegisterRequest registerRequest){
        Call<RegisterResponse> registerResponseCall = ApiClient.getRegister().saveUser(registerRequest);

        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(SignUp.this, "Register Successfully!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), Login.class));
                }else{
                    try {
                        // Chuyển error thành đối tượng để lấy message

                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        JSONObject jsonObjectErrors = jsonObject.getJSONObject("error");
                        JSONArray jsonArray =  jsonObjectErrors.getJSONArray("message");

                        for(int i=0; i<jsonArray.length(); i++){
                            String strErrorMessage = jsonArray.getString(i);
                            Toast.makeText(SignUp.this, strErrorMessage, Toast.LENGTH_LONG).show();
                        }

//                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
//                        Toast.makeText(SignUp.this, jsonObject.getJSONObject("error").getString("message"), Toast.LENGTH_LONG).show();
                    }catch (Exception e){
                        Toast.makeText(SignUp.this,"Register Failed! This username is already in use!", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(SignUp.this, "Register Failed! "+ t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}