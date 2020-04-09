package com.dashletter;

public class ChipInfo {
    private String chipName;
    private String chipUrl;

    public ChipInfo(String chipName, String chipUrl) {
        this.chipName = chipName;
        this.chipUrl = chipUrl;
    }

    public String getChipName() {
        return chipName;
    }

    public void setChipName(String chipName) {
        this.chipName = chipName;
    }

    public String getChipUrl() {
        return chipUrl;
    }

    public void setChipUrl(String chipUrl) {
        this.chipUrl = chipUrl;
    }
}
