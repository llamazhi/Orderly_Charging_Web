<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="变压器ID" prop="transformerId">
        <el-input
          v-model="queryParams.transformerId"
          placeholder="请输入变压器ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="小区ID" prop="rcId">
        <el-input
          v-model="queryParams.rcId"
          placeholder="请输入小区ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="充电桩类型ID" prop="typeId">
        <el-input
          v-model="queryParams.typeId"
          placeholder="请输入充电桩类型ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="过期时间" prop="expiringDate">
        <el-input
          v-model="queryParams.expiringDate"
          placeholder="请输入过期时间"
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
          v-hasPermi="['station:chargingStation:add']"
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
          v-hasPermi="['station:chargingStation:edit']"
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
          v-hasPermi="['station:chargingStation:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['station:chargingStation:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="chargingStationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="充电桩ID" align="center" prop="csId" v-if="true"/>
      <el-table-column label="变压器ID" align="center" prop="transformerId" />
      <el-table-column label="小区ID" align="center" prop="rcId" />
      <el-table-column label="充电桩类型ID" align="center" prop="typeId" />
      <el-table-column label="过期时间" align="center" prop="expiringDate" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['station:chargingStation:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['station:chargingStation:remove']"
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

    <!-- 添加或修改充电桩对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="变压器ID" prop="transformerId">
          <el-input v-model="form.transformerId" placeholder="请输入变压器ID" />
        </el-form-item>
        <el-form-item label="小区ID" prop="rcId">
          <el-input v-model="form.rcId" placeholder="请输入小区ID" />
        </el-form-item>
        <el-form-item label="充电桩类型ID" prop="typeId">
          <el-input v-model="form.typeId" placeholder="请输入充电桩类型ID" />
        </el-form-item>
        <el-form-item label="过期时间" prop="expiringDate">
          <el-input v-model="form.expiringDate" placeholder="请输入过期时间" />
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
import { listChargingStation, getChargingStation, delChargingStation, addChargingStation, updateChargingStation } from "@/api/station/chargingStation";

export default {
  name: "ChargingStation",
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
      // 充电桩表格数据
      chargingStationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        transformerId: undefined,
        rcId: undefined,
        typeId: undefined,
        expiringDate: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        csId: [
          { required: true, message: "充电桩ID不能为空", trigger: "blur" }
        ],
        transformerId: [
          { required: true, message: "变压器ID不能为空", trigger: "blur" }
        ],
        rcId: [
          { required: true, message: "小区ID不能为空", trigger: "blur" }
        ],
        typeId: [
          { required: true, message: "充电桩类型ID不能为空", trigger: "blur" }
        ],
        expiringDate: [
          { required: true, message: "过期时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询充电桩列表 */
    getList() {
      this.loading = true;
      listChargingStation(this.queryParams).then(response => {
        this.chargingStationList = response.rows;
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
        csId: undefined,
        transformerId: undefined,
        rcId: undefined,
        typeId: undefined,
        expiringDate: undefined,
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
      this.ids = selection.map(item => item.csId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加充电桩";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.loading = true;
      this.reset();
      const csId = row.csId || this.ids
      getChargingStation(csId).then(response => {
        this.loading = false;
        this.form = response.data;
        this.open = true;
        this.title = "修改充电桩";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.buttonLoading = true;
          if (this.form.csId != null) {
            updateChargingStation(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            }).finally(() => {
              this.buttonLoading = false;
            });
          } else {
            addChargingStation(this.form).then(response => {
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
      const csIds = row.csId || this.ids;
      this.$modal.confirm('是否确认删除充电桩编号为"' + csIds + '"的数据项？').then(() => {
        this.loading = true;
        return delChargingStation(csIds);
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
      this.download('station/chargingStation/export', {
        ...this.queryParams
      }, `chargingStation_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
