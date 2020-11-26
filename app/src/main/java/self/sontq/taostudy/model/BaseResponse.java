package self.sontq.taostudy.model;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {
    @SerializedName("status")
    private boolean status;
    @SerializedName("error_code")
    private int error_code;
    @SerializedName("error_message")
    private String error_message;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }
}
