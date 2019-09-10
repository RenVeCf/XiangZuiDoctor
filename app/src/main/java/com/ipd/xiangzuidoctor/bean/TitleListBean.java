package com.ipd.xiangzuidoctor.bean;

import java.util.List;

public class TitleListBean {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"titleList":[{"searchValue":null,"createBy":null,"createTime":"2019-07-27 12:32:15","updateBy":null,"updateTime":null,"remark":null,"params":{},"titleId":1,"titleName":"初级(住院医师)","status":0},{"searchValue":null,"createBy":null,"createTime":"2019-07-27 12:32:15","updateBy":null,"updateTime":null,"remark":null,"params":{},"titleId":2,"titleName":"中级(主治医师)","status":0},{"searchValue":null,"createBy":null,"createTime":"2019-07-27 12:32:15","updateBy":null,"updateTime":null,"remark":null,"params":{},"titleId":3,"titleName":"副高级(副主任医师)","status":0},{"searchValue":null,"createBy":null,"createTime":"2019-07-27 12:32:15","updateBy":null,"updateTime":null,"remark":null,"params":{},"titleId":4,"titleName":"正高级(主任医师)","status":0}]}
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
        private List<TitleListsBean> titleList;

        public List<TitleListsBean> getTitleList() {
            return titleList;
        }

        public void setTitleList(List<TitleListsBean> titleList) {
            this.titleList = titleList;
        }

        public static class TitleListsBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : 2019-07-27 12:32:15
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * titleId : 1
             * titleName : 初级(住院医师)
             * status : 0
             */

            private Object searchValue;
            private Object createBy;
            private String createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBean params;
            private int titleId;
            private String titleName;
            private int status;

            public Object getSearchValue() {
                return searchValue;
            }

            public void setSearchValue(Object searchValue) {
                this.searchValue = searchValue;
            }

            public Object getCreateBy() {
                return createBy;
            }

            public void setCreateBy(Object createBy) {
                this.createBy = createBy;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(Object updateBy) {
                this.updateBy = updateBy;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public ParamsBean getParams() {
                return params;
            }

            public void setParams(ParamsBean params) {
                this.params = params;
            }

            public int getTitleId() {
                return titleId;
            }

            public void setTitleId(int titleId) {
                this.titleId = titleId;
            }

            public String getTitleName() {
                return titleName;
            }

            public void setTitleName(String titleName) {
                this.titleName = titleName;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public static class ParamsBean {
            }
        }
    }
}
