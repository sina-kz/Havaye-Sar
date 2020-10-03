package com.example.khodeti.Networking;

import com.google.gson.annotations.SerializedName;

public class ReceiveImageResponse {
    @SerializedName("isOk")
    public boolean isOk;

    public ReceiveImageResponse(boolean isOk) {
        this.isOk = isOk;
    }
}
