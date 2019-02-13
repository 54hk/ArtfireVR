package com.artfire.ninedraw.artfire_code.Bean;

import java.util.List;

/**
 * Created by 54hk on 2018/8/13.
 */

public class AliPlayPersonSum {

    /**
     * type : 2
     * content : {"seeNum":150,"data":[{"umiid":44,"headimg":"http://artfire-image.oss-cn-beijing.aliyuncs.com/touxiang.png"},{"umiid":47,"headimg":"http://artfire-image.oss-cn-beijing.aliyuncs.com/touxiang.png"},{"umiid":42,"headimg":"http://artfire-image.oss-cn-beijing.aliyuncs.com/70ce2c6d30654697a45915ba7015aad6_256x256.jpg"},{"umiid":45,"headimg":"http://artfire-image.oss-cn-beijing.aliyuncs.com/8a8e83436ef14f0d8fd415898a7ef9d0_256x256.jpg"}]}
     */

    private int type;
    private ContentBean content;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * seeNum : 150
         * data : [{"umiid":44,"headimg":"http://artfire-image.oss-cn-beijing.aliyuncs.com/touxiang.png"},{"umiid":47,"headimg":"http://artfire-image.oss-cn-beijing.aliyuncs.com/touxiang.png"},{"umiid":42,"headimg":"http://artfire-image.oss-cn-beijing.aliyuncs.com/70ce2c6d30654697a45915ba7015aad6_256x256.jpg"},{"umiid":45,"headimg":"http://artfire-image.oss-cn-beijing.aliyuncs.com/8a8e83436ef14f0d8fd415898a7ef9d0_256x256.jpg"}]
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
             * umiid : 44
             * headimg : http://artfire-image.oss-cn-beijing.aliyuncs.com/touxiang.png
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
}
