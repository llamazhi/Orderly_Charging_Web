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
      <el-form-item label="变压器名称" prop="tName">
        <el-input
          v-model="queryParams.tName"
          placeholder="请输入变压器名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="变压器最大负荷" prop="tMax">
        <el-input
          v-model="queryParams.tMax"
          placeholder="请输入变压器最大负荷"
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
          v-hasPermi="['transformer:transformer:add']"
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
          v-hasPermi="['transformer:transformer:edit']"
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
          v-hasPermi="['transformer:transformer:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['transformer:transformer:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="transformerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="变压器ID" align="center" prop="transformerId" v-if="true"/>
      <el-table-column label="小区ID" align="center" prop="rcId" />
      <el-table-column label="变压器名称" align="center" prop="tName" />
      <el-table-column label="变压器最大负荷" align="center" prop="tMax" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['transformer:transformer:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['transformer:transformer:remove']"
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

    <!-- 添加或修改变压器对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="小区ID" prop="rcId">
          <el-input v-model="form.rcId" placeholder="请输入小区ID" />
        </el-form-item>
        <el-form-item label="变压器名称" prop="tName">
          <el-input v-model="form.tName" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="变压器最大负荷" prop="tMax">
          <el-input v-model="form.tMax" placeholder="请输入变压器最大负荷" />
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
import { listTransformer, getTransformer, delTransformer, addTransformer, updateTransformer } from "@/api/transformer/transformer";

export default {
  name: "Transformer",
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
      // 变压器表格数据
      transformerList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        rcId: undefined,
        tName: undefined,
        tMax: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        transformerId: [
          { required: true, message: "变压器ID不能为空", trigger: "blur" }
        ],
        rcId: [
          { required: true, message: "小区ID不能为空", trigger: "blur" }
        ],
        tName: [
          { required: true, message: "变压器名称不能为空", trigger: "blur" }
        ],
        tMax: [
          { required: true, message: "变压器最大负荷不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询变压器列表 */
    getList() {
      this.loading = true;
      listTransformer(this.queryParams).then(response => {
        this.transformerList = response.rows;
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
        transformerId: undefined,
        rcId: undefined,
        tName: undefined,
        tMax: undefined,
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
      this.ids = selection.map(item => item.transformerId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加变压器";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.loading = true;
      this.reset();
      const transformerId = row.transformerId || this.ids
      getTransformer(transformerId).then(response => {
        this.loading = false;
        this.form = response.data;
        this.open = true;
        this.title = "修改变压器";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.buttonLoading = true;
          if (this.form.transformerId != null) {
            updateTransformer(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            }).finally(() => {
              this.buttonLoading = false;
            });
          } else {
            addTransformer(this.form).then(response => {
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
      const transformerIds = row.transformerId || this.ids;
      this.$modal.confirm('是否确认删除变压器编号为"' + transformerIds + '"的数据项？').then(() => {
        this.loading = true;
        return delTransformer(transformerIds);
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
      this.download('transformer/transformer/export', {
        ...this.queryParams
      }, `transformer_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
