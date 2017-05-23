package com.drom.dromtesttask.model;

import android.os.Parcel;
import android.os.Parcelable;

public class StateEndlessRecycler implements Parcelable {

    private int currentPage;
    private int previousTotalItemCount;
    private boolean loading;

    public StateEndlessRecycler() {

    }

    // region - get/set -

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPreviousTotalItemCount() {
        return previousTotalItemCount;
    }

    public void setPreviousTotalItemCount(int previousTotalItemCount) {
        this.previousTotalItemCount = previousTotalItemCount;
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    // endregion

    // region - Parcelable -

    protected StateEndlessRecycler(Parcel in) {
        currentPage = in.readInt();
        previousTotalItemCount = in.readInt();
        loading = in.readByte() != 0;
    }

    public static final Creator<StateEndlessRecycler> CREATOR = new Creator<StateEndlessRecycler>() {
        @Override
        public StateEndlessRecycler createFromParcel(Parcel in) {
            return new StateEndlessRecycler(in);
        }

        @Override
        public StateEndlessRecycler[] newArray(int size) {
            return new StateEndlessRecycler[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(currentPage);
        dest.writeInt(previousTotalItemCount);
        dest.writeByte((byte) (loading ? 1 : 0));
    }

    // endregion
}
