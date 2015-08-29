package com.android.tonight8.dao.entity;

/**
 * 每次接口传输必传的一些字段
 * Created by LiXiaoSong
 * Date: 2015/8/26 0026
 */
public class Common {
    private String version;
    private String deviceType;
    private String imei;
    private String dpi;
    private String ppi;
    private String osVersion;
    private String phoneModel;
    private String authCode;
    private String coordinate;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getPpi() {
        return ppi;
    }

    public void setPpi(String ppi) {
        this.ppi = ppi;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public String toString() {
        return "Common{" +
                "version='" + version + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", imei='" + imei + '\'' +
                ", dpi='" + dpi + '\'' +
                ", ppi='" + ppi + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", phoneModel='" + phoneModel + '\'' +
                ", authCode='" + authCode + '\'' +
                ", coordinate='" + coordinate + '\'' +
                '}';
    }
}
