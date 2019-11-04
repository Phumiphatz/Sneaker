package com.example.sneaky


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.sneaky.databinding.FragmentInsertBinding
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.util.*
import androidx.lifecycle.Observer

/**
 * A simple [Fragment] subclass.
 */
class insert : Fragment() {
    private lateinit var binding: FragmentInsertBinding
    private lateinit var model: dataViewModel

    var selectSpinner = ""
    var fileUri: Uri? = null
    var Url = ""
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    var mAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentInsertBinding>(
            inflater,
            R.layout.fragment_insert, container, false
        )
        binding.imageButton.setOnClickListener {
            val pickImageIntent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(pickImageIntent, AppConstants.PICK_PHOTO_REQUEST)
        }
        model = ViewModelProviders.of(this).get(dataViewModel::class.java)
        selected()
        auth()
       // model.clear()
        insert()
        model.all.observe(this, Observer { t ->
            t.forEach {
                Log.i("checkUpload", "${it} ")
            }
        })
        return binding.root
    }

    object AppConstants {
        val TAKE_PHOTO_REQUEST: Int = 2
        val PICK_PHOTO_REQUEST: Int = 1
    }

    private fun auth() {
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        mAuth.signInWithEmailAndPassword("59161109@go.buu.ac.th", "@bBc213ee03")
            ?.addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    val firebaseUser = this.mAuth.currentUser!!
                } else {
                    Log.e("error", "${task.exception?.message}")
                }
            }
    }

    private fun selected() {
        var list_of_items = arrayOf("nike", "vans", "converse", "adidas", "birkenstock")
        val spinner = binding.spinner
        spinner?.adapter = activity?.applicationContext?.let {
            ArrayAdapter(
                it,
                R.layout.support_simple_spinner_dropdown_item,
                list_of_items
            )
        } as SpinnerAdapter
        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val type = parent?.getItemAtPosition(position).toString()
                selectSpinner = type
            }

        }
    }

    private fun addUploadRecordToDb(uri: String) {
        val db = FirebaseFirestore.getInstance()
        val data = HashMap<String, Any>()
        data["imageUrl"] = uri
        db.collection("posts")
            .add(data)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(getActivity(), "success", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(getActivity(), "${e}", Toast.LENGTH_LONG).show()
            }
    }

    private fun uploadImage() {
        if (fileUri != null) {
            Log.d("urlImg4","เข้า4")
            val ref = storageReference?.child("uploads/" + UUID.randomUUID().toString())
            val uploadTask = ref?.putFile(fileUri!!)
            uploadTask?.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->

                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                return@Continuation ref.downloadUrl
            })?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    Url = downloadUri.toString()
                    addUploadRecordToDb(downloadUri.toString())
                } else {
                }
            }?.addOnFailureListener {

            }
        } else {
            Toast.makeText(getActivity(), "upload fail", Toast.LENGTH_SHORT).show()
        }
    }

    private fun insert() {
        binding.btnSubmit.setOnClickListener {
            if (!binding.insertBand.text.isEmpty() && !binding.insertDetail.text.isEmpty() &&fileUri != null) {

                Handler().postDelayed({
                    model.insert(
                        sneakyDatabaseModels(
                            0,
                            selectSpinner,
                            binding.insertBand.text.toString(),
                            Url,
                            binding.insertDetail.text.toString()
                        )
                    )
                    Toast.makeText(getActivity(), "บันทึกข้อมูลเรียบร้อย", Toast.LENGTH_LONG).show()
                    view?.findNavController()
                        ?.navigate(R.id.action_insert_to_typeChoose)
                }, 2000)
            } else {
                Toast.makeText(getActivity(), "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK && requestCode == AppConstants.PICK_PHOTO_REQUEST) {
            Log.d("urlImg3","เข้า3")
            fileUri = data?.data
            binding.imageButton.setImageURI(fileUri)
            uploadImage()
            Log.d("urlImg",Url)
        }
        Log.d("urlImg2","ไม่เข้า")
    }
}
