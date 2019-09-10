package com.ipd.xiangzuidoctor.bean;

import java.util.List;

public class HomeBean {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"alreadyList":[],"stayList":[{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"orderId":29,"userId":5,"orderType":"1","surgeryName":"段-测试单台","hospitalName":"测试-医院","prov":"上海","city":"上海市","dist":"青浦区","address":"华徐公路888号","duration":2,"urgent":"2","urgentMoney":50,"premium":"1","premiumMoney":0,"evenNum":1,"expectMoney":2000,"status":"1","payType":null,"takeOrderId":null,"takeOrderTime":null,"cancelTime":null,"prompt":"","arriveTime":null,"beginTime":"2019-09-10 20:33:00","waitTime":null,"surgeryTime":null,"waitMoney":0,"surgeryMoney":0,"totalMoney":0,"taxMoney":0,"version":1,"orderNo":"190909878530","ahNumber":"18736044102","adNumber":null,"overtimeMoney":0,"invoicepayMoney":1610,"promptMoney":300,"endTime":null,"adMoney":1700,"ahMoney":2350,"adactualMoney":0,"ahactualMoney":0,"adpremiumMoney":0,"ahpremiumMoney":0},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"orderId":26,"userId":11,"orderType":"2","surgeryName":"","hospitalName":"医院名字","prov":"11","city":"11","dist":"111","address":"11","duration":1,"urgent":"2","urgentMoney":50,"premium":"1","premiumMoney":0,"evenNum":1,"expectMoney":1200,"status":"1","payType":null,"takeOrderId":null,"takeOrderTime":null,"cancelTime":null,"prompt":"","arriveTime":null,"beginTime":"2019-09-06 21:45:00","waitTime":null,"surgeryTime":null,"waitMoney":0,"surgeryMoney":0,"totalMoney":0,"taxMoney":0,"version":1,"orderNo":"190970663425","ahNumber":"15937016361","adNumber":null,"overtimeMoney":0,"invoicepayMoney":1380,"promptMoney":180,"endTime":null,"adMoney":1020,"ahMoney":1430,"adactualMoney":0,"ahactualMoney":0,"adpremiumMoney":0,"ahpremiumMoney":0}],"infoList":[{"searchValue":null,"createBy":null,"createTime":"2019-09-09 02:51:14","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":40,"userId":10,"infoType":"3","title":"订单完成","brief":null,"content":"190947835678已完成手术","orderId":null,"status":null,"nickname":"享醉994726","surgeryName":"测试01","orderCost":1384},{"searchValue":null,"createBy":null,"createTime":"2019-09-09 01:59:12","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":39,"userId":10,"infoType":"3","title":"订单完成","brief":null,"content":"190911551426已完成手术","orderId":null,"status":null,"nickname":"享醉994726","surgeryName":"测试最新订单02","orderCost":1445},{"searchValue":null,"createBy":null,"createTime":"2019-09-09 01:45:35","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":38,"userId":10,"infoType":"3","title":"订单完成","brief":null,"content":"190971360392已完成手术","orderId":null,"status":null,"nickname":"享醉994726","surgeryName":"最新测试","orderCost":1809},{"searchValue":null,"createBy":null,"createTime":"2019-09-09 01:33:59","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":37,"userId":10,"infoType":"3","title":"订单完成","brief":null,"content":"190971360392已完成手术","orderId":null,"status":null,"nickname":"享醉994726","surgeryName":"最新测试","orderCost":976},{"searchValue":null,"createBy":null,"createTime":"2019-09-08 23:43:24","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":36,"userId":10,"infoType":"3","title":"订单完成","brief":null,"content":"190971360392已完成手术","orderId":null,"status":null,"nickname":"享醉994726","surgeryName":"最新测试","orderCost":976},{"searchValue":null,"createBy":null,"createTime":"2019-09-06 22:31:17","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":35,"userId":3,"infoType":"3","title":"订单完成","brief":null,"content":"190979078479已完成手术","orderId":null,"status":null,"nickname":"懒懒的小猪","surgeryName":"连台手术","orderCost":1020},{"searchValue":null,"createBy":null,"createTime":"2019-09-05 15:04:20","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":34,"userId":8,"infoType":"3","title":"订单完成","brief":null,"content":"190973597383已完成手术","orderId":null,"status":null,"nickname":"测试医生端01","surgeryName":"连台手术","orderCost":1860},{"searchValue":null,"createBy":null,"createTime":"2019-09-05 14:44:09","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":33,"userId":8,"infoType":"3","title":"订单完成","brief":null,"content":"190925942947已完成手术","orderId":null,"status":null,"nickname":"测试医生端01","surgeryName":"测试单台手术10","orderCost":1180},{"searchValue":null,"createBy":null,"createTime":"2019-09-05 12:05:18","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":30,"userId":3,"infoType":"3","title":"订单完成","brief":null,"content":"190989490630已完成手术","orderId":null,"status":null,"nickname":"懒懒的小猪","surgeryName":"手术名字","orderCost":1860},{"searchValue":null,"createBy":null,"createTime":"2019-09-05 10:16:04","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":29,"userId":10,"infoType":"3","title":"订单完成","brief":null,"content":"190969584040已完成手术","orderId":null,"status":null,"nickname":"享醉994726","surgeryName":"张三订单","orderCost":976},{"searchValue":null,"createBy":null,"createTime":"2019-09-05 09:59:15","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":28,"userId":8,"infoType":"3","title":"订单完成","brief":null,"content":"190920919074已完成手术","orderId":null,"status":null,"nickname":"测试医生端01","surgeryName":"连台手术","orderCost":1860},{"searchValue":null,"createBy":null,"createTime":"2019-09-05 01:33:01","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":27,"userId":10,"infoType":"3","title":"订单完成","brief":null,"content":"190923567746已完成手术","orderId":null,"status":null,"nickname":"享醉994726","surgeryName":"患者信息","orderCost":1180},{"searchValue":null,"createBy":null,"createTime":"2019-09-04 23:37:29","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":26,"userId":8,"infoType":"3","title":"订单完成","brief":null,"content":"190903332025已完成手术","orderId":null,"status":null,"nickname":"测试医生端01","surgeryName":"连台手术","orderCost":6960},{"searchValue":null,"createBy":null,"createTime":"2019-09-04 23:24:38","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":25,"userId":8,"infoType":"3","title":"订单完成","brief":null,"content":"190914934875已完成手术","orderId":null,"status":null,"nickname":"测试医生端01","surgeryName":"连台手术","orderCost":2199.32},{"searchValue":null,"createBy":null,"createTime":"2019-09-04 23:13:24","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":24,"userId":8,"infoType":"3","title":"订单完成","brief":null,"content":"190965597346已完成手术","orderId":null,"status":null,"nickname":"测试医生端01","surgeryName":"测试单台手术01","orderCost":2132},{"searchValue":null,"createBy":null,"createTime":"2019-09-04 20:47:32","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":23,"userId":10,"infoType":"3","title":"订单完成","brief":null,"content":"190987615535已完成手术","orderId":null,"status":null,"nickname":"享醉994726","surgeryName":"手术名字","orderCost":1860},{"searchValue":null,"createBy":null,"createTime":"2019-09-04 13:53:12","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":18,"userId":10,"infoType":"3","title":"订单完成","brief":null,"content":"190942059278已完成手术","orderId":null,"status":null,"nickname":"享醉994726","surgeryName":"测试单台手术","orderCost":0}],"activityList":[{"searchValue":null,"createBy":null,"createTime":"2019-09-06 14:52:01","updateBy":null,"updateTime":"2019-09-06 14:53:40","remark":null,"params":{},"activityId":1,"logo":"upload/2019/09/06/5bc37a4a3fda19651bd60b22decc573e.jpg","title":"测试免费活动","content":"<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>绿野户外论坛是国内驴友长期聚集活跃的户外运动社区,也是绿野网旗下主打的旅游论坛。绿野为用户提供户外知识资讯,自助游线路,旅行装备评测,活动发起,户外旅行保险等服务<\/strong><\/p><p><img src=\"http://47.244.137.174:8083//ueditor/jsp/upload/image/20190906/1567752816213025403.jpg\" title=\"1567752816213025403.jpg\" width=\"100%\" height=\"auto\" alt=\"b7241d2b658503ee9677d54048943626.jpg\"/><\/p>","status":"2","stopTime":null,"total":0,"isMoney":"1","discountsMoney":0,"platformMoney":0,"tip":"2","picPath":"upload/2019/09/06/ab1ba8249b4dd2105371043eeba29e6d.jpg","startTime":"2019-10-12 00:00:00","isRegiSt":"1"}],"pictureList":[{"pictureId":30,"title":"测试医生端轮播图文本内容02","picPath":"upload/2019/09/04/76c5beb42467afa3f440c521adc8119f.jpg","status":"1","content":"<p>测试医生端轮播图文本内容02<\/p>","type":"3","pictureType":"3","url":null,"createTime":"2019-09-04 17:58:07","updateTime":null},{"pictureId":29,"title":"测试医生端轮播图链接网址02","picPath":"upload/2019/09/04/b89e395c6df30b419fca47c35e591a2c.jpg","status":"1","content":null,"type":"2","pictureType":"3","url":"https://www.163.com","createTime":"2019-09-04 17:57:32","updateTime":null},{"pictureId":28,"title":"测试医生端轮播图无02","picPath":"upload/2019/09/04/8dcb159258dc364a6259798b224661db.jpg","status":"1","content":null,"type":"1","pictureType":"3","url":null,"createTime":"2019-09-04 17:56:44","updateTime":null},{"pictureId":27,"title":"测试医生端轮播图文本内容01","picPath":"upload/2019/09/04/d4a5b2b886d6995148672a7ec264384f.jpg","status":"1","content":"<p>测试医生端轮播图文本内容01<\/p>","type":"3","pictureType":"3","url":null,"createTime":"2019-09-04 17:56:08","updateTime":null},{"pictureId":26,"title":"测试医生端轮播图链接网址01","picPath":"upload/2019/09/04/681821ed7a8c4119935f12ffe1f3b8c2.jpg","status":"1","content":null,"type":"2","pictureType":"3","url":"https://www.baidu.com","createTime":"2019-09-04 17:55:31","updateTime":null},{"pictureId":25,"title":"测试医生端轮播图无01","picPath":"upload/2019/09/04/7080357c8cff61e1377b404bd028d47d.jpg","status":"1","content":null,"type":"1","pictureType":"3","url":null,"createTime":"2019-09-04 17:54:49","updateTime":null}]}
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
        private List<AlreadyListBean> alreadyList;
        private List<StayListBean> stayList;
        private List<InfoListBean> infoList;
        private List<ActivityListBean> activityList;
        private List<PictureListBean> pictureList;

        public List<AlreadyListBean> getAlreadyList() {
            return alreadyList;
        }

        public void setAlreadyList(List<AlreadyListBean> alreadyList) {
            this.alreadyList = alreadyList;
        }

        public List<StayListBean> getStayList() {
            return stayList;
        }

        public void setStayList(List<StayListBean> stayList) {
            this.stayList = stayList;
        }

        public List<InfoListBean> getInfoList() {
            return infoList;
        }

        public void setInfoList(List<InfoListBean> infoList) {
            this.infoList = infoList;
        }

        public List<ActivityListBean> getActivityList() {
            return activityList;
        }

        public void setActivityList(List<ActivityListBean> activityList) {
            this.activityList = activityList;
        }

        public List<PictureListBean> getPictureList() {
            return pictureList;
        }

        public void setPictureList(List<PictureListBean> pictureList) {
            this.pictureList = pictureList;
        }

        public static class AlreadyListBean {

            /**
             * searchValue : null
             * createBy : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * orderId : 3
             * userId : 4
             * orderType : 1
             * surgeryName : 1
             * hospitalName : 1
             * prov : 1
             * city : 1
             * dist : 1
             * address : 1
             * duration : 1
             * urgent : 1
             * urgentMoney : 1
             * premium : null
             * premiumMoney : null
             * evenNum : null
             * expectMoney : null
             * status : 2
             * payType : null
             * takeOrderId : 4
             * takeOrderTime : null
             * cancelTime : null
             * prompt : null
             * arriveTime : null
             * beginTime : null
             * waitTime : null
             * surgeryTime : null
             * waitMoney : null
             * surgeryMoney : null
             * totalMoney : null
             * taxMoney : null
             * version : 1
             * orderNo : null
             */

            private Object searchValue;
            private Object createBy;
            private Object createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBean params;
            private int orderId;
            private int userId;
            private String orderType;
            private String surgeryName;
            private String hospitalName;
            private String prov;
            private String city;
            private String dist;
            private String address;
            private int duration;
            private String urgent;
            private int urgentMoney;
            private Object premium;
            private Object premiumMoney;
            private Object evenNum;
            private double expectMoney;
            private String status;
            private Object payType;
            private int takeOrderId;
            private Object takeOrderTime;
            private Object cancelTime;
            private Object prompt;
            private Object arriveTime;
            private String beginTime;
            private Object waitTime;
            private Object surgeryTime;
            private Object waitMoney;
            private Object surgeryMoney;
            private Object totalMoney;
            private Object taxMoney;
            private int version;
            private Object orderNo;

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

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
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

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getOrderType() {
                return orderType;
            }

            public void setOrderType(String orderType) {
                this.orderType = orderType;
            }

            public String getSurgeryName() {
                return surgeryName;
            }

            public void setSurgeryName(String surgeryName) {
                this.surgeryName = surgeryName;
            }

            public String getHospitalName() {
                return hospitalName;
            }

            public void setHospitalName(String hospitalName) {
                this.hospitalName = hospitalName;
            }

            public String getProv() {
                return prov;
            }

            public void setProv(String prov) {
                this.prov = prov;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDist() {
                return dist;
            }

            public void setDist(String dist) {
                this.dist = dist;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public String getUrgent() {
                return urgent;
            }

            public void setUrgent(String urgent) {
                this.urgent = urgent;
            }

            public int getUrgentMoney() {
                return urgentMoney;
            }

            public void setUrgentMoney(int urgentMoney) {
                this.urgentMoney = urgentMoney;
            }

            public Object getPremium() {
                return premium;
            }

            public void setPremium(Object premium) {
                this.premium = premium;
            }

            public Object getPremiumMoney() {
                return premiumMoney;
            }

            public void setPremiumMoney(Object premiumMoney) {
                this.premiumMoney = premiumMoney;
            }

            public Object getEvenNum() {
                return evenNum;
            }

            public void setEvenNum(Object evenNum) {
                this.evenNum = evenNum;
            }

            public double getExpectMoney() {
                return expectMoney;
            }

            public void setExpectMoney(double expectMoney) {
                this.expectMoney = expectMoney;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Object getPayType() {
                return payType;
            }

            public void setPayType(Object payType) {
                this.payType = payType;
            }

            public int getTakeOrderId() {
                return takeOrderId;
            }

            public void setTakeOrderId(int takeOrderId) {
                this.takeOrderId = takeOrderId;
            }

            public Object getTakeOrderTime() {
                return takeOrderTime;
            }

            public void setTakeOrderTime(Object takeOrderTime) {
                this.takeOrderTime = takeOrderTime;
            }

            public Object getCancelTime() {
                return cancelTime;
            }

            public void setCancelTime(Object cancelTime) {
                this.cancelTime = cancelTime;
            }

            public Object getPrompt() {
                return prompt;
            }

            public void setPrompt(Object prompt) {
                this.prompt = prompt;
            }

            public Object getArriveTime() {
                return arriveTime;
            }

            public void setArriveTime(Object arriveTime) {
                this.arriveTime = arriveTime;
            }

            public String getBeginTime() {
                return beginTime;
            }

            public void setBeginTime(String beginTime) {
                this.beginTime = beginTime;
            }

            public Object getWaitTime() {
                return waitTime;
            }

            public void setWaitTime(Object waitTime) {
                this.waitTime = waitTime;
            }

            public Object getSurgeryTime() {
                return surgeryTime;
            }

            public void setSurgeryTime(Object surgeryTime) {
                this.surgeryTime = surgeryTime;
            }

            public Object getWaitMoney() {
                return waitMoney;
            }

            public void setWaitMoney(Object waitMoney) {
                this.waitMoney = waitMoney;
            }

            public Object getSurgeryMoney() {
                return surgeryMoney;
            }

            public void setSurgeryMoney(Object surgeryMoney) {
                this.surgeryMoney = surgeryMoney;
            }

            public Object getTotalMoney() {
                return totalMoney;
            }

            public void setTotalMoney(Object totalMoney) {
                this.totalMoney = totalMoney;
            }

            public Object getTaxMoney() {
                return taxMoney;
            }

            public void setTaxMoney(Object taxMoney) {
                this.taxMoney = taxMoney;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }

            public Object getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(Object orderNo) {
                this.orderNo = orderNo;
            }

            public static class ParamsBean {
            }
        }

        public static class StayListBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * orderId : 29
             * userId : 5
             * orderType : 1
             * surgeryName : 段-测试单台
             * hospitalName : 测试-医院
             * prov : 上海
             * city : 上海市
             * dist : 青浦区
             * address : 华徐公路888号
             * duration : 2.0
             * urgent : 2
             * urgentMoney : 50.0
             * premium : 1
             * premiumMoney : 0.0
             * evenNum : 1
             * expectMoney : 2000.0
             * status : 1
             * payType : null
             * takeOrderId : null
             * takeOrderTime : null
             * cancelTime : null
             * prompt :
             * arriveTime : null
             * beginTime : 2019-09-10 20:33:00
             * waitTime : null
             * surgeryTime : null
             * waitMoney : 0.0
             * surgeryMoney : 0.0
             * totalMoney : 0.0
             * taxMoney : 0.0
             * version : 1
             * orderNo : 190909878530
             * ahNumber : 18736044102
             * adNumber : null
             * overtimeMoney : 0.0
             * invoicepayMoney : 1610.0
             * promptMoney : 300.0
             * endTime : null
             * adMoney : 1700.0
             * ahMoney : 2350.0
             * adactualMoney : 0.0
             * ahactualMoney : 0.0
             * adpremiumMoney : 0.0
             * ahpremiumMoney : 0.0
             */

            private Object searchValue;
            private Object createBy;
            private Object createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBean params;
            private int orderId;
            private int userId;
            private String orderType;
            private String surgeryName;
            private String hospitalName;
            private String prov;
            private String city;
            private String dist;
            private String address;
            private double duration;
            private String urgent;
            private double urgentMoney;
            private String premium;
            private double premiumMoney;
            private int evenNum;
            private double expectMoney;
            private String status;
            private Object payType;
            private Object takeOrderId;
            private Object takeOrderTime;
            private Object cancelTime;
            private String prompt;
            private Object arriveTime;
            private String beginTime;
            private Object waitTime;
            private Object surgeryTime;
            private double waitMoney;
            private double surgeryMoney;
            private double totalMoney;
            private double taxMoney;
            private int version;
            private String orderNo;
            private String ahNumber;
            private Object adNumber;
            private double overtimeMoney;
            private double invoicepayMoney;
            private double promptMoney;
            private Object endTime;
            private double adMoney;
            private double ahMoney;
            private double adactualMoney;
            private double ahactualMoney;
            private double adpremiumMoney;
            private double ahpremiumMoney;

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

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
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

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getOrderType() {
                return orderType;
            }

            public void setOrderType(String orderType) {
                this.orderType = orderType;
            }

            public String getSurgeryName() {
                return surgeryName;
            }

            public void setSurgeryName(String surgeryName) {
                this.surgeryName = surgeryName;
            }

            public String getHospitalName() {
                return hospitalName;
            }

            public void setHospitalName(String hospitalName) {
                this.hospitalName = hospitalName;
            }

            public String getProv() {
                return prov;
            }

            public void setProv(String prov) {
                this.prov = prov;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDist() {
                return dist;
            }

            public void setDist(String dist) {
                this.dist = dist;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public double getDuration() {
                return duration;
            }

            public void setDuration(double duration) {
                this.duration = duration;
            }

            public String getUrgent() {
                return urgent;
            }

            public void setUrgent(String urgent) {
                this.urgent = urgent;
            }

            public double getUrgentMoney() {
                return urgentMoney;
            }

            public void setUrgentMoney(double urgentMoney) {
                this.urgentMoney = urgentMoney;
            }

            public String getPremium() {
                return premium;
            }

            public void setPremium(String premium) {
                this.premium = premium;
            }

            public double getPremiumMoney() {
                return premiumMoney;
            }

            public void setPremiumMoney(double premiumMoney) {
                this.premiumMoney = premiumMoney;
            }

            public int getEvenNum() {
                return evenNum;
            }

            public void setEvenNum(int evenNum) {
                this.evenNum = evenNum;
            }

            public double getExpectMoney() {
                return expectMoney;
            }

            public void setExpectMoney(double expectMoney) {
                this.expectMoney = expectMoney;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Object getPayType() {
                return payType;
            }

            public void setPayType(Object payType) {
                this.payType = payType;
            }

            public Object getTakeOrderId() {
                return takeOrderId;
            }

            public void setTakeOrderId(Object takeOrderId) {
                this.takeOrderId = takeOrderId;
            }

            public Object getTakeOrderTime() {
                return takeOrderTime;
            }

            public void setTakeOrderTime(Object takeOrderTime) {
                this.takeOrderTime = takeOrderTime;
            }

            public Object getCancelTime() {
                return cancelTime;
            }

            public void setCancelTime(Object cancelTime) {
                this.cancelTime = cancelTime;
            }

            public String getPrompt() {
                return prompt;
            }

            public void setPrompt(String prompt) {
                this.prompt = prompt;
            }

            public Object getArriveTime() {
                return arriveTime;
            }

            public void setArriveTime(Object arriveTime) {
                this.arriveTime = arriveTime;
            }

            public String getBeginTime() {
                return beginTime;
            }

            public void setBeginTime(String beginTime) {
                this.beginTime = beginTime;
            }

            public Object getWaitTime() {
                return waitTime;
            }

            public void setWaitTime(Object waitTime) {
                this.waitTime = waitTime;
            }

            public Object getSurgeryTime() {
                return surgeryTime;
            }

            public void setSurgeryTime(Object surgeryTime) {
                this.surgeryTime = surgeryTime;
            }

            public double getWaitMoney() {
                return waitMoney;
            }

            public void setWaitMoney(double waitMoney) {
                this.waitMoney = waitMoney;
            }

            public double getSurgeryMoney() {
                return surgeryMoney;
            }

            public void setSurgeryMoney(double surgeryMoney) {
                this.surgeryMoney = surgeryMoney;
            }

            public double getTotalMoney() {
                return totalMoney;
            }

            public void setTotalMoney(double totalMoney) {
                this.totalMoney = totalMoney;
            }

            public double getTaxMoney() {
                return taxMoney;
            }

            public void setTaxMoney(double taxMoney) {
                this.taxMoney = taxMoney;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public String getAhNumber() {
                return ahNumber;
            }

            public void setAhNumber(String ahNumber) {
                this.ahNumber = ahNumber;
            }

            public Object getAdNumber() {
                return adNumber;
            }

            public void setAdNumber(Object adNumber) {
                this.adNumber = adNumber;
            }

            public double getOvertimeMoney() {
                return overtimeMoney;
            }

            public void setOvertimeMoney(double overtimeMoney) {
                this.overtimeMoney = overtimeMoney;
            }

            public double getInvoicepayMoney() {
                return invoicepayMoney;
            }

            public void setInvoicepayMoney(double invoicepayMoney) {
                this.invoicepayMoney = invoicepayMoney;
            }

            public double getPromptMoney() {
                return promptMoney;
            }

            public void setPromptMoney(double promptMoney) {
                this.promptMoney = promptMoney;
            }

            public Object getEndTime() {
                return endTime;
            }

            public void setEndTime(Object endTime) {
                this.endTime = endTime;
            }

            public double getAdMoney() {
                return adMoney;
            }

            public void setAdMoney(double adMoney) {
                this.adMoney = adMoney;
            }

            public double getAhMoney() {
                return ahMoney;
            }

            public void setAhMoney(double ahMoney) {
                this.ahMoney = ahMoney;
            }

            public double getAdactualMoney() {
                return adactualMoney;
            }

            public void setAdactualMoney(double adactualMoney) {
                this.adactualMoney = adactualMoney;
            }

            public double getAhactualMoney() {
                return ahactualMoney;
            }

            public void setAhactualMoney(double ahactualMoney) {
                this.ahactualMoney = ahactualMoney;
            }

            public double getAdpremiumMoney() {
                return adpremiumMoney;
            }

            public void setAdpremiumMoney(double adpremiumMoney) {
                this.adpremiumMoney = adpremiumMoney;
            }

            public double getAhpremiumMoney() {
                return ahpremiumMoney;
            }

            public void setAhpremiumMoney(double ahpremiumMoney) {
                this.ahpremiumMoney = ahpremiumMoney;
            }

            public static class ParamsBean {
            }
        }

        public static class InfoListBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : 2019-09-09 02:51:14
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * infoId : 40
             * userId : 10
             * infoType : 3
             * title : 订单完成
             * brief : null
             * content : 190947835678已完成手术
             * orderId : null
             * status : null
             * nickname : 享醉994726
             * surgeryName : 测试01
             * orderCost : 1384.0
             */

            private Object searchValue;
            private Object createBy;
            private String createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBeanX params;
            private int infoId;
            private int userId;
            private String infoType;
            private String title;
            private Object brief;
            private String content;
            private Object orderId;
            private Object status;
            private String nickname;
            private String surgeryName;
            private double orderCost;

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

            public ParamsBeanX getParams() {
                return params;
            }

            public void setParams(ParamsBeanX params) {
                this.params = params;
            }

            public int getInfoId() {
                return infoId;
            }

            public void setInfoId(int infoId) {
                this.infoId = infoId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getInfoType() {
                return infoType;
            }

            public void setInfoType(String infoType) {
                this.infoType = infoType;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public Object getBrief() {
                return brief;
            }

            public void setBrief(Object brief) {
                this.brief = brief;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public Object getOrderId() {
                return orderId;
            }

            public void setOrderId(Object orderId) {
                this.orderId = orderId;
            }

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getSurgeryName() {
                return surgeryName;
            }

            public void setSurgeryName(String surgeryName) {
                this.surgeryName = surgeryName;
            }

            public double getOrderCost() {
                return orderCost;
            }

            public void setOrderCost(double orderCost) {
                this.orderCost = orderCost;
            }

            public static class ParamsBeanX {
            }
        }

        public static class ActivityListBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : 2019-09-06 14:52:01
             * updateBy : null
             * updateTime : 2019-09-06 14:53:40
             * remark : null
             * params : {}
             * activityId : 1
             * logo : upload/2019/09/06/5bc37a4a3fda19651bd60b22decc573e.jpg
             * title : 测试免费活动
             * content : <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>绿野户外论坛是国内驴友长期聚集活跃的户外运动社区,也是绿野网旗下主打的旅游论坛。绿野为用户提供户外知识资讯,自助游线路,旅行装备评测,活动发起,户外旅行保险等服务</strong></p><p><img src="http://47.244.137.174:8083//ueditor/jsp/upload/image/20190906/1567752816213025403.jpg" title="1567752816213025403.jpg" width="100%" height="auto" alt="b7241d2b658503ee9677d54048943626.jpg"/></p>
             * status : 2
             * stopTime : null
             * total : 0
             * isMoney : 1
             * discountsMoney : 0.0
             * platformMoney : 0.0
             * tip : 2
             * picPath : upload/2019/09/06/ab1ba8249b4dd2105371043eeba29e6d.jpg
             * startTime : 2019-10-12 00:00:00
             * isRegiSt : 1
             */

            private Object searchValue;
            private Object createBy;
            private String createTime;
            private Object updateBy;
            private String updateTime;
            private Object remark;
            private ParamsBeanXX params;
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

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public ParamsBeanXX getParams() {
                return params;
            }

            public void setParams(ParamsBeanXX params) {
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

            public static class ParamsBeanXX {
            }
        }

        public static class PictureListBean {
            /**
             * pictureId : 30
             * title : 测试医生端轮播图文本内容02
             * picPath : upload/2019/09/04/76c5beb42467afa3f440c521adc8119f.jpg
             * status : 1
             * content : <p>测试医生端轮播图文本内容02</p>
             * type : 3
             * pictureType : 3
             * url : null
             * createTime : 2019-09-04 17:58:07
             * updateTime : null
             */

            private int pictureId;
            private String title;
            private String picPath;
            private String status;
            private String content;
            private String type;
            private String pictureType;
            private Object url;
            private String createTime;
            private Object updateTime;

            public int getPictureId() {
                return pictureId;
            }

            public void setPictureId(int pictureId) {
                this.pictureId = pictureId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPicPath() {
                return picPath;
            }

            public void setPicPath(String picPath) {
                this.picPath = picPath;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getPictureType() {
                return pictureType;
            }

            public void setPictureType(String pictureType) {
                this.pictureType = pictureType;
            }

            public Object getUrl() {
                return url;
            }

            public void setUrl(Object url) {
                this.url = url;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }
        }
    }
}
