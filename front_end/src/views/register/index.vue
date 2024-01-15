<template>
  <div class="register-page">
    <s-header></s-header>
    <div class="page-title">Register</div>
    <div class="register-box">
      <el-card shadow="always">
        <el-form
          ref="ruleForm"
          :model="form"
          label-width="145px"
          :rules="rules"
        >
          <el-form-item label="Username" prop="username">
            <el-input v-model="form.username"></el-input>
          </el-form-item>
          <el-form-item label="Email" prop="eMail">
            <el-input v-model="form.eMail"></el-input>
          </el-form-item>
          <el-form-item label="Address" prop="address">
            <el-input v-model="form.address"></el-input>
          </el-form-item>
          <el-form-item label="Password" prop="password">
            <el-input type="password" v-model="form.password"></el-input>
          </el-form-item>
          <el-form-item label="Repeat password" prop="repeatPassword">
            <el-input type="password" v-model="form.repeatPassword"></el-input>
          </el-form-item>
          <div class="register" @click="toLogin">
            <el-link class="my-link"
              >Already have an account? Please Sign in</el-link
            >
          </div>
          <el-form-item>
            <el-button size="medium" type="danger" @click="onSubmit"
              >Register</el-button
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
        eMail: "",
        address: "",
        password: "",
        repeatPassword: "",
        type: [],
      },
      rules: {
        username: [
          {
            required: true,
            message: "Please input Activity name",
            trigger: "blur",
          },
          {
            min: 3,
            max: 10,
            message: "Length should be 3 to 10",
            trigger: "blur",
          },
        ],
        eMail: [
          {
            required: true,
            message: "Please input E-mail",
            trigger: "blur",
          },
        ],
        address: [
          {
            required: true,
            message: "Please input address",
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
        repeatPassword: [
          {
            required: true,
            message: "Please input Repeat passwprd",
            trigger: "blur",
          },
        ],

      },
    };
  },
  methods: {
    onSubmit() {
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          if (this.form.password !== this.form.repeatPassword) {
            this.$message({
              message: "The input passwords must be consistent",
              type: "error",
            });
            return;
          }
          console.log(this.form);
          this.$axios
            .post("/user/regist", {
              username: this.form.username,
              eMail: this.form.eMail,
              address: this.form.address,
              password: this.form.password,
              repeatPassword: this.form.repeatPassword,
            })
            .then(
              (res) => {
                if (res.code == 400) {
                  this.$message({
                    message: res.msg || "Username already exists",
                    type: "error",
                  });
                } else {
                  this.$message({
                    message: "Registration succeeded",
                    type: "success",
                  });
                  this.$router.push("/login");
                }
                console.log(res);
              },
              (err) => {
                this.$message({
                  message: "Username already exists",
                  type: "error",
                });
                console.log(err);
                this.$router.push("/register");
              }
            );
        } else {
          return false;
        }
      });
    },
    toLogin() {
      this.$router.push("login");
    },
  },
};
</script>
<style lang="less">
.register-page {
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
.register-page {
  .el-form-item {
    text-align: left;
    white-space: nowrap;
  }
  .agree {
    line-height: 30px;
    height: 30px;
  }
  .register-box {
    width: 50%;
    min-width: 590px;
    margin: 0 auto;
    .register {
      margin-top: -5px;
      margin-left: 145px;
      text-align: left;
      font-size: 12px;
      margin-bottom: 15px;
    }
  }
}
</style>
