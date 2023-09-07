<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="时间ID" prop="uid">
        <el-input
          v-model="queryParams.uid"
          placeholder="请输入时间ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车辆ID" prop="evid">
        <el-input
          v-model="queryParams.evid"
          placeholder="请输入车辆ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="优化前起始充电时间" prop="oldStartTime">
        <el-date-picker clearable
          v-model="queryParams.oldStartTime"
          type="date"
          value-format="HH:mm:ss"
          placeholder="请选择优化前起始充电时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="优化前结束充电时间" prop="oldEndTime">
        <el-date-picker clearable
          v-model="queryParams.oldEndTime"
          type="date"
          value-format="HH:mm:ss"
          placeholder="请选择优化前结束充电时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="优化后起始充电时间" prop="newStartTime">
        <el-date-picker clearable
          v-model="queryParams.newStartTime"
          type="date"
          value-format="HH:mm:ss"
          placeholder="请选择优化后起始充电时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="优化后结束充电时间" prop="newEndTime">
        <el-date-picker clearable
          v-model="queryParams.newEndTime"
          type="date"
          value-format="HH:mm:ss"
          placeholder="请选择优化后结束充电时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="车辆离开时间" prop="leavingTime">
        <el-date-picker clearable
          v-model="queryParams.leavingTime"
          type="date"
          value-format="HH:mm:ss"
          placeholder="请选择车辆离开时间">
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
          v-hasPermi="['ev:timeComparison:add']"
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
          v-hasPermi="['ev:timeComparison:edit']"
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
          v-hasPermi="['ev:timeComparison:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['ev:timeComparison:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="timeComparisonList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="表单ID" align="center" prop="id" v-if="true"/>
      <el-table-column label="时间ID" align="center" prop="uid" />
      <el-table-column label="车辆ID" align="center" prop="evid" />
      <el-table-column label="优化前起始充电时间" align="center" prop="oldStartTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.oldStartTime, '{h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="优化前结束充电时间" align="center" prop="oldEndTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.oldEndTime, '{h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="优化后起始充电时间" align="center" prop="newStartTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.newStartTime, '{h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="优化后结束充电时间" align="center" prop="newEndTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.newEndTime, '{h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="车辆离开时间" align="center" prop="leavingTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.leavingTime, '{h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ev:timeComparison:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ev:timeComparison:remove']"
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

    <!-- 添加或修改Time Comparison对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="时间ID" prop="uid">
          <el-input v-model="form.uid" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="车辆ID" prop="evid">
          <el-input v-model="form.evid" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="优化前起始充电时间" prop="oldStartTime">
          <el-date-picker clearable
            v-model="form.oldStartTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择优化前起始充电时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="优化前结束充电时间" prop="oldEndTime">
          <el-date-picker clearable
            v-model="form.oldEndTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择优化前结束充电时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="优化后起始充电时间" prop="newStartTime">
          <el-date-picker clearable
            v-model="form.newStartTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择优化后起始充电时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="优化后结束充电时间" prop="newEndTime">
          <el-date-picker clearable
            v-model="form.newEndTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择优化后结束充电时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="车辆离开时间" prop="leavingTime">
          <el-date-picker clearable
            v-model="form.leavingTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择车辆离开时间">
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
import { listTimeComparison, getTimeComparison, delTimeComparison, addTimeComparison, updateTimeComparison } from "@/api/ev/timeComparison";

export default {
  name: "TimeComparison",
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
      // Time Comparison表格数据
      timeComparisonList: [],
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
        leavingTime: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        id: [
          { required: true, message: "表单ID不能为空", trigger: "blur" }
        ],
        uid: [
          { required: true, message: "时间ID不能为空", trigger: "blur" }
        ],
        evid: [
          { required: true, message: "车辆ID不能为空", trigger: "blur" }
        ],
        oldStartTime: [
          { required: true, message: "优化前起始充电时间不能为空", trigger: "blur" }
        ],
        oldEndTime: [
          { required: true, message: "优化前结束充电时间不能为空", trigger: "blur" }
        ],
        newStartTime: [
          { required: true, message: "优化后起始充电时间不能为空", trigger: "blur" }
        ],
        newEndTime: [
          { required: true, message: "优化后结束充电时间不能为空", trigger: "blur" }
        ],
        leavingTime: [
          { required: true, message: "车辆离开时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询Time Comparison列表 */
    getList() {
      this.loading = true;
      listTimeComparison(this.queryParams).then(response => {
        this.timeComparisonList = response.rows;
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
        id: undefined,
        uid: undefined,
        evid: undefined,
        oldStartTime: undefined,
        oldEndTime: undefined,
        newStartTime: undefined,
        newEndTime: undefined,
        leavingTime: undefined,
        createBy: undefined,
        createTime: undefined,
        updateBy: undefined,
        updateTime: undefined
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加Time Comparison";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.loading = true;
      this.reset();
      const id = row.id || this.ids
      getTimeComparison(id).then(response => {
        this.loading = false;
        this.form = response.data;
        this.open = true;
        this.title = "修改Time Comparison";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.buttonLoading = true;
          if (this.form.id != null) {
            updateTimeComparison(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            }).finally(() => {
              this.buttonLoading = false;
            });
          } else {
            addTimeComparison(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除Time Comparison编号为"' + ids + '"的数据项？').then(() => {
        this.loading = true;
        return delTimeComparison(ids);
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
      this.download('ev/timeComparison/export', {
        ...this.queryParams
      }, `timeComparison_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
