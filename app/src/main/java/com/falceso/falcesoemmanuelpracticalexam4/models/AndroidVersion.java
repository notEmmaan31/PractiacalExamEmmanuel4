package com.falceso.falcesoemmanuelpracticalexam4.models;

public class AndroidVersion {
    String id, codeName, releaseDate, apiLevel;

    public AndroidVersion(){

    }

    public AndroidVersion(String id, String codeName, String releaseDate, String apiLevel) {
        this.id = id;
        this.codeName = codeName;
        this.releaseDate = releaseDate;
        this.apiLevel = apiLevel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getApiLevel() {
        return apiLevel;
    }

    public void setApiLevel(String apiLevel) {
        this.apiLevel = apiLevel;
    }


}
