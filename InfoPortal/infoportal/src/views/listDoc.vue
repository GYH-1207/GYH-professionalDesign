<template>
 <div>
  <file-model :ruleForm="ruleForm"/>
 </div>
</template>

<script>
import { list } from '@/network/doc'
import fileModel from './filetype/fileModel.vue';

 export default {
   name: 'listDoc',
   data () {
     return {
      ruleForm: []
     }
   },
   computed: {
   },
   components: {
    fileModel
   },
   created() {
    this.init();
   },
   methods: {
    init() {
      // this.getRouterData()
      list().then(res => {
        // console.log("reslist=",res.data.data)
        this.ruleForm=res.data.data
      })
    }
    // getRouterData() {
    //   this.type=this.$route.query.type;
    // }
   }
 }
</script>

<style>

 
</style>
