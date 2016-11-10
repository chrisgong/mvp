package com.shuzijiayuan.myapplication.data.model.profile;

import com.shuzijiayuan.myapplication.data.model.BaseResult;

import java.util.ArrayList;

/**
 * 获取profile的网络请求接口的result对象的数据.
 */

public class ProfileListResult extends BaseResult<ProfileListResult.Data> {

    public class Data {
        //当前关联总人数
        private int total;

        //限制人数大小
        private int limit;

        //当前登录用户所关联的用户列表
        ArrayList<ProfileInfo> list;

        @Override
        public String toString() {
            return "Data{" +
                    "limit=" + limit +
                    ", total=" + total +
                    ", list=" + list +
                    '}';
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }


        public ArrayList<ProfileInfo> getList() {
            return list;
        }

        public void setList(ArrayList<ProfileInfo> list) {
            this.list = list;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }

}
