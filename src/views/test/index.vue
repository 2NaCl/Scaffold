<!--eslint-disable-->
<template>
  <div class="app-container">
    <el-table
      :data="selectData"
      stripe
      style="width: 100%">
      <el-table-column
        prop="deptSort"
        label="日期"
        width="180">
      </el-table-column>
      <el-table-column
        prop="deptSort"
        label="姓名"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="地址">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
          <el-button type="text" size="small">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-button @click="test222()">test button</el-button>
    <el-dropdown>
      <span class="el-dropdown-link">
        下拉菜单<i class="el-icon-arrow-down el-icon--right"></i>
      </span>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item>黄金糕</el-dropdown-item>
        <el-dropdown-item>狮子头</el-dropdown-item>
        <el-dropdown-item>螺蛳粉</el-dropdown-item>
        <el-dropdown-item disabled>双皮奶</el-dropdown-item>
        <el-dropdown-item divided>蚵仔煎</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
    <div class="line"></div>
    <div class="line"></div>
    <el-button type="text" @click="open1">点击打开 Message Box</el-button>
    <el-button class="filter-item"
               size="mini"
               type="primary"
               icon="el-icon-plus" @click="select">test select</el-button>
    <div class="line"></div>
    <el-button type="text" @click="open">点击打开 Message Box</el-button>
    <div class="line"></div>
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
      <el-menu-item index="1">处理中心</el-menu-item>
      <el-submenu index="2">
        <template slot="title">我的工作台</template>
        <el-menu-item index="2-1">选项1</el-menu-item>
        <el-menu-item index="2-2">选项2</el-menu-item>
        <el-menu-item index="2-3">选项3</el-menu-item>
        <el-submenu index="2-4">
          <template slot="title">选项4</template>
          <el-menu-item index="2-4-1">选项1</el-menu-item>
          <el-menu-item index="2-4-2">选项2</el-menu-item>
          <el-menu-item index="2-4-3">选项3</el-menu-item>
        </el-submenu>
      </el-submenu>
      <el-menu-item index="3" disabled>消息中心</el-menu-item>
      <el-menu-item index="4"><a href="https://www.ele.me" target="_blank">订单管理</a></el-menu-item>
    </el-menu>
    <div class="line"></div>
    <el-menu
      :default-active="activeIndex2"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
    >
      <el-menu-item index="1">处理中心</el-menu-item>
      <el-submenu index="2">
        <template slot="title">我的工作台</template>
        <el-menu-item index="2-1">选项1</el-menu-item>
        <el-menu-item index="2-2">选项2</el-menu-item>
        <el-menu-item index="2-3">选项3</el-menu-item>
        <el-submenu index="2-4">
          <template slot="title">选项4</template>
          <el-menu-item index="2-4-1">选项1</el-menu-item>
          <el-menu-item index="2-4-2">选项2</el-menu-item>
          <el-menu-item index="2-4-3">选项3</el-menu-item>
        </el-submenu>
      </el-submenu>
      <el-menu-item index="3" disabled>消息中心</el-menu-item>
      <el-menu-item index="4"><a href="https://www.ele.me" target="_blank">订单管理</a></el-menu-item>
    </el-menu>
  </div>

</template>
<!--eslint-disable -->
<script>
import axios from 'axios'

export default {
  data() {
    return {
      activeIndex: '1',
      activeIndex2: '1',
      tableData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1517 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1519 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }],
      selectData: []
    }
  },
  methods: {
    handleClick(row) {
      console.log(row);
    },
    test222(){
      let url = `http://localhost:8013/api/test`
      axios.get(url).then(
        promise => console.log(promise.data)
      )
    },
    select(){
      let url = `http://localhost:8013/api/select`
      let dept = {
        deptSort: 999,
        enabled: true,
        id: 2,
        isTop: "1",
        name: "test2",
        pid: 18,
        subCount: 0
      }
      axios.post(url,dept).then(
        promise=> {
          console.log(promise.data)
          this.selectData = JSON.parse(JSON.stringify(promise.data))
          console.log(this.selectData)
        }).catch(err=>{
          let {resp} = err
          let resJson = JSON.parse(resp)
          resJson.then((res)=>{
            console.log(res)
          })
      })
    },
    add(){
      let url = `http://localhost:8013/api/add`
      let dept = {

      }
    }
    ,
    update(){
      let url = `http://localhost:8013/api/update`
      let dept = {
        dept_id : 1,

      }
    },
    handleSelect(key, keyPath) {
      console.log(key, keyPath)
    },
    open() {
      let url = `http://localhost:8013/api/test`
      axios.get(url).then(
        promise => this.$alert(promise.data)
      )
    },
    open1() {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$message({
          type: 'success',
          message: '删除成功!'
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.el-dropdown-link {
  cursor: pointer;
  color: #409EFF;
}

.el-icon-arrow-down {
  font-size: 12px;
}

</style>
