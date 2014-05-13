package com.tobrun.android.sample.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;

/**
 * DialogFragment example demonstrating the communication between fragment and activity
 * <p/>
 * <p/>
 * <p/>
 * Created by tobrun on 10/05/14.
 */
public class MainDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    /**
     * Activity communication
     */
    private OnDialogCloseListener mCallback;

    /**
     * Person model object
     */
    private Person mPerson;

    /**
     * View linked to model
     */
    private EditText mEditText;

    /**
     * Bundle Key of model
     */
    private final static String BUNDLE_KEY = Person.class.getName();

    /**
     * @param person
     * @return
     */
    public static DialogFragment getInstance(Person person) {
        DialogFragment dialog = new MainDialogFragment();
        Bundle arguments = new Bundle();
        arguments.putParcelable(Person.class.getName(), person);
        dialog.setArguments(arguments);
        return dialog;
    }

    /**
     * @param activity
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnDialogCloseListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.getClass().getSimpleName()
                    + " must implement OnDialogCloseListener");
        }
    }

    /**
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreatePerson(savedInstanceState);
    }

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Enter person name");
        builder.setNegativeButton("Cancel", null);
        builder.setPositiveButton("Save", this);

        mEditText = new EditText(getActivity());
        mEditText.setText(mPerson.getName());
        builder.setView(mEditText);

        return builder.create();
    }

    /**
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        onUpdatePerson();
        outState.putParcelable(BUNDLE_KEY, mPerson);
    }

    /**
     * @param dialogInterface
     * @param i
     */
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if (DialogInterface.BUTTON_POSITIVE == i) {
            onUpdatePerson();
            mCallback.onDialogClose(mPerson);
        }
    }

    private void onCreatePerson(Bundle bundle) {

        boolean initialCreation = false;
        if (bundle == null) {
            initialCreation = true;
            bundle = getArguments();
        }

        if (bundle != null && bundle.containsKey(BUNDLE_KEY)) {
            mPerson = bundle.getParcelable(BUNDLE_KEY);
        } else {
            throw new IllegalArgumentException(getClass().getSimpleName()
                    + " is missing an argument from bundle with key : "
                    + BUNDLE_KEY
                    + ", "
                    + (initialCreation
                    ?
                    "this fragment must be instantiated using getInstance method"
                    :
                    "verify the savedInstanceState method"
            )
            );
        }
    }

    private void onUpdatePerson() {
        mPerson.setName(mEditText.getText().toString());
    }


    interface OnDialogCloseListener {

        void onDialogClose(Person person);

    }


}
