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
        <el-button type="primary" @click="submitForm('ruleForm')">注册</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
 </div>
</template>

<script>
import {regist} from '@/network/user'

 export default {
   name: 'AddStu',
   data () {
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
   components: {

   },
   methods: {
    submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            const params = {
              "username":this.ruleForm.username,
              "password":this.ruleForm.password
            }
            regist(params).then(res => {
                if(res.data.code!=1){
                    this.$message.promptBox(res.data.msg,'error')
                }else{
                    this.$message.promptBox('注册成功','success')
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
   },
 }
</script>

<style>

 
</style>
