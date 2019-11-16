package com.yh.user.entity;

import java.io.Serializable;

import com.yh.core.base.entity.AbstractEntity;

public class User extends AbstractEntity{
        private String name;  //名字
        private String userName; //用户名
        private String passWord; //密码
        private Short  gender; //性别
        private Short  age;    //年龄
        private String phone1; //电话
        private String phone2; //电话
        private String emall;  //邮箱
        
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getPassWord() {
			return passWord;
		}
		public void setPassWord(String passWord) {
			this.passWord = passWord;
		}
		public String getPhone1() {
			return phone1;
		}
		public void setPhone1(String phone1) {
			this.phone1 = phone1;
		}
		public String getPhone2() {
			return phone2;
		}
		public void setPhone2(String phone2) {
			this.phone2 = phone2;
		}
		public String getEmall() {
			return emall;
		}
		public void setEmall(String emall) {
			this.emall = emall;
		}
}
