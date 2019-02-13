package com.artfire.ninedraw.artfire_code.Bean;

import java.util.List;

/**
 * Created by Lenovo on 2017/9/21.
 */

public class ArtistListBean {
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
        private List<ListBean> list;
        private String versionnum;

        public String getVersionnum() {
            return versionnum;
        }

        public void setVersionnum(String versionnum) {
            this.versionnum = versionnum;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * title : D
             * datas : [{"pid":4,"oname":"Diego Rodriguez de Silvay Velazquez","cname":"迭戈\u2022罗德里格斯\u2022德席尔瓦\u2022委拉斯贵支","info":"委拉斯贵支是文艺复兴后期西班牙最伟大的画家，弗朗西斯科·戈雅认为委拉斯贵支是自己的\u201c伟大教师之一\u201d。委拉斯贵支对印象派以及后来的画家都影响很大。\n委拉斯贵支是一位现实主义的画家，他通常只画所见到的事物，所画的人物，几乎能走出画面；委拉斯贵支也画过一些宗教画，但其中的神像宛如人间，充满紧张和痛苦的表情；委拉斯贵支画的马和狗充满活力。\n委拉斯贵支的大量肖像画都坚持写实原则，并且注重刻画人物的精神面貌。可惜的是画面的气氛过于和平，与史实有明显的差别，但这并没有影响这幅作品的价值。对统治阶级的代表人物如《腓力四世像》，重在表现其虚伪、冷漠和浅薄，决不阿谀奉承。对于朋友和处在社会底层的劳动者，如《拿扇子的妇人》、《矮子安东尼奥》，则被描绘得平易近人，有的还具有社会批判色彩。","listimg":"http://artfire-file.oss-cn-beijing.aliyuncs.com/artfire_wiki_collections_img/F4RY4iDtNM.jpeg","icon":"","birthlocation":""}]
             */

            private String title;
            private List<DatasBean> datas;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<DatasBean> getDatas() {
                return datas;
            }

            public void setDatas(List<DatasBean> datas) {
                this.datas = datas;
            }

            public static class DatasBean {
                /**
                 * pid : 4
                 * oname : Diego Rodriguez de Silvay Velazquez
                 * cname : 迭戈•罗德里格斯•德席尔瓦•委拉斯贵支
                 * info : 委拉斯贵支是文艺复兴后期西班牙最伟大的画家，弗朗西斯科·戈雅认为委拉斯贵支是自己的“伟大教师之一”。委拉斯贵支对印象派以及后来的画家都影响很大。
                 委拉斯贵支是一位现实主义的画家，他通常只画所见到的事物，所画的人物，几乎能走出画面；委拉斯贵支也画过一些宗教画，但其中的神像宛如人间，充满紧张和痛苦的表情；委拉斯贵支画的马和狗充满活力。
                 委拉斯贵支的大量肖像画都坚持写实原则，并且注重刻画人物的精神面貌。可惜的是画面的气氛过于和平，与史实有明显的差别，但这并没有影响这幅作品的价值。对统治阶级的代表人物如《腓力四世像》，重在表现其虚伪、冷漠和浅薄，决不阿谀奉承。对于朋友和处在社会底层的劳动者，如《拿扇子的妇人》、《矮子安东尼奥》，则被描绘得平易近人，有的还具有社会批判色彩。
                 * listimg : http://artfire-file.oss-cn-beijing.aliyuncs.com/artfire_wiki_collections_img/F4RY4iDtNM.jpeg
                 * icon :
                 * birthlocation :
                 */

                private int pid;
                private String oname;
                private String cname;
                private String info;
                private String listimg;
                private String icon;
                private String birthlocation;
                private String title;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getPid() {
                    return pid;
                }

                public void setPid(int pid) {
                    this.pid = pid;
                }

                public String getOname() {
                    return oname;
                }

                public void setOname(String oname) {
                    this.oname = oname;
                }

                public String getCname() {
                    return cname;
                }

                public void setCname(String cname) {
                    this.cname = cname;
                }

                public String getInfo() {
                    return info;
                }

                public void setInfo(String info) {
                    this.info = info;
                }

                public String getListimg() {
                    return listimg;
                }

                public void setListimg(String listimg) {
                    this.listimg = listimg;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public String getBirthlocation() {
                    return birthlocation;
                }

                public void setBirthlocation(String birthlocation) {
                    this.birthlocation = birthlocation;
                }
            }
        }
    }
}
