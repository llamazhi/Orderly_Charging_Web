<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="" prop="uid">
        <el-input
          v-model="queryParams.uid"
          placeholder="请输入"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="" prop="evid">
        <el-input
          v-model="queryParams.evid"
          placeholder="请输入"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="" prop="oldStartTime">
        <el-date-picker clearable
          v-model="queryParams.oldStartTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="" prop="oldEndTime">
        <el-date-picker clearable
          v-model="queryParams.oldEndTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="" prop="newStartTime">
        <el-date-picker clearable
          v-model="queryParams.newStartTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="" prop="newEndTime">
        <el-date-picker clearable
          v-model="queryParams.newEndTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="" prop="leavingTime">
        <el-date-picker clearable
          v-model="queryParams.leavingTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择">
        </el-date-picker>
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
          v-hasPermi="[':timeComparisonTable:add']"
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
          v-hasPermi="[':timeComparisonTable:edit']"
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
          v-hasPermi="[':timeComparisonTable:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="[':timeComparisonTable:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="timeComparisonTableList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="" align="center" prop="uid" />
      <el-table-column label="" align="center" prop="evid" />
      <el-table-column label="" align="center" prop="oldStartTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.oldStartTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="" align="center" prop="oldEndTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.oldEndTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="" align="center" prop="newStartTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.newStartTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="" align="center" prop="newEndTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.newEndTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="" align="center" prop="leavingTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.leavingTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="[':timeComparisonTable:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="[':timeComparisonTable:remove']"
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

    <!-- 添加或修改【请填写功能名称】对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="" prop="uid">
          <el-input v-model="form.uid" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="" prop="evid">
          <el-input v-model="form.evid" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="" prop="oldStartTime">
          <el-date-picker clearable
            v-model="form.oldStartTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="" prop="oldEndTime">
          <el-date-picker clearable
            v-model="form.oldEndTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="" prop="newStartTime">
          <el-date-picker clearable
            v-model="form.newStartTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="" prop="newEndTime">
          <el-date-picker clearable
            v-model="form.newEndTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="" prop="leavingTime">
          <el-date-picker clearable
            v-model="form.leavingTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择">
          </el-date-picker>
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
import { listTimeComparisonTable, getTimeComparisonTable, delTimeComparisonTable, addTimeComparisonTable, updateTimeComparisonTable } from "@/api//timeComparisonTable";

export default {
  name: "TimeComparisonTable",
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
      // 【请填写功能名称】表格数据
      timeComparisonTableList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        uid: undefined,
        evid: undefined,
        oldStartTime: undefined,
        oldEndTime: undefined,
        newStartTime: undefined,
        newEndTime: undefined,
        leavingTime: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        uid: [
          { required: true, message: "不能为空", trigger: "blur" }
        ],
        evid: [
          { required: true, message: "不能为空", trigger: "blur" }
        ],
        oldStartTime: [
          { required: true, message: "不能为空", trigger: "blur" }
        ],
        oldEndTime: [
          { required: true, message: "不能为空", trigger: "blur" }
        ],
        newStartTime: [
          { required: true, message: "不能为空", trigger: "blur" }
        ],
        newEndTime: [
          { required: true, message: "不能为空", trigger: "blur" }
        ],
        leavingTime: [
          { required: true, message: "不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询【请填写功能名称】列表 */
    getList() {
      this.loading = true;
      listTimeComparisonTable(this.queryParams).then(response => {
        this.timeComparisonTableList = response.rows;
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
        uid: undefined,
        evid: undefined,
        oldStartTime: undefined,
        oldEndTime: undefined,
        newStartTime: undefined,
        newEndTime: undefined,
        leavingTime: undefined
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
      this.ids = selection.map(item => item.uid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加【请填写功能名称】";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.loading = true;
      this.reset();
      const uid = row.uid || this.ids
      getTimeComparisonTable(uid).then(response => {
        this.loading = false;
        this.form = response.data;
        this.open = true;
        this.title = "修改【请填写功能名称】";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.buttonLoading = true;
          if (this.form.uid != null) {
            updateTimeComparisonTable(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            }).finally(() => {
              this.buttonLoading = false;
            });
          } else {
            addTimeComparisonTable(this.form).then(response => {
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
      const uids = row.uid || this.ids;
      this.$modal.confirm('是否确认删除【请填写功能名称】编号为"' + uids + '"的数据项？').then(() => {
        this.loading = true;
        return delTimeComparisonTable(uids);
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
      this.download('/timeComparisonTable/export', {
        ...this.queryParams
      }, `timeComparisonTable_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
