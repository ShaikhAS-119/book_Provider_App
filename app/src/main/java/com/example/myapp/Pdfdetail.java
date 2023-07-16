 package com.example.myapp;

 import android.app.ProgressDialog;
 import android.content.Intent;
 import android.net.Uri;
 import android.os.Bundle;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.AdapterView;
 import android.widget.ArrayAdapter;
 import android.widget.Button;
 import android.widget.EditText;
 import android.widget.ImageView;
 import android.widget.RadioButton;
 import android.widget.Spinner;
 import android.widget.Toast;

 import androidx.annotation.Nullable;
 import androidx.fragment.app.Fragment;

 import com.google.android.gms.tasks.Task;
 import com.google.firebase.database.DatabaseReference;
 import com.google.firebase.database.FirebaseDatabase;
 import com.google.firebase.storage.FirebaseStorage;
 import com.google.firebase.storage.StorageReference;

 import java.util.ArrayList;
 import java.util.List;

 import static android.app.Activity.RESULT_OK;

public class Pdfdetail extends Fragment  {
    
    RadioButton radioButton, radioButton1;
    EditText editText;
    Spinner spinner, spinner1;
    ImageView image;
    Button button;
    pdfModel model;
    DatabaseReference firebaseDatabase;
    StorageReference mStorageRef;

    public Pdfdetail() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_pdfdetail, container, false);


//        radioButton = view.findViewById(R.id.radioButton);
//        radioButton1 = view.findViewById(R.id.radioButton1);
//        RadioGroup radioGroup=view.findViewById(R.id.radiog);

        mStorageRef= FirebaseStorage.getInstance().getReference();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference("uploadpdf");
        spinner = view.findViewById(R.id.spinner);
        //select year
        //String[] yrs = {"Select Year","First Year", "Second Year", "Third Year"};
        List<String> categories=new ArrayList<>();
        categories.add(0,"Select Category");
        categories.add("E-Books");
        categories.add("Research Paper");
        categories.add("Other");
        ArrayAdapter adapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals(toString())){

                   // String pos=parent.getSelectedItem().toString();
                }
                else {
                    parent.getItemAtPosition(position).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getContext(), "Category not selected", Toast.LENGTH_SHORT).show();
            }
        });

        /*spinner1 = view.findViewById(R.id.spinner1);
        //select semester
        List<String> cat=new ArrayList<>();
        cat.add("Select Semester");
        cat.add("1");
        cat.add("2");
        cat.add("3");
        cat.add("4");
        cat.add("5");
        cat.add("6");
        ArrayAdapter arrayAdapter=new ArrayAdapter(this.getActivity(),android.R.layout.simple_spinner_item,cat);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter);
        spinner1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select year")){
                }
                else {
                    parent.getItemAtPosition(position).toString();
                }
            }
        });*/
        //enter pdf name/title
        image=view.findViewById(R.id.image2);
        image.setOnClickListener(view1 -> {
            Intent intent =new Intent();
            intent.setType("application/pdf");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent,"Select pdf file"), 7);
        });
        editText=view.findViewById(R.id.edit2);
        //its same as clicking on attach image
//        editText.setOnClickListener(v -> {
//            Intent intent =new Intent();
//            intent.setType("application/pdf");
//            intent.setAction(Intent.ACTION_GET_CONTENT);
//            startActivityForResult(Intent.createChooser(intent,"Select pdf file"), 7);
//        });
        button=view.findViewById(R.id.button);

        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==7 && resultCode==RESULT_OK && data!= null && data.getData() != null){

            String path=data.getData().getPath()+".pdf";
            editText.setText(path);
            editText.setSelection(editText.length());

            //check condition here for field   reamin
            button.setOnClickListener(v -> UploadFile(data.getData()));
        }
        else{
            Toast.makeText(getContext(), "Fieid is empty", Toast.LENGTH_SHORT).show();
        }
    }
    private void UploadFile(Uri data) {
        final ProgressDialog progressDialog=new ProgressDialog(getContext());
        progressDialog.setTitle("uploading..");
        progressDialog.show();
        StorageReference reference= mStorageRef.child("upload/"+System.currentTimeMillis()+".pdf");
        reference.putFile(data)
                .addOnProgressListener(snapshot -> {
                    double progress=(100.0*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                    progressDialog.setMessage("File Uploded..."+(int)progress +"%");

                })
                .addOnSuccessListener(taskSnapshot -> {
                    Task<Uri> uriTask =taskSnapshot.getStorage().getDownloadUrl();
                    while (!uriTask.isComplete());
                        Uri url=uriTask.getResult();
                    String name=editText.getText().toString();
                    //String category=spinner.getSelectedItem().toString();
                    //   String radioText=radioButton.getText().toString();
                  //  model.setPdfSemester(spinner1.getSelectedItem().toString());
                  //  model.setPdfYear(spinner.getSelectedItem().toString());
                      model=new pdfModel(url.toString(),name );
                      model.setCategory(spinner.getSelectedItem().toString());
                      firebaseDatabase.child((firebaseDatabase.push().getKey())).setValue(model);
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(),"uploaded successfully",Toast.LENGTH_SHORT).show();

                });
    }
}
