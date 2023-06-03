<template>
 <div>
  <el-table
    :data="users.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
    style="width: 100%">
    <el-table-column
      label="Id"
      prop="id">
    </el-table-column>
    <el-table-column
      label="Username"
      prop="username">
    </el-table-column>
    <el-table-column
      label="Password"
      prop="password">
    </el-table-column>
    <el-table-column
      label="size"
      prop="size">
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
  import {list} from '@/network/user'

 export default {
   name: 'home',
   data () {
      return {
        users: [],
        search: ''
      }
   },
   created() {
    this.init()
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
    init() {
      list().then(res => {
        // console.log("reslist=",res.data.data)
        this.users=res.data.data
      })
    }
  }
 }
</script>

<style>

 
</style>
