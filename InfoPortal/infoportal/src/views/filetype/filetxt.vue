<template>
    <div>
       <file-model :ruleForm="ruleForm"/>
    </div>
   </template>
   
   <script>
   import fileModel from './fileModel.vue'
   import { list } from '@/network/doc'
   
    export default {
      name: 'filetxt',
      data () {
        return {
           ruleForm: []
        }
      },
      components: {
       fileModel
      },
      created() {
       this.init()
      },
      methods: {
       init() {
         console.log("this.$route.query.type=",this.$route.query.type)
         list({'type':this.$route.query.type}).then(res => {
             this.ruleForm=res.data.data
         })
       },
       // reload() {
       //   this.$router.replace({
       //     path: '/refresh',
       //     query: {
       //       t: Date.now()
       //     }
       //   })
       // }
      },
    }
   </script>
   
   <style>
   
    
   </style>
   