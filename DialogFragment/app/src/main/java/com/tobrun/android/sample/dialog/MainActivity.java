package com.tobrun.android.sample.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;

public class MainActivity extends ActionBarActivity implements MainDialogFragment.OnDialogCloseListener {

    private Person mPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mPerson = savedInstanceState.getParcelable(Person.class.getName());
        } else {
            mPerson = new Person("Tobrun");
        }

        DialogFragment dialog = MainDialogFragment.getInstance(mPerson);
        dialog.show(getSupportFragmentManager(), "inputDialog");
    }

    @Override
    public void onDialogClose(Person person) {
        mPerson = person;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Person.class.getName(), mPerson);
    }

}
