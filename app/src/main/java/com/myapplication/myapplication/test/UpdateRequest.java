package com.myapplication.myapplication.test;

public class UpdateRequest {

        private String phoneNumber; // 手机号
        private String name;        // 名字
        private String password;    // 密码
        private String avatar;      // 头像（URL 或 Base64 字符串）

        // 构造函数
        public UpdateRequest(String phoneNumber, String name, String password, String avatar) {
            this.phoneNumber = phoneNumber;
            this.name = name;
            this.password = password;
            this.avatar = avatar;
        }

        // Getter 和 Setter 方法
        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }



