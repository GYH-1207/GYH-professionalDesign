<template>
  <div id="app">
    <top-bar/>
    <!-- <router-link to="/accountCenter/regist">regist</router-link> -->
    <router-view/>
  </div>
</template>

<script>
import TopBar from '@/components/topBar.vue';

export default {
  name: 'App',
  provide() {
    return {
      reload: this.reload
    }
  },
  data() {
    return {
      // 控制router-view的隐藏与展示
      isShow: true
    }
  },
  components: {
    TopBar
  },
  // 定义reload方法
  methods: {
    reload () {
      // 先隐藏
      this.isShow = false
      
      // $nextTick() 将回调延迟到下次 DOM 更新循环之后执行
      this.$nextTick(() => {
        this.isShow = true
      })
    }
  }
}
</script>

<style>

</style>
