<template>
 <div>
    <el-upload class="avatar-uploader"
            action="http://127.0.0.1:8081/common/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeUpload"
            ref="upload">
        <img v-if="imageUrl" :src="imageUrl" class="avatar"/>    
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
    </el-upload>
 </div>
</template>

<script>
 export default {
   name: 'imgUpDown',
   data () {
     return {
        imageUrl: '',
     }
   },
   components: {

   },
   methods: {
    handleAvatarSuccess(response, file, fileList) {
        console.log('response.date=',response.data)
        this.imageUrl = `http://127.0.0.1:8081/common/download?name=${response.data.name}`
        this.setImageInfo(response.data)
    },
    beforeUpload(file) {
        if (file) {
            const suffix = file.name.split('.')[1]
            const size = file.size / 1024 / 1024 < 2
            if (['png', 'jpeg', 'jpg'].indexOf(suffix) < 0) {
                this.$message.error('上传图片只支持 png、jpeg、jpg 格式！')
                this.$refs.upload.clearFiles()
                return false
            }
            if (!size) {
                this.$message.error('上传文件大小不能超过 2MB!')
                return false
            }
            return file
        }
    },
    setImageInfo(fileMap) {
        this.$emit('transfer',fileMap)
    }
   },
 }
</script>

<style>
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}
.avatar-uploader .el-upload:hover {
    border-color: #409EFF;
}
.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
}
.avatar {
    width: 178px;
    height: 178px;
    display: block;
}

</style>
