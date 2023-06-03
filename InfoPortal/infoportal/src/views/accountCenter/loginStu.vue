<template>
 <div>
    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="账号" prop="username">
        <el-input type="text" v-model="ruleForm.username" autocomplete="off"></el-input>
      </el-form-item>  
      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
 </div>
</template>

<script>
  import {login} from '@/network/user'

 export default {
   name: 'index',
   data() {
      var validateUsername = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入账号'));
        }else {
          callback()
        }
      };
      var validatePassword = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        }else {
          callback()
        }
      };
      return {
        ruleForm: {
          username: '',
          password: ''
        },
        rules: {
          username: [
            { validator: validateUsername, trigger: 'blur' }
          ],
          password: [
            { validator: validatePassword, trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            const params = {
              "username":this.ruleForm.username,
              "password":this.ruleForm.password
            }
            console.log("params=",params)
            login(params).then(res => {
              // console.log("res=",res)
              // let {code,msg,data}=res.data;
              if(res.data.code==1){
	    	
                //********* 主要就是通过这个存储localStorage
                  
                  localStorage.setItem("uToken",res.data.data.uToken);
                  localStorage.setItem("tokenUser",res.data.data.tokenUser);
              
              //********* 存储end***********
          
                  //登录成功后跳转到主界面
                  window.location.href = '/stulist';
              }else {
                  alert(res.data.msg);
              }
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }

 }
</script>

<style>

 
</style>
