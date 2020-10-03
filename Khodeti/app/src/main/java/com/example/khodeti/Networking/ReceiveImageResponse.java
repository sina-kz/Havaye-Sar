package com.example.khodeti.Networking;

import com.google.gson.annotations.SerializedName;

public class ReceiveImageResponse {
    @SerializedName("Status")
    public boolean Status;

    public ReceiveImageResponse(boolean Status) {
        this.Status = Status;
    }
}
