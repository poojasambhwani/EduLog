package com.example.edulog.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.edulog.R;
import com.example.edulog.apis.APIClient;
import com.example.edulog.apis.ApiInterface;
import com.example.edulog.models.ProfileModel;
import com.example.edulog.models.RegisterModel;
import com.example.edulog.models.UploadImageModel;
import com.example.edulog.utils.Constants;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class ProfileActivity extends AppCompatActivity {

    private ImageView ivProfile, ivAadhar, ivTenth, ivTwlth,editAadhar,editTenth,editTwlth,editProfile;
    private EditText et_fname, et_lname, et_mail,et_number;
    ApiInterface apiInterface = new APIClient().getApiInterface();
    private JSONObject req;
    private JsonObject object;
    private String token, user_id;
    private SharedPreferences sp;
    private String[] permissions = new String[]{
            READ_EXTERNAL_STORAGE,
            WRITE_EXTERNAL_STORAGE};
    public static final int MULTIPLE_PERMISSIONS = 10;
    private String filename = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sp = getSharedPreferences(Constants.USER_PREFS, Context.MODE_PRIVATE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }

    @Override
    protected void onStart() {
        super.onStart();

        init();
        onCall(user_id, token);
        call_permissions();
        checkPermission();
        clickEdit();
        sendImage(filename);
    }

    private void init() {
        user_id = sp.getString(Constants.USER_ID, null);
        token = sp.getString(Constants.TOKEN, null);
        ivProfile = findViewById(R.id.ivProfile);
        ivAadhar = findViewById(R.id.ivAadhar);
        ivTenth = findViewById(R.id.ivTenth);
        ivTwlth = findViewById(R.id.ivTwlth);
        editAadhar = findViewById(R.id.editAadhar);
        editTenth = findViewById(R.id.editTenth);
        editTwlth =findViewById(R.id.editTwlth);
        editProfile = findViewById(R.id.edit_profile);
        et_fname = findViewById(R.id.et_fname);
        et_lname = findViewById(R.id.et_lname);
        et_mail = findViewById(R.id.et_mail);
        et_number = findViewById(R.id.et_number);
        req = new JSONObject();


        Glide.with(this)
                .load("https://dev.hawkscode.com.au/edumitr/managepro/assets/uploads/profileImage/16076866071607686615664.jpg")
                .into(ivProfile);

        Glide.with(this)
                .load("https://cdn.newsnationtv.com/resize/460_-/images/2020/08/29/aadhaar-card1-90.jpg")
                .into(ivAadhar);

        Glide.with(this)
                .load("https://cache.careers360.mobi/media/articles/uploads/froala_editor/images/2020/7/15/CBSE-Result-2020-class-10-marksheet.jpg")
                .into(ivTenth);

        Glide.with(this)
                .load("https://www.iittm.org/wp-content/uploads/2020/07/Digi-Locker-CBSE-Marksheet-2020.jpg")
                .into(ivTwlth);
    }

    private void onCall(String user_id, String token) {
        try {

            JSONObject array = new JSONObject();

            array.put("user_id", user_id);
            array.put("Token", token);
            req.put("auth", array);

            JsonParser jsonParser = new JsonParser();
            object = (JsonObject) jsonParser.parse(req.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<ProfileModel> call = apiInterface.getProfile(object);
        call.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                try {
                    if (response.body().getCode() == 200) {
                        et_fname.setText(response.body().getData().getFirstName());
                        et_lname.setText(response.body().getData().getLastName());
                        et_mail.setText(response.body().getData().getEmail());
                        et_number.setText(response.body().getData().getPhoneNumber());
                    }
                } catch (NullPointerException e) {
                }

            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "" + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onFailure: " + t);

            }
        });

    }

    private void clickEdit(){
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkPermission()){
                    Intent intent = new Intent(Intent.ACTION_VIEW, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setData(Uri.parse("content://media/external/images/media/"));
                    startActivityForResult(Intent.createChooser(intent, ""), Constants.CHOOSE);
                }else{
                    call_permissions();
                }
            }
        });

        editAadhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkPermission()){
                    Intent intent = new Intent(Intent.ACTION_VIEW, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setData(Uri.parse("content://media/external/images/media/"));
                    startActivityForResult(Intent.createChooser(intent, ""), Constants.CHOOSE);
                }else{
                    call_permissions();
                }
            }
        });

        editTenth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkPermission()){
                    Intent intent = new Intent(Intent.ACTION_VIEW, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setData(Uri.parse("content://media/external/images/media/"));
                    startActivityForResult(Intent.createChooser(intent, ""), Constants.CHOOSE);
                }else{
                    call_permissions();
                }
            }
        });
        editTwlth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkPermission()){
                    Intent intent = new Intent(Intent.ACTION_VIEW, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setData(Uri.parse("content://media/external/images/media/"));
                    startActivityForResult(Intent.createChooser(intent, ""), Constants.CHOOSE);
                }else{
                    call_permissions();
                }
            }
        });
    }

    /**
     * Check permissions code
     *
     */

    /**
     * check if permissions are allowed
     */


    private boolean checkPermission() {
        int result1 = ContextCompat.checkSelfPermission(ProfileActivity.this,READ_EXTERNAL_STORAGE);
        int result2 = ContextCompat.checkSelfPermission(ProfileActivity.this, WRITE_EXTERNAL_STORAGE);

        return result1 == PackageManager.PERMISSION_GRANTED &&
                result2 == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * call permissions
     * /
     */
    private void call_permissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(ProfileActivity.this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(ProfileActivity.this, listPermissionsNeeded.toArray
                    (new String[listPermissionsNeeded.size()]), MULTIPLE_PERMISSIONS);
        }
        return;
    }


    /**
    * shows result of the requested permission
    /*/
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MULTIPLE_PERMISSIONS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // if permissions granted then open gallery
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(Intent.createChooser(intent, ""), Constants.CHOOSE);
                } else {
                    // no permissions granted then show toast
                    Typeface tf = ResourcesCompat.getFont(ProfileActivity.this, R.font.rubik_medium);
                    Snackbar snackbar = Snackbar.make(ProfileActivity.this.findViewById(android.R.id.content),"We're facing some issues.", BaseTransientBottomBar.LENGTH_LONG);
                    snackbar.setActionTextColor(Color.BLUE);
                    snackbar.setBackgroundTint(Color.RED);
                    View snackBarView = snackbar.getView();
                    TextView textView = snackBarView.findViewById(R.id.snackbar_text);
                    textView.setTextColor(Color.WHITE);
                    textView.setTypeface(tf);
                    snackbar.show();
                }
                return;
            }
        }
    }

    /**
     *
     * get the real path from uri(convert string uri to path)
     /*/
    private String getRealPathFromURI(Uri contentURI)
    {
        Cursor cursor = ProfileActivity.this.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null)
        {
            return contentURI.getPath();
        } else {
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
        }
    }

    /**
     *
     * get the real path from uri(convert string uri to path)
     /*/
    private String getRealPathFromURI4(String contentURI) {
         Uri contentUri = Uri.parse(contentURI);
         Cursor cursor = ProfileActivity.this.getContentResolver().query(contentUri, null, null, null, null);
         if (cursor == null) {
             return contentUri.getPath();
         } else {
             cursor.moveToFirst();
             int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
             return cursor.getString(index);
         }
     }

     /**
     * handle the compression for image
     /*/

     public String compressImage4(String imageUri) {
         String filePath = getRealPathFromURI4(imageUri);
         Bitmap scaledBitmap = null;
         BitmapFactory.Options options = new BitmapFactory.Options();
         // by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded.
         // If you try the use the bitmap here, you will get null.
         options.inJustDecodeBounds = true;
         Bitmap bmp = BitmapFactory.decodeFile(filePath, options);
         int actualHeight = options.outHeight;
         int actualWidth = options.outWidth;
         // max Height and width values of the compressed image is taken as 816x612

         float maxHeight = 816.0f;
         float maxWidth = 612.0f;
         float imgRatio = actualWidth / actualHeight;
         float maxRatio = maxWidth / maxHeight;
         // width and height values are set maintaining the aspect ratio of the image

         if (actualHeight > maxHeight || actualWidth > maxWidth) {
             if (imgRatio < maxRatio) {
                 imgRatio = maxHeight / actualHeight;
                 actualWidth = (int) (imgRatio * actualWidth);
                 actualHeight = (int) maxHeight;
             } else if (imgRatio > maxRatio) {
                 imgRatio = maxWidth / actualWidth;
                 actualHeight = (int) (imgRatio * actualHeight);
                 actualWidth = (int) maxWidth;
             } else {
                 actualHeight = (int) maxHeight;
                 actualWidth = (int) maxWidth;
             }
         }
         // setting inSampleSize value allows to load a scaled down version of the original image
         options.inSampleSize = calculateInSampleSize4(options, actualWidth, actualHeight);
         // inJustDecodeBounds set to false to load the actual bitmap
         options.inJustDecodeBounds = false;
         // this options allow android to claim the bitmap memory if it runs low on memory
         options.inPurgeable = true;
         options.inInputShareable = true;
         options.inTempStorage = new byte[16 * 1024];
         try {
             // load the bitmap from its path
             bmp = BitmapFactory.decodeFile(filePath, options);
         } catch (OutOfMemoryError exception) {
             exception.printStackTrace();
         }
         try {
             scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
         } catch (OutOfMemoryError exception) {
             exception.printStackTrace();
         }

         float ratioX = actualWidth / (float) options.outWidth;
         float ratioY = actualHeight / (float) options.outHeight;
         float middleX = actualWidth / 2.0f;
         float middleY = actualHeight / 2.0f;

         Matrix scaleMatrix = new Matrix();
         scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);
         Canvas canvas = new Canvas(scaledBitmap);
         canvas.setMatrix(scaleMatrix);
         canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

         // check the rotation of the image and display it properly
         ExifInterface exif;
         try {
             exif = new ExifInterface(filePath);
             int orientation = exif.getAttributeInt(
                     ExifInterface.TAG_ORIENTATION, 0);
             Log.d("EXIF", "Exif: " + orientation);
             Matrix matrix = new Matrix();
             if (orientation == 6) {
                 matrix.postRotate(90);
                 Log.d("EXIF", "Exif: " + orientation);
             } else if (orientation == 3) {
                 matrix.postRotate(180);
                 Log.d("EXIF", "Exif: " + orientation);
             } else if (orientation == 8) {
                 matrix.postRotate(270);
                 Log.d("EXIF", "Exif: " + orientation);
             }
             scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
         } catch (IOException e) {
             e.printStackTrace();
         }
         FileOutputStream out = null;
         filename = getFilename4();
         try { out = new FileOutputStream(filename);
             // write the compressed bitmap at the destination specified by filename.
             scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }
         Log.d("et==", filename);
         //save_image4(filename);
         return filename;
     }

     /**
     *
     *calculate height of the image
     * /*/
    public int calculateInSampleSize4(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height/ (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        final float totalPixels = width*height;
        final float totalReqPixelsCap = reqWidth*reqHeight*2; while (totalPixels / (inSampleSize*inSampleSize) > totalReqPixelsCap) {
                inSampleSize++;
            }
            previewMedia4(true);
            return inSampleSize;
    }

    /**
     *
     *
     * check for image or video
     * /*/
    private void previewMedia4(boolean isImage) {
        // Checking whether captured media is image or video
        if (isImage) {
            //vidPreview.setVisibility(View.GONE);
            // bimatp factory
        BitmapFactory.Options options = new BitmapFactory.Options();
        // down sizing image as it throws OutOfMemory Exception for larger images
            options.inSampleSize = 2;

            final Bitmap bitmap = BitmapFactory.decodeFile(filename, options);
            options.inJustDecodeBounds = true;

        }
    }
    /**
     *
    * this will get the filename of the selected image
     * /*/
    public String getFilename4() {
        File file = new File(Environment.getExternalStorageDirectory().getPath(), "EduLog");
        if (!file.exists()) {
            file.mkdirs();
        }
        String uriSting = (file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg");
        return uriSting;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 1) {
            Uri targetUri = data.getData();

            String compress_path = compressImage4(getRealPathFromURI(targetUri));

                Toast.makeText(ProfileActivity.this, "profile", Toast.LENGTH_SHORT).show();
                Glide.with(ProfileActivity.this).load(targetUri).into(ivProfile);

        }
    }

    private void sendImage(String file) {
        File file1 = new File(file);
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file1);
        // MultipartBody.Part is used to send the actual file name as well
        MultipartBody.Part body = MultipartBody.Part.createFormData("images", file1.getName(), requestFile);
        // you may add another part within the multipart request
        Call<UploadImageModel> call = apiInterface.uploadImage(object);
        call.enqueue(new Callback<UploadImageModel>() {
            @Override
            public void onResponse(Call<UploadImageModel> call, Response<UploadImageModel> response) {
                if (response.code() == 200) {
                    Glide.with(editProfile)
                            .load("https://eduvriksh.com/managepro/assets/uploads/profileImage/1614751945profilePic.jpg")
                            .into(ivProfile);
                    Toast.makeText(ProfileActivity.this, "" + response.body().getUrl(), Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<UploadImageModel> call, Throwable t) {
                //Toast.makeText(ProfileActivity.this, "Fail" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}