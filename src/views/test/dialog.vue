<!--eslint-disable-->
<template>
  <div class="app-container">
    <div>
      <el-input v-model="form.name" clearable size="small" style="width: 200px;" class="filter-item" placeholder="请输入搜索名字"></el-input>
      <el-button class="filter-item"
                 size="mini"
                 type="primary"
                 icon="el-icon-plus" @click="inputSelect(form.name)">搜索</el-button>
    </div>
    <el-button class="filter-item"
               size="mini"
               type="primary"
               icon="el-icon-plus" @click="dialogForm = true"
    >添加
    </el-button>
    <el-dialog title="添加员工" :visible.sync="dialogForm">
      <el-form :model="form" :rules="rules1" ref="form" label-width="100px" class="demo-ruleForm">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="region">
          <el-select v-model="form.region" placeholder="请选择活动区域">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="活动时间" required>
          <el-col :span="11">
            <el-form-item prop="date1">
              <el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 100%;"
              ></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col class="line" :span="2">-</el-col>
          <el-col :span="11">
            <el-form-item prop="date2">
              <el-time-picker placeholder="选择时间" v-model="form.date2" style="width: 100%;"></el-time-picker>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item label="即时配送" prop="delivery">
          <el-switch v-model="form.delivery"></el-switch>
        </el-form-item>
        <el-form-item label="活动性质" prop="type">
          <el-checkbox-group v-model="form.type">
            <el-checkbox label="美食/餐厅线上活动" name="type"></el-checkbox>
            <el-checkbox label="地推活动" name="type"></el-checkbox>
            <el-checkbox label="线下主题活动" name="type"></el-checkbox>
            <el-checkbox label="单纯品牌曝光" name="type"></el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="特殊资源" prop="resource">
          <el-radio-group v-model="form.resource">
            <el-radio label="线上品牌商赞助"></el-radio>
            <el-radio label="线下场地免费"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="活动形式" prop="desc">
          <el-input type="textarea" v-model="form.desc"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('form',form)">立即创建</el-button>
          <el-button @click="resetForm('form')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>


      <el-dialog title="收货地址" :visible.sync="dialogFormTest">
        <el-form :model="form">
          <el-form-item label="活动名称" :label-width="formLabelWidth">
            <el-input v-model="form.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="活动区域" :label-width="formLabelWidth">
            <el-select v-model="form.region" placeholder="请选择活动区域">
              <el-option label="区域3" value="shanghai"></el-option>
              <el-option label="区域4" value="beijing"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormTest = false">取 消</el-button>
          <el-button type="primary" @click="dialogFormTest = false">确 定</el-button>
        </div>
      </el-dialog>

      <el-table
        :data="selectData"
        stripe
        style="width: 100%"
      >
        <el-table-column
          prop="date1"
          label="日期"
          width="180"
        >
        </el-table-column>
        <el-table-column
          prop="date2"
          label="日期2"
          width="180"
        >
        </el-table-column>
        <el-table-column
          prop="name"
          label="姓名"
          width="180"
        >
        </el-table-column>
        <el-table-column
          prop="resource"
          label="资源"
          width="180"
        >
        </el-table-column>
        <el-table-column
          prop="region"
          label="地址"
        >
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="100"
        >
          <template slot-scope="scope">
            <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
            <el-button type="text" @click="modifyData(scope.$index,scope.row)">编辑</el-button>
            <el-button type="text" @click.native.prevent="confirmDelete(scope.$index,selectData)" size="small">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-button class="filter-item"
                 size="mini"
                 type="primary"
                 icon="el-icon-plus" v-trigger @click="select"
      >test select
      </el-button>
  </div>
</template>

<!--eslint-disable-->
<script>
import axios from 'axios'

var _index;
export default {
  name: 'dialog.vue',
  data() {
    return {
      input: '',
      //复杂弹窗
      rules1: {
        name: [
          { required: true, message: '请输入活动名称', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        region: [
          { required: true, message: '请选择活动区域', trigger: 'change' }
        ],
        date1: [
          { type: 'date', required: true, message: '请选择日期', trigger: 'change' }
        ],
        date2: [
          { type: 'date', required: true, message: '请选择时间', trigger: 'change' }
        ],
        type: [
          { type: 'array', required: true, message: '请至少选择一个活动性质', trigger: 'change' }
        ],
        resource: [
          { required: true, message: '请选择活动资源', trigger: 'change' }
        ],
        desc: [
          { required: true, message: '请填写活动形式', trigger: 'blur' }
        ]
      },
      //dialog
      dialogForm: false,
      dialogFormTest: false,
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
  //todo.. 自定义一个自动的事件触发
  directives: {
    trigger: {
      inserted(el, binging) {
        el.click()
      }
    }
  },
  methods: {
    modifyData(index,row){
      this.dialogForm = true
      this.form= Object.assign({},row)
      _index = index
    },
    inputSelect(name){
      console.log(name)
      let url = 'http://localhost:8013/api/selectByName'
      let data = {
        name:name
      }
      axios.post(url,data).then(
        promise=>{
          console.log(promise.data)
          this.selectData = JSON.parse(JSON.stringify(promise.data))
        }
      )
    },
    //复杂弹窗
    submitForm(formName,form) {
      let url = 'http://localhost:8013/api/select'
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post(url,form).then(
            promise=>{
              this.selectData = JSON.parse(JSON.stringify(promise.data))
            }
          )
          alert('submit!');
          this.dialogForm = false
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    //删除button
    deleteRow(index, rows) {
      rows.splice(index, 1)
      return '删除成功'
    },
    confirmDelete(index, rows) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$message({
          type: 'success',
          message: this.deleteRow(index, rows)
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })

    },
    handleClick(row) {
      console.log(row)
    },
    // dialog method
    handleClose() {
      // this.$refs.ruleForm.resetFields()
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
    handleSave() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          console.log('输入正确')
          console.log(this.form)
          this.dialogFormVisible = false
        } else {
          console.log('输入错误')
        }
      })
    },
    select() {
      let url = `http://localhost:8013/api/select`
      let dept = {
        name: '张三',
        region: '中国',
        date1: '2020-02-02',
        date2: '2021-07-24',
        delivery: false,
        type: [],
        resource: 'nico',
        desc: '1'
      }
      axios.post(url, dept).then(
        promise => {
          console.log(promise.data)
          this.selectData = JSON.parse(JSON.stringify(promise.data))
          console.log(this.selectData)
        }).catch(err => {
        let { resp } = err
        let resJson = JSON.parse(resp)
        resJson.then((res) => {
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
