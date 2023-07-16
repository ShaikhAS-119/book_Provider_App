 package com.example.myapp;

 import android.app.Activity;
 import android.app.ProgressDialog;
 import android.content.Intent;
 import android.net.Uri;
 import android.os.Bundle;
 import android.os.Handler;
 import android.os.Looper;
 import android.text.TextUtils;
 import android.util.Log;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.Button;
 import android.widget.EditText;
 import android.widget.ImageView;
 import android.widget.Toast;

 import androidx.fragment.app.Fragment;

 import com.airbnb.lottie.LottieAnimationView;
 import com.google.android.gms.tasks.Task;
 import com.google.android.material.floatingactionbutton.FloatingActionButton;
 import com.google.firebase.database.DatabaseReference;
 import com.google.firebase.database.FirebaseDatabase;
 import com.google.firebase.storage.FirebaseStorage;
 import com.google.firebase.storage.StorageReference;
 import com.google.firebase.storage.UploadTask;


 public class Bookdetail extends Fragment {
   // int SLECT_PHOTO=1;
    ImageView imageView;
    EditText editText1,editText2,editText3,editText4,editText5,editText6;
     FirebaseDatabase database;

     //private final int progressStatus = 0;
     //private final Handler handler = new Handler();
     Button button;
     private Uri currentUri;
    StorageReference mStorageRef;


    public Bookdetail() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_bookdetail, container, false);

        mStorageRef= FirebaseStorage.getInstance().getReference();
        database = FirebaseDatabase.getInstance();
        imageView = view.findViewById(R.id.image);



        FloatingActionButton floatingActionButton = view.findViewById(R.id.camera);
        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent,"Select photo"),1);

        });

        editText1 = view.findViewById(R.id.Edt1);
        editText2 = view.findViewById(R.id.Edt2);
        editText3 = view.findViewById(R.id.Edt3);
        editText4 = view.findViewById(R.id.Edt4);
        editText5 = view.findViewById(R.id.Edt5);
        editText6 = view.findViewById(R.id.Edt6);

        button = view.findViewById(R.id.button1);
        button.setOnClickListener(v -> {


            if(TextUtils.isEmpty(editText1.getText().toString())){
                Toast.makeText(getContext(), "field is empty", Toast.LENGTH_SHORT).show();
            }
            else {
                selectedImage(currentUri);

            }

        });

        return view;

    }

     // onActivityResult() handles callbacks from the photo picker.
     @Override
     public void onActivityResult(
             int requestCode, int resultCode, final Intent data) {

         if (resultCode != Activity.RESULT_OK) {
             // Handle error
             Log.i("Bookdetail","select image" );
         }

         else {

                 // Get photo picker response for single select.
                  currentUri = data.getData();
                 // Do stuff with the photo/video URI.
                 imageView.setImageURI(currentUri);
                 //this new line is added
                 //selectedImage(currentUri);

         }
     }
     private void selectedImage(Uri data)  {

         String bookName = editText1.getEditableText().toString().trim();
         String publisherName = editText2.getText().toString();
         String writerName = editText3.getText().toString();
         String price = editText4.getText().toString();
         String pages = editText5.getText().toString().trim();
         String contact = editText6.getEditableText().toString();

         final ProgressDialog progressDialog=new ProgressDialog(getContext());
         progressDialog.setTitle("Uploading..");
         progressDialog.show();
         StorageReference reference= mStorageRef.child("upload/"+System.currentTimeMillis()+".png");
         reference.putFile(data)

           .addOnProgressListener(snapshot -> {

                     double progress=(100.0*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                     progressDialog.setMessage("File Uploded..."+(int)progress +"%");

                 }) .addOnSuccessListener((UploadTask.TaskSnapshot taskSnapshot) -> {

                     Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                     while (!uriTask.isComplete()) ;
                     Uri url = uriTask.getResult();

                     DatabaseReference myRef = database.getReference("Bookdetail");
                     bookdetailpro Bookdetailpro = new bookdetailpro(bookName, publisherName, writerName, price, pages, contact, url.toString());
                     myRef.child("detail").push().setValue(Bookdetailpro);

                     progressDialog.dismiss();

                     Intent intent=new Intent(getActivity(),historypost.class);
                     startActivity(intent);


                     Toast.makeText(getActivity(),"uploaded successfully",Toast.LENGTH_SHORT).show();

                 });
     }


 }