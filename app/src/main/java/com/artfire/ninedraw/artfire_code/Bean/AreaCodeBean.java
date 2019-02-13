package com.artfire.ninedraw.artfire_code.Bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/8 0008
 */

public class AreaCodeBean {


    private List<ContentListBean> contentList;

    public List<ContentListBean> getContentList() {
        return contentList;
    }

    public void setContentList(List<ContentListBean> contentList) {
        this.contentList = contentList;
    }

    public static class ContentListBean {
        /**
         * name : 建议国家/区域
         * data : [{"name":"中国","code":86},{"name":"中国台湾","code":886},{"name":"中国香港","code":852},{"name":"中国澳门","code":853},{"name":"新加坡","code":65},{"name":"美国","code":1},{"name":"英国","code":44},{"name":"马来西亚","code":60},{"name":"日本","code":81},{"name":"法国","code":33},{"name":"德国","code":49},{"name":"荷兰","code":31},{"name":"西班牙","code":34},{"name":"意大利","code":39},{"name":"泰国","code":66},{"name":"韩国","code":82},{"name":"印度","code":91},{"name":"澳大利亚","code":61},{"name":"柬埔寨","code":855},{"name":"智利","code":56},{"name":"以色列","code":972},{"name":"肯尼亚","code":254},{"name":"墨西哥","code":52}]
         */

        private String name;
        private List<DataBean> data;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * name : 中国
             * code : 86
             */

            private String name;
            private String code;
            private String title;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }
        }
    }
}
