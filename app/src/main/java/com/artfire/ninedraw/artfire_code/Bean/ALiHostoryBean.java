package com.artfire.ninedraw.artfire_code.Bean;

import java.util.List;

/**
 * Created by 54hk on 2018/8/12.
 */

public class ALiHostoryBean {

    /**
     * message :
     * resultType : 1
     * appendData : {"chatInfo":{"zanNum":172,"total":65,"data":[{"id":65,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"25","sendtime":1534061004138},{"id":64,"type":1,"umiid":5043,"role":1,"name":"In","content":"12","sendtime":1534060994272},{"id":63,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"25","sendtime":1534060929261},{"id":62,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"25","sendtime":1534060900324},{"id":61,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"25","sendtime":1534060834744},{"id":60,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"牛逼哄哄","sendtime":1534060713221},{"id":59,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"牛逼哄哄","sendtime":1534060229123},{"id":58,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"牛逼哄哄","sendtime":1534060056562},{"id":57,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"靓仔2","sendtime":1534060010794},{"id":56,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"靓仔","sendtime":1534059907137}]}}
     * logMessage :
     */

    private String message;
    private int resultType;
    private AppendDataBean appendData;
    private String logMessage;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResultType() {
        return resultType;
    }

    public void setResultType(int resultType) {
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
         * chatInfo : {"zanNum":172,"total":65,"data":[{"id":65,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"25","sendtime":1534061004138},{"id":64,"type":1,"umiid":5043,"role":1,"name":"In","content":"12","sendtime":1534060994272},{"id":63,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"25","sendtime":1534060929261},{"id":62,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"25","sendtime":1534060900324},{"id":61,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"25","sendtime":1534060834744},{"id":60,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"牛逼哄哄","sendtime":1534060713221},{"id":59,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"牛逼哄哄","sendtime":1534060229123},{"id":58,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"牛逼哄哄","sendtime":1534060056562},{"id":57,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"靓仔2","sendtime":1534060010794},{"id":56,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"靓仔","sendtime":1534059907137}]}
         */

        private ChatInfoBean chatInfo;

        public ChatInfoBean getChatInfo() {
            return chatInfo;
        }

        public void setChatInfo(ChatInfoBean chatInfo) {
            this.chatInfo = chatInfo;
        }

        public static class ChatInfoBean {
            /**
             * zanNum : 172
             * total : 65
             * data : [{"id":65,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"25","sendtime":1534061004138},{"id":64,"type":1,"umiid":5043,"role":1,"name":"In","content":"12","sendtime":1534060994272},{"id":63,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"25","sendtime":1534060929261},{"id":62,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"25","sendtime":1534060900324},{"id":61,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"25","sendtime":1534060834744},{"id":60,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"牛逼哄哄","sendtime":1534060713221},{"id":59,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"牛逼哄哄","sendtime":1534060229123},{"id":58,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"牛逼哄哄","sendtime":1534060056562},{"id":57,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"靓仔2","sendtime":1534060010794},{"id":56,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"靓仔","sendtime":1534059907137}]
             */

            private int zanNum;
            private int total;
            private List<DataBeanX> data;

            public int getZanNum() {
                return zanNum;
            }

            public void setZanNum(int zanNum) {
                this.zanNum = zanNum;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public List<DataBeanX> getData() {
                return data;
            }

            public void setData(List<DataBeanX> data) {
                this.data = data;
            }

            public static class DataBeanX {
                /**
                 * id : 65
                 * type : 1
                 * umiid : 65881
                 * role : 1
                 * name : 枫叶情缘
                 * content : 25
                 * sendtime : 1534061004138
                 */

                private int id;
                private int type;
                private int umiid;
                private int role;
                private String name;
                private String content;
                private long sendtime;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public int getUmiid() {
                    return umiid;
                }

                public void setUmiid(int umiid) {
                    this.umiid = umiid;
                }

                public int getRole() {
                    return role;
                }

                public void setRole(int role) {
                    this.role = role;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public long getSendtime() {
                    return sendtime;
                }

                public void setSendtime(long sendtime) {
                    this.sendtime = sendtime;
                }
            }
        }
    }
}
