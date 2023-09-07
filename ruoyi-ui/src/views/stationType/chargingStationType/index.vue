<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="充电站ID" prop="csId">
        <el-input
          v-model="queryParams.csId"
          placeholder="请输入充电站ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="制造商" prop="manufacturer">
        <el-input
          v-model="queryParams.manufacturer"
          placeholder="请输入制造商"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属公司" prop="company">
        <el-input
          v-model="queryParams.company"
          placeholder="请输入所属公司"
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
          v-hasPermi="['stationType:chargingStationType:add']"
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
          v-hasPermi="['stationType:chargingStationType:edit']"
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
          v-hasPermi="['stationType:chargingStationType:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['stationType:chargingStationType:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="chargingStationTypeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="充电桩种类ID" align="center" prop="typeId" v-if="true"/>
      <el-table-column label="充电站ID" align="center" prop="csId" />
      <el-table-column label="制造商" align="center" prop="manufacturer" />
      <el-table-column label="所属公司" align="center" prop="company" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['stationType:chargingStationType:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['stationType:chargingStationType:remove']"
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

    <!-- 添加或修改充电桩类型对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="充电站ID" prop="csId">
          <el-input v-model="form.csId" placeholder="请输入充电站ID" />
        </el-form-item>
        <el-form-item label="制造商" prop="manufacturer">
          <el-input v-model="form.manufacturer" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="所属公司" prop="company">
          <el-input v-model="form.company" type="textarea" placeholder="请输入内容" />
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
import { listChargingStationType, getChargingStationType, delChargingStationType, addChargingStationType, updateChargingStationType } from "@/api/stationType/chargingStationType";

export default {
  name: "ChargingStationType",
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
      // 充电桩类型表格数据
      chargingStationTypeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        csId: undefined,
        manufacturer: undefined,
        company: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        typeId: [
          { required: true, message: "充电桩种类ID不能为空", trigger: "blur" }
        ],
        csId: [
          { required: true, message: "充电站ID不能为空", trigger: "blur" }
        ],
        manufacturer: [
          { required: true, message: "制造商不能为空", trigger: "blur" }
        ],
        company: [
          { required: true, message: "所属公司不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询充电桩类型列表 */
    getList() {
      this.loading = true;
      listChargingStationType(this.queryParams).then(response => {
        this.chargingStationTypeList = response.rows;
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
        typeId: undefined,
        csId: undefined,
        manufacturer: undefined,
        company: undefined,
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
      this.ids = selection.map(item => item.typeId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加充电桩类型";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.loading = true;
      this.reset();
      const typeId = row.typeId || this.ids
      getChargingStationType(typeId).then(response => {
        this.loading = false;
        this.form = response.data;
        this.open = true;
        this.title = "修改充电桩类型";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.buttonLoading = true;
          if (this.form.typeId != null) {
            updateChargingStationType(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            }).finally(() => {
              this.buttonLoading = false;
            });
          } else {
            addChargingStationType(this.form).then(response => {
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
      const typeIds = row.typeId || this.ids;
      this.$modal.confirm('是否确认删除充电桩类型编号为"' + typeIds + '"的数据项？').then(() => {
        this.loading = true;
        return delChargingStationType(typeIds);
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
      this.download('stationType/chargingStationType/export', {
        ...this.queryParams
      }, `chargingStationType_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
