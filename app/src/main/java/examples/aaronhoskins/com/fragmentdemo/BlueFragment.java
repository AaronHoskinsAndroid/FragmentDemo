package examples.aaronhoskins.com.fragmentdemo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlueFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlueFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlueFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_INIT_DATA = "init_data";
    private String initData;
    private OnFragmentInteractionListener mListener;
    private TextView tvInitData;
    private EditText etUserInput;
    private Button btnSendData;

    public BlueFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param initData Parameter 1.
     *
     * @return A new instance of fragment BlueFragment.
     */
    public static BlueFragment newInstance(String initData) {
        BlueFragment fragment = new BlueFragment();
        Bundle args = new Bundle();
        args.putString(ARG_INIT_DATA, initData);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            initData = getArguments().getString(ARG_INIT_DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blue, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvInitData = view.findViewById(R.id.tvInitData);
        etUserInput = view.findViewById(R.id.etUserInput);
        btnSendData = view.findViewById(R.id.btnSendUserInput);
        btnSendData.setOnClickListener(this);

        tvInitData.setText(initData);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        final String input = etUserInput.getText().toString();
        mListener.onDataSent(input);
    }

    public interface OnFragmentInteractionListener {
        void onDataSent(String dataToSend);
    }
}
