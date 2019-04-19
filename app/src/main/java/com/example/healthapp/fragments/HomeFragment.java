package com.example.healthapp.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.healthapp.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText editTextUserWeight;
    EditText editTextUserHeight;
    TextView textViewResult;
    Button buttonCalculate;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        editTextUserWeight = (EditText)view.findViewById(R.id.editText_home_fragment_user_weight);
        editTextUserHeight = (EditText)view.findViewById(R.id.editText_home_fragment_user_height);
        textViewResult = (TextView)view.findViewById(R.id.textView_home_fragment_result);
        buttonCalculate = (Button) view.findViewById(R.id.button_home_fragment_calculate);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
        return view;
    }

    private void calculate() {

        if (editTextUserWeight.getText().length() > 0 && editTextUserHeight.getText().length() > 0) {
            float userWeight =  Float.parseFloat(editTextUserWeight.getText().toString());
            float userHeight = Float.parseFloat(editTextUserHeight.getText().toString());
            if(userHeight>0) {
                userHeight = userHeight / 100;
                float result = (userWeight / ((userHeight * userHeight)));
                String resultDescription = "";

                if (result < 15) {
                    resultDescription = "Aşırı Zayıf";
                } else if (result > 15 && result <= 30) {
                    resultDescription = "Zayıf";
                } else if (result > 30 && result <= 40) {
                    resultDescription = "Normal";
                } else if (result > 40) {
                    resultDescription = "Aşırı Şişman (Morbid Obez)";
                } else {
                    resultDescription = "Aşırı Şişman (Morbid Obez)";
                }

                String resultText = "Vücut kitle endeksiniz: " + result + "\n" + resultDescription;
                textViewResult.setText(resultText);
            }
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Hata!");
            builder.setMessage("Kilo veya boy bilgisi boş bırakılamaz.");
            builder.setNegativeButton("TAMAM", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            builder.show();
        }
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}