package com.artfire.ninedraw.artfire_code.Bean;

import java.util.List;

/**
 * Created by 54hk on 2019/1/10.
 */

public class MainClass {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private List<CourtOfficesListBean> courtOfficesList;
        private List<CourtLawyerListBean> courtLawyerList;

        public List<CourtOfficesListBean> getCourtOfficesList() {
            return courtOfficesList;
        }

        public void setCourtOfficesList(List<CourtOfficesListBean> courtOfficesList) {
            this.courtOfficesList = courtOfficesList;
        }

        public List<CourtLawyerListBean> getCourtLawyerList() {
            return courtLawyerList;
        }

        public void setCourtLawyerList(List<CourtLawyerListBean> courtLawyerList) {
            this.courtLawyerList = courtLawyerList;
        }

        public static class CourtOfficesListBean {
            /**
             * createDate : 2019-01-28 16:11:25.0
             * desc : 辽宁汕众律师事务所成立于2011年，位于沈阳市大东区滂江街81号振浩大厦，现有律师（律师助理）20余人，以执业十年以上的资深律师牵头组建了刑事法律事务部、民商事法律事务部、劳动法律事务部及公司法律事务部等专业化服务团队，培养出了一大批从事专业化服务的律师。

             坚持秉持“汇众人之力，解一人之忧”的服务理念，以专业化服务团队为基础，针对具体案件深入剖析，为当事人提供积极的、具有建设性和可操作性的个性化法律服务解决方案。
             * id : 1
             * levelCode :
             * levelName : 沈阳市
             * officesAddress :
             * officesLatitude :
             * officesLongitude :
             * officesName : 辽宁汕众律师事务所
             * officesPkid :
             * officesTel :
             * officesType :
             * provinceCode :
             * provinceName : 辽宁省
             * statues : 1
             */

            private String createDate;
            private String desc;
            private int id;
            private String levelCode;
            private String levelName;
            private String officesAddress;
            private String officesLatitude;
            private String officesLongitude;
            private String officesName;
            private String officesPkid;
            private String officesTel;
            private String officesType;
            private String provinceCode;
            private String provinceName;
            private String statues;

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLevelCode() {
                return levelCode;
            }

            public void setLevelCode(String levelCode) {
                this.levelCode = levelCode;
            }

            public String getLevelName() {
                return levelName;
            }

            public void setLevelName(String levelName) {
                this.levelName = levelName;
            }

            public String getOfficesAddress() {
                return officesAddress;
            }

            public void setOfficesAddress(String officesAddress) {
                this.officesAddress = officesAddress;
            }

            public String getOfficesLatitude() {
                return officesLatitude;
            }

            public void setOfficesLatitude(String officesLatitude) {
                this.officesLatitude = officesLatitude;
            }

            public String getOfficesLongitude() {
                return officesLongitude;
            }

            public void setOfficesLongitude(String officesLongitude) {
                this.officesLongitude = officesLongitude;
            }

            public String getOfficesName() {
                return officesName;
            }

            public void setOfficesName(String officesName) {
                this.officesName = officesName;
            }

            public String getOfficesPkid() {
                return officesPkid;
            }

            public void setOfficesPkid(String officesPkid) {
                this.officesPkid = officesPkid;
            }

            public String getOfficesTel() {
                return officesTel;
            }

            public void setOfficesTel(String officesTel) {
                this.officesTel = officesTel;
            }

            public String getOfficesType() {
                return officesType;
            }

            public void setOfficesType(String officesType) {
                this.officesType = officesType;
            }

            public String getProvinceCode() {
                return provinceCode;
            }

            public void setProvinceCode(String provinceCode) {
                this.provinceCode = provinceCode;
            }

            public String getProvinceName() {
                return provinceName;
            }

            public void setProvinceName(String provinceName) {
                this.provinceName = provinceName;
            }

            public String getStatues() {
                return statues;
            }

            public void setStatues(String statues) {
                this.statues = statues;
            }
        }

        public static class CourtLawyerListBean {
            /**
             * city : 北京
             * duties : 律师
             * id : 1
             * lawfirmName : 北京司陀律师事务所
             * lawyerName : 高义宝
             * levelCode : 0
             * licnumber :
             * lictype : 0
             * licyears : 20
             * officesPkid :
             * phoen : 01067739190
             * pic : http://sd.glorychains.cn/lawyerImg/gaoyibao20180128.jpg
             * provinceCode : 0
             * region : ·沈阳市和平区人民法院 ·沈阳市沈河区人民法院 ·沈阳市大东区人民法院 ·沈阳市皇姑区人民法院
             ·沈阳市铁西区人民法院
             * sex : 男
             * statues : 1
             */

            private String city;
            private String duties;
            private int id;
            private String lawfirmName;
            private String lawyerName;
            private int levelCode;
            private String licnumber;
            private int lictype;
            private int licyears;
            private String officesPkid;
            private String phoen;
            private String pic;
            private int provinceCode;
            private String region;
            private String sex;
            private int statues;

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDuties() {
                return duties;
            }

            public void setDuties(String duties) {
                this.duties = duties;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLawfirmName() {
                return lawfirmName;
            }

            public void setLawfirmName(String lawfirmName) {
                this.lawfirmName = lawfirmName;
            }

            public String getLawyerName() {
                return lawyerName;
            }

            public void setLawyerName(String lawyerName) {
                this.lawyerName = lawyerName;
            }

            public int getLevelCode() {
                return levelCode;
            }

            public void setLevelCode(int levelCode) {
                this.levelCode = levelCode;
            }

            public String getLicnumber() {
                return licnumber;
            }

            public void setLicnumber(String licnumber) {
                this.licnumber = licnumber;
            }

            public int getLictype() {
                return lictype;
            }

            public void setLictype(int lictype) {
                this.lictype = lictype;
            }

            public int getLicyears() {
                return licyears;
            }

            public void setLicyears(int licyears) {
                this.licyears = licyears;
            }

            public String getOfficesPkid() {
                return officesPkid;
            }

            public void setOfficesPkid(String officesPkid) {
                this.officesPkid = officesPkid;
            }

            public String getPhoen() {
                return phoen;
            }

            public void setPhoen(String phoen) {
                this.phoen = phoen;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public int getProvinceCode() {
                return provinceCode;
            }

            public void setProvinceCode(int provinceCode) {
                this.provinceCode = provinceCode;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public int getStatues() {
                return statues;
            }

            public void setStatues(int statues) {
                this.statues = statues;
            }
        }
    }
/*
    public static void main(String[] arg){
        Log.e("TTT",trunMS("60000"));
    }
    *//**
     * 将时间戳 转化为 分和秒
     *//*
    public static String trunMS(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        String[] split = res.split(":");
        if (null != split && split.length >= 3) {
            return ((Integer.valueOf(split[0]) * 60) + Integer.valueOf(split[1])) + ":" + split[2];
        } else {
            return Integer.valueOf(split[1]) + ":" + split[2];
        }
    }*/



}
