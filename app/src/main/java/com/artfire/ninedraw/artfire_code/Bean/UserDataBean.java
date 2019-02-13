package com.artfire.ninedraw.artfire_code.Bean;


import com.artfire.ninedraw.artfire_code.base.Globals;

import java.util.List;

public class UserDataBean {

    /**
     * message :
     * resultType : 1
     * appendData : {"o2":{"sex":"1","client":0,"nickname":"XchEn","unionid":"oIuG_s37erPjIeJoz6LjMCYCaqys","openid3":"","openid4":"","openid2":"o84ndvk82AHAQK0LSeHJ_Y0phogM","city":"","country":"CN","umiid":90326,"openid5":"","":[],"province":"Beijing","uwiid":81502,"headimgurl":"http://wx.qlogo.cn/mmopen/C6nnRGnPbvwrPDOib3fvUtzR4sgqHMMGuhNC80GMKcLGhavkL2JQ2Td7qRSA1h7cKLGjOibicOhRxo41PlaKibokXmqtDPyIw4c8/0"},"o1":{"umcounty":"","umcity":"Mountay圭圭","umaddress":"","umfavornum":0,"aismallpic":"","umprofile":"人生三大爱-爱生活，爱艺术，爱艺伙!","umhobby":"这家伙很懒，没有爱好，只爱艺伙!","umaliasen":"XchE","openfireToken":"","umistalents":0,"umiid":90326,"umprovince":"Heilongjiang","followUserNum":0,"umalias":"XchE","ainame":"","umnation":"中国","umid":"191053","umrole":"2","friendType":-2,"umbirth":"1976-9-7","umlargepic":"http://artfire-file.oss-cn-beijing.aliyuncs.com/avatar/90326/1501228030817.jpg","umbckimg":"http://artfire-file.oss-cn-beijing.aliyuncs.com/avatar/90326/1501228030817.jpg","rkimgNum":0,"umtab":"我们都是艺伙的!","utoken":"ed90f5560aa6d8ec02bf5d7e1bcfd129","aidesc":"","umcareer":"建筑设计师","umsmallpic":"http://artfire-file.oss-cn-beijing.aliyuncs.com/avatar/90326/1501228030817.jpg?x-oss-process=image/resize,m_lfit,h_500,w_500","umsex":0,"umpoplevel":"0","foucusUserNum":0,"umlevel":"","opservicename":"iz25tao44zrz","timusersig":"eJxlkF1PgzAUhu-5FYRbjZ6C3dLdNQszOAlqlxm8abAtrDOwCuVjMf53lZnI4vXznLzvez4c13W9zT27yoQ4tJXl9miU5y5cD7zLP2iMljyzPKjlP6gGo2vFs9yqeoQ*Jj7AVNFSVVbn*lcgEPizCW7kGx8jRopuvo-RHMGZoosRxmG6jB6XezyUyfVWyP5i3e5wlKfrXL-TOA2YJez12CRWCNOljNJoR5OXcPuMVwyepIjxbdP4xcrsH3A4Y0PfUlVCobTpqn5zN4m0ujx9AmHwSUDmwbRQp*pGH6rTYEAYIUTgZ7Xz6XwBVnBc-A__","isteacher":false,"hasauthority":true},"o3":null,"l2":[],"l1":[]}
     * logMessage :
     */

    private String message;
    private String resultType;
    private AppendDataBean appendData;
    private String logMessage;

    public String getLogMessage2() {
        return logMessage2;
    }

    public void setLogMessage2(String logMessage2) {
        this.logMessage2 = logMessage2;
    }

    private String logMessage2;

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
        /**
         * o2 : {"sex":"1","client":0,"nickname":"XchEn","unionid":"oIuG_s37erPjIeJoz6LjMCYCaqys","openid3":"","openid4":"","openid2":"o84ndvk82AHAQK0LSeHJ_Y0phogM","city":"","country":"CN","umiid":90326,"openid5":"","":[],"province":"Beijing","uwiid":81502,"headimgurl":"http://wx.qlogo.cn/mmopen/C6nnRGnPbvwrPDOib3fvUtzR4sgqHMMGuhNC80GMKcLGhavkL2JQ2Td7qRSA1h7cKLGjOibicOhRxo41PlaKibokXmqtDPyIw4c8/0"}
         * o1 : {"umcounty":"","umcity":"Mountay圭圭","umaddress":"","umfavornum":0,"aismallpic":"","umprofile":"人生三大爱-爱生活，爱艺术，爱艺伙!","umhobby":"这家伙很懒，没有爱好，只爱艺伙!","umaliasen":"XchE","openfireToken":"","umistalents":0,"umiid":90326,"umprovince":"Heilongjiang","followUserNum":0,"umalias":"XchE","ainame":"","umnation":"中国","umid":"191053","umrole":"2","friendType":-2,"umbirth":"1976-9-7","umlargepic":"http://artfire-file.oss-cn-beijing.aliyuncs.com/avatar/90326/1501228030817.jpg","umbckimg":"http://artfire-file.oss-cn-beijing.aliyuncs.com/avatar/90326/1501228030817.jpg","rkimgNum":0,"umtab":"我们都是艺伙的!","utoken":"ed90f5560aa6d8ec02bf5d7e1bcfd129","aidesc":"","umcareer":"建筑设计师","umsmallpic":"http://artfire-file.oss-cn-beijing.aliyuncs.com/avatar/90326/1501228030817.jpg?x-oss-process=image/resize,m_lfit,h_500,w_500","umsex":0,"umpoplevel":"0","foucusUserNum":0,"umlevel":"","opservicename":"iz25tao44zrz","timusersig":"eJxlkF1PgzAUhu-5FYRbjZ6C3dLdNQszOAlqlxm8abAtrDOwCuVjMf53lZnI4vXznLzvez4c13W9zT27yoQ4tJXl9miU5y5cD7zLP2iMljyzPKjlP6gGo2vFs9yqeoQ*Jj7AVNFSVVbn*lcgEPizCW7kGx8jRopuvo-RHMGZoosRxmG6jB6XezyUyfVWyP5i3e5wlKfrXL-TOA2YJez12CRWCNOljNJoR5OXcPuMVwyepIjxbdP4xcrsH3A4Y0PfUlVCobTpqn5zN4m0ujx9AmHwSUDmwbRQp*pGH6rTYEAYIUTgZ7Xz6XwBVnBc-A__","isteacher":false,"hasauthority":true}
         * o3 : null
         * l2 : []
         * l1 : []
         */

        private O2Bean o2;
        private O1Bean o1;
        private Object o3;
        private List<?> l2;
        private List<?> l1;

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

        public Object getO3() {
            return o3;
        }

        public void setO3(Object o3) {
            this.o3 = o3;
        }

        public List<?> getL2() {
            return l2;
        }

        public void setL2(List<?> l2) {
            this.l2 = l2;
        }

        public List<?> getL1() {
            return l1;
        }

        public void setL1(List<?> l1) {
            this.l1 = l1;
        }

        public static class O2Bean {
            /**
             * sex : 1
             * client : 0
             * nickname : XchEn
             * unionid : oIuG_s37erPjIeJoz6LjMCYCaqys
             * openid3 :
             * openid4 :
             * openid2 : o84ndvk82AHAQK0LSeHJ_Y0phogM
             * city :
             * country : CN
             * umiid : 90326
             * openid5 :
             * province : Beijing
             * uwiid : 81502
             * headimgurl : http://wx.qlogo.cn/mmopen/C6nnRGnPbvwrPDOib3fvUtzR4sgqHMMGuhNC80GMKcLGhavkL2JQ2Td7qRSA1h7cKLGjOibicOhRxo41PlaKibokXmqtDPyIw4c8/0
             */

            private String sex;
            private int client;
            private String nickname;
            private String unionid;
            private String openid3;
            private String openid4;
            private String openid2;
            private String city;
            private String country;
            private int umiid;
            private String openid5;
            private String province;
            private int uwiid;
            private String headimgurl;

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public int getClient() {
                return client;
            }

            public void setClient(int client) {
                this.client = client;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getUnionid() {
                return unionid;
            }

            public void setUnionid(String unionid) {
                this.unionid = unionid;
            }

            public String getOpenid3() {
                return openid3;
            }

            public void setOpenid3(String openid3) {
                this.openid3 = openid3;
            }

            public String getOpenid4() {
                return openid4;
            }

            public void setOpenid4(String openid4) {
                this.openid4 = openid4;
            }

            public String getOpenid2() {
                return openid2;
            }

            public void setOpenid2(String openid2) {
                this.openid2 = openid2;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public int getUmiid() {
                return umiid;
            }

            public void setUmiid(int umiid) {
                this.umiid = umiid;
            }

            public String getOpenid5() {
                return openid5;
            }

            public void setOpenid5(String openid5) {
                this.openid5 = openid5;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public int getUwiid() {
                return uwiid;
            }

            public void setUwiid(int uwiid) {
                this.uwiid = uwiid;
            }

            public String getHeadimgurl() {
                return headimgurl;
            }

            public void setHeadimgurl(String headimgurl) {
                this.headimgurl = headimgurl;
            }

        }

        public static class O1Bean {
            /**
             * umcounty :
             * umcity : Mountay圭圭
             * umaddress :
             * umfavornum : 0
             * aismallpic :
             * umprofile : 人生三大爱-爱生活，爱艺术，爱艺伙!
             * umhobby : 这家伙很懒，没有爱好，只爱艺伙!
             * umaliasen : XchE
             * openfireToken :
             * umistalents : 0
             * umiid : 90326
             * umprovince : Heilongjiang
             * followUserNum : 0
             * umalias : XchE
             * ainame :
             * umnation : 中国
             * umid : 191053
             * umrole : 2
             * friendType : -2
             * umbirth : 1976-9-7
             * umlargepic : http://artfire-file.oss-cn-beijing.aliyuncs.com/avatar/90326/1501228030817.jpg
             * umbckimg : http://artfire-file.oss-cn-beijing.aliyuncs.com/avatar/90326/1501228030817.jpg
             * rkimgNum : 0
             * umtab : 我们都是艺伙的!
             * utoken : ed90f5560aa6d8ec02bf5d7e1bcfd129
             * aidesc :
             * umcareer : 建筑设计师
             * umsmallpic : http://artfire-file.oss-cn-beijing.aliyuncs.com/avatar/90326/1501228030817.jpg?x-oss-process=image/resize,m_lfit,h_500,w_500
             * umsex : 0
             * umpoplevel : 0
             * foucusUserNum : 0
             * umlevel :
             * opservicename : iz25tao44zrz
             * timusersig : eJxlkF1PgzAUhu-5FYRbjZ6C3dLdNQszOAlqlxm8abAtrDOwCuVjMf53lZnI4vXznLzvez4c13W9zT27yoQ4tJXl9miU5y5cD7zLP2iMljyzPKjlP6gGo2vFs9yqeoQ*Jj7AVNFSVVbn*lcgEPizCW7kGx8jRopuvo-RHMGZoosRxmG6jB6XezyUyfVWyP5i3e5wlKfrXL-TOA2YJez12CRWCNOljNJoR5OXcPuMVwyepIjxbdP4xcrsH3A4Y0PfUlVCobTpqn5zN4m0ujx9AmHwSUDmwbRQp*pGH6rTYEAYIUTgZ7Xz6XwBVnBc-A__
             * isteacher : false
             * hasauthority : true
             */

            private String umcounty;
            private String umcity;
            private String umaddress;
            private String umfavornum;
            private String aismallpic;
            private String umprofile;
            private String umhobby;
            private String umaliasen;
            private String openfireToken;
            private int umistalents;
            private int umiid;
            private String umprovince;
            private int followUserNum;
            private String umalias;
            private String ainame;
            private String umnation;
            private String umid;
            private String umrole;
            private int friendType;
            private String umbirth;
            private String umlargepic;
            private String umbckimg;
            private int rkimgNum;
            private String umtab;
            private String utoken;
            private String aidesc;
            private String umcareer;
            private String umsmallpic;
            private String umsex;
            private String umpoplevel;
            private int foucusUserNum;
            private String umlevel;
            private String opservicename;
            private String timusersig;
            private boolean isteacher;
            private String paynumcr1;
            private String paynumcrs;
            private String paynumsc;
            private String client = Globals.CLIENT;

            public String getPaynumcr1() {
                return paynumcr1;
            }

            public void setPaynumcr1(String paynumcr1) {
                this.paynumcr1 = paynumcr1;
            }

            public String getPaynumcrs() {
                return paynumcrs;
            }

            public void setPaynumcrs(String paynumcrs) {
                this.paynumcrs = paynumcrs;
            }

            public String getPaynumsc() {
                return paynumsc;
            }

            public void setPaynumsc(String paynumsc) {
                this.paynumsc = paynumsc;
            }

            public boolean isteacher() {
                return isteacher;
            }

            public String getHasauthority() {
                return hasauthority;
            }

            public void setHasauthority(String hasauthority) {
                this.hasauthority = hasauthority;
            }

            private String hasauthority;

            public String getUmcounty() {
                return umcounty;
            }

            public void setUmcounty(String umcounty) {
                this.umcounty = umcounty;
            }

            public String getUmcity() {
                return umcity;
            }

            public void setUmcity(String umcity) {
                this.umcity = umcity;
            }

            public String getUmaddress() {
                return umaddress;
            }

            public void setUmaddress(String umaddress) {
                this.umaddress = umaddress;
            }

            public String getUmfavornum() {
                return umfavornum;
            }

            public void setUmfavornum(String umfavornum) {
                this.umfavornum = umfavornum;
            }

            public String getAismallpic() {
                return aismallpic;
            }

            public void setAismallpic(String aismallpic) {
                this.aismallpic = aismallpic;
            }

            public String getUmprofile() {
                return umprofile;
            }

            public void setUmprofile(String umprofile) {
                this.umprofile = umprofile;
            }

            public String getUmhobby() {
                return umhobby;
            }

            public void setUmhobby(String umhobby) {
                this.umhobby = umhobby;
            }

            public String getUmaliasen() {
                return umaliasen;
            }

            public void setUmaliasen(String umaliasen) {
                this.umaliasen = umaliasen;
            }

            public String getOpenfireToken() {
                return openfireToken;
            }

            public void setOpenfireToken(String openfireToken) {
                this.openfireToken = openfireToken;
            }

            public int getUmistalents() {
                return umistalents;
            }

            public void setUmistalents(int umistalents) {
                this.umistalents = umistalents;
            }

            public int getUmiid() {
                return umiid;
            }

            public void setUmiid(int umiid) {
                this.umiid = umiid;
            }

            public String getUmprovince() {
                return umprovince;
            }

            public void setUmprovince(String umprovince) {
                this.umprovince = umprovince;
            }

            public int getFollowUserNum() {
                return followUserNum;
            }

            public void setFollowUserNum(int followUserNum) {
                this.followUserNum = followUserNum;
            }

            public String getUmalias() {
                return umalias;
            }

            public void setUmalias(String umalias) {
                this.umalias = umalias;
            }

            public String getAiname() {
                return ainame;
            }

            public void setAiname(String ainame) {
                this.ainame = ainame;
            }

            public String getUmnation() {
                return umnation;
            }

            public void setUmnation(String umnation) {
                this.umnation = umnation;
            }

            public String getUmid() {
                return umid;
            }

            public void setUmid(String umid) {
                this.umid = umid;
            }

            public String getUmrole() {
                return umrole;
            }

            public void setUmrole(String umrole) {
                this.umrole = umrole;
            }

            public int getFriendType() {
                return friendType;
            }

            public void setFriendType(int friendType) {
                this.friendType = friendType;
            }

            public String getUmbirth() {
                return umbirth;
            }

            public void setUmbirth(String umbirth) {
                this.umbirth = umbirth;
            }

            public String getUmlargepic() {
                return umlargepic;
            }

            public void setUmlargepic(String umlargepic) {
                this.umlargepic = umlargepic;
            }

            public String getUmbckimg() {
                return umbckimg;
            }

            public void setUmbckimg(String umbckimg) {
                this.umbckimg = umbckimg;
            }

            public int getRkimgNum() {
                return rkimgNum;
            }

            public void setRkimgNum(int rkimgNum) {
                this.rkimgNum = rkimgNum;
            }

            public String getUmtab() {
                return umtab;
            }

            public void setUmtab(String umtab) {
                this.umtab = umtab;
            }

            public String getUtoken() {
                return utoken;
            }

            public void setUtoken(String utoken) {
                this.utoken = utoken;
            }

            public String getAidesc() {
                return aidesc;
            }

            public void setAidesc(String aidesc) {
                this.aidesc = aidesc;
            }

            public String getUmcareer() {
                return umcareer;
            }

            public void setUmcareer(String umcareer) {
                this.umcareer = umcareer;
            }

            public String getUmsmallpic() {
                return umsmallpic;
            }

            public void setUmsmallpic(String umsmallpic) {
                this.umsmallpic = umsmallpic;
            }

            public String getUmsex() {
                return umsex;
            }

            public void setUmsex(String umsex) {
                this.umsex = umsex;
            }

            public String getUmpoplevel() {
                return umpoplevel;
            }

            public void setUmpoplevel(String umpoplevel) {
                this.umpoplevel = umpoplevel;
            }

            public int getFoucusUserNum() {
                return foucusUserNum;
            }

            public void setFoucusUserNum(int foucusUserNum) {
                this.foucusUserNum = foucusUserNum;
            }

            public String getUmlevel() {
                return umlevel;
            }

            public void setUmlevel(String umlevel) {
                this.umlevel = umlevel;
            }

            public String getOpservicename() {
                return opservicename;
            }

            public void setOpservicename(String opservicename) {
                this.opservicename = opservicename;
            }

            public String getTimusersig() {
                return timusersig;
            }

            public void setTimusersig(String timusersig) {
                this.timusersig = timusersig;
            }

            public boolean isIsteacher() {
                return isteacher;
            }

            public void setIsteacher(boolean isteacher) {
                this.isteacher = isteacher;
            }

        }
    }
}
