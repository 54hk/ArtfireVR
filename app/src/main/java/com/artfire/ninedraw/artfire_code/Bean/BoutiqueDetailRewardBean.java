package com.artfire.ninedraw.artfire_code.Bean;

/**
 * Created by Lenovo on 2017/11/22.
 */

public class BoutiqueDetailRewardBean {

    /**
     * message :
     * resultType : 1
     * appendData : {"o2":{"timestamp":"1511315311","sign":"256EE4C70E3D0B7F5DA27EFDD0C02BF0","noncestr":"1DHyjLpuACa49gSC","packagee":"Sign=WXPay","mchid":"1353197502","prepayid":"wx2017112209483104bc50b7290459494521","code_url":"","orderid":"V2P2T1U084849D15113153114731","signbefore":"appid=wxd531dd2dcfc1f1a9&noncestr=1DHyjLpuACa49gSC&package=Sign=WXPay&partnerid=1353197502&prepayid=wx2017112209483104bc50b7290459494521&timestamp=1511315311&key=4s0adc3939ba59abbe86e057f20f8333"},"o1":{"corid":158571,"yzordernum":"","corordernum":"V2P2T1U084849D15113153114731","corclient":101,"corcdname":"打赏","crsids":"","corstate":0,"corcommodities":"","corchannels":"-1","moduletype":0,"corbuyerumiid":84849,"dcpid":0,"corstatedesc":"尚未支付","correceiveumiid":6229,"corislf":0,"yiid":0,"corpaytype":2,"crids":"372","cdids":"","corsumprice":1,"acid":0,"corordertype":1,"cortime":"2017-11-22 09:48:31","corisasyncverified":0,"scid":0},"l2":[]}
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
         * o2 : {"timestamp":"1511315311","sign":"256EE4C70E3D0B7F5DA27EFDD0C02BF0","noncestr":"1DHyjLpuACa49gSC","packagee":"Sign=WXPay","mchid":"1353197502","prepayid":"wx2017112209483104bc50b7290459494521","code_url":"","orderid":"V2P2T1U084849D15113153114731","signbefore":"appid=wxd531dd2dcfc1f1a9&noncestr=1DHyjLpuACa49gSC&package=Sign=WXPay&partnerid=1353197502&prepayid=wx2017112209483104bc50b7290459494521&timestamp=1511315311&key=4s0adc3939ba59abbe86e057f20f8333"}
         * o1 : {"corid":158571,"yzordernum":"","corordernum":"V2P2T1U084849D15113153114731","corclient":101,"corcdname":"打赏","crsids":"","corstate":0,"corcommodities":"","corchannels":"-1","moduletype":0,"corbuyerumiid":84849,"dcpid":0,"corstatedesc":"尚未支付","correceiveumiid":6229,"corislf":0,"yiid":0,"corpaytype":2,"crids":"372","cdids":"","corsumprice":1,"acid":0,"corordertype":1,"cortime":"2017-11-22 09:48:31","corisasyncverified":0,"scid":0}
         * l2 : []
         */

        private O2Bean o2;
        private O1Bean o1;

        public O2Bean getO2() {
            return o2;
        }

        public void setO2(O2Bean o2) {
            this.o2 = o2;
        }

        public O1Bean getO1() {
            return o1;
        }

        public void setO1(O1Bean o1) {
            this.o1 = o1;
        }


        public static class O2Bean {
            /**
             * timestamp : 1511315311
             * sign : 256EE4C70E3D0B7F5DA27EFDD0C02BF0
             * noncestr : 1DHyjLpuACa49gSC
             * packagee : Sign=WXPay
             * mchid : 1353197502
             * prepayid : wx2017112209483104bc50b7290459494521
             * code_url :
             * orderid : V2P2T1U084849D15113153114731
             * signbefore : appid=wxd531dd2dcfc1f1a9&noncestr=1DHyjLpuACa49gSC&package=Sign=WXPay&partnerid=1353197502&prepayid=wx2017112209483104bc50b7290459494521&timestamp=1511315311&key=4s0adc3939ba59abbe86e057f20f8333
             */

            private String timestamp;
            private String sign;
            private String noncestr;
            private String packagee;
            private String mchid;
            private String prepayid;
            private String code_url;
            private String orderid;
            private String signbefore;

            public String getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public String getNoncestr() {
                return noncestr;
            }

            public void setNoncestr(String noncestr) {
                this.noncestr = noncestr;
            }

            public String getPackagee() {
                return packagee;
            }

            public void setPackagee(String packagee) {
                this.packagee = packagee;
            }

            public String getMchid() {
                return mchid;
            }

            public void setMchid(String mchid) {
                this.mchid = mchid;
            }

            public String getPrepayid() {
                return prepayid;
            }

            public void setPrepayid(String prepayid) {
                this.prepayid = prepayid;
            }

            public String getCode_url() {
                return code_url;
            }

            public void setCode_url(String code_url) {
                this.code_url = code_url;
            }

            public String getOrderid() {
                return orderid;
            }

            public void setOrderid(String orderid) {
                this.orderid = orderid;
            }

            public String getSignbefore() {
                return signbefore;
            }

            public void setSignbefore(String signbefore) {
                this.signbefore = signbefore;
            }
        }

        public static class O1Bean {
            /**
             * corid : 158571
             * yzordernum :
             * corordernum : V2P2T1U084849D15113153114731
             * corclient : 101
             * corcdname : 打赏
             * crsids :
             * corstate : 0
             * corcommodities :
             * corchannels : -1
             * moduletype : 0
             * corbuyerumiid : 84849
             * dcpid : 0
             * corstatedesc : 尚未支付
             * correceiveumiid : 6229
             * corislf : 0
             * yiid : 0
             * corpaytype : 2
             * crids : 372
             * cdids :
             * corsumprice : 1
             * acid : 0
             * corordertype : 1
             * cortime : 2017-11-22 09:48:31
             * corisasyncverified : 0
             * scid : 0
             */

            private int corid;
            private String yzordernum;
            private String corordernum;
            private int corclient;
            private String corcdname;
            private String crsids;
            private int corstate;
            private String corcommodities;
            private String corchannels;
            private int moduletype;
            private int corbuyerumiid;
            private int dcpid;
            private String corstatedesc;
            private int correceiveumiid;
            private int corislf;
            private int yiid;
            private int corpaytype;
            private String crids;
            private String cdids;
            private double corsumprice;
            private int acid;
            private int corordertype;
            private String cortime;
            private int corisasyncverified;
            private int scid;

            public int getCorid() {
                return corid;
            }

            public void setCorid(int corid) {
                this.corid = corid;
            }

            public String getYzordernum() {
                return yzordernum;
            }

            public void setYzordernum(String yzordernum) {
                this.yzordernum = yzordernum;
            }

            public String getCorordernum() {
                return corordernum;
            }

            public void setCorordernum(String corordernum) {
                this.corordernum = corordernum;
            }

            public int getCorclient() {
                return corclient;
            }

            public void setCorclient(int corclient) {
                this.corclient = corclient;
            }

            public String getCorcdname() {
                return corcdname;
            }

            public void setCorcdname(String corcdname) {
                this.corcdname = corcdname;
            }

            public String getCrsids() {
                return crsids;
            }

            public void setCrsids(String crsids) {
                this.crsids = crsids;
            }

            public int getCorstate() {
                return corstate;
            }

            public void setCorstate(int corstate) {
                this.corstate = corstate;
            }

            public String getCorcommodities() {
                return corcommodities;
            }

            public void setCorcommodities(String corcommodities) {
                this.corcommodities = corcommodities;
            }

            public String getCorchannels() {
                return corchannels;
            }

            public void setCorchannels(String corchannels) {
                this.corchannels = corchannels;
            }

            public int getModuletype() {
                return moduletype;
            }

            public void setModuletype(int moduletype) {
                this.moduletype = moduletype;
            }

            public int getCorbuyerumiid() {
                return corbuyerumiid;
            }

            public void setCorbuyerumiid(int corbuyerumiid) {
                this.corbuyerumiid = corbuyerumiid;
            }

            public int getDcpid() {
                return dcpid;
            }

            public void setDcpid(int dcpid) {
                this.dcpid = dcpid;
            }

            public String getCorstatedesc() {
                return corstatedesc;
            }

            public void setCorstatedesc(String corstatedesc) {
                this.corstatedesc = corstatedesc;
            }

            public int getCorreceiveumiid() {
                return correceiveumiid;
            }

            public void setCorreceiveumiid(int correceiveumiid) {
                this.correceiveumiid = correceiveumiid;
            }

            public int getCorislf() {
                return corislf;
            }

            public void setCorislf(int corislf) {
                this.corislf = corislf;
            }

            public int getYiid() {
                return yiid;
            }

            public void setYiid(int yiid) {
                this.yiid = yiid;
            }

            public int getCorpaytype() {
                return corpaytype;
            }

            public void setCorpaytype(int corpaytype) {
                this.corpaytype = corpaytype;
            }

            public String getCrids() {
                return crids;
            }

            public void setCrids(String crids) {
                this.crids = crids;
            }

            public String getCdids() {
                return cdids;
            }

            public void setCdids(String cdids) {
                this.cdids = cdids;
            }

            public double getCorsumprice() {
                return corsumprice;
            }

            public void setCorsumprice(double corsumprice) {
                this.corsumprice = corsumprice;
            }

            public int getAcid() {
                return acid;
            }

            public void setAcid(int acid) {
                this.acid = acid;
            }

            public int getCorordertype() {
                return corordertype;
            }

            public void setCorordertype(int corordertype) {
                this.corordertype = corordertype;
            }

            public String getCortime() {
                return cortime;
            }

            public void setCortime(String cortime) {
                this.cortime = cortime;
            }

            public int getCorisasyncverified() {
                return corisasyncverified;
            }

            public void setCorisasyncverified(int corisasyncverified) {
                this.corisasyncverified = corisasyncverified;
            }

            public int getScid() {
                return scid;
            }

            public void setScid(int scid) {
                this.scid = scid;
            }
        }
    }
}
