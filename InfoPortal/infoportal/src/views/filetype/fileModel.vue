<template>
 <div>
    <el-table
    :data="ruleForm.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
    style="width: 100%">
    <el-table-column
      type="index"
      :index="numIndex">
    </el-table-column>
    <el-table-column
      label="name"
      prop="name">
    </el-table-column>
    <el-table-column
      label="realName"
      prop="realName">
    </el-table-column>
    <el-table-column
      label="size"
      prop="size">
    </el-table-column>
    <el-table-column
      label="type"
      prop="type">
    </el-table-column>
    <el-table-column
      label="userName"
      prop="userName">
    </el-table-column>
    <el-table-column
      label="creatTime"
      prop="creatTime">
    </el-table-column>

    <el-table-column
      align="right">
      <template slot="header" slot-scope="scope">
        <el-input
          v-model="search"
          size="mini"
          placeholder="输入关键字搜索"/>
      </template>
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
      </template>
    </el-table-column>
  </el-table>
 </div>
</template>

<script>
 export default {
   name: '',
   props: {
    ruleForm: []
   },
   data () {
     return {
        numIndex: 1,
        search: ''
     }
   },
   components: {

   },
   methods: {
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
    },
   }
 }
</script>

<style>

 
</style>
