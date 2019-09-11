package com.ipd.xiangzuidoctor.bean;

public class SpecialColumnDetailsBean {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"isFollow":1,"h5medicalDetails":"H5/document/medicalDetails.html?medicalId=10"}
     */

    private String msg;
    private int code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * isFollow : 1
         * h5medicalDetails : H5/document/medicalDetails.html?medicalId=10
         */

        private int isFollow;
        private String h5medicalDetails;

        public int getIsFollow() {
            return isFollow;
        }

        public void setIsFollow(int isFollow) {
            this.isFollow = isFollow;
        }

        public String getH5medicalDetails() {
            return h5medicalDetails;
        }

        public void setH5medicalDetails(String h5medicalDetails) {
            this.h5medicalDetails = h5medicalDetails;
        }
    }
}
