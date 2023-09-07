<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="小区ID" prop="rcId">
        <el-input
          v-model="queryParams.rcId"
          placeholder="请输入小区ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="现在时刻" prop="currTime">
        <el-date-picker clearable
          v-model="queryParams.currTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择现在时刻">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="现在充电负荷" prop="currChargingLoad">
        <el-input
          v-model="queryParams.currChargingLoad"
          placeholder="请输入现在充电负荷"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="变压器ID" prop="transformerId">
        <el-input
          v-model="queryParams.transformerId"
          placeholder="请输入变压器ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="现在日常负荷" prop="currDailyLoad">
        <el-input
          v-model="queryParams.currDailyLoad"
          placeholder="请输入现在日常负荷"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['transRecord:transformerRecords:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['transRecord:transformerRecords:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['transRecord:transformerRecords:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['transRecord:transformerRecords:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="transformerRecordsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="变压器记录id" align="center" prop="recordId" v-if="true"/>
      <el-table-column label="小区ID" align="center" prop="rcId" />
      <el-table-column label="现在时刻" align="center" prop="currTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.currTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="现在充电负荷" align="center" prop="currChargingLoad" />
      <el-table-column label="变压器ID" align="center" prop="transformerId" />
      <el-table-column label="现在日常负荷" align="center" prop="currDailyLoad" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['transRecord:transformerRecords:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['transRecord:transformerRecords:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改变压器记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="小区ID" prop="rcId">
          <el-input v-model="form.rcId" placeholder="请输入小区ID" />
        </el-form-item>
        <el-form-item label="现在时刻" prop="currTime">
          <el-date-picker clearable
            v-model="form.currTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择现在时刻">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="现在充电负荷" prop="currChargingLoad">
          <el-input v-model="form.currChargingLoad" placeholder="请输入现在充电负荷" />
        </el-form-item>
        <el-form-item label="变压器ID" prop="transformerId">
          <el-input v-model="form.transformerId" placeholder="请输入变压器ID" />
        </el-form-item>
        <el-form-item label="现在日常负荷" prop="currDailyLoad">
          <el-input v-model="form.currDailyLoad" placeholder="请输入现在日常负荷" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button :loading="buttonLoading" type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listTransformerRecords, getTransformerRecords, delTransformerRecords, addTransformerRecords, updateTransformerRecords } from "@/api/transRecord/transformerRecords";

export default {
  name: "TransformerRecords",
  data() {
    return {
      // 按钮loading
      buttonLoading: false,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 变压器记录表格数据
      transformerRecordsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        rcId: undefined,
        currTime: undefined,
        currChargingLoad: undefined,
        transformerId: undefined,
        currDailyLoad: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        recordId: [
          { required: true, message: "变压器记录id不能为空", trigger: "blur" }
        ],
        rcId: [
          { required: true, message: "小区ID不能为空", trigger: "blur" }
        ],
        currTime: [
          { required: true, message: "现在时刻不能为空", trigger: "blur" }
        ],
        currChargingLoad: [
          { required: true, message: "现在充电负荷不能为空", trigger: "blur" }
        ],
        transformerId: [
          { required: true, message: "变压器ID不能为空", trigger: "blur" }
        ],
        currDailyLoad: [
          { required: true, message: "现在日常负荷不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询变压器记录列表 */
    getList() {
      this.loading = true;
      listTransformerRecords(this.queryParams).then(response => {
        this.transformerRecordsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        recordId: undefined,
        rcId: undefined,
        currTime: undefined,
        currChargingLoad: undefined,
        createBy: undefined,
        createTime: undefined,
        updateBy: undefined,
        updateTime: undefined,
        transformerId: undefined,
        currDailyLoad: undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.recordId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加变压器记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.loading = true;
      this.reset();
      const recordId = row.recordId || this.ids
      getTransformerRecords(recordId).then(response => {
        this.loading = false;
        this.form = response.data;
        this.open = true;
        this.title = "修改变压器记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.buttonLoading = true;
          if (this.form.recordId != null) {
            updateTransformerRecords(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            }).finally(() => {
              this.buttonLoading = false;
            });
          } else {
            addTransformerRecords(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            }).finally(() => {
              this.buttonLoading = false;
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const recordIds = row.recordId || this.ids;
      this.$modal.confirm('是否确认删除变压器记录编号为"' + recordIds + '"的数据项？').then(() => {
        this.loading = true;
        return delTransformerRecords(recordIds);
      }).then(() => {
        this.loading = false;
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      }).finally(() => {
        this.loading = false;
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('transRecord/transformerRecords/export', {
        ...this.queryParams
      }, `transformerRecords_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
