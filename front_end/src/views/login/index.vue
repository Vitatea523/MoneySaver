<template>
  <div class="login-page">
    <s-header></s-header>
    <div class="page-title">Login</div>
    <div class="login-box">
      <el-card shadow="always">
        <el-form ref="form" :model="form" :rules="rules" label-width="120px">
          <el-form-item label="Username" prop="username">
            <el-input v-model="form.username"></el-input>
          </el-form-item>
          <el-form-item label="Password" prop="password">
            <el-input type="password" v-model="form.password"></el-input>
          </el-form-item>
          <div class="register" @click="toRegister">
            <el-link class="my-link">Not have account? Please Register</el-link>
          </div>
          <el-form-item>
            <el-button size="medium" type="danger" @click="onSubmit"
              >Login</el-button
            >
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </div>
</template>

<script>
import sHeader from "../../components/header.vue";
export default {
  components: {
    sHeader,
  },
  data() {
    return {
      form: {
        username: "",
        password: "",
      },
      rules: {
        username: [
          {
            required: true,
            message: "Please input username",
            trigger: "blur",
          },
        ],
        password: [
          {
            required: true,
            message: "Please input password",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    onSubmit() {
      // 验证通过
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.$axios
            .post("/user/login", {
              username: this.form.username,
              password: this.form.password,
            })
            .then(
              (res) => {
                console.log(res);
                if (res.code == 400) {
                  this.$message({
                    message: res.msg || "Password is wrong",
                    type: "error",
                  });
                } else {
                  const token = res.data.userId;
                  sessionStorage.setItem("token", JSON.stringify(token));
                  this.$router.push("home");
                }
              },
              (err) => {
                this.$message({
                  message: "Password is wrong",
                  type: "error",
                });
                sessionStorage.setItem("token", JSON.stringify(5555));
                this.$router.push("home");
                console.log(err);
              }
            );
        }
      });
    },
    toRegister() {
      this.$router.push("register");
    },
  },
};
</script>
<style lang="less">
.login-page {
  .el-card__body {
    padding: 25px 25px 0;
  }
  .my-link:hover {
    color: #e4393c;
    &::after {
      border-bottom-color: #e4393c;
    }
  }
}
</style>
<style lang="less" scoped>
.login-page {
  .el-form-item {
    text-align: left;
  }
  .login-box {
    width: 50%;
    margin: 0 auto;
    .register {
      margin-top: -8px;
      margin-left: 120px;
      text-align: left;
      margin-bottom: 15px;
    }
  }
}
</style>
