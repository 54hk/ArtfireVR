package com.artfire.ninedraw.artfire_code.Bean;

import java.util.List;

/**
 * Created by 54hk on 2018/8/12.
 */

public class AliBeginBean {

    /**
     * message :
     * resultType : 1
     * appendData : {"baseInfo":{"crid":3,"crName":"弓小早/卡通人物剪纸画","roomName":"c3","announcement":"欢迎加入 ARTFIRE艺伙 VIP用户QQ群：417974528","startTime":1534089600000,"endTime":1534162719000,"liveStatus":2,"role":1,"pushUrl":"","pullUrl":"http://artfire-live.oss-cn-beijing.aliyuncs.com/artfire-test/c3/c31534218674948.m3u8","duration":4267838,"notice":"","teacher":{"umiid":5655,"name":"弓小早（小早兄）","headImg":"http://artfire-file.oss-cn-beijing.aliyuncs.com/userimg/user5655.jpg?x-oss-process=image/resize,m_lfit,h_300,w_300","isFocus":0}},"userInfo":{"seeNum":884,"data":[{"umiid":65881,"headimg":"http://artfire-file.oss-cn-beijing.aliyuncs.com/userimg/user65881.jpg?x-oss-process=image/resize,m_lfit,h_300,w_300"},{"umiid":162794,"headimg":"http://artfire-file.oss-cn-beijing.aliyuncs.com/avatar/162794/1532508980062.jpg?x-oss-process=image/resize,m_lfit,h_500,w_500"},{"umiid":5043,"headimg":"http://artfire-file.oss-cn-beijing.aliyuncs.com/artfire-file/avatar/5043/1496737964570.jpeg?x-oss-process=image/resize,m_lfit,h_500,w_500"},{"umiid":103245,"headimg":"http://artfire-file.oss-cn-beijing.aliyuncs.com/userimg/user103245.jpg?x-oss-process=image/resize,m_lfit,h_300,w_300"}]},"chatInfo":{"zanNum":942,"total":166,"data":[{"id":166,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534242132113},{"id":165,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534242129996},{"id":164,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"侯坤真帅啊","sendtime":1534241065207},{"id":163,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"侯坤真帅啊","sendtime":1534240663928},{"id":162,"type":1,"umiid":162794,"role":4,"name":"演习。","content":"？？？","sendtime":1534237478454},{"id":161,"type":1,"umiid":162794,"role":4,"name":"演习。","content":"你好看你","sendtime":1534237473450},{"id":160,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"5656656","sendtime":1534237463842},{"id":159,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"说说222","sendtime":1534237312709},{"id":158,"type":1,"umiid":162794,"role":4,"name":"演习。","content":"看看咯","sendtime":1534237137255},{"id":157,"type":1,"umiid":162794,"role":4,"name":"演习。","content":"啊啊啊啊啊","sendtime":1534236906725},{"id":156,"type":1,"umiid":162794,"role":4,"name":"演习。","content":"你说啥","sendtime":1534236892996},{"id":155,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"说说222","sendtime":1534236877288},{"id":154,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"说说","sendtime":1534236721950},{"id":153,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"说说","sendtime":1534236716885},{"id":152,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534236712593},{"id":151,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534236705782},{"id":150,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534236689438},{"id":149,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534236678330},{"id":148,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534236523322},{"id":147,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534236498620},{"id":146,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534236349529},{"id":145,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534236137625},{"id":144,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534236129456},{"id":143,"type":1,"umiid":162794,"role":4,"name":"演习。","content":"去","sendtime":1534231001203},{"id":142,"type":1,"umiid":162794,"role":4,"name":"演习。","content":"你说说","sendtime":1534230911924},{"id":141,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534228090237},{"id":140,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534227360245},{"id":139,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534227200405},{"id":138,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534226846706},{"id":137,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534225901653}]}}
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
         * baseInfo : {"crid":3,"crName":"弓小早/卡通人物剪纸画","roomName":"c3","announcement":"欢迎加入 ARTFIRE艺伙 VIP用户QQ群：417974528","startTime":1534089600000,"endTime":1534162719000,"liveStatus":2,"role":1,"pushUrl":"","pullUrl":"http://artfire-live.oss-cn-beijing.aliyuncs.com/artfire-test/c3/c31534218674948.m3u8","duration":4267838,"notice":"","teacher":{"umiid":5655,"name":"弓小早（小早兄）","headImg":"http://artfire-file.oss-cn-beijing.aliyuncs.com/userimg/user5655.jpg?x-oss-process=image/resize,m_lfit,h_300,w_300","isFocus":0}}
         * userInfo : {"seeNum":884,"data":[{"umiid":65881,"headimg":"http://artfire-file.oss-cn-beijing.aliyuncs.com/userimg/user65881.jpg?x-oss-process=image/resize,m_lfit,h_300,w_300"},{"umiid":162794,"headimg":"http://artfire-file.oss-cn-beijing.aliyuncs.com/avatar/162794/1532508980062.jpg?x-oss-process=image/resize,m_lfit,h_500,w_500"},{"umiid":5043,"headimg":"http://artfire-file.oss-cn-beijing.aliyuncs.com/artfire-file/avatar/5043/1496737964570.jpeg?x-oss-process=image/resize,m_lfit,h_500,w_500"},{"umiid":103245,"headimg":"http://artfire-file.oss-cn-beijing.aliyuncs.com/userimg/user103245.jpg?x-oss-process=image/resize,m_lfit,h_300,w_300"}]}
         * chatInfo : {"zanNum":942,"total":166,"data":[{"id":166,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534242132113},{"id":165,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534242129996},{"id":164,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"侯坤真帅啊","sendtime":1534241065207},{"id":163,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"侯坤真帅啊","sendtime":1534240663928},{"id":162,"type":1,"umiid":162794,"role":4,"name":"演习。","content":"？？？","sendtime":1534237478454},{"id":161,"type":1,"umiid":162794,"role":4,"name":"演习。","content":"你好看你","sendtime":1534237473450},{"id":160,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"5656656","sendtime":1534237463842},{"id":159,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"说说222","sendtime":1534237312709},{"id":158,"type":1,"umiid":162794,"role":4,"name":"演习。","content":"看看咯","sendtime":1534237137255},{"id":157,"type":1,"umiid":162794,"role":4,"name":"演习。","content":"啊啊啊啊啊","sendtime":1534236906725},{"id":156,"type":1,"umiid":162794,"role":4,"name":"演习。","content":"你说啥","sendtime":1534236892996},{"id":155,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"说说222","sendtime":1534236877288},{"id":154,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"说说","sendtime":1534236721950},{"id":153,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"说说","sendtime":1534236716885},{"id":152,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534236712593},{"id":151,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534236705782},{"id":150,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534236689438},{"id":149,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534236678330},{"id":148,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534236523322},{"id":147,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534236498620},{"id":146,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534236349529},{"id":145,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534236137625},{"id":144,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534236129456},{"id":143,"type":1,"umiid":162794,"role":4,"name":"演习。","content":"去","sendtime":1534231001203},{"id":142,"type":1,"umiid":162794,"role":4,"name":"演习。","content":"你说说","sendtime":1534230911924},{"id":141,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534228090237},{"id":140,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534227360245},{"id":139,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534227200405},{"id":138,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534226846706},{"id":137,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534225901653}]}
         */

        private BaseInfoBean baseInfo;
        private UserInfoBean userInfo;
        private ChatInfoBean chatInfo;

        public BaseInfoBean getBaseInfo() {
            return baseInfo;
        }

        public void setBaseInfo(BaseInfoBean baseInfo) {
            this.baseInfo = baseInfo;
        }

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public ChatInfoBean getChatInfo() {
            return chatInfo;
        }

        public void setChatInfo(ChatInfoBean chatInfo) {
            this.chatInfo = chatInfo;
        }

        public static class BaseInfoBean {
            /**
             * crid : 3
             * crName : 弓小早/卡通人物剪纸画
             * roomName : c3
             * announcement : 欢迎加入 ARTFIRE艺伙 VIP用户QQ群：417974528
             * startTime : 1534089600000
             * endTime : 1534162719000
             * liveStatus : 2
             * role : 1
             * pushUrl :
             * pullUrl : http://artfire-live.oss-cn-beijing.aliyuncs.com/artfire-test/c3/c31534218674948.m3u8
             * duration : 4267838
             * notice :
             * teacher : {"umiid":5655,"name":"弓小早（小早兄）","headImg":"http://artfire-file.oss-cn-beijing.aliyuncs.com/userimg/user5655.jpg?x-oss-process=image/resize,m_lfit,h_300,w_300","isFocus":0}
             */

            private int crid;
            private String crName;
            private String roomName;
            private String announcement;
            private long startTime;
            private long endTime;
            private int liveStatus;
            private int role;
            private String pushUrl;
            private String pullUrl;
            private int duration;
            private String notice;
            private String headImg;
            private TeacherBean teacher;
            private ShareBean share;

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }

            public ShareBean getShare() {
                return share;
            }

            public void setShare(ShareBean share) {
                this.share = share;
            }

            public int getCrid() {
                return crid;
            }

            public void setCrid(int crid) {
                this.crid = crid;
            }

            public String getCrName() {
                return crName;
            }

            public void setCrName(String crName) {
                this.crName = crName;
            }

            public String getRoomName() {
                return roomName;
            }

            public void setRoomName(String roomName) {
                this.roomName = roomName;
            }

            public String getAnnouncement() {
                return announcement;
            }

            public void setAnnouncement(String announcement) {
                this.announcement = announcement;
            }

            public long getStartTime() {
                return startTime;
            }

            public void setStartTime(long startTime) {
                this.startTime = startTime;
            }

            public long getEndTime() {
                return endTime;
            }

            public void setEndTime(long endTime) {
                this.endTime = endTime;
            }

            public int getLiveStatus() {
                return liveStatus;
            }

            public void setLiveStatus(int liveStatus) {
                this.liveStatus = liveStatus;
            }

            public int getRole() {
                return role;
            }

            public void setRole(int role) {
                this.role = role;
            }

            public String getPushUrl() {
                return pushUrl;
            }

            public void setPushUrl(String pushUrl) {
                this.pushUrl = pushUrl;
            }

            public String getPullUrl() {
                return pullUrl;
            }

            public void setPullUrl(String pullUrl) {
                this.pullUrl = pullUrl;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }

            public TeacherBean getTeacher() {
                return teacher;
            }

            public void setTeacher(TeacherBean teacher) {
                this.teacher = teacher;
            }

            public static class ShareBean {
                private String headimg;
                private String title;
                private String desc;
                private String url;

                public String getHeadimg() {
                    return headimg;
                }

                public void setHeadimg(String headimg) {
                    this.headimg = headimg;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }

            public static class TeacherBean {
                /**
                 * umiid : 5655
                 * name : 弓小早（小早兄）
                 * headImg : http://artfire-file.oss-cn-beijing.aliyuncs.com/userimg/user5655.jpg?x-oss-process=image/resize,m_lfit,h_300,w_300
                 * isFocus : 0
                 */

                private int umiid;
                private String name;
                private String headImg;
                private int isFocus;

                public int getUmiid() {
                    return umiid;
                }

                public void setUmiid(int umiid) {
                    this.umiid = umiid;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getHeadImg() {
                    return headImg;
                }

                public void setHeadImg(String headImg) {
                    this.headImg = headImg;
                }

                public int getIsFocus() {
                    return isFocus;
                }

                public void setIsFocus(int isFocus) {
                    this.isFocus = isFocus;
                }
            }
        }

        public static class UserInfoBean {
            /**
             * seeNum : 884
             * data : [{"umiid":65881,"headimg":"http://artfire-file.oss-cn-beijing.aliyuncs.com/userimg/user65881.jpg?x-oss-process=image/resize,m_lfit,h_300,w_300"},{"umiid":162794,"headimg":"http://artfire-file.oss-cn-beijing.aliyuncs.com/avatar/162794/1532508980062.jpg?x-oss-process=image/resize,m_lfit,h_500,w_500"},{"umiid":5043,"headimg":"http://artfire-file.oss-cn-beijing.aliyuncs.com/artfire-file/avatar/5043/1496737964570.jpeg?x-oss-process=image/resize,m_lfit,h_500,w_500"},{"umiid":103245,"headimg":"http://artfire-file.oss-cn-beijing.aliyuncs.com/userimg/user103245.jpg?x-oss-process=image/resize,m_lfit,h_300,w_300"}]
             */

            private int seeNum;
            private List<DataBean> data;

            public int getSeeNum() {
                return seeNum;
            }

            public void setSeeNum(int seeNum) {
                this.seeNum = seeNum;
            }

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public static class DataBean {
                /**
                 * umiid : 65881
                 * headimg : http://artfire-file.oss-cn-beijing.aliyuncs.com/userimg/user65881.jpg?x-oss-process=image/resize,m_lfit,h_300,w_300
                 */

                private int umiid;
                private String headimg;

                public int getUmiid() {
                    return umiid;
                }

                public void setUmiid(int umiid) {
                    this.umiid = umiid;
                }

                public String getHeadimg() {
                    return headimg;
                }

                public void setHeadimg(String headimg) {
                    this.headimg = headimg;
                }
            }
        }

        public static class ChatInfoBean {
            /**
             * zanNum : 942
             * total : 166
             * data : [{"id":166,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534242132113},{"id":165,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534242129996},{"id":164,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"侯坤真帅啊","sendtime":1534241065207},{"id":163,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"侯坤真帅啊","sendtime":1534240663928},{"id":162,"type":1,"umiid":162794,"role":4,"name":"演习。","content":"？？？","sendtime":1534237478454},{"id":161,"type":1,"umiid":162794,"role":4,"name":"演习。","content":"你好看你","sendtime":1534237473450},{"id":160,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"5656656","sendtime":1534237463842},{"id":159,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"说说222","sendtime":1534237312709},{"id":158,"type":1,"umiid":162794,"role":4,"name":"演习。","content":"看看咯","sendtime":1534237137255},{"id":157,"type":1,"umiid":162794,"role":4,"name":"演习。","content":"啊啊啊啊啊","sendtime":1534236906725},{"id":156,"type":1,"umiid":162794,"role":4,"name":"演习。","content":"你说啥","sendtime":1534236892996},{"id":155,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"说说222","sendtime":1534236877288},{"id":154,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"说说","sendtime":1534236721950},{"id":153,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"说说","sendtime":1534236716885},{"id":152,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534236712593},{"id":151,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534236705782},{"id":150,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534236689438},{"id":149,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534236678330},{"id":148,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534236523322},{"id":147,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534236498620},{"id":146,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534236349529},{"id":145,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534236137625},{"id":144,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534236129456},{"id":143,"type":1,"umiid":162794,"role":4,"name":"演习。","content":"去","sendtime":1534231001203},{"id":142,"type":1,"umiid":162794,"role":4,"name":"演习。","content":"你说说","sendtime":1534230911924},{"id":141,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534228090237},{"id":140,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534227360245},{"id":139,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534227200405},{"id":138,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534226846706},{"id":137,"type":1,"umiid":65881,"role":1,"name":"枫叶情缘","content":"束带结发","sendtime":1534225901653}]
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
                 * id : 166
                 * type : 1
                 * umiid : 65881
                 * role : 1
                 * name : 枫叶情缘
                 * content : 束带结发
                 * sendtime : 1534242132113
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