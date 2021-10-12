<!--eslint-disable-->
<template>
  <div class="app-container">
    <el-button class="filter-item"
               size="mini"
               type="primary"
               icon="el-icon-plus" @click="dialogForm = true"
    >添加
    </el-button>
    <el-dialog title="收货地址" :visible.sync="dialogForm">
      <el-form :model="ruleForm" :rules="rules1" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="region">
          <el-select v-model="ruleForm.region" placeholder="请选择活动区域">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="活动时间" required>
          <el-col :span="11">
            <el-form-item prop="date1">
              <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.date1" style="width: 100%;"
              ></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col class="line" :span="2">-</el-col>
          <el-col :span="11">
            <el-form-item prop="date2">
              <el-time-picker placeholder="选择时间" v-model="ruleForm.date2" style="width: 100%;"></el-time-picker>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item label="即时配送" prop="delivery">
          <el-switch v-model="ruleForm.delivery"></el-switch>
        </el-form-item>
        <el-form-item label="活动性质" prop="type">
          <el-checkbox-group v-model="ruleForm.type">
            <el-checkbox label="美食/餐厅线上活动" name="type"></el-checkbox>
            <el-checkbox label="地推活动" name="type"></el-checkbox>
            <el-checkbox label="线下主题活动" name="type"></el-checkbox>
            <el-checkbox label="单纯品牌曝光" name="type"></el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="特殊资源" prop="resource">
          <el-radio-group v-model="ruleForm.resource">
            <el-radio label="线上品牌商赞助"></el-radio>
            <el-radio label="线下场地免费"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="活动形式" prop="desc">
          <el-input type="textarea" v-model="ruleForm.desc"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
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
          prop="deptSort"
          label="日期"
          width="180"
        >
        </el-table-column>
        <el-table-column
          prop="deptSort"
          label="姓名"
          width="180"
        >
        </el-table-column>
        <el-table-column
          prop="name"
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
            <el-button type="text" @click="dialogFormVisible = true">编辑</el-button>
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

export default {
  name: 'dialog.vue',
  data() {
    return {
      //复杂弹窗
      ruleForm: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
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
    //复杂弹窗
    submitForm(formName) {

      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!');
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
      this.$refs.ruleForm.validate((valid) => {
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
        deptSort: 999,
        enabled: true,
        id: 2,
        isTop: '1',
        name: 'test2',
        pid: 18,
        subCount: 0
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
