<template>
    <el-menu
      @select="handleSelect"
      mode="horizontal"
      :default-active="$route.path"
      active-text-color="#ff9900"
    >
    <!-- :router="true" -->
    <!-- @click="to($route.path)" -->
    <!-- {{$route.path}} -->
      <template v-for="(menu, menuId) in $router.options.routes">
        <!-- 菜单不隐藏,hidden=false。 -->
        <template v-if="!menu.meta.hidden">
  
          <!-- 判断是否只有一个节点 -->
          <el-menu-item v-if="menu.meta.leaf" :index="menu.redirect" :key="menuId">
            <i class="el-icon-view"></i>
            <span slot="title">{{menu.meta.title}}</span>
          </el-menu-item>
  
          <!-- 多个节点 -->
          <el-submenu v-else :index="menu.path" :key="menuId+1">
            <template slot="title">
              <i class="el-icon-s-custom"></i>
              <span>{{menu.meta.title}}</span>
            </template>
            <el-menu-item-group>
              <el-menu-item v-for="(child,childId) in menu.children" :key="childId"
                            :index="menu.path +'/'+ child.path">
                            {{child.meta.title}}
              </el-menu-item>
            </el-menu-item-group>
          </el-submenu>
  
        </template>
      </template>
    </el-menu>
</template>

<script>

 export default {
   name: 'topBar',
   data () {
     return {

     }
    },
   components: {

   },
   methods: {
    handleSelect(index,indexPath) {
      // console.log("index=",index)
      // console.log("index=",indexPath)
      if(indexPath.length>1){

        var indexUrl=indexPath[1].lastIndexOf('/')
        var url=indexPath[1].substring(indexUrl+1);
        // console.log("url=",url)
        this.$router.push({
          name: url,
          query: {
            type: url
          }
        })
      }else{
        this.$router.push(index)
      }
    }
   }
}
</script>

<style>

 
</style>
