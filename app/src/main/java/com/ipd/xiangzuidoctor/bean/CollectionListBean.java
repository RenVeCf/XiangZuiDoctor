package com.ipd.xiangzuidoctor.bean;

import java.util.List;

public class CollectionListBean {
    /**
     * msg : 操作成功
     * total : 1
     * code : 200
     * data : {"collectionList":[{"medicalId":10,"logo":"upload/2019/08/30/c62969a2cbeb9a43ca2f65c981f2e49f.png","picPath":null,"title":"测试新闻文章","describ":"医院（Hospital）一词是来自于拉丁文原意为\u201c客人\u201d，因为一开始设立时，是供人避难，还备有休息间，使来者舒适，有招待意图。后来，才逐渐成为满足人类医疗需求，提供医疗服务的专业机构，收容和治疗病人的服务场所。","content":"<p><span style=\"color: rgb(51, 51, 51); font-family: arial, 宋体, sans-serif; font-size: 14px; text-indent: 28px; background-color: rgb(255, 255, 255);\">医院是指按照法律法规和行业规范，为病员开展必要的医学检查、治疗措施、<\/span><a target=\"_blank\" href=\"https://baike.baidu.com/item/%E6%8A%A4%E7%90%86\" style=\"color: rgb(19, 110, 194); text-decoration-line: none; font-family: arial, 宋体, sans-serif; font-size: 14px; text-indent: 28px; white-space: normal; background-color: rgb(255, 255, 255);\">护理<\/a><span style=\"color: rgb(51, 51, 51); font-family: arial, 宋体, sans-serif; font-size: 14px; text-indent: 28px; background-color: rgb(255, 255, 255);\">技术、接诊服务、康复设备、救治运输等服务，以救死扶伤为主要目的<\/span><a target=\"_blank\" href=\"https://baike.baidu.com/item/%E5%8C%BB%E7%96%97%E6%9C%BA%E6%9E%84/3459708\" style=\"color: rgb(19, 110, 194); text-decoration-line: none; font-family: arial, 宋体, sans-serif; font-size: 14px; text-indent: 28px; white-space: normal; background-color: rgb(255, 255, 255);\">医疗机构<\/a><span style=\"color: rgb(51, 51, 51); font-family: arial, 宋体, sans-serif; font-size: 14px; text-indent: 28px; background-color: rgb(255, 255, 255);\">。其服务对象不仅包括有症状的病员和伤员，也包括不能自理或活动受限有医疗护理依赖的老年人，法医评定有医疗护理依赖或病情不稳定需要长期康复经常观察检查的重度病伤员，或有其它特定的情况和人群，如健康人（如<\/span><a target=\"_blank\" href=\"https://baike.baidu.com/item/%E5%AD%95%E5%A6%87/4780575\" style=\"color: rgb(19, 110, 194); text-decoration-line: none; font-family: arial, 宋体, sans-serif; font-size: 14px; text-indent: 28px; white-space: normal; background-color: rgb(255, 255, 255);\">孕妇<\/a><span style=\"color: rgb(51, 51, 51); font-family: arial, 宋体, sans-serif; font-size: 14px; text-indent: 28px; background-color: rgb(255, 255, 255);\">、<\/span><a target=\"_blank\" href=\"https://baike.baidu.com/item/%E4%BA%A7%E5%A6%87/5617924\" style=\"color: rgb(19, 110, 194); text-decoration-line: none; font-family: arial, 宋体, sans-serif; font-size: 14px; text-indent: 28px; white-space: normal; background-color: rgb(255, 255, 255);\">产妇<\/a><span style=\"color: rgb(51, 51, 51); font-family: arial, 宋体, sans-serif; font-size: 14px; text-indent: 28px; background-color: rgb(255, 255, 255);\">、<\/span><a target=\"_blank\" href=\"https://baike.baidu.com/item/%E6%96%B0%E7%94%9F%E5%84%BF/83388\" style=\"color: rgb(19, 110, 194); text-decoration-line: none; font-family: arial, 宋体, sans-serif; font-size: 14px; text-indent: 28px; white-space: normal; background-color: rgb(255, 255, 255);\">新生儿<\/a><span style=\"color: rgb(51, 51, 51); font-family: arial, 宋体, sans-serif; font-size: 14px; text-indent: 28px; background-color: rgb(255, 255, 255);\">）以及完全健康的人（如来医院<\/span><a target=\"_blank\" href=\"https://baike.baidu.com/item/%E8%BF%9B%E8%A1%8C/9989812\" style=\"color: rgb(19, 110, 194); text-decoration-line: none; font-family: arial, 宋体, sans-serif; font-size: 14px; text-indent: 28px; white-space: normal; background-color: rgb(255, 255, 255);\">进行<\/a><a target=\"_blank\" href=\"https://baike.baidu.com/item/%E4%BD%93%E6%A0%BC%E6%A3%80%E6%9F%A5/3426045\" style=\"color: rgb(19, 110, 194); text-decoration-line: none; font-family: arial, 宋体, sans-serif; font-size: 14px; text-indent: 28px; white-space: normal; background-color: rgb(255, 255, 255);\">体格检查<\/a><span style=\"color: rgb(51, 51, 51); font-family: arial, 宋体, sans-serif; font-size: 14px; text-indent: 28px; background-color: rgb(255, 255, 255);\">或口腔清洁的人）。最初设立时，是供人避难，还备有娱乐节目，使来者舒适，有招待意图。后来，才逐渐成为收容和治疗病人的专门机构。<\/span><\/p>","tag":"1","tip":"","createTime":null,"params":null}]}
     */

    private String msg;
    private int total;
    private int code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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
        private List<CollectionListsBean> collectionList;

        public List<CollectionListsBean> getCollectionList() {
            return collectionList;
        }

        public void setCollectionList(List<CollectionListsBean> collectionList) {
            this.collectionList = collectionList;
        }

        public static class CollectionListsBean {
            /**
             * medicalId : 10
             * logo : upload/2019/08/30/c62969a2cbeb9a43ca2f65c981f2e49f.png
             * picPath : null
             * title : 测试新闻文章
             * describ : 医院（Hospital）一词是来自于拉丁文原意为“客人”，因为一开始设立时，是供人避难，还备有休息间，使来者舒适，有招待意图。后来，才逐渐成为满足人类医疗需求，提供医疗服务的专业机构，收容和治疗病人的服务场所。
             * content : <p><span style="color: rgb(51, 51, 51); font-family: arial, 宋体, sans-serif; font-size: 14px; text-indent: 28px; background-color: rgb(255, 255, 255);">医院是指按照法律法规和行业规范，为病员开展必要的医学检查、治疗措施、</span><a target="_blank" href="https://baike.baidu.com/item/%E6%8A%A4%E7%90%86" style="color: rgb(19, 110, 194); text-decoration-line: none; font-family: arial, 宋体, sans-serif; font-size: 14px; text-indent: 28px; white-space: normal; background-color: rgb(255, 255, 255);">护理</a><span style="color: rgb(51, 51, 51); font-family: arial, 宋体, sans-serif; font-size: 14px; text-indent: 28px; background-color: rgb(255, 255, 255);">技术、接诊服务、康复设备、救治运输等服务，以救死扶伤为主要目的</span><a target="_blank" href="https://baike.baidu.com/item/%E5%8C%BB%E7%96%97%E6%9C%BA%E6%9E%84/3459708" style="color: rgb(19, 110, 194); text-decoration-line: none; font-family: arial, 宋体, sans-serif; font-size: 14px; text-indent: 28px; white-space: normal; background-color: rgb(255, 255, 255);">医疗机构</a><span style="color: rgb(51, 51, 51); font-family: arial, 宋体, sans-serif; font-size: 14px; text-indent: 28px; background-color: rgb(255, 255, 255);">。其服务对象不仅包括有症状的病员和伤员，也包括不能自理或活动受限有医疗护理依赖的老年人，法医评定有医疗护理依赖或病情不稳定需要长期康复经常观察检查的重度病伤员，或有其它特定的情况和人群，如健康人（如</span><a target="_blank" href="https://baike.baidu.com/item/%E5%AD%95%E5%A6%87/4780575" style="color: rgb(19, 110, 194); text-decoration-line: none; font-family: arial, 宋体, sans-serif; font-size: 14px; text-indent: 28px; white-space: normal; background-color: rgb(255, 255, 255);">孕妇</a><span style="color: rgb(51, 51, 51); font-family: arial, 宋体, sans-serif; font-size: 14px; text-indent: 28px; background-color: rgb(255, 255, 255);">、</span><a target="_blank" href="https://baike.baidu.com/item/%E4%BA%A7%E5%A6%87/5617924" style="color: rgb(19, 110, 194); text-decoration-line: none; font-family: arial, 宋体, sans-serif; font-size: 14px; text-indent: 28px; white-space: normal; background-color: rgb(255, 255, 255);">产妇</a><span style="color: rgb(51, 51, 51); font-family: arial, 宋体, sans-serif; font-size: 14px; text-indent: 28px; background-color: rgb(255, 255, 255);">、</span><a target="_blank" href="https://baike.baidu.com/item/%E6%96%B0%E7%94%9F%E5%84%BF/83388" style="color: rgb(19, 110, 194); text-decoration-line: none; font-family: arial, 宋体, sans-serif; font-size: 14px; text-indent: 28px; white-space: normal; background-color: rgb(255, 255, 255);">新生儿</a><span style="color: rgb(51, 51, 51); font-family: arial, 宋体, sans-serif; font-size: 14px; text-indent: 28px; background-color: rgb(255, 255, 255);">）以及完全健康的人（如来医院</span><a target="_blank" href="https://baike.baidu.com/item/%E8%BF%9B%E8%A1%8C/9989812" style="color: rgb(19, 110, 194); text-decoration-line: none; font-family: arial, 宋体, sans-serif; font-size: 14px; text-indent: 28px; white-space: normal; background-color: rgb(255, 255, 255);">进行</a><a target="_blank" href="https://baike.baidu.com/item/%E4%BD%93%E6%A0%BC%E6%A3%80%E6%9F%A5/3426045" style="color: rgb(19, 110, 194); text-decoration-line: none; font-family: arial, 宋体, sans-serif; font-size: 14px; text-indent: 28px; white-space: normal; background-color: rgb(255, 255, 255);">体格检查</a><span style="color: rgb(51, 51, 51); font-family: arial, 宋体, sans-serif; font-size: 14px; text-indent: 28px; background-color: rgb(255, 255, 255);">或口腔清洁的人）。最初设立时，是供人避难，还备有娱乐节目，使来者舒适，有招待意图。后来，才逐渐成为收容和治疗病人的专门机构。</span></p>
             * tag : 1
             * tip :
             * createTime : null
             * params : null
             */

            private int medicalId;
            private String logo;
            private Object picPath;
            private String title;
            private String describ;
            private String content;
            private String tag;
            private String tip;
            private Object createTime;
            private Object params;

            public int getMedicalId() {
                return medicalId;
            }

            public void setMedicalId(int medicalId) {
                this.medicalId = medicalId;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public Object getPicPath() {
                return picPath;
            }

            public void setPicPath(Object picPath) {
                this.picPath = picPath;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescrib() {
                return describ;
            }

            public void setDescrib(String describ) {
                this.describ = describ;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getTip() {
                return tip;
            }

            public void setTip(String tip) {
                this.tip = tip;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public Object getParams() {
                return params;
            }

            public void setParams(Object params) {
                this.params = params;
            }
        }
    }
}
