<template>
 <div>
    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="文件名称" prop="name">
        <el-input type="text" v-model="ruleForm.name" autocomplete="off"></el-input>
      </el-form-item>  
      <el-form-item label="图片" prop="img">
        <img-up-down @transfer="getImageInfo" ref="imgUpDown"/>
      </el-form-item>
      <el-form-item label="真实路径" prop="realname">
        {{ ruleForm.realname }}
        <!-- <el-input type="text" v-model="ruleForm.realname" autocomplete="off" ></el-input> -->
      </el-form-item>  
      <el-form-item label="文件大小" prop="size">
        <!-- <el-input type="text" v-model="ruleForm.size" autocomplete="off"></el-input> -->
        {{ ruleForm.size }}
      </el-form-item>  
      <el-form-item label="文件类型" prop="type">
        <!-- <el-row>
          <el-col :span="24">
            <el-form-item label="" prop="typeName">
              <el-select v-model="ruleForm.typeFormName" placeholder="请选择文件类型">
                <el-option v-for="item in ruleForm.typeForm" :label="item.label" :value="item.value"
                           :key="item.key"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row> -->
        {{ ruleForm.typeFormName }}
      </el-form-item>
      <!-- <el-form-item label="所属用户" prop="user_name">
        <el-input type="text" v-model="ruleForm.user_name" autocomplete="off"></el-input>
      </el-form-item> -->
      <!-- <el-form-item label="创建时间" prop="creatTime">
        <el-input type="text" v-model="ruleForm.creatTime" autocomplete="off"></el-input>
      </el-form-item> -->
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">添加文件</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
 </div>
</template>

<script>
import imgUpDown from '@/components/imgUpDown.vue';
import {add} from '@/network/doc'

 export default {
   name: 'docAdd',
   data () {
    var validate = (rule, value, callback) => {
        if (value === '') {
            callback(new Error('请输入信息'));
        }else {
            callback()
        }
    };
     return {
        ruleForm: {
            name: '',
            realname: '',
            size: '',
            typeForm: [
                {
                    label: '.txt',
                    value: '.txt'
                },
                {
                    label: '.jpg',
                    value: '.jpg'
                },
                {
                    label: '.png',
                    value: '.png'
                },
                {
                    label: '.rar',
                    value: '.rar'
                }
            ],
            //user_name: '',
            creatTime: '',
            typeFormName: ''
        },
        rules: {
            name: [
                { validator: validate, trigger: 'blur' }
            ],
            // realname: [
            //     { validator: validate, trigger: 'blur' }
            // ],
            size: [
                { validator: validate, trigger: 'blur' }
            ],
            type: [
                { validator: validate, trigger: 'blur' }
            ],
            // user_name: [
            //     { validator: validate, trigger: 'blur' }
            // ],
            // creatTime: [
            //     { validator: validate, trigger: 'blur' }
            // ],
            typeFormName: [
                { validator: validate, trigger: 'blur' }
            ]
        }
     }
    },
   components: {
    imgUpDown
   },
   created() {
    
   },
   methods: {
    init() {
        
    },
    getImageInfo(fileMap) {
        this.ruleForm.realname='F:\\java技术核心二笔记\\庄老师的ssm实验\\ssm_lab_img\\'+fileMap.name
        this.ruleForm.size=fileMap.fileSize
        this.ruleForm.typeFormName=fileMap.suffix
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if(valid){
          const params={
            "name": this.ruleForm.name,
            "realName": this.ruleForm.realname,
            "size": this.ruleForm.size,
            "type": this.ruleForm.typeFormName
          }
          add(params).then(res => {
            if(res.data.code==1){
              this.$message.promptBox(res.data.data,'success')
            }else{
              this.$message.promptBox("添加失败",'error')
            }
          })

        }else {
            console.log('error submit!!');
            return false;
        }
      })
    },
    resetForm(formName) {
        this.$refs[formName].resetFields();
    }
   }
 }
</script>

<style>

 
</style>
