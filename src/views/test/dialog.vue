<!--eslint-disable-->
<template>
  <div class="app-container">
  <div>
    <el-button type="text" @click="dialogFormVisible = true">打开嵌套表单的 Dialog</el-button>
    <el-dialog title="收货地址" :visible.sync="dialogFormVisible"  @closed="handleClose">
      <el-form :model="form" :rules="rules" ref="ruleForm">
        <el-form-item label="活动名称" :label-width="formLabelWidth" prop="name">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="活动区域" :label-width="formLabelWidth">
          <el-select v-model="form.region" placeholder="请选择活动区域">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleSave">确 定</el-button>
      </div>
    </el-dialog>
  </div>
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
          <el-button type="text" @click="dialogFormVisible = true">打开嵌套表单的 Dialog</el-button>
          <el-dialog title="收货地址" :visible.sync="dialogFormVisible"  @closed="handleClose">
            <el-form :model="form" :rules="rules" ref="ruleForm">
              <el-form-item label="活动名称" :label-width="formLabelWidth" prop="name">
                <el-input v-model="form.name" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="活动区域" :label-width="formLabelWidth">
                <el-select v-model="form.region" placeholder="请选择活动区域">
                  <el-option label="区域一" value="shanghai"></el-option>
                  <el-option label="区域二" value="beijing"></el-option>
                </el-select>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="handleSave">确 定</el-button>
            </div>
          </el-dialog>
      </template>
    </el-table-column>
  </el-table>
    <el-button class="filter-item"
               size="mini"
               type="primary"
               icon="el-icon-plus" @click="select">test select</el-button>
  </div>
</template>

<!--eslint-disable-->
<script>
import axios from 'axios'

export default {
  name: 'dialog.vue',
  data(){
    return {
      //dialog
      dialogFormVisible: false,
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      formLabelWidth: '120px',
      rules: {
        name: [
          { required: true, message: '请输入活动名称', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ]
      },
      selectData: []
    }
  },
  methods:{
    handleClick(row) {
      console.log(row);
    },
    // dialog method
    handleClose () {
      this.$refs.ruleForm.resetFields()
      this.form = {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      }
    },
    handleSave () {
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          console.log('输入正确')
          this.dialogFormVisible = false
        } else {
          console.log('输入错误')
        }
      })
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
