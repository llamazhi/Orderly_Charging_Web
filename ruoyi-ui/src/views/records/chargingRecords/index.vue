<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="车辆ID" prop="evId">
        <el-input
          v-model="queryParams.evId"
          placeholder="请输入车辆ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="小区ID" prop="csId">
        <el-input
          v-model="queryParams.csId"
          placeholder="请输入小区ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="变压器ID" prop="tId">
        <el-input
          v-model="queryParams.tId"
          placeholder="请输入变压器ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否使用快充" prop="ifUsingFastCharging">
        <el-input
          v-model="queryParams.ifUsingFastCharging"
          placeholder="请输入是否使用快充"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="剩余电量" prop="remainingSoc">
        <el-input
          v-model="queryParams.remainingSoc"
          placeholder="请输入剩余电量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预期充电结束时间" prop="expectedChargingEndTime">
        <el-date-picker clearable
          v-model="queryParams.expectedChargingEndTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择预期充电结束时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="起始充电时间" prop="chargingStartTime">
        <el-date-picker clearable
          v-model="queryParams.chargingStartTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择起始充电时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="是否使用有序充电" prop="ifUsingOrderlyCharging">
        <el-input
          v-model="queryParams.ifUsingOrderlyCharging"
          placeholder="请输入是否使用有序充电"
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
          v-hasPermi="['records:chargingRecords:add']"
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
          v-hasPermi="['records:chargingRecords:edit']"
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
          v-hasPermi="['records:chargingRecords:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['records:chargingRecords:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          :disabled="multiple"
          @click="handleOptimize"
          v-hasPermi="['records:chargingRecords:export']"
        >优化</el-button>
      </el-col>
      
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="chargingRecordsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="充电记录id" align="center" prop="recordId" v-if="true"/>
      <el-table-column label="车辆ID" align="center" prop="evId" />
      <el-table-column label="小区ID" align="center" prop="csId" />
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column label="变压器ID" align="center" prop="tId" />
      <el-table-column label="是否使用快充" align="center" prop="ifUsingFastCharging" />
      <el-table-column label="剩余电量" align="center" prop="remainingSoc" />
      <el-table-column label="预期充电结束时间" align="center" prop="expectedChargingEndTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.expectedChargingEndTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="起始充电时间" align="center" prop="chargingStartTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.chargingStartTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否使用有序充电" align="center" prop="ifUsingOrderlyCharging" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['records:chargingRecords:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['records:chargingRecords:remove']"
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

    <!-- 添加或修改充电记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车辆ID" prop="evId">
          <el-input v-model="form.evId" placeholder="请输入车辆ID" />
        </el-form-item>
        <el-form-item label="小区ID" prop="csId">
          <el-input v-model="form.csId" placeholder="请输入小区ID" />
        </el-form-item>
        <el-form-item label="用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户ID" />
        </el-form-item>
        <el-form-item label="变压器ID" prop="tId">
          <el-input v-model="form.tId" placeholder="请输入变压器ID" />
        </el-form-item>
        <el-form-item label="是否使用快充" prop="ifUsingFastCharging">
          <el-input v-model="form.ifUsingFastCharging" placeholder="请输入是否使用快充" />
        </el-form-item>
        <el-form-item label="剩余电量" prop="remainingSoc">
          <el-input v-model="form.remainingSoc" placeholder="请输入剩余电量" />
        </el-form-item>
        <el-form-item label="预期充电结束时间" prop="expectedChargingEndTime">
          <el-date-picker clearable
            v-model="form.expectedChargingEndTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择预期充电结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="起始充电时间" prop="chargingStartTime">
          <el-date-picker clearable
            v-model="form.chargingStartTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择起始充电时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="是否使用有序充电" prop="ifUsingOrderlyCharging">
          <el-input v-model="form.ifUsingOrderlyCharging" placeholder="请输入是否使用有序充电" />
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
import { listChargingRecords, getChargingRecords, delChargingRecords, addChargingRecords, updateChargingRecords } from "@/api/records/chargingRecords";

export default {
  name: "ChargingRecords",
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
      // 充电记录表格数据
      chargingRecordsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        evId: undefined,
        csId: undefined,
        userId: undefined,
        tId: undefined,
        ifUsingFastCharging: undefined,
        remainingSoc: undefined,
        expectedChargingEndTime: undefined,
        chargingStartTime: undefined,
        ifUsingOrderlyCharging: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        recordId: [
          { required: true, message: "充电记录id不能为空", trigger: "blur" }
        ],
        evId: [
          { required: true, message: "车辆ID不能为空", trigger: "blur" }
        ],
        csId: [
          { required: true, message: "小区ID不能为空", trigger: "blur" }
        ],
        userId: [
          { required: true, message: "用户ID不能为空", trigger: "blur" }
        ],
        tId: [
          { required: true, message: "变压器ID不能为空", trigger: "blur" }
        ],
        ifUsingFastCharging: [
          { required: true, message: "是否使用快充不能为空", trigger: "blur" }
        ],
        remainingSoc: [
          { required: true, message: "剩余电量不能为空", trigger: "blur" }
        ],
        expectedChargingEndTime: [
          { required: true, message: "预期充电结束时间不能为空", trigger: "blur" }
        ],
        chargingStartTime: [
          { required: true, message: "起始充电时间不能为空", trigger: "blur" }
        ],
        ifUsingOrderlyCharging: [
          { required: true, message: "是否使用有序充电不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询充电记录列表 */
    getList() {
      this.loading = true;
      listChargingRecords(this.queryParams).then(response => {
        this.chargingRecordsList = response.rows;
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
        evId: undefined,
        csId: undefined,
        userId: undefined,
        tId: undefined,
        ifUsingFastCharging: undefined,
        remainingSoc: undefined,
        expectedChargingEndTime: undefined,
        createTime: undefined,
        updateBy: undefined,
        updateTime: undefined,
        createBy: undefined,
        chargingStartTime: undefined,
        ifUsingOrderlyCharging: undefined
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
      this.title = "添加充电记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.loading = true;
      this.reset();
      const recordId = row.recordId || this.ids
      getChargingRecords(recordId).then(response => {
        this.loading = false;
        this.form = response.data;
        this.open = true;
        this.title = "修改充电记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.buttonLoading = true;
          if (this.form.recordId != null) {
            updateChargingRecords(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            }).finally(() => {
              this.buttonLoading = false;
            });
          } else {
            addChargingRecords(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除充电记录编号为"' + recordIds + '"的数据项？').then(() => {
        this.loading = true;
        return delChargingRecords(recordIds);
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
      this.download('records/chargingRecords/export', {
        ...this.queryParams
      }, `chargingRecords_${new Date().getTime()}.xlsx`)
    }
  },
   /** 优化按钮操作 */
   handleOptimize(row) {
      const recordIds = row.recordId || this.ids;
      console.log(row.evId);
    },
};
</script>
