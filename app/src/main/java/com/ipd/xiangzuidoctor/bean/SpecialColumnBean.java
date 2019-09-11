package com.ipd.xiangzuidoctor.bean;

import java.util.List;

public class SpecialColumnBean {
    /**
     * msg : 操作成功
     * total : 1
     * code : 200
     * data : {"medicalList":[{"medicalId":12,"logo":"upload/2019/09/03/907669489a0ab0b53c42af25f057ea28.jpg","picPath":null,"title":"测试临床文章","describ":"中用现有的理论无法解释的现象对临床中","content":"<p>临床信息系统(Clinical Information System,CIS)是支持医院医护人员的临床活动，收集和处理病人的临床医疗信息，丰富和积累临床医学知识，并提供临床咨询、辅助诊疗、辅助临床决策，提高医护人员的工作效率，为病人提供更多、更快、更好的服务。像医嘱处理系统、病人床边系统、医生工作站系统、实验室系统、药物咨询系统等就属于CIS范围。<\/p>","tag":"2","tip":"","createTime":null,"params":null}]}
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
        private List<MedicalListBean> medicalList;

        public List<MedicalListBean> getMedicalList() {
            return medicalList;
        }

        public void setMedicalList(List<MedicalListBean> medicalList) {
            this.medicalList = medicalList;
        }

        public static class MedicalListBean {
            /**
             * medicalId : 12
             * logo : upload/2019/09/03/907669489a0ab0b53c42af25f057ea28.jpg
             * picPath : null
             * title : 测试临床文章
             * describ : 中用现有的理论无法解释的现象对临床中
             * content : <p>临床信息系统(Clinical Information System,CIS)是支持医院医护人员的临床活动，收集和处理病人的临床医疗信息，丰富和积累临床医学知识，并提供临床咨询、辅助诊疗、辅助临床决策，提高医护人员的工作效率，为病人提供更多、更快、更好的服务。像医嘱处理系统、病人床边系统、医生工作站系统、实验室系统、药物咨询系统等就属于CIS范围。</p>
             * tag : 2
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
