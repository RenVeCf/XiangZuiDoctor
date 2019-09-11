package com.ipd.xiangzuidoctor.bean;

public class OfflineActivitiesDetailsBean {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"activityDetails":{"searchValue":null,"createBy":null,"createTime":"2019-09-06 14:56:10","updateBy":null,"updateTime":null,"remark":null,"params":{},"activityId":2,"logo":"upload/2019/09/06/cdd5316b1e294a4d119f35bb50f53eec.jpg","title":"测试收费活动","content":"<p>绿野户外论坛是国内驴友长期聚集活跃的户外运动社区,也是绿野网旗下主打的旅游论坛。绿野为用户提供户外知识资讯,自助游线路,旅行装备评测,活动发起,户外旅行保险等服务<img src=\"http://47.244.137.174:8083//ueditor/jsp/upload/image/20190906/1567752961978065391.jpg\" title=\"1567752961978065391.jpg\" width=\"100%\" height=\"auto\" alt=\"安卓壁纸_5155080148d5b95c2d42c1fa.jpg\"/><\/p>","status":"2","stopTime":null,"total":0,"isMoney":"2","discountsMoney":99,"platformMoney":199,"tip":"1","picPath":"upload/2019/09/06/21a6d5b6073a5319950c70ec1ae208cc.jpg","startTime":"2019-10-13 00:00:00","isRegiSt":"1"}}
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
         * activityDetails : {"searchValue":null,"createBy":null,"createTime":"2019-09-06 14:56:10","updateBy":null,"updateTime":null,"remark":null,"params":{},"activityId":2,"logo":"upload/2019/09/06/cdd5316b1e294a4d119f35bb50f53eec.jpg","title":"测试收费活动","content":"<p>绿野户外论坛是国内驴友长期聚集活跃的户外运动社区,也是绿野网旗下主打的旅游论坛。绿野为用户提供户外知识资讯,自助游线路,旅行装备评测,活动发起,户外旅行保险等服务<img src=\"http://47.244.137.174:8083//ueditor/jsp/upload/image/20190906/1567752961978065391.jpg\" title=\"1567752961978065391.jpg\" width=\"100%\" height=\"auto\" alt=\"安卓壁纸_5155080148d5b95c2d42c1fa.jpg\"/><\/p>","status":"2","stopTime":null,"total":0,"isMoney":"2","discountsMoney":99,"platformMoney":199,"tip":"1","picPath":"upload/2019/09/06/21a6d5b6073a5319950c70ec1ae208cc.jpg","startTime":"2019-10-13 00:00:00","isRegiSt":"1"}
         */

        private ActivityDetailsBean activityDetails;

        public ActivityDetailsBean getActivityDetails() {
            return activityDetails;
        }

        public void setActivityDetails(ActivityDetailsBean activityDetails) {
            this.activityDetails = activityDetails;
        }

        public static class ActivityDetailsBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : 2019-09-06 14:56:10
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * activityId : 2
             * logo : upload/2019/09/06/cdd5316b1e294a4d119f35bb50f53eec.jpg
             * title : 测试收费活动
             * content : <p>绿野户外论坛是国内驴友长期聚集活跃的户外运动社区,也是绿野网旗下主打的旅游论坛。绿野为用户提供户外知识资讯,自助游线路,旅行装备评测,活动发起,户外旅行保险等服务<img src="http://47.244.137.174:8083//ueditor/jsp/upload/image/20190906/1567752961978065391.jpg" title="1567752961978065391.jpg" width="100%" height="auto" alt="安卓壁纸_5155080148d5b95c2d42c1fa.jpg"/></p>
             * status : 2
             * stopTime : null
             * total : 0
             * isMoney : 2
             * discountsMoney : 99.0
             * platformMoney : 199.0
             * tip : 1
             * picPath : upload/2019/09/06/21a6d5b6073a5319950c70ec1ae208cc.jpg
             * startTime : 2019-10-13 00:00:00
             * isRegiSt : 1
             */

            private Object searchValue;
            private Object createBy;
            private String createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBean params;
            private int activityId;
            private String logo;
            private String title;
            private String content;
            private String status;
            private Object stopTime;
            private int total;
            private String isMoney;
            private double discountsMoney;
            private double platformMoney;
            private String tip;
            private String picPath;
            private String startTime;
            private String isRegiSt;

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

            public int getActivityId() {
                return activityId;
            }

            public void setActivityId(int activityId) {
                this.activityId = activityId;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Object getStopTime() {
                return stopTime;
            }

            public void setStopTime(Object stopTime) {
                this.stopTime = stopTime;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public String getIsMoney() {
                return isMoney;
            }

            public void setIsMoney(String isMoney) {
                this.isMoney = isMoney;
            }

            public double getDiscountsMoney() {
                return discountsMoney;
            }

            public void setDiscountsMoney(double discountsMoney) {
                this.discountsMoney = discountsMoney;
            }

            public double getPlatformMoney() {
                return platformMoney;
            }

            public void setPlatformMoney(double platformMoney) {
                this.platformMoney = platformMoney;
            }

            public String getTip() {
                return tip;
            }

            public void setTip(String tip) {
                this.tip = tip;
            }

            public String getPicPath() {
                return picPath;
            }

            public void setPicPath(String picPath) {
                this.picPath = picPath;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getIsRegiSt() {
                return isRegiSt;
            }

            public void setIsRegiSt(String isRegiSt) {
                this.isRegiSt = isRegiSt;
            }

            public static class ParamsBean {
            }
        }
    }
}
