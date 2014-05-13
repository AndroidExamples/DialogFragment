package com.tobrun.android.sample.dialog;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by home on 10/05/14.
 */
public class Person implements Parcelable {

    private String name;

    public Person(String name){
        this.name = name;
    }

    public Person(Parcel in){
        this.name = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel destination, int flags) {
        destination.writeString(this.name);
    }

    public final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

}
