package com.example.rpp2;

import com.google.gson.annotations.SerializedName;

import java.util.logging.Logger;

public class Technology {
    @SerializedName("name")
    private String name;
    @SerializedName("helptext")
    private String helpText;
    private final String baseImageUrl =
            "https://raw.githubusercontent.com/wesleywerner/ancient-tech/" +
                    "02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/";
    @SerializedName("graphic")
    private String imageUrl;

    public Technology(String name, String helpText, String imageUrl) {
        this.name = name;
        this.helpText = helpText;
        this.imageUrl = baseImageUrl + imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getHelpText() {
        return helpText;
    }

    public String getImageUrl() {
        return baseImageUrl + imageUrl;

    }
}
