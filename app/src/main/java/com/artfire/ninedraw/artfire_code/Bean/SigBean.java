package com.artfire.ninedraw.artfire_code.Bean;

/**
 * Created by Lenovo on 2017/7/24.
 */

public class SigBean {

    /**
     * message :
     * resultType : 1
     * appendData : {"sig":"eJxl0E1Pg0AQBuA7v4JwxegsuALetEUhrQZSjJbLhsJSVmVZ2C39iv9dpSZiPD-vZOado6brupHMF*dZnjcbrojaC2ro17oBxtkvCsEKkilid8U-pDvBOkqyUtFuQAt7FsA4wgrKFSvZT8AD27oasSzeyLBiUHT5NYwcBH8ibD3gg7*chPE0DibmLKvWTS39VXwfiOV7-wLm4W5bSi8Rm8C-lSnKZ7v9NqxuouawemxxGLkMLp7LNFXTFlu1Yz7ZPOKLJAherXLet7waH61YffoEwgCu42DsjrSnnWQNPxUGhBFCHny31j60TzbbXHw_"}
     * logMessage :
     */

    private String message;
    private String resultType;
    private AppendDataBean appendData;
    private String logMessage;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public AppendDataBean getAppendData() {
        return appendData;
    }

    public void setAppendData(AppendDataBean appendData) {
        this.appendData = appendData;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    public static class AppendDataBean {
        /**
         * sig : eJxl0E1Pg0AQBuA7v4JwxegsuALetEUhrQZSjJbLhsJSVmVZ2C39iv9dpSZiPD-vZOado6brupHMF*dZnjcbrojaC2ro17oBxtkvCsEKkilid8U-pDvBOkqyUtFuQAt7FsA4wgrKFSvZT8AD27oasSzeyLBiUHT5NYwcBH8ibD3gg7*chPE0DibmLKvWTS39VXwfiOV7-wLm4W5bSi8Rm8C-lSnKZ7v9NqxuouawemxxGLkMLp7LNFXTFlu1Yz7ZPOKLJAherXLet7waH61YffoEwgCu42DsjrSnnWQNPxUGhBFCHny31j60TzbbXHw_
         */

        private String sig;

        public String getSig() {
            return sig;
        }

        public void setSig(String sig) {
            this.sig = sig;
        }
    }
}
